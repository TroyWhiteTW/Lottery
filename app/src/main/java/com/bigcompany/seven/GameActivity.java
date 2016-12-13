package com.bigcompany.seven;

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
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class GameActivity extends AppCompatActivity {
    private Button btn_history, btn_member, btn_game, btn_list, btn_qselect;
    private Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_0, btn_X;
    private Button clear, commit;
    private String cookie;
    private StringBuilder sb, sb_2, sb_fail;
    private UIHandler handler;
    private UIHandler_2 handler_2;
    private UIHandler_3 handler_3;
    private TextView number, numberType, money, game_open, tv_fail;
    private ScrollView gameContent;
    private LinearLayout recentOrder;
    private ProgressDialog pDialog;
    private pDialogHandler pDialogHandler;
    private int textPos = 0;
    private RadioButton rb_allfour, rb_change, rb_normal;
    private int rcedits, rcedits_use;
    private String app_net;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        app_net = getResources().getString(R.string.app_net);

        initial();

        handler = new UIHandler();
        handler_2 = new UIHandler_2();
        handler_3 = new UIHandler_3();
        pDialogHandler = new pDialogHandler();

        Intent it = getIntent();
        cookie = it.getStringExtra("cookie");
        Log(cookie);

        sb = new StringBuilder();
        sb_2 = new StringBuilder();
        sb_fail = new StringBuilder();

        pDialog = new ProgressDialog(this);
        pDialog.setTitle("Loading Data");
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        numBtn();
        getData();
        setFnBtn();
    }

    public void initial() {
        rb_allfour = (RadioButton) findViewById(R.id.rb_allfour);
        rb_change = (RadioButton) findViewById(R.id.rb_change);
        rb_normal = (RadioButton) findViewById(R.id.rb_normal);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_X = (Button) findViewById(R.id.btn_X);
        recentOrder = (LinearLayout) findViewById(R.id.recentOrder);
        gameContent = (ScrollView) findViewById(R.id.gameContent);
        game_open = (TextView) findViewById(R.id.game_open);
        number = (TextView) findViewById(R.id.number);
        numberType = (TextView) findViewById(R.id.numberType);
        tv_fail = (TextView) findViewById(R.id.tv_fail);
        money = (TextView) findViewById(R.id.money);
        clear = (Button) findViewById(R.id.clear);
        commit = (Button) findViewById(R.id.commit);

        rb_normal.setChecked(true);
        number.setBackgroundColor(Color.parseColor("#da8c8c"));

        number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number.setBackgroundColor(Color.parseColor("#da8c8c"));
                money.setBackgroundColor(Color.parseColor("#ffffff"));
                textPos = 0;
            }
        });

        money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                money.setBackgroundColor(Color.parseColor("#da8c8c"));
                number.setBackgroundColor(Color.parseColor("#ffffff"));
                textPos = 1;
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });

        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sb_fail.setLength(0);
                tv_fail.setText("");
                String a = number.getText().toString();
                String b = money.getText().toString();
                int i = a.length();
                if (rb_allfour.isChecked() && i != 4) {
                    Toast("四字現號碼錯誤");
                } else if (a.isEmpty() || b.isEmpty() || i > 4 || i < 2) {
                    Toast("號碼或金額輸入錯誤");
                } else if (Integer.parseInt(b) > rcedits - rcedits_use) {
                    Toast("信用餘額不足");
                } else if (i == 2 && a.contains("X")) {
                    Toast("號碼或金額輸入錯誤");
                } else if (i == 3 && a.contains("X")) {
                    Toast("號碼或金額輸入錯誤");
                } else if (i == 4 && a.contains("X")) {
                    int x = 0;
                    if (String.valueOf(a.charAt(0)).equals("X")) x++;
                    if (String.valueOf(a.charAt(1)).equals("X")) x++;
                    if (String.valueOf(a.charAt(2)).equals("X")) x++;
                    if (String.valueOf(a.charAt(3)).equals("X")) x++;
                    Log("x=" + x);
                    if (x >= 3) {
                        Toast("號碼或金額輸入錯誤");
                    } else {
                        sendData(a, b);
                    }
                } else {
                    sendData(a, b);
                }
                reset();
            }
        });
    }

    public void getData() {
        pDialog.show();
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                getGameData();
                Looper.loop();
            }
        }.start();
    }

    public void getGameData() {
        try {
            MultipartUtility_tw mu = new MultipartUtility_tw("http://" + app_net + "/mobile/wap_ajax.php?action=app_head_data");
            mu.sendCookie(cookie);
//            List<String> aa = mu.getHtml();
//            for (String line : aa) {
//                Log.i("troy", line);
//            }
            JSONObject jo = mu.getJSONObjectData();
            rcedits = jo.getInt("rcedits");
            Log("rcedits= " + rcedits);
            rcedits_use = jo.getInt("rcedits_use");
            Log("rcedits_use= " + rcedits_use);

            Message msg = new Message();
            Bundle b = new Bundle();
            b.putInt("game_open", jo.getInt("game_open"));
            msg.setData(b);
            handler.sendMessage(msg);

            MultipartUtility_tw mu_2 = new MultipartUtility_tw("http://" + app_net + "/mobile/wap_ajax.php?action=app_order_dtl");
            mu_2.sendCookie(cookie);
            JSONObject rec = mu_2.getJSONObjectData().getJSONArray("list").getJSONObject(0);
            String number = rec.getString("number");
            String money = rec.getString("money");
            String frank = rec.getString("frank");

            Message msg_2 = new Message();
            Bundle b_2 = new Bundle();
            b_2.putString("number", number);
            b_2.putString("money", money);
            b_2.putString("frank", frank);
            msg_2.setData(b_2);
            handler_2.sendMessage(msg_2);

//            MultipartUtility_tw mu_3 = new MultipartUtility_tw("http://"+app_net+"/mobile/wap_ajax.php?action=app_soonsend");
//            mu.sendCookie(cookie);
////            List<String> aa = mu.getHtml();
////            for (String line : aa) {
////                Log.i("troy", line);
////            }
//            JSONArray ja = mu_3.getJSONObjectData().getJSONObject("fail_dtl").getJSONArray("l");
//            int len = ja.length();
//            for (int i = 0; i < len; i++) {
//                sb_fail.append(ja.getJSONObject(i).getString("number"));
//                sb_fail.append(" x ");
//                sb_fail.append(ja.getJSONObject(i).getInt("money"));
//                sb_fail.append("\n");
//            }
//            handler_3.sendEmptyMessage(0);
        } catch (Exception e) {
            Toast("無法與伺服器取得連線");
            Log(e.toString());
        }
        pDialogHandler.sendEmptyMessage(0);
    }

    public void sendData(final String a, final String b) {
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                sendGameData(a, b);
                Looper.loop();
            }
        }.start();
    }

    public void sendGameData(String a, String b) {
        int fail = 0;
        try {
            int allfournumber = 0;
            if (rb_allfour.isChecked()) {
                allfournumber = 1;
            }
            MultipartUtility_tw mu = new MultipartUtility_tw("http://" + app_net + "/mobile/wap_ajax.php?action=app_soonsend");
            mu.sendCookie(cookie);
            mu.postKeyValue("post_number", a + "," + b + "," + allfournumber);
//            List<String> aa = mu.getHtml();
//            for (String line : aa) {
//                Log.i("troy", line);
//            }
            JSONArray ja = mu.getJSONObjectData().getJSONObject("fail_dtl").getJSONArray("l");
            int len = ja.length();
            for (int i = 0; i < len; i++) {
                sb_fail.append(ja.getJSONObject(i).getString("number"));
                sb_fail.append(" x ");
                sb_fail.append(ja.getJSONObject(i).getInt("money"));
                sb_fail.append("\n");
                if (ja.getJSONObject(i).getString("number").equals(a) && ja.getJSONObject(i).getString("money").equals(b)) {
                    fail = 1;
                }
            }
            handler_3.sendEmptyMessage(0);

            if (fail == 1) {
                Toast("下注失敗");
            } else {
                MultipartUtility_tw mu_2 = new MultipartUtility_tw("http://" + app_net + "/mobile/wap_ajax.php?action=app_order_dtl");
                mu_2.sendCookie(cookie);
                JSONObject rec = mu_2.getJSONObjectData().getJSONArray("list").getJSONObject(0);
                String number = rec.getString("number");
                String money = rec.getString("money");
                String frank = rec.getString("frank");

                Message msg_2 = new Message();
                Bundle b_2 = new Bundle();
                b_2.putString("number", number);
                b_2.putString("money", money);
                b_2.putString("frank", frank);
                msg_2.setData(b_2);
                handler_2.sendMessage(msg_2);
                Toast("下注成功");
            }

            MultipartUtility_tw mu_3 = new MultipartUtility_tw("http://" + app_net + "/mobile/wap_ajax.php?action=app_head_data");
            mu_3.sendCookie(cookie);
            JSONObject jo = mu_3.getJSONObjectData();
            rcedits = jo.getInt("rcedits");
            Log("rcedits= " + rcedits);
            rcedits_use = jo.getInt("rcedits_use");
            Log("rcedits_use= " + rcedits_use);

        } catch (IOException e) {
            Toast("無法與伺服器取得連線");
            Log(e.toString());
        } catch (JSONException e) {
            Toast("資料錯誤");
            Log(e.toString());
        }
    }

    public void setFnBtn() {
        btn_list = (Button) findViewById(R.id.btn_list_game);
        btn_game = (Button) findViewById(R.id.btn_game_game);
        btn_member = (Button) findViewById(R.id.btn_member_game);
        btn_history = (Button) findViewById(R.id.btn_history_game);
        btn_qselect = (Button) findViewById(R.id.btn_qselect_game);
        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(GameActivity.this, ListActivity.class);
                it.putExtra("cookie", cookie);
                startActivity(it);
                finish();
            }
        });
        btn_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn_qselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(GameActivity.this, QselectActivity.class);
                it.putExtra("cookie", cookie);
                startActivity(it);
                finish();
            }
        });
        btn_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(GameActivity.this, MemberActivity.class);
                it.putExtra("cookie", cookie);
                startActivity(it);
                finish();
            }
        });
        btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(GameActivity.this, HistoryActivity.class);
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

            int game_open_code = msg.getData().getInt("game_open");
            if (game_open_code == 0) {
                game_open_toast(0);
                game_open.setText("關盤中");
            } else if (game_open_code == 1) {
                game_open_toast(1);
                game_open.setText("開盤中");
                gameContent.setVisibility(View.VISIBLE);
            }
        }
    }

    private class UIHandler_2 extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            String number = msg.getData().getString("number");
            String money = msg.getData().getString("money");
            String frank = msg.getData().getString("frank");
            list(number, money, frank);
        }
    }

    private class UIHandler_3 extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            tv_fail.setText(sb_fail.toString());
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

    public void game_open_toast(int i) {
        if (i == 0) {
            Toast("關盤中");
        } else if (i == 1) {
            Toast("開盤中");
        }

    }

    public void numBtn() {
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = "1";
                if (textPos == 0) {
                    setNumberText(a);
                } else if (textPos == 1) {
                    setMoneyText(a);
                }

            }
        });
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = "2";
                if (textPos == 0) {
                    setNumberText(a);
                } else if (textPos == 1) {
                    setMoneyText(a);
                }
            }
        });
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = "3";
                if (textPos == 0) {
                    setNumberText(a);
                } else if (textPos == 1) {
                    setMoneyText(a);
                }
            }
        });
        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = "4";
                if (textPos == 0) {
                    setNumberText(a);
                } else if (textPos == 1) {
                    setMoneyText(a);
                }
            }
        });
        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = "5";
                if (textPos == 0) {
                    setNumberText(a);
                } else if (textPos == 1) {
                    setMoneyText(a);
                }
            }
        });
        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = "6";
                if (textPos == 0) {
                    setNumberText(a);
                } else if (textPos == 1) {
                    setMoneyText(a);
                }
            }
        });
        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = "7";
                if (textPos == 0) {
                    setNumberText(a);
                } else if (textPos == 1) {
                    setMoneyText(a);
                }
            }
        });
        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = "8";
                if (textPos == 0) {
                    setNumberText(a);
                } else if (textPos == 1) {
                    setMoneyText(a);
                }
            }
        });
        btn_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = "9";
                if (textPos == 0) {
                    setNumberText(a);
                } else if (textPos == 1) {
                    setMoneyText(a);
                }
            }
        });
        btn_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = "0";
                if (textPos == 0) {
                    setNumberText(a);
                } else if (textPos == 1) {
                    setMoneyText(a);
                }
            }
        });
        btn_X.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = "X";
                if (rb_allfour.isChecked() && textPos == 0) {

                } else if (textPos == 0) {
                    setNumberText(a);
                } else if (textPos == 1) {
//                    setMoneyText(a);
                }
            }
        });
    }

    public void setNumberText(String s) {
        if (!rb_allfour.isChecked()) {
            if (sb.length() < 4) {
                sb.append(s);
                Log("sb--" + sb.toString());
                number.setText(sb.toString());

                switch (sb.length()) {
                    case 2:
                        numberType.setText("現");
                        break;
                    case 3:
                        numberType.setText("現");
                        break;
                    default:
                        numberType.setText("--");
                        break;
                }
            }
        } else {
            numberType.setText("");
            if (sb.length() < 4) {
                sb.append(s);
                Log("sb--" + sb.toString());
                number.setText(sb.toString());
            }
        }

    }

    public void setMoneyText(String s) {
        if (sb_2.length() < 7) {
            sb_2.append(s);
            Log("sb_2--" + sb_2.toString());
            money.setText(sb_2.toString());
        }
    }

    public void reset() {
        number.setText("");
        numberType.setText("--");
        money.setText("");
        sb.setLength(0);
        sb_2.setLength(0);
        number.setBackgroundColor(Color.parseColor("#da8c8c"));
        money.setBackgroundColor(Color.parseColor("#ffffff"));
        textPos = 0;
    }

    public void list(String number, String money, String frank) {
        LinearLayout ll = new LinearLayout(GameActivity.this);
        ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ll.setOrientation(LinearLayout.HORIZONTAL);
        TextView tv0 = new TextView(GameActivity.this);
        tv0.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT));
        TextView tv1 = new TextView(GameActivity.this);
        tv1.setText(number);
        tv1.setTextSize(20);
        tv1.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        tv1.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        TextView tv2 = new TextView(GameActivity.this);
        tv2.setText(money);
        tv2.setTextSize(20);
        tv2.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        tv2.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        TextView tv3 = new TextView(GameActivity.this);
        tv3.setText(frank);
        tv3.setTextSize(20);
        tv3.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        tv3.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        TextView tv4 = new TextView(GameActivity.this);
        tv4.setLayoutParams(new LinearLayout.LayoutParams(50, LinearLayout.LayoutParams.MATCH_PARENT));
        ll.addView(tv0);
        ll.addView(tv1);
        ll.addView(tv2);
        ll.addView(tv3);
        ll.addView(tv4);
        recentOrder.addView(ll);
    }

    public void Toast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    public void Log(String s) {
        Log.i("troy", s);
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
                GameActivity.this.finish();//關閉activity
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
