package com.example.user.lottery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btn_history, btn_member, btn_game, btn_list;
    private String cookie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                getTestData();
            }
        }.start();
    }

    public void getTestData() {
        try {
            MultipartUtility_tw mu_2 = new MultipartUtility_tw("http://mb.sm2.xyz/mobile/wap_ajax.php?action=app_head_data");
            mu_2.sendCookie(cookie);
            List<String> ret_2 = mu_2.getHtml();
            for (String line : ret_2) {
                Log.i("troy", line);
            }
            String line = ret_2.get(0);
            JSONObject jo = new JSONObject(line);
            String v1 = jo.getString("username");
            Log.i("troy", v1);
        } catch (Exception e) {
            Log.i("troy", e.toString());
        }
    }

    public void getHistoryData() {

    }

    public void setFnBtn() {
        btn_list = (Button) findViewById(R.id.btn_list_main);
        btn_game = (Button) findViewById(R.id.btn_game_main);
        btn_member = (Button) findViewById(R.id.btn_member_main);
        btn_history = (Button) findViewById(R.id.btn_history_main);

        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, ListActivity.class);
                it.putExtra("cookie", cookie);
                startActivity(it);
                finish();
            }
        });
        btn_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, GameActivity.class);
                it.putExtra("cookie", cookie);
                startActivity(it);
                finish();
            }
        });
        btn_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, MemberActivity.class);
                it.putExtra("cookie", cookie);
                startActivity(it);
                finish();
            }
        });
        btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, HistoryActivity.class);
                it.putExtra("cookie", cookie);
                startActivity(it);
                finish();
            }
        });
    }
}
