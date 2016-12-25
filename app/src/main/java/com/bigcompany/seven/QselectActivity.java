package com.bigcompany.seven;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
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
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class QselectActivity extends AppCompatActivity {
    private Button btn_history, btn_member, btn_game, btn_list, btn_qselect;
    private Button btn_erDing, btn_sanDing, btn_siDing, btn_erXian, btn_sanXian, btn_siXian, btn_qselectRes, btn_qselectReset;
    private EditText et_qian, et_bai, et_shi, et_ge;
    private EditText et_21, et_22, et_31, et_32, et_33, et_41, et_42, et_43, et_44;
    private int gameStyle = 0;//classID: 1=二定位; 2=三定位; 3=四定位; 4=二字現; 5=三字現; 6=四字現
    private int gameSet = 0;
    private LinearLayout ll_dingPos, ll_dingEdit, ll_peiEdit_2, ll_peiEdit_3, ll_peiEdit_4;
    private ProgressDialog pDialog;
    private pDialogHandler pDialogHandler;
    private RadioGroup rg_dingBasic, rg_peiBasic;
    private RadioButton rb_dingChu, rb_dingQu, rb_peiChu, rb_peiQu;
    private RadioButton rb_pei_chu, rb_pei_qu;
    private String cookie;
    private String app_net;
    private ScrollView sv_qselect;
    private TextView tv_gameStyle, game_open_false, game_open_true;
    private UIHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qselect);

        app_net = getResources().getString(R.string.app_net);

        Intent it = getIntent();
        cookie = it.getStringExtra("cookie");
        Log(cookie);

        handler = new UIHandler();
        pDialogHandler = new pDialogHandler();

        pDialog = new ProgressDialog(this);
        pDialog.setTitle("Loading Data");
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        initial();
        setFnBtn();

        getData();

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
        tv_gameStyle = (TextView) findViewById(R.id.tv_gameStyle);
        game_open_false = (TextView) findViewById(R.id.game_open_false);
        game_open_true = (TextView) findViewById(R.id.game_open_true);
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
        sv_qselect = (ScrollView) findViewById(R.id.sv_qselect);

        et_qian.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnOnClick();
        rbSetting();
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

            Message msg = new Message();
            Bundle b = new Bundle();
            b.putInt("game_open", jo.getInt("game_open"));
            msg.setData(b);
            handler.sendMessage(msg);

        } catch (Exception e) {
            Toast("無法與伺服器取得連線");
            Log(e.toString());
        }
        pDialogHandler.sendEmptyMessage(0);
    }

    private class UIHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            int game_open_code = msg.getData().getInt("game_open");
            if (game_open_code == 0) {
                game_open_toast(0);
                game_open_false.setVisibility(View.VISIBLE);
                sv_qselect.setVisibility(View.GONE);
            } else if (game_open_code == 1) {
                game_open_toast(1);
                game_open_true.setVisibility(View.VISIBLE);
                sv_qselect.setVisibility(View.VISIBLE);
            }
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

    public void gameSet() {
        if (rb_dingChu.isChecked()) gameSet = 1;
        if (rb_dingQu.isChecked()) gameSet = 2;
        if (rb_peiChu.isChecked()) gameSet = 3;
        if (rb_peiQu.isChecked()) gameSet = 4;
        if (rb_pei_chu.isChecked()) gameSet = 5;
        if (rb_pei_qu.isChecked()) gameSet = 6;
        Log("gameSet=" + gameSet);
    }

    public void btnOnClick() {
        btn_erDing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameStyle = 1;
                tv_gameStyle.setText("---二字定---");
                changeGameStyle();
            }
        });
        btn_sanDing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameStyle = 2;
                tv_gameStyle.setText("---三字定---");
                changeGameStyle();
            }
        });
        btn_siDing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameStyle = 3;
                tv_gameStyle.setText("---四字定---");
                changeGameStyle();
            }
        });
        btn_erXian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameStyle = 4;
                tv_gameStyle.setText("---二字現---");
                changeGameStyle();
            }
        });
        btn_sanXian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameStyle = 5;
                tv_gameStyle.setText("---三字現---");
                changeGameStyle();
            }
        });
        btn_siXian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameStyle = 6;
                tv_gameStyle.setText("---四字現---");
                changeGameStyle();
            }
        });
        btn_qselectRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameSet();
                if (gameSet != 0) {
                    switch (gameStyle) {
                        case 0:
                            Toast("下注資料輸入錯誤");
                            break;
                        case 1:
                            switch (gameSet) {
                                case 1:
                                    if (et_qian.getText().toString().isEmpty() && et_bai.getText().toString().isEmpty() && et_shi.getText().toString().isEmpty() && et_ge.getText().toString().isEmpty()) {
                                        Toast("下注資料輸入錯誤");
                                    } else if (!et_qian.getText().toString().isEmpty() && !et_bai.getText().toString().isEmpty() && !et_shi.getText().toString().isEmpty() && !et_ge.getText().toString().isEmpty()) {
                                        Toast("下注資料輸入錯誤");
                                    } else if (!et_qian.getText().toString().isEmpty() && !et_bai.getText().toString().isEmpty() && !et_shi.getText().toString().isEmpty() && et_ge.getText().toString().isEmpty()) {
                                        Toast("下注資料輸入錯誤");
                                    } else if (!et_qian.getText().toString().isEmpty() && !et_bai.getText().toString().isEmpty() && et_shi.getText().toString().isEmpty() && !et_ge.getText().toString().isEmpty()) {
                                        Toast("下注資料輸入錯誤");
                                    } else if (!et_qian.getText().toString().isEmpty() && et_bai.getText().toString().isEmpty() && !et_shi.getText().toString().isEmpty() && !et_ge.getText().toString().isEmpty()) {
                                        Toast("下注資料輸入錯誤");
                                    } else if (et_qian.getText().toString().isEmpty() && !et_bai.getText().toString().isEmpty() && !et_shi.getText().toString().isEmpty() && !et_ge.getText().toString().isEmpty()) {
                                        Toast("下注資料輸入錯誤");
                                    } else {
                                        toResActivity();
                                    }
                                    break;
                                case 2:
                                    if (et_qian.getText().toString().isEmpty() && et_bai.getText().toString().isEmpty() && et_shi.getText().toString().isEmpty() && et_ge.getText().toString().isEmpty()) {
                                        Toast("下注資料輸入錯誤");
                                    } else if (!et_qian.getText().toString().isEmpty() && !et_bai.getText().toString().isEmpty() && !et_shi.getText().toString().isEmpty() && !et_ge.getText().toString().isEmpty()) {
                                        Toast("下注資料輸入錯誤");
                                    } else if (!et_qian.getText().toString().isEmpty() && !et_bai.getText().toString().isEmpty() && !et_shi.getText().toString().isEmpty() && et_ge.getText().toString().isEmpty()) {
                                        Toast("下注資料輸入錯誤");
                                    } else if (!et_qian.getText().toString().isEmpty() && !et_bai.getText().toString().isEmpty() && et_shi.getText().toString().isEmpty() && !et_ge.getText().toString().isEmpty()) {
                                        Toast("下注資料輸入錯誤");
                                    } else if (!et_qian.getText().toString().isEmpty() && et_bai.getText().toString().isEmpty() && !et_shi.getText().toString().isEmpty() && !et_ge.getText().toString().isEmpty()) {
                                        Toast("下注資料輸入錯誤");
                                    } else if (et_qian.getText().toString().isEmpty() && !et_bai.getText().toString().isEmpty() && !et_shi.getText().toString().isEmpty() && !et_ge.getText().toString().isEmpty()) {
                                        Toast("下注資料輸入錯誤");
                                    } else {
                                        toResActivity();
                                    }
                                    break;
                                case 3:
                                    if (et_21.getText().toString().isEmpty() && et_22.getText().toString().isEmpty()) {
                                        Toast("下注資料輸入錯誤");
                                    } else {
                                        toResActivity();
                                    }
                                    break;
                                case 4:
                                    if (et_21.getText().toString().isEmpty() && et_22.getText().toString().isEmpty()) {
                                        Toast("下注資料輸入錯誤");
                                    } else {
                                        toResActivity();
                                    }
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case 2:
                            switch (gameSet) {
                                case 1:
                                    if (et_qian.getText().toString().isEmpty() && et_bai.getText().toString().isEmpty() && et_shi.getText().toString().isEmpty() && et_ge.getText().toString().isEmpty()) {
                                        Toast("下注資料輸入錯誤");
                                    } else if (!et_qian.getText().toString().isEmpty() && !et_bai.getText().toString().isEmpty() && !et_shi.getText().toString().isEmpty() && !et_ge.getText().toString().isEmpty()) {
                                        Toast("下注資料輸入錯誤");
                                    } else {
                                        toResActivity();
                                    }
                                    break;
                                case 2:
                                    if (et_qian.getText().toString().isEmpty() && et_bai.getText().toString().isEmpty() && et_shi.getText().toString().isEmpty() && et_ge.getText().toString().isEmpty()) {
                                        Toast("下注資料輸入錯誤");
                                    } else if (!et_qian.getText().toString().isEmpty() && !et_bai.getText().toString().isEmpty() && !et_shi.getText().toString().isEmpty() && !et_ge.getText().toString().isEmpty()) {
                                        Toast("下注資料輸入錯誤");
                                    } else {
                                        toResActivity();
                                    }
                                    break;
                                case 3:
                                    if (et_31.getText().toString().isEmpty() && et_32.getText().toString().isEmpty() && et_33.getText().toString().isEmpty()) {
                                        Toast("下注資料輸入錯誤");
                                    } else {
                                        toResActivity();
                                    }
                                    break;
                                case 4:
                                    if (et_31.getText().toString().isEmpty() && et_32.getText().toString().isEmpty() && et_33.getText().toString().isEmpty()) {
                                        Toast("下注資料輸入錯誤");
                                    } else {
                                        toResActivity();
                                    }
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case 3:
                            switch (gameSet) {
                                case 1:
                                    if (et_qian.getText().toString().isEmpty() && et_bai.getText().toString().isEmpty() && et_shi.getText().toString().isEmpty() && et_ge.getText().toString().isEmpty()) {
                                        Toast("下注資料輸入錯誤");
                                    } else {
                                        toResActivity();
                                    }
                                    break;
                                case 2:
                                    if (et_qian.getText().toString().isEmpty() && et_bai.getText().toString().isEmpty() && et_shi.getText().toString().isEmpty() && et_ge.getText().toString().isEmpty()) {
                                        Toast("下注資料輸入錯誤");
                                    } else {
                                        toResActivity();
                                    }
                                    break;
                                case 3:
                                    if (et_41.getText().toString().isEmpty() && et_42.getText().toString().isEmpty() && et_43.getText().toString().isEmpty() && et_44.getText().toString().isEmpty()) {
                                        Toast("下注資料輸入錯誤");
                                    } else {
                                        toResActivity();
                                    }
                                    break;
                                case 4:
                                    if (et_41.getText().toString().isEmpty() && et_42.getText().toString().isEmpty() && et_43.getText().toString().isEmpty() && et_44.getText().toString().isEmpty()) {
                                        Toast("下注資料輸入錯誤");
                                    } else {
                                        toResActivity();
                                    }
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case 4:
                            if (et_21.getText().toString().isEmpty() && et_22.getText().toString().isEmpty()) {
                                Toast("下注資料輸入錯誤");
                            } else {
                                toResActivity();
                            }
                            break;
                        case 5:
                            if (et_31.getText().toString().isEmpty() && et_32.getText().toString().isEmpty() && et_33.getText().toString().isEmpty()) {
                                Toast("下注資料輸入錯誤");
                            } else {
                                toResActivity();
                            }
                            break;
                        case 6:
                            if (et_41.getText().toString().isEmpty() && et_42.getText().toString().isEmpty() && et_43.getText().toString().isEmpty() && et_44.getText().toString().isEmpty()) {
                                Toast("下注資料輸入錯誤");
                            } else {
                                toResActivity();
                            }
                            break;
                        default:
                            break;
                    }
                } else {
                    Toast("下注資料輸入錯誤");
                }

            }
        });
        btn_qselectReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearEditText();
            }
        });
    }

    public void toResActivity() {
        Intent it = new Intent(QselectActivity.this, QselectResActivity.class);
        it.putExtra("cookie", cookie);
        it.putExtra("gameStyle", gameStyle);
        it.putExtra("gameSet", gameSet);
        it.putExtra("et_qian", et_qian.getText().toString());
        it.putExtra("et_bai", et_bai.getText().toString());
        it.putExtra("et_shi", et_shi.getText().toString());
        it.putExtra("et_ge", et_ge.getText().toString());
        it.putExtra("et_21", et_21.getText().toString());
        it.putExtra("et_22", et_22.getText().toString());
        it.putExtra("et_31", et_31.getText().toString());
        it.putExtra("et_32", et_32.getText().toString());
        it.putExtra("et_33", et_33.getText().toString());
        it.putExtra("et_41", et_41.getText().toString());
        it.putExtra("et_42", et_42.getText().toString());
        it.putExtra("et_43", et_43.getText().toString());
        it.putExtra("et_44", et_44.getText().toString());
//                    Log("et_qian=" + et_qian.getText().toString());
//                    Log("et_bai=" + et_bai.getText().toString());
//                    Log("et_shi=" + et_shi.getText().toString());
//                    Log("et_ge=" + et_ge.getText().toString());
//                    Log("et_21=" + et_21.getText().toString());
//                    Log("et_22=" + et_22.getText().toString());
//                    Log("et_31=" + et_31.getText().toString());
//                    Log("et_32=" + et_32.getText().toString());
//                    Log("et_33=" + et_33.getText().toString());
//                    Log("et_41=" + et_41.getText().toString());
//                    Log("et_42=" + et_42.getText().toString());
//                    Log("et_43=" + et_43.getText().toString());
//                    Log("et_44=" + et_44.getText().toString());
        startActivity(it);
    }

    public void rbSetting() {
        rb_dingChu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (rb_dingChu.isChecked()) {
                    reset();
                    ll_dingPos.setVisibility(View.VISIBLE);
                    ll_dingEdit.setVisibility(View.VISIBLE);
                }
            }
        });
        rb_dingQu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (rb_dingQu.isChecked()) {
                    reset();
                    ll_dingPos.setVisibility(View.VISIBLE);
                    ll_dingEdit.setVisibility(View.VISIBLE);
                }
            }
        });
        rb_peiChu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (rb_peiChu.isChecked()) {
                    reset();
                    switch (gameStyle) {
                        case 1:
                            ll_peiEdit_2.setVisibility(View.VISIBLE);
                            break;
                        case 2:
                            ll_peiEdit_3.setVisibility(View.VISIBLE);
                            break;
                        case 3:
                            ll_peiEdit_4.setVisibility(View.VISIBLE);
                            break;
                        default:
                            reset();
                            break;
                    }
                }
            }
        });
        rb_peiQu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (rb_peiQu.isChecked()) {
                    reset();
                    switch (gameStyle) {
                        case 1:
                            ll_peiEdit_2.setVisibility(View.VISIBLE);
                            break;
                        case 2:
                            ll_peiEdit_3.setVisibility(View.VISIBLE);
                            break;
                        case 3:
                            ll_peiEdit_4.setVisibility(View.VISIBLE);
                            break;
                        default:
                            reset();
                            break;
                    }
                }
            }
        });
        rb_pei_chu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (rb_pei_chu.isChecked()) {
                    reset();
                    switch (gameStyle) {
                        case 4:
                            ll_peiEdit_2.setVisibility(View.VISIBLE);
                            break;
                        case 5:
                            ll_peiEdit_3.setVisibility(View.VISIBLE);
                            break;
                        case 6:
                            ll_peiEdit_4.setVisibility(View.VISIBLE);
                            break;
                        default:
                            reset();
                            break;
                    }
                }
            }
        });
        rb_pei_qu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (rb_pei_qu.isChecked()) {
                    reset();
                    switch (gameStyle) {
                        case 4:
                            ll_peiEdit_2.setVisibility(View.VISIBLE);
                            break;
                        case 5:
                            ll_peiEdit_3.setVisibility(View.VISIBLE);
                            break;
                        case 6:
                            ll_peiEdit_4.setVisibility(View.VISIBLE);
                            break;
                        default:
                            reset();
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
            case 1:
                rg_dingBasic.setVisibility(View.VISIBLE);
                rg_peiBasic.setVisibility(View.GONE);
                break;
            case 2:
                rg_dingBasic.setVisibility(View.VISIBLE);
                rg_peiBasic.setVisibility(View.GONE);
                break;
            case 3:
                rg_dingBasic.setVisibility(View.VISIBLE);
                rg_peiBasic.setVisibility(View.GONE);
                break;
            case 4:
                rg_dingBasic.setVisibility(View.GONE);
                rg_peiBasic.setVisibility(View.VISIBLE);
                break;
            case 5:
                rg_dingBasic.setVisibility(View.GONE);
                rg_peiBasic.setVisibility(View.VISIBLE);
                break;
            case 6:
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
        rg_dingBasic.clearCheck();
        rg_peiBasic.clearCheck();
        ll_dingPos.setVisibility(View.GONE);
        ll_dingEdit.setVisibility(View.GONE);
        ll_peiEdit_2.setVisibility(View.GONE);
        ll_peiEdit_3.setVisibility(View.GONE);
        ll_peiEdit_4.setVisibility(View.GONE);
        clearEditText();
    }

    public void clearEditText() {
        et_qian.setText("");
        et_bai.setText("");
        et_shi.setText("");
        et_ge.setText("");
        et_21.setText("");
        et_22.setText("");
        et_31.setText("");
        et_32.setText("");
        et_33.setText("");
        et_41.setText("");
        et_42.setText("");
        et_43.setText("");
        et_44.setText("");
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
