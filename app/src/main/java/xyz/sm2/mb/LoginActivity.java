package xyz.sm2.mb;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private AutoCompleteTextView login_act;
    private Button btn_login, btn_agreement;
    private CheckBox check_agreement;
    private EditText login_pw;
    private String cookie;
    private String app_net;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initial();
    }

    public void initial() {
        app_net = getResources().getString(R.string.app_net);
        login_act = (AutoCompleteTextView) findViewById(R.id.login_act);
        login_pw = (EditText) findViewById(R.id.login_pw);
        check_agreement = (CheckBox) findViewById(R.id.check_agreement);
        btn_agreement = (Button) findViewById(R.id.btn_agreement);
        btn_login = (Button) findViewById(R.id.btn_login);

        btn_agreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, AgreementActivity.class));
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check_agreement.isChecked()) {
                    login();
                } else {
                    Toast("請先同意會員協議後方可登入");
                }
            }
        });
    }

    public void login() {
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                doLogin();
                Looper.loop();
            }
        }.start();
    }

    public void doLogin() {
        try {
            MultipartUtility_tw mu = new MultipartUtility_tw("http://" + app_net + "/ajax_login.php?action=LogApp");
            mu.postKeyValue("username", String.valueOf(login_act.getText()));
            mu.postKeyValue("password", String.valueOf(login_pw.getText()));
//            mu.postKeyValue("useragent", "mozilla/5.0 (windows nt 6.1; wow64; trident/7.0; slcc2; .net clr 2.0.50727; .net clr 3.5.30729; .net clr 3.0.30729; .net4.0c; .net4.0e; media center pc 6.0; infopath.3; rv:11.0) like gecko");
//            String cookie = mu.getCookie();
//            Log.i("troy", cookie);
//            String[] b = cookie.split("; ");
//            Log.i("troy", b[0]);
//            List<String> ret = mu.getHtml();
//            for (String line : ret) {
//                Log.i("troy", line);
//            }
            String serverID = mu.getCookie().split("; ")[0];
            Log(serverID);
            JSONObject jo = mu.getJSONObjectData();
            Log(jo.getString("status"));
            if (jo.getInt("status") == 200) {
                Log(jo.getString("msg"));
                Log(jo.getString("PHPSESSID"));
                cookie = "PHPSESSID=" + jo.getString("PHPSESSID") + "; " + serverID + "; path=/";
                Toast("成功登录");
                Intent it = new Intent(LoginActivity.this, MainActivity.class);
                it.putExtra("cookie", cookie);
                startActivity(it);
                finish();
            } else {
                Toast("账号和密码不匹配，请重新登录。");
            }
        } catch (Exception e) {
            Toast("無法與伺服器取得連線");
            Log(e.toString());
        }
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
                LoginActivity.this.finish();//關閉activity
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
