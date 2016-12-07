package xyz.sm2.mb;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private Button btn_history, btn_member, btn_game, btn_list;
    private Button btn_print_list, btn_winGame, btn_moreData, order_cancel;
    private String cookie, ListID;
    private LinearLayout orderList;
    private ProgressDialog pDialog;
    private UIHandler handler;
    private pDialogHandler pDialogHandler;
    private int totalPage;
    private int s_issueno;
    private StringBuilder sb;
    private ArrayList<String> cancelList;
    private String app_net;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        app_net = getResources().getString(R.string.app_net);

        handler = new UIHandler();
        pDialogHandler = new pDialogHandler();
        cancelList = new ArrayList();

        Intent it = getIntent();
        cookie = it.getStringExtra("cookie");
        Log.i("troy", cookie);

        orderList = (LinearLayout) findViewById(R.id.orderList);
        pDialog = new ProgressDialog(this);
        pDialog.setTitle("Loading Data");
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        btn_print_list = (Button) findViewById(R.id.btn_print_list);
        btn_print_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ListActivity.this, BTPrintActivity.class);
                it.putExtra("cookie", cookie);
                it.putExtra("ListID", ListID);
                startActivity(it);
            }
        });

        btn_winGame = (Button) findViewById(R.id.btn_winGame);
        btn_winGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn_moreData = (Button) findViewById(R.id.btn_moreData);
        btn_moreData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ListActivity.this, MoreListDataActivity.class);
                it.putExtra("cookie", cookie);
                it.putExtra("totalPage", totalPage);
                it.putExtra("s_issueno", String.valueOf(s_issueno));
                startActivity(it);
            }
        });

        sb = new StringBuilder();

        order_cancel = (Button) findViewById(R.id.order_cancel);
        order_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderCancel();
            }
        });

        getData();
        setFnBtn();
    }

    public void orderCancel() {
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                doCamcel();
                Looper.loop();
            }
        }.start();
    }

    public void doCamcel() {
        for (String a : cancelList) {
            Log.i("troy", a);
            sb.append(a);
            sb.append(",");
        }
        try {
            MultipartUtility_tw mu = new MultipartUtility_tw("http://" + app_net + "/mobile/wap_ajax.php?action=app_exe_order_cancel");
            mu.sendCookie(cookie);
            mu.postKeyValue("idarray", sb.toString());
//            List<String> a = mu.getHtml();
//            for (String line : a) {
//                Log.i("troy", line);
//            }
            String b = mu.getJSONObjectData().getString("message");
            Log.i("troy", b);

//            Toast.makeText(this, "退碼成功", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
//            Toast.makeText(this, "退碼失敗", Toast.LENGTH_LONG).show();
            Log.i("troy", e.toString());
        }
        finish();
        startActivity(getIntent());
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
            MultipartUtility_tw mu = new MultipartUtility_tw("http://" + app_net + "/mobile/wap_ajax.php?action=app_order_dtl");
            mu.sendCookie(cookie);
//            List<String> a = mu.getHtml();
//            for (String line : a) {
//                Log.i("troy", line);
//            }
            JSONObject jo = mu.getJSONObjectData();
            totalPage = jo.getInt("total_page");
            Log.i("troy", "共有" + totalPage + "頁");
            s_issueno = jo.getInt("s_issueno");
            Log.i("troy", "第" + s_issueno + "期");

            JSONArray ja = jo.getJSONArray("list");
            int len = ja.length();
            Log.i("troy", "共有" + len + "筆資料");
            ListID = ja.getJSONObject(0).getString("id");

            for (int i = 0; i < len; i++) {
                JSONObject rec = ja.getJSONObject(i);
                String number = rec.getString("number");
                String money = rec.getString("money");
                String frank = rec.getString("frank");
                int cancel_able = rec.getInt("cancel_able");
                String id = rec.getString("id");

                Message msg = new Message();
                Bundle b = new Bundle();
                b.putString("number", number);
                b.putString("money", money);
                b.putString("frank", frank);
                b.putInt("cancel_able", cancel_able);
                b.putInt("i", i);
                b.putString("id", id);
                msg.setData(b);
                handler.sendMessage(msg);
            }
        } catch (Exception e) {
            Toast.makeText(this, "無法與伺服器取得連線", Toast.LENGTH_LONG).show();
            Log.i("troy", e.toString());
        }
        pDialogHandler.sendEmptyMessage(0);
    }

    public void setFnBtn() {
        btn_list = (Button) findViewById(R.id.btn_list_list);
        btn_game = (Button) findViewById(R.id.btn_game_list);
        btn_member = (Button) findViewById(R.id.btn_member_list);
        btn_history = (Button) findViewById(R.id.btn_history_list);

        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ListActivity.this, GameActivity.class);
                it.putExtra("cookie", cookie);
                startActivity(it);
                finish();
            }
        });
        btn_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ListActivity.this, MemberActivity.class);
                it.putExtra("cookie", cookie);
                startActivity(it);
                finish();
            }
        });
        btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ListActivity.this, HistoryActivity.class);
                it.putExtra("cookie", cookie);
                startActivity(it);
                finish();
            }
        });
    }

    public int dpToPx(int i) {
        return (int) ((i * getBaseContext().getResources().getDisplayMetrics().density) + 0.5);
    }

    public void list(String number, String money, String frank, int i, int cancel_able, final String id) {
        LinearLayout ll = new LinearLayout(ListActivity.this);
        ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dpToPx(50)));
        ll.setOrientation(LinearLayout.HORIZONTAL);
        if (i % 2 == 0) {
            ll.setBackgroundColor(Color.parseColor("#d1d0d0"));
        }

        TextView tv0 = new TextView(ListActivity.this);
        tv0.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT));

        TextView tv1 = new TextView(ListActivity.this);
        tv1.setText(number);
        tv1.setTextSize(20);
        tv1.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        tv1.setGravity(Gravity.CENTER);
        tv1.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        TextView tv2 = new TextView(ListActivity.this);
        tv2.setText(money);
        tv2.setTextSize(20);
        tv2.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        tv2.setGravity(Gravity.CENTER);
        tv2.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        TextView tv3 = new TextView(ListActivity.this);
        tv3.setText(frank);
        tv3.setTextSize(20);
        tv3.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        tv3.setGravity(Gravity.CENTER);
        tv3.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));

        TextView tv5 = new TextView(ListActivity.this);
        tv5.setLayoutParams(new LinearLayout.LayoutParams(dpToPx(10), LinearLayout.LayoutParams.MATCH_PARENT));

        ll.addView(tv0);
        ll.addView(tv1);
        ll.addView(tv2);
        ll.addView(tv3);
        if (cancel_able == 1) {
            CheckBox tv4 = new CheckBox(ListActivity.this);
            int cbID = Integer.parseInt(id);
            tv4.setId(cbID);
            tv4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (((CheckBox) view).isChecked()) {
                        cancelList.add(id);
                        Log.i("troy", cancelList.toString());
                    } else {
                        cancelList.remove(id);
                        Log.i("troy", cancelList.toString());
                    }
                }
            });
            tv4.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
            ll.addView(tv4);
        } else {
            TextView tv4 = new TextView(ListActivity.this);
            tv4.setText("--");
            tv4.setTextSize(20);
            tv4.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
            tv4.setGravity(Gravity.CENTER);
            tv4.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
            ll.addView(tv4);
        }
        ll.addView(tv5);
        orderList.addView(ll);
    }

    private class UIHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            String number = msg.getData().getString("number");
            String money = msg.getData().getString("money");
            String frank = msg.getData().getString("frank");
            int cancel_able = msg.getData().getInt("cancel_able");
            int i = msg.getData().getInt("i");
            String id = msg.getData().getString("id");
            list(number, money, frank, i, cancel_able, id);
        }
    }

    private class pDialogHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (pDialog.isShowing()) {
                pDialog.dismiss();
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        確定按下退出鍵and防止重複按下退出鍵
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exitDialog();
        }
        return false;
    }

    public void exitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);//創建訊息方塊
        builder.setMessage("確定要離開？");
        builder.setTitle("離開");
        builder.setPositiveButton("確認", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();//dismiss為關閉dialog,Activity還會保留dialog的狀態
                ListActivity.this.finish();//關閉activity
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }
}
