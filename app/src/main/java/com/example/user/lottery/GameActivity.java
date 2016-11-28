package com.example.user.lottery;

import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {
    private Button btn_history, btn_member, btn_game, btn_list;
    private String cookie;
    private TextView number;
    private EditText money;
    private Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_0, btn_X;
    private Button clear, commit;
    private StringBuilder sb;
    private ScrollView gameContent;
    private TextView game_open;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent it = getIntent();
        cookie = it.getStringExtra("cookie");
        Log.i("troy", cookie);

        gameContent = (ScrollView) findViewById(R.id.gameContent);
        game_open = (TextView) findViewById(R.id.game_open);

        sb = new StringBuilder();

        number = (TextView) findViewById(R.id.number);
        money = (EditText) findViewById(R.id.money);

        numBtn();

        clear = (Button) findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });

        commit = (Button) findViewById(R.id.commit);
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = number.getText().toString();
                String b = money.getText().toString();
                Log.i("troy", String.valueOf(a.length()));
                if (a.isEmpty() || b.isEmpty() || a.length() != 4) {
                    Log.i("troy", "XX");
                    commitErr();
                } else {
                    Log.i("troy", "OK");
                    getData(a, b);
                    reset();
                }
            }
        });

        getData();
        setFnBtn();
    }

    public void getData() {
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
            MultipartUtility_tw mu = new MultipartUtility_tw("http://mb.sm2.xyz/mobile/wap_ajax.php?action=app_head_data");
            mu.sendCookie(cookie);
//            List<String> aa = mu.getHtml();
//            for (String line : aa) {
//                Log.i("troy", line);
//            }
            JSONObject jo = mu.getJSONObjectData();
            Log.i("troy", jo.getString("webname"));
            if (jo.getInt("game_open") == 0) {
                Toast.makeText(this, "關盤中", Toast.LENGTH_LONG).show();
                game_open.setText("關盤中");
            } else if (jo.getInt("game_open") == 1) {
                Toast.makeText(this, "開盤中", Toast.LENGTH_LONG).show();
                game_open.setText("開盤中");
                gameContent.setVisibility(View.VISIBLE);
            }

        } catch (Exception e) {
            Toast.makeText(this, "無法與伺服器取得連線", Toast.LENGTH_LONG).show();
            Log.i("troy", e.toString());
        }
    }

    public void getData(final String a, final String b) {
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                getGameData(a, b);
                Looper.loop();
            }
        }.start();
    }

    public void getGameData(String a, String b) {
        try {
            MultipartUtility_tw mu = new MultipartUtility_tw("http://mb.sm2.xyz/mobile/wap_ajax.php?action=app_soonsend");
            mu.sendCookie(cookie);
            mu.postKeyValue("post_number", a + "," + b + "," + "0");
            List<String> aa = mu.getHtml();
            for (String line : aa) {
                Log.i("troy", line);
            }
            Toast.makeText(this, "下注成功", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "無法與伺服器取得連線", Toast.LENGTH_LONG).show();
            Log.i("troy", e.toString());
        }
    }

    public void setFnBtn() {
        btn_list = (Button) findViewById(R.id.btn_list_game);
        btn_game = (Button) findViewById(R.id.btn_game_game);
        btn_member = (Button) findViewById(R.id.btn_member_game);
        btn_history = (Button) findViewById(R.id.btn_history_game);

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

    public void commitErr() {
        Toast.makeText(this, "號碼或金額輸入錯誤", Toast.LENGTH_LONG).show();
    }

    public void numBtn() {
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = "1";
                if (sb.length() < 4) {
                    sb.append(a);
                    Log.i("troy", "sb--" + sb.toString());
                    number.setText(sb.toString());
                }
            }
        });
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = "2";
                if (sb.length() < 4) {
                    sb.append(a);
                    Log.i("troy", "sb--" + sb.toString());
                    number.setText(sb.toString());
                }
            }
        });
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = "3";
                if (sb.length() < 4) {
                    sb.append(a);
                    Log.i("troy", "sb--" + sb.toString());
                    number.setText(sb.toString());
                }
            }
        });
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = "4";
                if (sb.length() < 4) {
                    sb.append(a);
                    Log.i("troy", "sb--" + sb.toString());
                    number.setText(sb.toString());
                }
            }
        });
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = "5";
                if (sb.length() < 4) {
                    sb.append(a);
                    Log.i("troy", "sb--" + sb.toString());
                    number.setText(sb.toString());
                }
            }
        });
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = "6";
                if (sb.length() < 4) {
                    sb.append(a);
                    Log.i("troy", "sb--" + sb.toString());
                    number.setText(sb.toString());
                }
            }
        });
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = "7";
                if (sb.length() < 4) {
                    sb.append(a);
                    Log.i("troy", "sb--" + sb.toString());
                    number.setText(sb.toString());
                }
            }
        });
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = "8";
                if (sb.length() < 4) {
                    sb.append(a);
                    Log.i("troy", "sb--" + sb.toString());
                    number.setText(sb.toString());
                }
            }
        });
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = "9";
                if (sb.length() < 4) {
                    sb.append(a);
                    Log.i("troy", "sb--" + sb.toString());
                    number.setText(sb.toString());
                }
            }
        });
        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = "0";
                if (sb.length() < 4) {
                    sb.append(a);
                    Log.i("troy", "sb--" + sb.toString());
                    number.setText(sb.toString());
                }
            }
        });
        btn_X = (Button) findViewById(R.id.btn_X);
        btn_X.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = "X";
                if (sb.length() < 4) {
                    sb.append(a);
                    Log.i("troy", "sb--" + sb.toString());
                    number.setText(sb.toString());
                }
            }
        });
    }

    public void reset() {
        number.setText("");
        money.setText("");
        sb.setLength(0);
    }
}
