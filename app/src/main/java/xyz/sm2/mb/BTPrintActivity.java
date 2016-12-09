package xyz.sm2.mb;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class BTPrintActivity extends AppCompatActivity {
    private ArrayList<BluetoothDevice> pairedDeviceArrayList;
    private ArrayAdapter<BluetoothDevice> pairedDeviceAdapter;
    private ArrayList<String> pairedNameList;
    private BluetoothAdapter bluetoothAdapter;
    private Button btn_print;
    private static final int REQUEST_ENABLE_BT = 1;
    private List<HashMap<String, String>> list;
    private ListView listViewPairedDevice;
    private ProgressDialog pDialog;
    private String cookie, ListID;
    private String app_net;
    private String rec;
    private ScrollView sv_preview;
    private TextView textStatus;
    private TextView tv_preview;
    private ThreadConnectBTdevice myThreadConnectBTdevice;
    private ThreadConnected myThreadConnected;
    private UUID myUUID;
    private UIHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btprint);

        app_net = getResources().getString(R.string.app_net);

        handler = new UIHandler();

        Intent it = getIntent();
        cookie = it.getStringExtra("cookie");
        ListID = it.getStringExtra("ListID");
        Log(cookie);

        textStatus = (TextView) findViewById(R.id.status);
        listViewPairedDevice = (ListView) findViewById(R.id.pairedlist);
        sv_preview = (ScrollView) findViewById(R.id.sv_preview);
        tv_preview = (TextView) findViewById(R.id.tv_preview);
        btn_print = (Button) findViewById(R.id.btn_print);

        pDialog = new ProgressDialog(this);
        pDialog.setTitle("Loading Data");
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        btn_print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                print(rec);
                clearData();
            }
        });

        // 以下 Android 6+
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 2);
        }

        // 如果裝置沒有藍芽，顯示不支援藍芽的Toast並結束程式
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH)) {
            Toast("本機無藍芽裝置，無法使用藍芽打印功能");
            finish();
            return;
        }

        //generate UUID on web: http://www.famkruithof.net/uuid/uuidgen
        //have to match the UUID on the another device of the BT connection
        myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

        // 如果無法取得bluetoothAdapter，顯示不支援並結束程式
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            Toast("此設備不支援藍芽");
            finish();
            return;
        }

        getData();
    }

    public void print(String s) {
        if (myThreadConnected != null) {
            try {
                byte[] bytesToSend = s.getBytes("GBK");
                myThreadConnected.write(bytesToSend);
            } catch (Exception e) {
                Log(e.toString());
            }
        }
    }

    public void getData() {
        pDialog.show();
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                getListData();
                Looper.loop();
            }
        }.start();
    }

    public void getListData() {
        try {
            MultipartUtility_tw mu = new MultipartUtility_tw("http://" + app_net + "/mobile/wap_ajax.php?action=app_get_order_print_long");
            mu.sendCookie(cookie);
//            mu.postKeyValue("idarray", ListID);
//            List<String> a = mu.getHtml();
//            for (String line : a) {
//                Log.i("troy", line);
//            }
            String a = mu.getJSONObjectData().getString("list");
//            Log.i("troy", a);
            rec = new JSONArray(a).getJSONObject(0).getString("ticket");
//            Log.i("troy", rec);
//            Message msg = new Message();
//            Bundle b = new Bundle();
//            b.putString("rec", rec);
//            msg.setData(b);
//            handler.sendMessage(msg);

        } catch (Exception e) {
            Log(e.toString());
            Toast("無未打印資料");
            finish();
        }
        handler.sendEmptyMessage(0);
    }

    public void clearData() {
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                clearListData();
                Looper.loop();
            }
        }.start();
    }

    public void clearListData() {
        try {
            MultipartUtility_tw mu = new MultipartUtility_tw("http://" + app_net + "/mobile/wap_ajax.php?action=app_clr_order_print");
            mu.sendCookie(cookie);
            List<String> b = mu.getHtml();
            for (String line : b) {
                Log(line);
            }
        } catch (Exception e) {
            Log(e.toString());
        }
    }

    // 從assets拿到json檔案
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("123.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    protected void onStart() {
        super.onStart();

        //Turn ON BlueTooth if it is OFF
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
        }

        setup();
    }

    private void setup() {
        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            pairedDeviceArrayList = new ArrayList<BluetoothDevice>();
            pairedNameList = new ArrayList<String>();
            list = new ArrayList<>();

            for (BluetoothDevice device : pairedDevices) {
                pairedDeviceArrayList.add(device);
                pairedNameList.add(device.getName());
            }

            pairedDeviceAdapter = new ArrayAdapter<BluetoothDevice>(this,
                    android.R.layout.simple_list_item_1,
                    pairedDeviceArrayList);
            ListAdapter listAdapter = new SimpleAdapter(this,
                    list,
                    android.R.layout.simple_list_item_2,
                    new String[]{"ad", "name"},
                    new int[]{android.R.id.text1, android.R.id.text2});

            listViewPairedDevice.setAdapter(pairedDeviceAdapter);

            listViewPairedDevice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    BluetoothDevice device = (BluetoothDevice) parent.getItemAtPosition(position);
                    Toast.makeText(BTPrintActivity.this,
                            "藍芽打印機連接成功",
                            Toast.LENGTH_LONG).show();

                    textStatus.setText("start ThreadConnectBTdevice");
                    myThreadConnectBTdevice = new ThreadConnectBTdevice(device);
                    myThreadConnectBTdevice.start();
                    btn_print.setVisibility(View.VISIBLE);
                    sv_preview.setVisibility(View.VISIBLE);
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (myThreadConnectBTdevice != null) {
            myThreadConnectBTdevice.cancel();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 2) {
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                setup();
            } else {
                Toast("藍芽未開啟");
                finish();
            }
        }
    }

    private class ThreadConnectBTdevice extends Thread {
        private BluetoothSocket bluetoothSocket = null;
        private final BluetoothDevice bluetoothDevice;

        public ThreadConnectBTdevice(BluetoothDevice device) {
            bluetoothDevice = device;
            try {
                bluetoothSocket = device.createRfcommSocketToServiceRecord(myUUID);
                textStatus.setText("bluetoothSocket: \n" + bluetoothSocket);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            boolean success = false;
            try {
                bluetoothSocket.connect();
                success = true;
            } catch (IOException e) {
                e.printStackTrace();

                final String eMessage = e.getMessage();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textStatus.setText("something wrong bluetoothSocket.connect(): \n" + eMessage);
                    }
                });
                try {
                    bluetoothSocket.close();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

            if (success) {
                //connect successful
                final String msgconnected = "connect successful:\n" +
                        "BluetoothSocket: " + bluetoothSocket + "\n" +
                        "BluetoothDevice: " + bluetoothDevice;

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textStatus.setText(msgconnected);
                        listViewPairedDevice.setVisibility(View.GONE);
                    }
                });
                startThreadConnected(bluetoothSocket);
            } else {
                //fail
            }
        }

        public void cancel() {
            Toast.makeText(getApplicationContext(),
                    "close bluetoothSocket",
                    Toast.LENGTH_LONG).show();
            try {
                bluetoothSocket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void startThreadConnected(BluetoothSocket socket) {
        myThreadConnected = new ThreadConnected(socket);
        myThreadConnected.start();
    }

    private class ThreadConnected extends Thread {
        private final BluetoothSocket connectedBluetoothSocket;
        private final InputStream connectedInputStream;
        private final OutputStream connectedOutputStream;

        public ThreadConnected(BluetoothSocket socket) {
            connectedBluetoothSocket = socket;
            InputStream in = null;
            OutputStream out = null;

            try {
                in = socket.getInputStream();
                out = socket.getOutputStream();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            connectedInputStream = in;
            connectedOutputStream = out;
        }

        @Override
        public void run() {
            byte[] buffer = new byte[1024];
            int bytes;

            while (true) {
                try {
                    bytes = connectedInputStream.read(buffer);
                    String strReceived = new String(buffer, 0, bytes);
                    final String msgReceived = String.valueOf(bytes) +
                            " bytes received:\n" + strReceived;

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textStatus.setText(msgReceived);
                        }
                    });

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();

                    final String msgConnectionLost = "Connection lost:\n" + e.getMessage();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textStatus.setText(msgConnectionLost);
                        }
                    });
                }
            }
        }

        public void write(byte[] buffer) {
            try {
                connectedOutputStream.write(buffer);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        public void cancel() {
            try {
                connectedBluetoothSocket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private class UIHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

//            String rec = msg.getData().getString("rec");
            tv_preview.setText(rec);

            if (pDialog.isShowing()) {
                pDialog.dismiss();
            }
        }
    }

    public void Toast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    public void Log(String s) {
        Log.i("troy", s);
    }
}
