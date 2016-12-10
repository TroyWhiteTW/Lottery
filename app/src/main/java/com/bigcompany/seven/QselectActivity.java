package com.bigcompany.seven;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

public class QselectActivity extends AppCompatActivity {
    private Button btn_history, btn_member, btn_game, btn_list, btn_qselect;
    private Button btn_erDing, btn_sanDing, btn_siDing, btn_erXian, btn_sanXian, btn_siXian, btn_qselectRes, btn_qselectReset;
    private LinearLayout ll_dingPos;
    private RadioButton rb_dingChu, rb_dingQu, rb_peiChu, rb_peiQu;
    private String cookie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qselect);

        Intent it = getIntent();
        cookie = it.getStringExtra("cookie");
        Log(cookie);

        initial();
        setFnBtn();
    }

    public void initial() {
        btn_erDing = (Button) findViewById(R.id.btn_erDing);
        btn_sanDing = (Button) findViewById(R.id.btn_sanDing);
        btn_siDing = (Button) findViewById(R.id.btn_siDing);
        btn_erXian = (Button) findViewById(R.id.btn_erXian);
        btn_sanXian = (Button) findViewById(R.id.btn_sanXian);
        btn_siXian = (Button) findViewById(R.id.btn_siXian);
        btn_qselectRes = (Button) findViewById(R.id.btn_qselectRes);
        btn_qselectReset = (Button) findViewById(R.id.btn_qselectReset);
        ll_dingPos = (LinearLayout) findViewById(R.id.ll_dingPos);
        rb_dingChu = (RadioButton) findViewById(R.id.rb_dingChu);
        rb_dingQu = (RadioButton) findViewById(R.id.rb_dingQu);
        rb_peiChu = (RadioButton) findViewById(R.id.rb_peiChu);
        rb_peiQu = (RadioButton) findViewById(R.id.rb_peiQu);

        rb_dingChu.setChecked(true);
        if (rb_dingChu.isChecked() || rb_dingQu.isChecked()) {
            ll_dingPos.setVisibility(View.VISIBLE);
        } else if (rb_peiChu.isChecked() || rb_peiQu.isChecked()) {
            ll_dingPos.setVisibility(View.GONE);
        }

        btnOnClick();
    }

    public void btnOnClick() {
        btn_erDing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn_sanDing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn_siDing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn_erXian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn_sanXian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn_siXian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn_qselectRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(QselectActivity.this, QselectResActivity.class);
                it.putExtra("cookie", cookie);
                startActivity(it);
            }
        });
        btn_qselectReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void setFnBtn() {
        btn_list = (Button) findViewById(R.id.btn_list_qselect);
        btn_game = (Button) findViewById(R.id.btn_game_qselect);
        btn_member = (Button) findViewById(R.id.btn_member_qselect);
        btn_history = (Button) findViewById(R.id.btn_history_qselect);
        btn_qselect = (Button) findViewById(R.id.btn_qselect_qselect);
        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(QselectActivity.this, ListActivity.class);
                it.putExtra("cookie", cookie);
                startActivity(it);
                finish();
            }
        });
        btn_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(QselectActivity.this, GameActivity.class);
                it.putExtra("cookie", cookie);
                startActivity(it);
                finish();
            }
        });
        btn_qselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(QselectActivity.this, MemberActivity.class);
                it.putExtra("cookie", cookie);
                startActivity(it);
                finish();
            }
        });
        btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(QselectActivity.this, HistoryActivity.class);
                it.putExtra("cookie", cookie);
                startActivity(it);
                finish();
            }
        });
    }

    public void Toast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    public void Log(String s) {
        Log.i("troy", s);
    }
}
