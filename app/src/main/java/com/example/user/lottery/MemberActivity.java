package com.example.user.lottery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.List;

public class MemberActivity extends AppCompatActivity {
    private Button btn_history, btn_member, btn_game, btn_list;
    private String cookie;
    private TextView rcedits, rcedits_use;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        Intent it = getIntent();
        cookie = it.getStringExtra("cookie");
        Log.i("troy", cookie);

        rcedits = (TextView) findViewById(R.id.rcedits);
        rcedits_use = (TextView) findViewById(R.id.rcedits_use);

        getData();
        setFnBtn();
    }

    public void getData() {
        new Thread() {
            @Override
            public void run() {
                getMemberData();
            }
        }.start();
    }

    public void getMemberData() {
        try {
            MultipartUtility_tw mu = new MultipartUtility_tw("http://mb.sm2.xyz/mobile/wap_ajax.php?action=app_get_mem_data");
            mu.sendCookie(cookie);
            int i = mu.getResponseCode();
            Log.i("troy", "----" + i + "----");

            JSONObject jo = mu.getJSONObjectData();
            int len  = jo.length();
            Log.i("troy", "共有" + len + "筆資料");

            rcedits.setText(jo.getJSONObject("head_data").getString("rcedits"));
            rcedits_use.setText(jo.getJSONObject("head_data").getString("rcedits_use"));
//            List<String> a = mu.getHtml();
//            for (String line : a) {
//                Log.i("troy", line);
//            }
//            JSONArray ja = mu.getJSONArrayData();
//            int len = ja.length();
//            Log.i("troy", "共有" + len + "筆資料");
//            for (int i = 0; i < len; i++) {
//                JSONObject rec = ja.getJSONObject(i);
//                String issueno = rec.getString("issueno");
//                String gold = rec.getString("gold");
//                String war = rec.getString("war");
//                String win = rec.getString("win");
//                String profit = rec.getString("profit");
//            }
        } catch (Exception e) {
            Log.i("troy", e.toString());
        }
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
}
