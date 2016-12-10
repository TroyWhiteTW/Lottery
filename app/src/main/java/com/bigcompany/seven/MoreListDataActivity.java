package com.bigcompany.seven;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class MoreListDataActivity extends AppCompatActivity {
    private boolean winList = false;
    private Button btn_loadNextPage;
    private int totalPage;
    private int whatpage = 1;
    private LinearLayout moreOrderList;
    private ProgressDialog pDialog;
    private pDialogHandler pDialogHandler;
    private String s_issueno;
    private String app_net;
    private String cookie;
    private TextView tv_totalPages;
    private UIHandler handler;
    private UIHandler_2 handler_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_list_data);

        app_net = getResources().getString(R.string.app_net);

        handler = new UIHandler();
        handler_2 = new UIHandler_2();
        pDialogHandler = new pDialogHandler();

        Intent it = getIntent();
        cookie = it.getStringExtra("cookie");
//        totalPage = it.getIntExtra("totalPage", 1);
        s_issueno = it.getStringExtra("s_issueno");
        winList = it.getBooleanExtra("winList", false);

        Log.i("troy", cookie);
        Log.i("troy", "第" + s_issueno + "期");
        Log.i("troy", "wunList：" + winList);

        tv_totalPages = (TextView) findViewById(R.id.tv_totalPages);

        btn_loadNextPage = (Button) findViewById(R.id.btn_loadNextPage);
        btn_loadNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadNextPage();
            }
        });

        pDialog = new ProgressDialog(this);
        pDialog.setTitle("Loading Data");
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        moreOrderList = (LinearLayout) findViewById(R.id.moreOrderList);

        getData();
    }

    public void loadNextPage() {
        if (whatpage < totalPage) {
            whatpage++;
            getData();
        } else {
            Toast.makeText(this, "已無更多資料", Toast.LENGTH_LONG).show();
        }
    }

    public void getData() {
        pDialog.show();
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                getMoreListData();
                Looper.loop();
            }
        }.start();
    }

    public void getMoreListData() {
        try {
//            for (int j = 1; j <= totalPage; j++) {
            String page = String.valueOf(whatpage);
            MultipartUtility_tw mu = new MultipartUtility_tw("http://" + app_net + "/mobile/wap_ajax.php?action=app_order_dtl");
            mu.sendCookie(cookie);
            mu.postKeyValue("page", page);
            mu.postKeyValue("s_issueno", s_issueno);
            if (winList == true) {
                mu.postKeyValue("doaction", "award");
            }
            JSONObject jo = mu.getJSONObjectData();
            totalPage = jo.getInt("total_page");
            Log.i("troy", "total_page：" + jo.getInt("total_page"));
            JSONArray ja = jo.getJSONArray("list");
            int len = ja.length();
//                Log.i("troy", "第" + j + "頁共有" + len + "筆資料");

            Message msg_2 = new Message();
            Bundle b_2 = new Bundle();
            b_2.putString("page", page);
            msg_2.setData(b_2);
            handler_2.sendMessage(msg_2);

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
                b.putInt("i", i);
                msg.setData(b);
                handler.sendMessage(msg);
            }
//            }
        } catch (Exception e) {
            Toast.makeText(this, "無法與伺服器取得連線", Toast.LENGTH_LONG).show();
            Log.i("troy", e.toString());
        }
        pDialogHandler.sendEmptyMessage(0);
    }

    private class UIHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            String number = msg.getData().getString("number");
            String money = msg.getData().getString("money");
            String frank = msg.getData().getString("frank");
            int i = msg.getData().getInt("i");
            list(number, money, frank, i);
        }
    }

    private class UIHandler_2 extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            String page = msg.getData().getString("page");
            listTitle(page);
        }
    }

    private class pDialogHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            tv_totalPages.setText("共 " + totalPage + " 頁");
            if (pDialog.isShowing()) {
                pDialog.dismiss();
            }
        }
    }

    public void listTitle(String page) {
        TextView page_tv = new TextView(MoreListDataActivity.this);
        page_tv.setText("第" + page + "頁");
        page_tv.setTextSize(30);
        page_tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        page_tv.setBackgroundColor(Color.parseColor("#da8c8c"));
        page_tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        moreOrderList.addView(page_tv);
    }

    public void list(String number, String money, String frank, int i) {
        LinearLayout ll = new LinearLayout(MoreListDataActivity.this);
        ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ll.setOrientation(LinearLayout.HORIZONTAL);
        if (i % 2 == 0) {
            ll.setBackgroundColor(Color.parseColor("#d1d0d0"));
        }
        TextView tv0 = new TextView(MoreListDataActivity.this);
        tv0.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT));
        TextView tv1 = new TextView(MoreListDataActivity.this);
        tv1.setText(number);
        tv1.setTextSize(20);
        tv1.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        tv1.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        TextView tv2 = new TextView(MoreListDataActivity.this);
        tv2.setText(money);
        tv2.setTextSize(20);
        tv2.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        tv2.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        TextView tv3 = new TextView(MoreListDataActivity.this);
        tv3.setText(frank);
        tv3.setTextSize(20);
        tv3.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        tv3.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        TextView tv4 = new TextView(MoreListDataActivity.this);
        tv4.setLayoutParams(new LinearLayout.LayoutParams(50, LinearLayout.LayoutParams.MATCH_PARENT));
        ll.addView(tv0);
        ll.addView(tv1);
        ll.addView(tv2);
        ll.addView(tv3);
        ll.addView(tv4);
        moreOrderList.addView(ll);
    }

    public void Toast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    public void Log(String s) {
        Log.i("troy", s);
    }
}
