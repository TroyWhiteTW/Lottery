package com.bigcompany.seven;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

public class QselectResActivity extends AppCompatActivity {
    private String cookie;
    private StringBuffer sb, sb2, sbTmp;
    private TextView tv_qselectres;
    private TreeSet<String> list = new TreeSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qselect_res);

        Intent it = getIntent();
        cookie = it.getStringExtra("cookie");
        Log(cookie);

        initial();

//        sbTmp.append("0369");
//        Log(sbTmp.toString());
//        Log(sbTmp.substring(0, 1));
//        for (int i = 0; i < sbTmp.length(); i++) {
//
//            erDingQian(sbTmp.substring(i, i + 1));
//
//        }

        Log(String.valueOf(list.size()));
        for (String s : list) {
            sb2.append(s);
            sb2.append("\n");
        }
        tv_qselectres.setText(sb2.toString());
    }

    public void initial() {
        tv_qselectres = (TextView) findViewById(R.id.tv_qselectres);
        sb = new StringBuffer();
        sb2 = new StringBuffer();
        sbTmp = new StringBuffer();
        sb.setLength(4);
    }

    public void all() {
        String s1, s2, s3, s4;
        for (int a = 0; a <= 10; a++) {
            for (int b = 0; b <= 10; b++) {
                for (int c = 0; c <= 10; c++) {
                    for (int d = 0; d <= 10; d++) {
                        if (a == 10) {
                            s1 = "X";
                        } else {
                            s1 = String.valueOf(a);
                        }
                        if (b == 10) {
                            s2 = "X";
                        } else {
                            s2 = String.valueOf(b);
                        }
                        if (c == 10) {
                            s3 = "X";
                        } else {
                            s3 = String.valueOf(c);
                        }
                        if (d == 10) {
                            s4 = "X";
                        } else {
                            s4 = String.valueOf(d);
                        }

                        if (s1.equals("X") && s2.equals("X") && s3.equals("X") && s4.equals("X")) {

                        } else if (s2.equals("X") && s3.equals("X") && s4.equals("X")) {

                        } else if (s1.equals("X") && s3.equals("X") && s4.equals("X")) {

                        } else if (s1.equals("X") && s2.equals("X") && s4.equals("X")) {

                        } else if (s1.equals("X") && s2.equals("X") && s3.equals("X")) {

                        } else {
                            sb.replace(0, 1, s1);
                            sb.replace(1, 2, s2);
                            sb.replace(2, 3, s3);
                            sb.replace(3, 4, s4);

                            list.add(sb.toString());
                        }
                    }
                }
            }
        }
    }

    public void erDingQuQian(String s) {
        String s1, s2, s3, s4;
        s1 = s;
        for (int b = 0; b <= 10; b++) {
            for (int c = 0; c <= 10; c++) {
                for (int d = 0; d <= 10; d++) {

                    if (b == 10) {
                        s2 = "X";
                    } else {
                        s2 = String.valueOf(b);
                    }
                    if (c == 10) {
                        s3 = "X";
                    } else {
                        s3 = String.valueOf(c);
                    }
                    if (d == 10) {
                        s4 = "X";
                    } else {
                        s4 = String.valueOf(d);
                    }

                    if (s1.equals("X") && s2.equals("X") && s3.equals("X") && s4.equals("X")) {

                    } else if (s2.equals("X") && s3.equals("X") && s4.equals("X")) {

                    } else if (s1.equals("X") && s3.equals("X") && s4.equals("X")) {

                    } else if (s1.equals("X") && s2.equals("X") && s4.equals("X")) {

                    } else if (s1.equals("X") && s2.equals("X") && s3.equals("X")) {

                    } else if (s2.equals("X") && s3.equals("X") && s4.equals("X")) {

                    } else if (s3.equals("X") && s4.equals("X")) {
                        sb.replace(0, 1, s1);
                        sb.replace(1, 2, s2);
                        sb.replace(2, 3, s3);
                        sb.replace(3, 4, s4);

                        list.add(sb.toString());
                    } else if (s2.equals("X") && s4.equals("X")) {
                        sb.replace(0, 1, s1);
                        sb.replace(1, 2, s2);
                        sb.replace(2, 3, s3);
                        sb.replace(3, 4, s4);

                        list.add(sb.toString());
                    } else if (s2.equals("X") && s3.equals("X")) {
                        sb.replace(0, 1, s1);
                        sb.replace(1, 2, s2);
                        sb.replace(2, 3, s3);
                        sb.replace(3, 4, s4);

                        list.add(sb.toString());
                    } else {

                    }
                }
            }
        }
    }

    public void erDingQuBai(String s) {
        String s1, s2, s3, s4;
        s2 = s;
        for (int a = 0; a <= 10; a++) {
            for (int c = 0; c <= 10; c++) {
                for (int d = 0; d <= 10; d++) {
                    if (a == 10) {
                        s1 = "X";
                    } else {
                        s1 = String.valueOf(a);
                    }

                    if (c == 10) {
                        s3 = "X";
                    } else {
                        s3 = String.valueOf(c);
                    }
                    if (d == 10) {
                        s4 = "X";
                    } else {
                        s4 = String.valueOf(d);
                    }

                    if (s1.equals("X") && s2.equals("X") && s3.equals("X") && s4.equals("X")) {

                    } else if (s2.equals("X") && s3.equals("X") && s4.equals("X")) {

                    } else if (s1.equals("X") && s3.equals("X") && s4.equals("X")) {

                    } else if (s1.equals("X") && s2.equals("X") && s4.equals("X")) {

                    } else if (s1.equals("X") && s2.equals("X") && s3.equals("X")) {

                    } else if (s1.equals("X") && s3.equals("X")) {
                        sb.replace(0, 1, s1);
                        sb.replace(1, 2, s2);
                        sb.replace(2, 3, s3);
                        sb.replace(3, 4, s4);

                        list.add(sb.toString());
                    } else if (s1.equals("X") && s4.equals("X")) {
                        sb.replace(0, 1, s1);
                        sb.replace(1, 2, s2);
                        sb.replace(2, 3, s3);
                        sb.replace(3, 4, s4);

                        list.add(sb.toString());
                    } else if (s3.equals("X") && s4.equals("X")) {
                        sb.replace(0, 1, s1);
                        sb.replace(1, 2, s2);
                        sb.replace(2, 3, s3);
                        sb.replace(3, 4, s4);

                        list.add(sb.toString());
                    } else {

                    }
                }
            }
        }
    }

    public void erDingQuShi(String s) {
        String s1, s2, s3, s4;
        s3 = s;
        for (int a = 0; a <= 10; a++) {
            for (int b = 0; b <= 10; b++) {
                for (int d = 0; d <= 10; d++) {
                    if (a == 10) {
                        s1 = "X";
                    } else {
                        s1 = String.valueOf(a);
                    }
                    if (b == 10) {
                        s2 = "X";
                    } else {
                        s2 = String.valueOf(b);
                    }

                    if (d == 10) {
                        s4 = "X";
                    } else {
                        s4 = String.valueOf(d);
                    }

                    if (s1.equals("X") && s2.equals("X") && s3.equals("X") && s4.equals("X")) {

                    } else if (s2.equals("X") && s3.equals("X") && s4.equals("X")) {

                    } else if (s1.equals("X") && s3.equals("X") && s4.equals("X")) {

                    } else if (s1.equals("X") && s2.equals("X") && s4.equals("X")) {

                    } else if (s1.equals("X") && s2.equals("X") && s3.equals("X")) {

                    } else if (s1.equals("X") && s2.equals("X")) {
                        sb.replace(0, 1, s1);
                        sb.replace(1, 2, s2);
                        sb.replace(2, 3, s3);
                        sb.replace(3, 4, s4);

                        list.add(sb.toString());
                    } else if (s1.equals("X") && s4.equals("X")) {
                        sb.replace(0, 1, s1);
                        sb.replace(1, 2, s2);
                        sb.replace(2, 3, s3);
                        sb.replace(3, 4, s4);

                        list.add(sb.toString());
                    } else if (s2.equals("X") && s4.equals("X")) {
                        sb.replace(0, 1, s1);
                        sb.replace(1, 2, s2);
                        sb.replace(2, 3, s3);
                        sb.replace(3, 4, s4);

                        list.add(sb.toString());
                    } else {

                    }
                }
            }
        }
    }

    public void erDingQuGe(String s) {
        String s1, s2, s3, s4;
        s4 = s;
        for (int a = 0; a <= 10; a++) {
            for (int b = 0; b <= 10; b++) {
                for (int c = 0; c <= 10; c++) {
                    if (a == 10) {
                        s1 = "X";
                    } else {
                        s1 = String.valueOf(a);
                    }
                    if (b == 10) {
                        s2 = "X";
                    } else {
                        s2 = String.valueOf(b);
                    }
                    if (c == 10) {
                        s3 = "X";
                    } else {
                        s3 = String.valueOf(c);
                    }

                    if (s1.equals("X") && s2.equals("X") && s3.equals("X") && s4.equals("X")) {

                    } else if (s2.equals("X") && s3.equals("X") && s4.equals("X")) {

                    } else if (s1.equals("X") && s3.equals("X") && s4.equals("X")) {

                    } else if (s1.equals("X") && s2.equals("X") && s4.equals("X")) {

                    } else if (s1.equals("X") && s2.equals("X") && s3.equals("X")) {

                    } else if (s1.equals("X") && s2.equals("X")) {
                        sb.replace(0, 1, s1);
                        sb.replace(1, 2, s2);
                        sb.replace(2, 3, s3);
                        sb.replace(3, 4, s4);

                        list.add(sb.toString());
                    } else if (s1.equals("X") && s3.equals("X")) {
                        sb.replace(0, 1, s1);
                        sb.replace(1, 2, s2);
                        sb.replace(2, 3, s3);
                        sb.replace(3, 4, s4);

                        list.add(sb.toString());
                    } else if (s2.equals("X") && s3.equals("X")) {
                        sb.replace(0, 1, s1);
                        sb.replace(1, 2, s2);
                        sb.replace(2, 3, s3);
                        sb.replace(3, 4, s4);

                        list.add(sb.toString());
                    } else {

                    }
                }
            }
        }
    }

    public void erDingQuQianBai(String s1, String s2) {
        String s3 = "X";
        String s4 = "X";
        sb.replace(0, 1, s1);
        sb.replace(1, 2, s2);
        sb.replace(2, 3, s3);
        sb.replace(3, 4, s4);

        list.add(sb.toString());
    }

    public void erDingQuBaiShi(String s2, String s3) {
        String s1 = "X";
        String s4 = "X";
        sb.replace(0, 1, s1);
        sb.replace(1, 2, s2);
        sb.replace(2, 3, s3);
        sb.replace(3, 4, s4);

        list.add(sb.toString());
    }

    public void erDingQuShiGe(String s3, String s4) {
        String s1 = "X";
        String s2 = "X";
        sb.replace(0, 1, s1);
        sb.replace(1, 2, s2);
        sb.replace(2, 3, s3);
        sb.replace(3, 4, s4);

        list.add(sb.toString());
    }

    public void erDingQuQianGe(String s1, String s4) {
        String s2 = "X";
        String s3 = "X";
        sb.replace(0, 1, s1);
        sb.replace(1, 2, s2);
        sb.replace(2, 3, s3);
        sb.replace(3, 4, s4);

        list.add(sb.toString());
    }

    public void erPeiQu_1(String s) {
        String s1, s2, s3, s4;
        for (int a = 0; a <= 10; a++) {
            for (int b = 0; b <= 10; b++) {
                for (int c = 0; c <= 10; c++) {
                    for (int d = 0; d <= 10; d++) {
                        if (a == 10) {
                            s1 = "X";
                        } else {
                            s1 = String.valueOf(a);
                        }
                        if (b == 10) {
                            s2 = "X";
                        } else {
                            s2 = String.valueOf(b);
                        }
                        if (c == 10) {
                            s3 = "X";
                        } else {
                            s3 = String.valueOf(c);
                        }
                        if (d == 10) {
                            s4 = "X";
                        } else {
                            s4 = String.valueOf(d);
                        }

                        if (s1.equals("X") && s2.equals("X") && s3.equals("X") && s4.equals("X")) {

                        } else if (s2.equals("X") && s3.equals("X") && s4.equals("X")) {

                        } else if (s1.equals("X") && s3.equals("X") && s4.equals("X")) {

                        } else if (s1.equals("X") && s2.equals("X") && s4.equals("X")) {

                        } else if (s1.equals("X") && s2.equals("X") && s3.equals("X")) {

                        } else if (s1.equals(s) && s2.equals("X") && s3.equals("X")) {
                            sb.replace(0, 1, s1);
                            sb.replace(1, 2, s2);
                            sb.replace(2, 3, s3);
                            sb.replace(3, 4, s4);

                            list.add(sb.toString());
                        } else if (s1.equals(s) && s2.equals("X") && s4.equals("X")) {
                            sb.replace(0, 1, s1);
                            sb.replace(1, 2, s2);
                            sb.replace(2, 3, s3);
                            sb.replace(3, 4, s4);

                            list.add(sb.toString());
                        } else if (s1.equals(s) && s3.equals("X") && s4.equals("X")) {
                            sb.replace(0, 1, s1);
                            sb.replace(1, 2, s2);
                            sb.replace(2, 3, s3);
                            sb.replace(3, 4, s4);

                            list.add(sb.toString());
                        } else if (s2.equals(s) && s1.equals("X") && s3.equals("X")) {
                            sb.replace(0, 1, s1);
                            sb.replace(1, 2, s2);
                            sb.replace(2, 3, s3);
                            sb.replace(3, 4, s4);

                            list.add(sb.toString());
                        } else if (s2.equals(s) && s1.equals("X") && s4.equals("X")) {
                            sb.replace(0, 1, s1);
                            sb.replace(1, 2, s2);
                            sb.replace(2, 3, s3);
                            sb.replace(3, 4, s4);

                            list.add(sb.toString());
                        } else if (s2.equals(s) && s3.equals("X") && s4.equals("X")) {
                            sb.replace(0, 1, s1);
                            sb.replace(1, 2, s2);
                            sb.replace(2, 3, s3);
                            sb.replace(3, 4, s4);

                            list.add(sb.toString());
                        } else if (s3.equals(s) && s1.equals("X") && s2.equals("X")) {
                            sb.replace(0, 1, s1);
                            sb.replace(1, 2, s2);
                            sb.replace(2, 3, s3);
                            sb.replace(3, 4, s4);

                            list.add(sb.toString());
                        } else if (s3.equals(s) && s1.equals("X") && s4.equals("X")) {
                            sb.replace(0, 1, s1);
                            sb.replace(1, 2, s2);
                            sb.replace(2, 3, s3);
                            sb.replace(3, 4, s4);

                            list.add(sb.toString());
                        } else if (s3.equals(s) && s2.equals("X") && s4.equals("X")) {
                            sb.replace(0, 1, s1);
                            sb.replace(1, 2, s2);
                            sb.replace(2, 3, s3);
                            sb.replace(3, 4, s4);

                            list.add(sb.toString());
                        } else if (s4.equals(s) && s1.equals("X") && s2.equals("X")) {
                            sb.replace(0, 1, s1);
                            sb.replace(1, 2, s2);
                            sb.replace(2, 3, s3);
                            sb.replace(3, 4, s4);

                            list.add(sb.toString());
                        } else if (s4.equals(s) && s1.equals("X") && s3.equals("X")) {
                            sb.replace(0, 1, s1);
                            sb.replace(1, 2, s2);
                            sb.replace(2, 3, s3);
                            sb.replace(3, 4, s4);

                            list.add(sb.toString());
                        } else if (s4.equals(s) && s2.equals("X") && s3.equals("X")) {
                            sb.replace(0, 1, s1);
                            sb.replace(1, 2, s2);
                            sb.replace(2, 3, s3);
                            sb.replace(3, 4, s4);

                            list.add(sb.toString());
                        } else {

                        }
                    }
                }
            }
        }
    }

    public void erPeiQu_2(String s_1, String s_2) {
        String s1, s2, s3, s4;
        for (int a = 0; a <= 10; a++) {
            for (int b = 0; b <= 10; b++) {
                for (int c = 0; c <= 10; c++) {
                    for (int d = 0; d <= 10; d++) {
                        if (a == 10) {
                            s1 = "X";
                        } else {
                            s1 = String.valueOf(a);
                        }
                        if (b == 10) {
                            s2 = "X";
                        } else {
                            s2 = String.valueOf(b);
                        }
                        if (c == 10) {
                            s3 = "X";
                        } else {
                            s3 = String.valueOf(c);
                        }
                        if (d == 10) {
                            s4 = "X";
                        } else {
                            s4 = String.valueOf(d);
                        }

                        if (s1.equals("X") && s2.equals("X") && s3.equals("X") && s4.equals("X")) {

                        } else if (s2.equals("X") && s3.equals("X") && s4.equals("X")) {

                        } else if (s1.equals("X") && s3.equals("X") && s4.equals("X")) {

                        } else if (s1.equals("X") && s2.equals("X") && s4.equals("X")) {

                        } else if (s1.equals("X") && s2.equals("X") && s3.equals("X")) {

                        } else if (s1.equals(s_1) && s2.equals(s_2) && s3.equals("X") && s4.equals("X")) {
                            sb.replace(0, 1, s1);
                            sb.replace(1, 2, s2);
                            sb.replace(2, 3, s3);
                            sb.replace(3, 4, s4);

                            list.add(sb.toString());
                        } else if (s1.equals(s_1) && s2.equals("X") && s3.equals(s_2) && s4.equals("X")) {
                            sb.replace(0, 1, s1);
                            sb.replace(1, 2, s2);
                            sb.replace(2, 3, s3);
                            sb.replace(3, 4, s4);

                            list.add(sb.toString());
                        } else if (s1.equals(s_1) && s2.equals("X") && s3.equals("X") && s4.equals(s_2)) {
                            sb.replace(0, 1, s1);
                            sb.replace(1, 2, s2);
                            sb.replace(2, 3, s3);
                            sb.replace(3, 4, s4);

                            list.add(sb.toString());
                        } else if (s1.equals(s_2) && s2.equals(s_1) && s3.equals("X") && s4.equals("X")) {
                            sb.replace(0, 1, s1);
                            sb.replace(1, 2, s2);
                            sb.replace(2, 3, s3);
                            sb.replace(3, 4, s4);

                            list.add(sb.toString());
                        } else if (s1.equals("X") && s2.equals(s_1) && s3.equals(s_2) && s4.equals("X")) {
                            sb.replace(0, 1, s1);
                            sb.replace(1, 2, s2);
                            sb.replace(2, 3, s3);
                            sb.replace(3, 4, s4);

                            list.add(sb.toString());
                        } else if (s1.equals("X") && s2.equals(s_1) && s3.equals("X") && s4.equals(s_2)) {
                            sb.replace(0, 1, s1);
                            sb.replace(1, 2, s2);
                            sb.replace(2, 3, s3);
                            sb.replace(3, 4, s4);

                            list.add(sb.toString());
                        } else if (s1.equals(s_2) && s2.equals("X") && s3.equals(s_1) && s4.equals("X")) {
                            sb.replace(0, 1, s1);
                            sb.replace(1, 2, s2);
                            sb.replace(2, 3, s3);
                            sb.replace(3, 4, s4);

                            list.add(sb.toString());
                        } else if (s1.equals("X") && s2.equals(s_2) && s3.equals(s_1) && s4.equals("X")) {
                            sb.replace(0, 1, s1);
                            sb.replace(1, 2, s2);
                            sb.replace(2, 3, s3);
                            sb.replace(3, 4, s4);

                            list.add(sb.toString());
                        } else if (s1.equals("X") && s2.equals("X") && s3.equals(s_1) && s4.equals(s_2)) {
                            sb.replace(0, 1, s1);
                            sb.replace(1, 2, s2);
                            sb.replace(2, 3, s3);
                            sb.replace(3, 4, s4);

                            list.add(sb.toString());
                        } else if (s1.equals(s_2) && s2.equals("X") && s3.equals("X") && s4.equals(s_1)) {
                            sb.replace(0, 1, s1);
                            sb.replace(1, 2, s2);
                            sb.replace(2, 3, s3);
                            sb.replace(3, 4, s4);

                            list.add(sb.toString());
                        } else if (s1.equals("X") && s2.equals(s_2) && s3.equals("X") && s4.equals(s_1)) {
                            sb.replace(0, 1, s1);
                            sb.replace(1, 2, s2);
                            sb.replace(2, 3, s3);
                            sb.replace(3, 4, s4);

                            list.add(sb.toString());
                        } else if (s1.equals("X") && s2.equals("X") && s3.equals(s_2) && s4.equals(s_1)) {
                            sb.replace(0, 1, s1);
                            sb.replace(1, 2, s2);
                            sb.replace(2, 3, s3);
                            sb.replace(3, 4, s4);

                            list.add(sb.toString());
                        } else {

                        }
                    }
                }
            }
        }
    }

    public void Toast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    public void Log(String s) {
        Log.i("troy", s);
    }
}
