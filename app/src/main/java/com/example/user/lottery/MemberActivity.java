package com.example.user.lottery;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class MemberActivity extends AppCompatActivity {
    private Button btn_history, btn_member, btn_game, btn_list;
    private String cookie;
    private ProgressDialog pDialog;
    private UIHandler handler;
    private pDialogHandler pDialogHandler;
    private TextView tv_rcedits, tv_rcedits_use;
    private Spinner sp0;
    private ArrayAdapter<String> lunchList;
    private String[] lunch = {"a", "b", "c", "d", "e"};
    private ArrayList list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        handler = new UIHandler();
        pDialogHandler = new pDialogHandler();

        Intent it = getIntent();
        cookie = it.getStringExtra("cookie");
        Log.i("troy", cookie);

        pDialog = new ProgressDialog(this);
        pDialog.setTitle("Loading Data");
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        tv_rcedits = (TextView) findViewById(R.id.rcedits);
        tv_rcedits_use = (TextView) findViewById(R.id.rcedits_use);

        list = new ArrayList();
        sp0 = (Spinner) findViewById(R.id.sp0);

        getData();
        setFnBtn();
    }

    public void getData() {
        pDialog.show();
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                getMemberData();
                Looper.loop();
            }
        }.start();
    }

    public void getMemberData() {
        try {
            MultipartUtility_tw mu = new MultipartUtility_tw("http://mb.sm2.xyz/mobile/wap_ajax.php?action=app_get_mem_data");
            mu.sendCookie(cookie);

            JSONObject jo = mu.getJSONObjectData();
            int len = jo.length();
            Log.i("troy", "共有" + len + "筆資料");

            String rcedits = jo.getJSONObject("head_data").getString("rcedits");
            String rcedits_use = jo.getJSONObject("head_data").getString("rcedits_use");

            Message msg = new Message();
            Bundle b = new Bundle();
            b.putString("rcedits", rcedits);
            b.putString("rcedits_use", rcedits_use);
            msg.setData(b);
            handler.sendMessage(msg);

            Iterator<String> iter = jo.getJSONObject("huishui_list").getJSONObject("list").getJSONObject("1").keys();
            while (iter.hasNext()) {
                String key = iter.next();
                Log.i("troy", key);
                list.add(key);
            }
            Log.i("troy", list.toString());
        } catch (Exception e) {
            Toast.makeText(this, "無法與伺服器取得連線", Toast.LENGTH_LONG).show();
            Log.i("troy", e.toString());
        }
        pDialogHandler.sendEmptyMessage(0);
    }

    public void setFnBtn() {
        btn_list = (Button) findViewById(R.id.btn_list_member);
        btn_game = (Button) findViewById(R.id.btn_game_member);
        btn_member = (Button) findViewById(R.id.btn_member_member);
        btn_history = (Button) findViewById(R.id.btn_history_member);

        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MemberActivity.this, ListActivity.class);
                it.putExtra("cookie", cookie);
                startActivity(it);
                finish();
            }
        });
        btn_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MemberActivity.this, GameActivity.class);
                it.putExtra("cookie", cookie);
                startActivity(it);
                finish();
            }
        });
        btn_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MemberActivity.this, HistoryActivity.class);
                it.putExtra("cookie", cookie);
                startActivity(it);
                finish();
            }
        });
    }

    private class UIHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            String rcedits = msg.getData().getString("rcedits");
            Log.i("troy", rcedits);
            String rcedits_use = msg.getData().getString("rcedits_use");
            Log.i("troy", rcedits_use);
            tv_rcedits.setText(rcedits);
            tv_rcedits_use.setText(rcedits_use);
            lunchList = new ArrayAdapter<>(MemberActivity.this, android.R.layout.simple_spinner_item, lunch);
            sp0.setAdapter(lunchList);
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
}
