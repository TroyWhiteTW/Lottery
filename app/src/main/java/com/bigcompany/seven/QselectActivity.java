package com.bigcompany.seven;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class QselectActivity extends AppCompatActivity {
    private Button btn_history, btn_member, btn_game, btn_list, btn_qselect;
    private Button btn_erDing, btn_sanDing, btn_siDing, btn_erXian, btn_sanXian, btn_siXian, btn_qselectRes, btn_qselectReset;
    private EditText et_qian, et_bai, et_shi, et_ge;
    private EditText et_21, et_22, et_31, et_32, et_33, et_41, et_42, et_43, et_44;
    private int gameStyle = 0;
    private LinearLayout ll_dingPos, ll_dingEdit, ll_peiEdit_2, ll_peiEdit_3, ll_peiEdit_4;
    private RadioGroup rg_dingBasic, rg_peiBasic;
    private RadioButton rb_dingChu, rb_dingQu, rb_peiChu, rb_peiQu;
    private RadioButton rb_pei_chu, rb_pei_qu;
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

        changeGameStyle();
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
        et_qian = (EditText) findViewById(R.id.et_qian);
        et_bai = (EditText) findViewById(R.id.et_bai);
        et_shi = (EditText) findViewById(R.id.et_shi);
        et_ge = (EditText) findViewById(R.id.et_ge);
        et_21 = (EditText) findViewById(R.id.et_21);
        et_22 = (EditText) findViewById(R.id.et_22);
        et_31 = (EditText) findViewById(R.id.et_31);
        et_32 = (EditText) findViewById(R.id.et_32);
        et_33 = (EditText) findViewById(R.id.et_33);
        et_41 = (EditText) findViewById(R.id.et_41);
        et_42 = (EditText) findViewById(R.id.et_42);
        et_43 = (EditText) findViewById(R.id.et_43);
        et_44 = (EditText) findViewById(R.id.et_44);
        ll_dingPos = (LinearLayout) findViewById(R.id.ll_dingPos);
        ll_dingEdit = (LinearLayout) findViewById(R.id.ll_dingEdit);
        ll_peiEdit_2 = (LinearLayout) findViewById(R.id.ll_peiEdit_2);
        ll_peiEdit_3 = (LinearLayout) findViewById(R.id.ll_peiEdit_3);
        ll_peiEdit_4 = (LinearLayout) findViewById(R.id.ll_peiEdit_4);
        rg_dingBasic = (RadioGroup) findViewById(R.id.rg_dingBasic);
        rg_peiBasic = (RadioGroup) findViewById(R.id.rg_peiBasic);
        rb_dingChu = (RadioButton) findViewById(R.id.rb_dingChu);
        rb_dingQu = (RadioButton) findViewById(R.id.rb_dingQu);
        rb_peiChu = (RadioButton) findViewById(R.id.rb_peiChu);
        rb_peiQu = (RadioButton) findViewById(R.id.rb_peiQu);
        rb_pei_chu = (RadioButton) findViewById(R.id.rb_pei_chu);
        rb_pei_qu = (RadioButton) findViewById(R.id.rb_pei_qu);

        btnOnClick();
        rbSetting();
    }

    public void btnOnClick() {
        btn_erDing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameStyle = 21;
                changeGameStyle();
            }
        });
        btn_sanDing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameStyle = 31;
                changeGameStyle();
            }
        });
        btn_siDing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameStyle = 41;
                changeGameStyle();
            }
        });
        btn_erXian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameStyle = 22;
                changeGameStyle();
            }
        });
        btn_sanXian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameStyle = 32;
                changeGameStyle();
            }
        });
        btn_siXian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameStyle = 42;
                changeGameStyle();
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

    public void rbSetting() {
        rb_dingChu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (rb_dingChu.isChecked()) {
                    ll_dingPos.setVisibility(View.VISIBLE);
                    ll_dingEdit.setVisibility(View.VISIBLE);
                    ll_peiEdit_2.setVisibility(View.GONE);
                    ll_peiEdit_3.setVisibility(View.GONE);
                    ll_peiEdit_4.setVisibility(View.GONE);
                }
            }
        });
        rb_dingQu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (rb_dingQu.isChecked()) {
                    ll_dingPos.setVisibility(View.VISIBLE);
                    ll_dingEdit.setVisibility(View.VISIBLE);
                    ll_peiEdit_2.setVisibility(View.GONE);
                    ll_peiEdit_3.setVisibility(View.GONE);
                    ll_peiEdit_4.setVisibility(View.GONE);
                }
            }
        });
        rb_peiChu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (rb_peiChu.isChecked()) {
                    ll_dingPos.setVisibility(View.GONE);
                    ll_dingEdit.setVisibility(View.GONE);
                    switch (gameStyle) {
                        case 21:
                            ll_peiEdit_2.setVisibility(View.VISIBLE);
                            ll_peiEdit_3.setVisibility(View.GONE);
                            ll_peiEdit_4.setVisibility(View.GONE);
                            break;
                        case 31:
                            ll_peiEdit_2.setVisibility(View.GONE);
                            ll_peiEdit_3.setVisibility(View.VISIBLE);
                            ll_peiEdit_4.setVisibility(View.GONE);
                            break;
                        case 41:
                            ll_peiEdit_2.setVisibility(View.GONE);
                            ll_peiEdit_3.setVisibility(View.GONE);
                            ll_peiEdit_4.setVisibility(View.VISIBLE);
                            break;
                        default:
                            ll_peiEdit_2.setVisibility(View.GONE);
                            ll_peiEdit_3.setVisibility(View.GONE);
                            ll_peiEdit_4.setVisibility(View.GONE);
                            break;
                    }
                }
            }
        });
        rb_peiQu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (rb_peiQu.isChecked()) {
                    ll_dingPos.setVisibility(View.GONE);
                    ll_dingEdit.setVisibility(View.GONE);
                    switch (gameStyle) {
                        case 21:
                            ll_peiEdit_2.setVisibility(View.VISIBLE);
                            ll_peiEdit_3.setVisibility(View.GONE);
                            ll_peiEdit_4.setVisibility(View.GONE);
                            break;
                        case 31:
                            ll_peiEdit_2.setVisibility(View.GONE);
                            ll_peiEdit_3.setVisibility(View.VISIBLE);
                            ll_peiEdit_4.setVisibility(View.GONE);
                            break;
                        case 41:
                            ll_peiEdit_2.setVisibility(View.GONE);
                            ll_peiEdit_3.setVisibility(View.GONE);
                            ll_peiEdit_4.setVisibility(View.VISIBLE);
                            break;
                        default:
                            ll_peiEdit_2.setVisibility(View.GONE);
                            ll_peiEdit_3.setVisibility(View.GONE);
                            ll_peiEdit_4.setVisibility(View.GONE);
                            break;
                    }
                }
            }
        });
    }

    public void changeGameStyle() {
        reset();
        switch (gameStyle) {
            case 0:
                rg_dingBasic.setVisibility(View.GONE);
                rg_peiBasic.setVisibility(View.GONE);
                break;
            case 21:
                rg_dingBasic.setVisibility(View.VISIBLE);
                rg_peiBasic.setVisibility(View.GONE);
                break;
            case 31:
                rg_dingBasic.setVisibility(View.VISIBLE);
                rg_peiBasic.setVisibility(View.GONE);
                break;
            case 41:
                rg_dingBasic.setVisibility(View.VISIBLE);
                rg_peiBasic.setVisibility(View.GONE);
                break;
            case 22:
                rg_dingBasic.setVisibility(View.GONE);
                rg_peiBasic.setVisibility(View.VISIBLE);
                break;
            case 32:
                rg_dingBasic.setVisibility(View.GONE);
                rg_peiBasic.setVisibility(View.VISIBLE);
                break;
            case 42:
                rg_dingBasic.setVisibility(View.GONE);
                rg_peiBasic.setVisibility(View.VISIBLE);
                break;
            default:
                rg_dingBasic.setVisibility(View.GONE);
                rg_peiBasic.setVisibility(View.GONE);
                break;
        }
    }

    public void reset() {
        rb_dingChu.setChecked(true);
        rb_dingQu.setChecked(true);
        rb_peiChu.setChecked(true);
        rb_peiQu.setChecked(true);
        rb_pei_chu.setChecked(true);
        rb_pei_qu.setChecked(true);
        rb_dingChu.setChecked(false);
        rb_dingQu.setChecked(false);
        rb_peiChu.setChecked(false);
        rb_peiQu.setChecked(false);
        rb_pei_chu.setChecked(false);
        rb_pei_qu.setChecked(false);
        ll_dingPos.setVisibility(View.GONE);
        ll_dingEdit.setVisibility(View.GONE);
        ll_peiEdit_2.setVisibility(View.GONE);
        ll_peiEdit_3.setVisibility(View.GONE);
        ll_peiEdit_4.setVisibility(View.GONE);
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
                QselectActivity.this.finish();//關閉activity
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
