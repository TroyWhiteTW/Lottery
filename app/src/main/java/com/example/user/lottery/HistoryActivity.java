package com.example.user.lottery;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private Button btn_history, btn_member, btn_game, btn_list;
    private String cookie;
    private LinearLayout historyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Intent it = getIntent();
        cookie = it.getStringExtra("cookie");
        Log.i("troy", cookie);

        historyList = (LinearLayout) findViewById(R.id.historyList);

        getData();
        setFnBtn();
    }

    public void getData() {
        new Thread() {
            @Override
            public void run() {
                getHistoryData();
            }
        }.start();
    }

    public void getHistoryData() {
        try {
            MultipartUtility_tw mu = new MultipartUtility_tw("http://mb.sm2.xyz/mobile/wap_ajax.php?action=app_get_order_history");
            mu.sendCookie(cookie);
//            List<String> a = mu_4.getHtml();
//            for (String line : a) {
//                Log.i("troy", line);
//            }
            JSONArray ja = mu.getJSONArrayData();
            int len = ja.length();
            Log.i("troy", "共有" + len + "筆資料");
            for (int i = 0; i < len; i++) {
                JSONObject rec = ja.getJSONObject(i);
                String issueno = rec.getString("issueno");
                String gold = rec.getString("gold");
                String war = rec.getString("war");
                String win = rec.getString("win");
                String profit = rec.getString("profit");
                list(issueno, gold, war, win, profit);
            }
        } catch (Exception e) {
            Log.i("troy", e.toString());
        }
    }

    public void setFnBtn() {
        btn_list = (Button) findViewById(R.id.btn_list_history);
        btn_game = (Button) findViewById(R.id.btn_game_history);
        btn_member = (Button) findViewById(R.id.btn_member_history);
        btn_history = (Button) findViewById(R.id.btn_history_history);

        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(HistoryActivity.this, ListActivity.class);
                it.putExtra("cookie", cookie);
                startActivity(it);
                finish();
            }
        });
        btn_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(HistoryActivity.this, GameActivity.class);
                it.putExtra("cookie", cookie);
                startActivity(it);
                finish();
            }
        });
        btn_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(HistoryActivity.this, MemberActivity.class);
                it.putExtra("cookie", cookie);
                startActivity(it);
                finish();
            }
        });
        btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    /**
     * issueno : 期數名稱
     * is_result : 已結算 --暫無使用--
     * gold : 金額
     * war : 回水
     * win : 中獎
     * profit : 盈虧
     */
    public void list(String issueno, String gold, String war, String win, String profit) {
        LinearLayout ll = new LinearLayout(HistoryActivity.this);
        ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ll.setOrientation(LinearLayout.HORIZONTAL);
        TextView tv1 = new TextView(HistoryActivity.this);
        tv1.setText(issueno);
        tv1.setTextSize(20);
        tv1.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        tv1.setBackgroundColor(Color.parseColor("#ffffff"));
        tv1.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        TextView tv2 = new TextView(HistoryActivity.this);
        tv2.setText(gold);
        tv2.setTextSize(20);
        tv2.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        tv2.setBackgroundColor(Color.parseColor("#ffffff"));
        tv2.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        TextView tv3 = new TextView(HistoryActivity.this);
        tv3.setText(war);
        tv3.setTextSize(20);
        tv3.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        tv3.setBackgroundColor(Color.parseColor("#ffffff"));
        tv3.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        TextView tv4 = new TextView(HistoryActivity.this);
        tv4.setText(win);
        tv4.setTextSize(20);
        tv4.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        tv4.setBackgroundColor(Color.parseColor("#ffffff"));
        tv4.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        TextView tv5 = new TextView(HistoryActivity.this);
        tv5.setText(profit);
        tv5.setTextSize(20);
        tv5.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        tv5.setBackgroundColor(Color.parseColor("#ffffff"));
        tv5.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        ll.addView(tv1);
        ll.addView(tv2);
        ll.addView(tv3);
        ll.addView(tv4);
        ll.addView(tv5);
        historyList.addView(ll);
    }
}
