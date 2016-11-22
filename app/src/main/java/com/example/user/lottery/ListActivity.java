package com.example.user.lottery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class ListActivity extends AppCompatActivity {
    private Button btn_history, btn_member, btn_game, btn_list;
    private String cookie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent it = getIntent();
        cookie = it.getStringExtra("cookie");
        Log.i("troy", cookie);

        getData();
        setFnBtn();
    }

    public void getData() {
        new Thread() {
            @Override
            public void run() {
                getListData();
            }
        }.start();
    }

    public void getListData() {
        try {
            MultipartUtility_tw mu = new MultipartUtility_tw("http://mb.sm2.xyz/mobile/wap_ajax.php?action=app_order_dtl");
            mu.sendCookie(cookie);
            List<String> a = mu.getHtml();
            for (String line : a) {
                Log.i("troy", line);
            }
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
}
