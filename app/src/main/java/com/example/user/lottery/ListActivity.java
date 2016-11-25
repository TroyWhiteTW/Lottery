package com.example.user.lottery;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class ListActivity extends AppCompatActivity {
    private Button btn_history, btn_member, btn_game, btn_list;
    private Button btn_print_list;
    private String cookie, ListID;
    private LinearLayout orderList;
    private ProgressDialog pDialog;
    private UIHandler handler;
    private pDialogHandler pDialogHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        handler = new UIHandler();
        pDialogHandler = new pDialogHandler();

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

        getData();
        setFnBtn();
    }

    public void getData() {
        pDialog.show();
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
//            List<String> a = mu.getHtml();
//            for (String line : a) {
//                Log.i("troy", line);
//            }
            JSONArray ja = mu.getJSONObjectData().getJSONArray("list");
            int len = ja.length();
            Log.i("troy", "共有" + len + "筆資料");
            ListID = ja.getJSONObject(0).getString("id");
            for (int i = 0; i < len; i++) {
                JSONObject rec = ja.getJSONObject(i);
                String number = rec.getString("number");
                String money = rec.getString("money");
                String frank = rec.getString("frank");

                Message msg = new Message();
                Bundle b = new Bundle();
                b.putString("number", number);
                b.putString("money", money);
                b.putString("frank", frank);
                msg.setData(b);
                handler.sendMessage(msg);
            }
        } catch (Exception e) {
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

    public void list(String number, String money, String frank) {
        LinearLayout ll = new LinearLayout(ListActivity.this);
        ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ll.setOrientation(LinearLayout.HORIZONTAL);
        TextView tv1 = new TextView(ListActivity.this);
        tv1.setText(number);
        tv1.setTextSize(20);
        tv1.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        tv1.setBackgroundColor(Color.parseColor("#ffffff"));
        tv1.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        TextView tv2 = new TextView(ListActivity.this);
        tv2.setText(money);
        tv2.setTextSize(20);
        tv2.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        tv2.setBackgroundColor(Color.parseColor("#ffffff"));
        tv2.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        TextView tv3 = new TextView(ListActivity.this);
        tv3.setText(frank);
        tv3.setTextSize(20);
        tv3.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        tv3.setBackgroundColor(Color.parseColor("#ffffff"));
        tv3.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        TextView tv4 = new TextView(ListActivity.this);
        tv4.setText("--");
        tv4.setTextSize(20);
        tv4.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        tv4.setBackgroundColor(Color.parseColor("#ffffff"));
        tv4.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        ll.addView(tv1);
        ll.addView(tv2);
        ll.addView(tv3);
        ll.addView(tv4);
        orderList.addView(ll);
    }

    private class UIHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            String number = msg.getData().getString("number");
            String money = msg.getData().getString("money");
            String frank = msg.getData().getString("frank");
            list(number, money, frank);
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
