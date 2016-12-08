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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class HistoryActivity extends AppCompatActivity {
    private Button btn_history, btn_member, btn_game, btn_list;
    private int totalPage;
    private LinearLayout historyList;
    private ProgressDialog pDialog;
    private pDialogHandler pDialogHandler;
    private String cookie;
    private String app_net;
    private UIHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        app_net = getResources().getString(R.string.app_net);

        handler = new UIHandler();
        pDialogHandler = new pDialogHandler();

        Intent it = getIntent();
        cookie = it.getStringExtra("cookie");
        Log.i("troy", cookie);

        historyList = (LinearLayout) findViewById(R.id.historyList);
        pDialog = new ProgressDialog(this);
        pDialog.setTitle("Loading Data");
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);


        getData();
        setFnBtn();
    }

    public void getData() {
        pDialog.show();
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                getHistoryData();
                Looper.loop();
            }
        }.start();
    }

    public void getHistoryData() {
        try {
            MultipartUtility_tw mu = new MultipartUtility_tw("http://" + app_net + "/mobile/wap_ajax.php?action=app_get_order_history");
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

                Message msg = new Message();
                Bundle b = new Bundle();
                b.putString("issueno", issueno);
                b.putString("gold", gold);
                b.putString("war", war);
                b.putString("win", win);
                b.putString("profit", profit);
                b.putInt("i", i);
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

    public int dpToPx(int i) {
        return (int) ((i * getBaseContext().getResources().getDisplayMetrics().density) + 0.5);
    }

    /**
     * issueno : 期數名稱
     * is_result : 已結算 --暫無使用--
     * gold : 金額
     * war : 回水
     * win : 中獎
     * profit : 盈虧
     */
    public void list(final String issueno, String gold, String war, String win, String profit, int i) {
        LinearLayout ll = new LinearLayout(HistoryActivity.this);
        ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dpToPx(50)));
        ll.setOrientation(LinearLayout.HORIZONTAL);
        if (i % 2 == 0) {
            ll.setBackgroundColor(Color.parseColor("#d1d0d0"));
        }

        TextView tv0 = new TextView(HistoryActivity.this);
        tv0.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT));

        Button btn1 = new Button(HistoryActivity.this);
        btn1.setText(issueno);
        btn1.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData_2(issueno);
            }
        });

        TextView tv2 = new TextView(HistoryActivity.this);
        tv2.setText(gold);
        tv2.setTextSize(20);
        tv2.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        tv2.setGravity(Gravity.CENTER);
        tv2.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        TextView tv3 = new TextView(HistoryActivity.this);
        tv3.setText(war);
        tv3.setTextSize(20);
        tv3.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        tv3.setGravity(Gravity.CENTER);
        tv3.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        TextView tv4 = new TextView(HistoryActivity.this);
        tv4.setText(win);
        tv4.setTextSize(20);
        tv4.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        tv4.setGravity(Gravity.CENTER);
        tv4.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        TextView tv5 = new TextView(HistoryActivity.this);
        tv5.setText(profit);
        tv5.setTextSize(20);
        tv5.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        tv5.setGravity(Gravity.CENTER);
        tv5.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));

        TextView tv6 = new TextView(HistoryActivity.this);
        tv6.setLayoutParams(new LinearLayout.LayoutParams(dpToPx(10), LinearLayout.LayoutParams.MATCH_PARENT));

        ll.addView(tv0);
        ll.addView(btn1);
        ll.addView(tv2);
        ll.addView(tv3);
        ll.addView(tv4);
        ll.addView(tv5);
        ll.addView(tv6);
        historyList.addView(ll);
    }

    private void getData_2(final String s) {
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                getListData(s);
                Looper.loop();
            }
        }.start();
    }

    private void getListData(String s) {
        try {
            MultipartUtility_tw mu = new MultipartUtility_tw("http://" + app_net + "/mobile/wap_ajax.php?action=app_order_dtl");
            mu.sendCookie(cookie);
            mu.postKeyValue("s_issueno", s);
            JSONObject jo = mu.getJSONObjectData();
            totalPage = jo.getInt("total_page");
            Log.i("troy", "共有" + totalPage + "頁");

            Intent it = new Intent(HistoryActivity.this, MoreListDataActivity.class);
            it.putExtra("cookie", cookie);
            it.putExtra("totalPage", totalPage);
            it.putExtra("s_issueno", s);
            it.putExtra("winList", false);
            startActivity(it);
        } catch (Exception e) {
            Toast.makeText(this, "無法與伺服器取得連線", Toast.LENGTH_LONG).show();
            Log.i("troy", e.toString());
        }
    }

    private class UIHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            String issueno = msg.getData().getString("issueno");
            String gold = msg.getData().getString("gold");
            String war = msg.getData().getString("war");
            String win = msg.getData().getString("win");
            String profit = msg.getData().getString("profit");
            int i = msg.getData().getInt("i");
            list(issueno, gold, war, win, profit, i);
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
                HistoryActivity.this.finish();//關閉activity
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
