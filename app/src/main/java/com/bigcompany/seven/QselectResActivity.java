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

//    sbTmp.append("0369");
//    Log(sbTmp.toString());
//    Log(sbTmp.substring(0, 1));
//    for (int i = 0; i < sbTmp.length(); i++) {
//
//        erDingQian(sbTmp.substring(i, i + 1));
//
//    }


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

    //把數組放進list結構裡面使用
    public void sbToList(String s1, String s2, String s3, String s4) {
        sb.replace(0, 1, s1);
        sb.replace(1, 2, s2);
        sb.replace(2, 3, s3);
        sb.replace(3, 4, s4);

        list.add(sb.toString());
    }

    //把數組從list結構移除
    public void sbRemoveList(String s1, String s2, String s3, String s4) {
        sb.replace(0, 1, s1);
        sb.replace(1, 2, s2);
        sb.replace(2, 3, s3);
        sb.replace(3, 4, s4);

        list.remove(sb.toString());
    }

    //二字定全組合
    public void erDingAll() {
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

                        } else if (s1.equals("X") && s2.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s3.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s2.equals("X") && s3.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s2.equals("X") && s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s3.equals("X") && s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else {

                        }
                    }
                }
            }
        }
    }

    //二字定取千位
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

                    } else if (s3.equals("X") && s4.equals("X")) {
                        sbToList(s1, s2, s3, s4);
                    } else if (s2.equals("X") && s4.equals("X")) {
                        sbToList(s1, s2, s3, s4);
                    } else if (s2.equals("X") && s3.equals("X")) {
                        sbToList(s1, s2, s3, s4);
                    } else {

                    }
                }
            }
        }
    }

    //二字定取百位
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
                        sbToList(s1, s2, s3, s4);
                    } else if (s1.equals("X") && s4.equals("X")) {
                        sbToList(s1, s2, s3, s4);
                    } else if (s3.equals("X") && s4.equals("X")) {
                        sbToList(s1, s2, s3, s4);
                    } else {

                    }
                }
            }
        }
    }

    //二字定取十位
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
                        sbToList(s1, s2, s3, s4);
                    } else if (s1.equals("X") && s4.equals("X")) {
                        sbToList(s1, s2, s3, s4);
                    } else if (s2.equals("X") && s4.equals("X")) {
                        sbToList(s1, s2, s3, s4);
                    } else {

                    }
                }
            }
        }
    }

    //二字定取個位
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
                        sbToList(s1, s2, s3, s4);
                    } else if (s1.equals("X") && s3.equals("X")) {
                        sbToList(s1, s2, s3, s4);
                    } else if (s2.equals("X") && s3.equals("X")) {
                        sbToList(s1, s2, s3, s4);
                    } else {

                    }
                }
            }
        }
    }

    //二字定取千+百
    public void erDingQuQianBai(String s1, String s2) {
        String s3 = "X";
        String s4 = "X";
        sbToList(s1, s2, s3, s4);
    }

    //二字定取千+十
    public void erDingQuQianShi(String s1, String s3) {
        String s2 = "X";
        String s4 = "X";
        sbToList(s1, s2, s3, s4);
    }

    //二字定取千+個
    public void erDingQuQianGe(String s1, String s4) {
        String s2 = "X";
        String s3 = "X";
        sbToList(s1, s2, s3, s4);
    }

    //二字定取百+十
    public void erDingQuBaiShi(String s2, String s3) {
        String s1 = "X";
        String s4 = "X";
        sbToList(s1, s2, s3, s4);
    }

    //二字定取百+個
    public void erDingQuBaiGe(String s2, String s4) {
        String s1 = "X";
        String s3 = "X";
        sbToList(s1, s2, s3, s4);
    }

    //二字定取百+個
    public void erDingQuShiGe(String s3, String s4) {
        String s1 = "X";
        String s2 = "X";
        sbToList(s1, s2, s3, s4);
    }

    //二字定配一位
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
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s) && s2.equals("X") && s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s) && s3.equals("X") && s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s2.equals(s) && s1.equals("X") && s3.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s2.equals(s) && s1.equals("X") && s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s2.equals(s) && s3.equals("X") && s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s3.equals(s) && s1.equals("X") && s2.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s3.equals(s) && s1.equals("X") && s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s3.equals(s) && s2.equals("X") && s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s4.equals(s) && s1.equals("X") && s2.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s4.equals(s) && s1.equals("X") && s3.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s4.equals(s) && s2.equals("X") && s3.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else {

                        }
                    }
                }
            }
        }
    }

    //二字定配二位
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
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals("X") && s3.equals(s_2) && s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals("X") && s3.equals("X") && s4.equals(s_2)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals(s_1) && s3.equals("X") && s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s2.equals(s_1) && s3.equals(s_2) && s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s2.equals(s_1) && s3.equals("X") && s4.equals(s_2)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals("X") && s3.equals(s_1) && s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s2.equals(s_2) && s3.equals(s_1) && s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s2.equals("X") && s3.equals(s_1) && s4.equals(s_2)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals("X") && s3.equals("X") && s4.equals(s_1)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s2.equals(s_2) && s3.equals("X") && s4.equals(s_1)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s2.equals("X") && s3.equals(s_2) && s4.equals(s_1)) {
                            sbToList(s1, s2, s3, s4);
                        } else {

                        }
                    }
                }
            }
        }
    }

    //二字定除千位
    public void erDingChuQian(String s) {
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

                        } else if (s1.equals(s) && s3.equals("X") && s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s) && s2.equals("X") && s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s) && s2.equals("X") && s3.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s2.equals("X")) { //錯誤刪除
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s3.equals("X")) { //錯誤刪除
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s4.equals("X")) { //錯誤刪除
                            sbRemoveList(s1, s2, s3, s4);
                        } else {

                        }
                    }
                }
            }
        }
    }

    //二字定除百位
    public void erDingChuBai(String s) {
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

                        } else if (s2.equals(s) && s3.equals("X") && s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s2.equals(s) && s1.equals("X") && s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s2.equals(s) && s1.equals("X") && s3.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s2.equals("X") && s1.equals("X")) { //錯誤刪除
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s2.equals("X") && s3.equals("X")) { //錯誤刪除
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s2.equals("X") && s4.equals("X")) { //錯誤刪除
                            sbRemoveList(s1, s2, s3, s4);
                        } else {

                        }
                    }
                }
            }
        }
    }

    //二字定除十位
    public void erDingChuShi(String s) {
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

                        } else if (s3.equals(s) && s1.equals("X") && s2.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s3.equals(s) && s1.equals("X") && s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s3.equals(s) && s2.equals("X") && s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s3.equals("X") && s1.equals("X")) { //錯誤刪除
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s3.equals("X") && s2.equals("X")) { //錯誤刪除
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s3.equals("X") && s4.equals("X")) { //錯誤刪除
                            sbRemoveList(s1, s2, s3, s4);
                        } else {

                        }
                    }
                }
            }
        }
    }

    //二字定除個位
    public void erDingChuGe(String s) {
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

                        } else if (s4.equals(s) && s1.equals("X") && s2.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s4.equals(s) && s1.equals("X") && s3.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s4.equals(s) && s2.equals("X") && s3.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s4.equals("X") && s1.equals("X")) { //錯誤刪除
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s4.equals("X") && s2.equals("X")) { //錯誤刪除
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s4.equals("X") && s3.equals("X")) { //錯誤刪除
                            sbRemoveList(s1, s2, s3, s4);
                        } else {

                        }
                    }
                }
            }
        }
    }

    //二字定除千+百
    public void erDingChuQianBai(String s_1, String s_2) {
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
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s2.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s3.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s2.equals("X") && s1.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s2.equals("X") && s3.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s2.equals("X") && s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else {

                        }
                    }
                }
            }
        }
    }

    //二字定除千+十
    public void erDingChuQianShi(String s_1, String s_3) {
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

                        } else if (s1.equals(s_1) && s2.equals("X") && s3.equals(s_3) && s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s2.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s3.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s3.equals("X") && s1.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s3.equals("X") && s2.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s3.equals("X") && s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else {

                        }
                    }
                }
            }
        }
    }

    //二字定除千+個
    public void erDingChuQianGe(String s_1, String s_4) {
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

                        } else if (s1.equals(s_1) && s2.equals("X") && s3.equals("X") && s4.equals(s_4)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s2.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s3.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s4.equals("X") && s1.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s4.equals("X") && s2.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s4.equals("X") && s3.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else {

                        }
                    }
                }
            }
        }
    }

    //二字定除百+十
    public void erDingChuBaiShi(String s_2, String s_3) {
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

                        } else if (s1.equals("X") && s2.equals(s_2) && s3.equals(s_3) && s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s2.equals("X") && s1.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s2.equals("X") && s3.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s2.equals("X") && s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s3.equals("X") && s1.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s3.equals("X") && s2.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s3.equals("X") && s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else {

                        }
                    }
                }
            }
        }
    }

    //二字定除百+個
    public void erDingChuBaiGe(String s_2, String s_4) {
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

                        } else if (s1.equals("X") && s2.equals(s_2) && s3.equals("X") && s4.equals(s_4)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s2.equals("X") && s1.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s2.equals("X") && s3.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s2.equals("X") && s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s4.equals("X") && s1.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s4.equals("X") && s2.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s4.equals("X") && s3.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else {

                        }
                    }
                }
            }
        }
    }

    //二字定除十+個
    public void erDingChuShiGe(String s_3, String s_4) {
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

                        } else if (s1.equals("X") && s2.equals("X") && s3.equals(s_3) && s4.equals(s_4)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s3.equals("X") && s1.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s3.equals("X") && s2.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s3.equals("X") && s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s4.equals("X") && s1.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s4.equals("X") && s2.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s4.equals("X") && s3.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else {

                        }
                    }
                }
            }
        }
    }

    //三字定全組合
    public void sanDingAll() {

    }

    //四字定全組合
    public void siDingAll() {
        String s1, s2, s3, s4;
        for (int a = 0; a <= 9; a++) {
            for (int b = 0; b <= 9; b++) {
                for (int c = 0; c <= 9; c++) {
                    for (int d = 0; d <= 9; d++) {
                        s1 = String.valueOf(a);
                        s2 = String.valueOf(b);
                        s3 = String.valueOf(c);
                        s4 = String.valueOf(d);
                        sbToList(s1, s2, s3, s4);
                    }
                }
            }
        }
    }

    //二字現全組合
    public void erXianAll() {

    }

    //三字現全組合
    public void sanXianAll() {

    }

    //四字現全組合
    public void siXianAll() {

    }

    public void Toast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    public void Log(String s) {
        Log.i("troy", s);
    }
}
