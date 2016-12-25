package com.bigcompany.seven;

import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class QselectResActivity extends AppCompatActivity {
    private ArrayList<String> al, al2, al3;
    private Button btn_sendGameSet;
    private EditText et_perMoney;
    private int gameStyle;//classID: 1=二定位; 2=三定位; 3=四定位; 4=二字現; 5=三字現; 6=四字現
    private int gameSet;
    private String et_qian, et_bai, et_shi, et_ge, et_21, et_22, et_31, et_32, et_33, et_41, et_42, et_43, et_44;
    private String cookie;
    private String app_net;
    private String selectlogs;
    private StringBuffer sb, sb2, sb3;
    private StringBuffer sb_et_qian, sb_et_bai, sb_et_shi, sb_et_ge,
            sb_et_21, sb_et_22,
            sb_et_31, sb_et_32, sb_et_33,
            sb_et_41, sb_et_42, sb_et_43, sb_et_44;
    private TextView tv_qselectres, tv_howMany, tv_totalMoney;
    private TreeSet<String> list = new TreeSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qselect_res);

        app_net = getResources().getString(R.string.app_net);

        Intent it = getIntent();
        cookie = it.getStringExtra("cookie");
        gameStyle = it.getIntExtra("gameStyle", 0);
        gameSet = it.getIntExtra("gameSet", 0);
        et_qian = it.getStringExtra("et_qian");
        et_bai = it.getStringExtra("et_bai");
        et_shi = it.getStringExtra("et_shi");
        et_ge = it.getStringExtra("et_ge");
        et_21 = it.getStringExtra("et_21");
        et_22 = it.getStringExtra("et_22");
        et_31 = it.getStringExtra("et_31");
        et_32 = it.getStringExtra("et_32");
        et_33 = it.getStringExtra("et_33");
        et_41 = it.getStringExtra("et_41");
        et_42 = it.getStringExtra("et_42");
        et_43 = it.getStringExtra("et_43");
        et_44 = it.getStringExtra("et_44");
//        Log(cookie);
//        Log("et_qian=" + et_qian);
//        Log("et_bai=" + et_bai);
//        Log("et_shi=" + et_shi);
//        Log("et_ge=" + et_ge);
//        Log("et_21=" + et_21);
//        Log("et_22=" + et_22);
//        Log("et_31=" + et_31);
//        Log("et_32=" + et_32);
//        Log("et_33=" + et_33);
//        Log("et_41=" + et_41);
//        Log("et_42=" + et_42);
//        Log("et_43=" + et_43);
//        Log("et_44=" + et_44);

        initial();

        gameSet();

        tv_howMany.setText("筆數：" + list.size());
        for (String s : list) {
            sb2.append(s);
            sb2.append("\n");
        }
        for (String s : list) {
            sb3.append(s);
            sb3.append(",");
        }
        tv_qselectres.setText(sb2.toString());
    }

    public void initial() {
        sb = new StringBuffer();
        sb2 = new StringBuffer();
        sb3 = new StringBuffer();
        sb_et_qian = new StringBuffer();
        sb_et_bai = new StringBuffer();
        sb_et_shi = new StringBuffer();
        sb_et_ge = new StringBuffer();
        sb_et_21 = new StringBuffer();
        sb_et_22 = new StringBuffer();
        sb_et_31 = new StringBuffer();
        sb_et_32 = new StringBuffer();
        sb_et_33 = new StringBuffer();
        sb_et_41 = new StringBuffer();
        sb_et_42 = new StringBuffer();
        sb_et_43 = new StringBuffer();
        sb_et_44 = new StringBuffer();

        btn_sendGameSet = (Button) findViewById(R.id.btn_sendGameSet);
        et_perMoney = (EditText) findViewById(R.id.et_perMoney);
        tv_qselectres = (TextView) findViewById(R.id.tv_qselectres);
        tv_howMany = (TextView) findViewById(R.id.tv_howMany);
        tv_totalMoney = (TextView) findViewById(R.id.tv_totalMoney);

        sb.setLength(4);
        sb_et_qian.append(et_qian);
        sb_et_bai.append(et_bai);
        sb_et_shi.append(et_shi);
        sb_et_ge.append(et_ge);
        sb_et_21.append(et_21);
        sb_et_22.append(et_22);
        sb_et_31.append(et_31);
        sb_et_32.append(et_32);
        sb_et_33.append(et_33);
        sb_et_41.append(et_41);
        sb_et_42.append(et_42);
        sb_et_43.append(et_43);
        sb_et_44.append(et_44);

        TreeMap<Integer, String> treeMap = new TreeMap<>();
        for (int i = 0; i < sb_et_qian.length(); i++) {
            treeMap.put(i, sb_et_qian.substring(i, i + 1));
        }
        ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(treeMap.entrySet());
        Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
            @Override
            public int compare(Map.Entry o1, Map.Entry o2) {
                return ((String) o1.getValue()).compareTo((String) o2.getValue());
            }
        };
        Collections.sort(entryList, sortByValue);
        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
        for (Map.Entry e : entryList)
            linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
        Log("linkedHashMap = " + linkedHashMap.toString());

        al2 = new ArrayList<>(linkedHashMap.values());
        for (int i = 0; i < al2.size(); i++) {
            Log("al2[" + i + "] = " + al2.get(i));
        }

        TreeMap<Integer, String> treeMap2 = new TreeMap<>();
        for (int i = 0; i < sb_et_bai.length(); i++) {
            treeMap2.put(i, sb_et_bai.substring(i, i + 1));
        }
        ArrayList<Map.Entry<Integer, String>> entryList2 = new ArrayList<>(treeMap2.entrySet());
        Comparator<Map.Entry> sortByValue2 = new Comparator<Map.Entry>() {
            @Override
            public int compare(Map.Entry o1, Map.Entry o2) {
                return ((String) o1.getValue()).compareTo((String) o2.getValue());
            }
        };
        Collections.sort(entryList2, sortByValue2);
        LinkedHashMap<Integer, String> linkedHashMap2 = new LinkedHashMap<>();
        for (Map.Entry e : entryList2)
            linkedHashMap2.put((Integer) e.getKey(), (String) e.getValue());
        Log("linkedHashMap2 = " + linkedHashMap2.toString());

        al3 = new ArrayList<>(linkedHashMap2.values());
        for (int i = 0; i < al3.size(); i++) {
            Log("al3[" + i + "] = " + al3.get(i));
        }

        btn_sendGameSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendGameSet();
            }
        });
    }

    public void sendGameSet() {
        switch (gameStyle) {
            case 1:
                switch (gameSet) {
                    case 1:
                        selectlogs = "1,0," +
                                et_qian +
                                "," +
                                et_bai +
                                "," +
                                et_shi +
                                "," +
                                et_ge +
                                ",|0,1,0,0,0,0,,0,0,0,0,,0,0,0,0,,0,0,0,0,,|0,0,,||,,,|0,0,0,0,|0,0,,,|0,0,||||0,0,|||0,0,,,,|0,0,0,0,0,0,|0,0,0,0,0,0,|||";
                        break;
                    case 2:
                        selectlogs = "0,1," +
                                et_qian +
                                "," +
                                et_bai +
                                "," +
                                et_shi +
                                "," +
                                et_ge +
                                ",|0,1,0,0,0,0,,0,0,0,0,,0,0,0,0,,0,0,0,0,,|0,0,,||,,,|0,0,0,0,|0,0,,,|0,0,||||0,0,|||0,0,,,,|0,0,0,0,0,0,|0,0,0,0,0,0,|||";
                        break;
                    case 3:
                        selectlogs = "|0,1,0,0,0,0,,0,0,0,0,,0,0,0,0,,0,0,0,0,,|0,0,,||,,,|0,0,0,0,|0,0,,,|0,0,||||0,0,|||0,0,,,,|0,0,0,0,0,0,|0,0,0,0,0,0,|" +
                                "1,0," +
                                et_21 +
                                "," +
                                et_22 +
                                ",,,0,0,0,0,||";
                        break;
                    case 4:
                        selectlogs = "|0,1,0,0,0,0,,0,0,0,0,,0,0,0,0,,0,0,0,0,,|0,0,,||,,,|0,0,0,0,|0,0,,,|0,0,||||0,0,|||0,0,,,,|0,0,0,0,0,0,|0,0,0,0,0,0,|" +
                                "0,1," +
                                et_21 +
                                "," +
                                et_22 +
                                ",,,0,0,0,0,||";
                        break;
                    default:
                        break;
                }
                break;
            case 2:
                switch (gameSet) {
                    case 1:
                        selectlogs = "1,0," +
                                et_qian +
                                "," +
                                et_bai +
                                "," +
                                et_shi +
                                "," +
                                et_ge +
                                ",|0,1,0,0,0,0,,0,0,0,0,,0,0,0,0,,0,0,0,0,,|0,0,,||,,,|0,0,0,0,|0,0,,,|0,0,||||0,0,|||0,0,,,,|0,0,0,0,0,0,|0,0,0,0,0,0,|||";
                        break;
                    case 2:
                        selectlogs = "0,1," +
                                et_qian +
                                "," +
                                et_bai +
                                "," +
                                et_shi +
                                "," +
                                et_ge +
                                ",|0,1,0,0,0,0,,0,0,0,0,,0,0,0,0,,0,0,0,0,,|0,0,,||,,,|0,0,0,0,|0,0,,,|0,0,||||0,0,|||0,0,,,,|0,0,0,0,0,0,|0,0,0,0,0,0,|||";
                        break;
                    case 3:
                        selectlogs = "|0,1,0,0,0,0,,0,0,0,0,,0,0,0,0,,0,0,0,0,,|0,0,,||,,,|0,0,0,0,|0,0,,,|0,0,||||0,0,|||0,0,,,,|0,0,0,0,0,0,|0,0,0,0,0,0,|" +
                                "1,0," +
                                et_31 +
                                "," +
                                et_32 +
                                "," +
                                et_33 +
                                ",,0,0,0,0,||";
                        break;
                    case 4:
                        selectlogs = "|0,1,0,0,0,0,,0,0,0,0,,0,0,0,0,,0,0,0,0,,|0,0,,||,,,|0,0,0,0,|0,0,,,|0,0,||||0,0,|||0,0,,,,|0,0,0,0,0,0,|0,0,0,0,0,0,|" +
                                "0,1," +
                                et_31 +
                                "," +
                                et_32 +
                                "," +
                                et_33 +
                                ",,0,0,0,0,||";
                        break;
                    default:
                        break;
                }
                break;
            case 3:
                switch (gameSet) {
                    case 1:
                        selectlogs = "1,0," +
                                et_qian +
                                "," +
                                et_bai +
                                "," +
                                et_shi +
                                "," +
                                et_ge +
                                ",|0,1,0,0,0,0,,0,0,0,0,,0,0,0,0,,0,0,0,0,,|0,0,,||,,,|0,0,0,0,|0,0,,,|0,0,||||0,0,|||0,0,,,,|0,0,0,0,0,0,|0,0,0,0,0,0,|||";
                        break;
                    case 2:
                        selectlogs = "0,1," +
                                et_qian +
                                "," +
                                et_bai +
                                "," +
                                et_shi +
                                "," +
                                et_ge +
                                ",|0,1,0,0,0,0,,0,0,0,0,,0,0,0,0,,0,0,0,0,,|0,0,,||,,,|0,0,0,0,|0,0,,,|0,0,||||0,0,|||0,0,,,,|0,0,0,0,0,0,|0,0,0,0,0,0,|||";
                        break;
                    case 3:
                        selectlogs = "|0,1,0,0,0,0,,0,0,0,0,,0,0,0,0,,0,0,0,0,,|0,0,,||,,,|0,0,0,0,|0,0,,,|0,0,||||0,0,|||0,0,,,,|0,0,0,0,0,0,|0,0,0,0,0,0,|" +
                                "1,0," +
                                et_41 +
                                "," +
                                et_42 +
                                "," +
                                et_43 +
                                "," +
                                et_44 +
                                ",0,0,0,0,||";
                        break;
                    case 4:
                        selectlogs = "|0,1,0,0,0,0,,0,0,0,0,,0,0,0,0,,0,0,0,0,,|0,0,,||,,,|0,0,0,0,|0,0,,,|0,0,||||0,0,|||0,0,,,,|0,0,0,0,0,0,|0,0,0,0,0,0,|" +
                                "0,1," +
                                et_41 +
                                "," +
                                et_42 +
                                "," +
                                et_43 +
                                "," +
                                et_44 +
                                ",0,0,0,0,||";
                        break;
                    default:
                        break;
                }
                break;
            case 4:
                switch (gameSet) {
                    case 5:
                        selectlogs = "||0,0,,||||0,0,,,|0,0,||||0,0,|||0,0,,,,|0,0,0,0,|0,0,0,0,|" +
                                "1,0," +
                                et_21 +
                                "," +
                                et_22 +
                                ",,,0,0,0,0,||";
                        break;
                    case 6:
                        selectlogs = "||0,0,,||||0,0,,,|0,0,||||0,0,|||0,0,,,,|0,0,0,0,|0,0,0,0,|" +
                                "0,1," +
                                et_21 +
                                "," +
                                et_22 +
                                ",,,0,0,0,0,||";
                        break;
                    default:
                        break;
                }
                break;
            case 5:
                switch (gameSet) {
                    case 5:
                        selectlogs = "||0,0,,||||0,0,,,|0,0,||||0,0,|||0,0,,,,|0,0,0,0,|0,0,0,0,|" +
                                "1,0," +
                                et_31 +
                                "," +
                                et_32 +
                                "," +
                                et_33 +
                                ",,0,0,0,0,||";
                        break;
                    case 6:
                        selectlogs = "||0,0,,||||0,0,,,|0,0,||||0,0,|||0,0,,,,|0,0,0,0,|0,0,0,0,|" +
                                "0,1," +
                                et_31 +
                                "," +
                                et_32 +
                                "," +
                                et_33 +
                                ",,0,0,0,0,||";
                        break;
                    default:
                        break;
                }
                break;
            case 6:
                switch (gameSet) {
                    case 5:
                        selectlogs = "||0,0,,||||0,0,,,|0,0,||||0,0,|||0,0,,,,|0,0,0,0,|0,0,0,0,|" +
                                "1,0," +
                                et_41 +
                                "," +
                                et_42 +
                                "," +
                                et_43 +
                                "," +
                                et_44 +
                                ",0,0,0,0,||";
                        break;
                    case 6:
                        selectlogs = "||0,0,,||||0,0,,,|0,0,||||0,0,|||0,0,,,,|0,0,0,0,|0,0,0,0,|" +
                                "0,1," +
                                et_41 +
                                "," +
                                et_42 +
                                "," +
                                et_43 +
                                "," +
                                et_44 +
                                ",0,0,0,0,||";
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        Log(selectlogs);
        Log(et_perMoney.getText().toString());
        Log(sb3.toString());
        Log(String.valueOf(gameStyle));
        sendData();
    }

    public void sendData() {
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                doSendData();
                Looper.loop();
            }
        }.start();
    }

    public void doSendData() {
        try {
            MultipartUtility_tw mu = new MultipartUtility_tw("http://" + app_net + "/mobile/wap_ajax.php?action=app_soonselect");
            mu.sendCookie(cookie);
            mu.postKeyValue("post_money", et_perMoney.getText().toString());
            mu.postKeyValue("post_number_money", sb3.toString());
            mu.postKeyValue("selectlogsclassid", String.valueOf(gameStyle));
            mu.postKeyValue("selectlogs", selectlogs);
            List<String> ret = mu.getHtml();
            for (String line : ret) {
                Log(line);
            }
            Toast("下注完成");
            finish();
        } catch (Exception e) {
            Toast("無法與伺服器取得連線");
            Log(e.toString());
        }
        if (!et_perMoney.getText().toString().equals("")) {

        } else {
            Toast("未輸入金額");
        }
    }

    public void sortEditTextString_2() {
        HashMap<Integer, String> hashMap = new HashMap<>();
        if (!et_21.equals("")) hashMap.put(0, et_21);
        if (!et_22.equals("")) hashMap.put(1, et_22);
        ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(hashMap.entrySet());
        Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
            @Override
            public int compare(Map.Entry o1, Map.Entry o2) {
                return ((String) o1.getValue()).compareTo((String) o2.getValue());
            }
        };
        Collections.sort(entryList, sortByValue);
        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
        for (Map.Entry e : entryList)
            linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
        Log("linkedHashMap = " + linkedHashMap.toString());

        al = new ArrayList<>(linkedHashMap.values());
        for (int i = 0; i < linkedHashMap.size(); i++) {
            Log("al[" + i + "] = " + al.get(i));
        }
    }

    public void sortEditTextString_3() {
        HashMap<Integer, String> hashMap = new HashMap<>();
        if (!et_31.equals("")) hashMap.put(0, et_31);
        if (!et_32.equals("")) hashMap.put(1, et_32);
        if (!et_33.equals("")) hashMap.put(2, et_33);
        ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(hashMap.entrySet());
        Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
            @Override
            public int compare(Map.Entry o1, Map.Entry o2) {
                return ((String) o1.getValue()).compareTo((String) o2.getValue());
            }
        };
        Collections.sort(entryList, sortByValue);
        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
        for (Map.Entry e : entryList)
            linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
        Log("linkedHashMap = " + linkedHashMap.toString());

        al = new ArrayList<>(linkedHashMap.values());
        for (int i = 0; i < linkedHashMap.size(); i++) {
            Log("al[" + i + "] = " + al.get(i));
        }
    }

    public void sortEditTextString_4() {
        HashMap<Integer, String> hashMap = new HashMap<>();
        if (!et_41.equals("")) hashMap.put(0, et_41);
        if (!et_42.equals("")) hashMap.put(1, et_42);
        if (!et_43.equals("")) hashMap.put(2, et_43);
        if (!et_44.equals("")) hashMap.put(3, et_44);
        ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(hashMap.entrySet());
        Comparator<Map.Entry> sortByValue = new Comparator<Map.Entry>() {
            @Override
            public int compare(Map.Entry o1, Map.Entry o2) {
                return ((String) o1.getValue()).compareTo((String) o2.getValue());
            }
        };
        Collections.sort(entryList, sortByValue);
        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
        for (Map.Entry e : entryList)
            linkedHashMap.put((Integer) e.getKey(), (String) e.getValue());
        Log("linkedHashMap = " + linkedHashMap.toString());

        al = new ArrayList<>(linkedHashMap.values());
        for (int i = 0; i < linkedHashMap.size(); i++) {
            Log("al[" + i + "] = " + al.get(i));
        }
    }

    public void gameSet() {
        switch (gameStyle) {
            case 1:
                switch (gameSet) {
                    case 1:
                        erDingAll();
                        if (!et_qian.isEmpty() && et_bai.isEmpty() && et_shi.isEmpty() && et_ge.isEmpty())
                            erDingChuQian(et_qian);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && et_ge.isEmpty())
                            erDingChuBai(et_bai);
                        if (et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            erDingChuShi(et_shi);
                        if (et_qian.isEmpty() && et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            erDingChuGe(et_ge);

                        if (!et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && et_ge.isEmpty())
                            erDingChuQianBai(et_qian, et_bai);
                        if (!et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            erDingChuQianShi(et_qian, et_shi);
                        if (!et_qian.isEmpty() && et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            erDingChuQianGe(et_qian, et_ge);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            erDingChuBaiShi(et_bai, et_shi);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            erDingChuBaiGe(et_bai, et_ge);
                        if (et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && !et_ge.isEmpty())
                            erDingChuShiGe(et_shi, et_ge);
                        break;
                    case 2:
                        if (!et_qian.isEmpty() && et_bai.isEmpty() && et_shi.isEmpty() && et_ge.isEmpty())
                            erDingQuQian(et_qian);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && et_ge.isEmpty())
                            erDingQuBai(et_bai);
                        if (et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            erDingQuShi(et_shi);
                        if (et_qian.isEmpty() && et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            erDingQuGe(et_ge);

                        if (!et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && et_ge.isEmpty())
                            for (int i = 0; i < al2.size(); i++) {
                                for (int j = 0; j < al3.size(); j++) {
                                    erDingQuQianBai(al2.get(i), al3.get(j));
                                }
                            }
//                            erDingQuQianBai(et_qian, et_bai);
                        if (!et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            erDingQuQianShi(et_qian, et_shi);
                        if (!et_qian.isEmpty() && et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            erDingQuQianGe(et_qian, et_ge);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            erDingQuBaiShi(et_bai, et_shi);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            erDingQuBaiGe(et_bai, et_ge);
                        if (et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && !et_ge.isEmpty())
                            erDingQuShiGe(et_shi, et_ge);
                        break;
                    case 3:
                        sortEditTextString_2();
                        erDingAll();
                        switch (al.size()) {
                            case 1:
                                erDingPeiChu1(al.get(0));
                                break;
                            case 2:
                                erDingPeiChu2(al.get(0), al.get(1));
                                break;
                            default:
                                break;
                        }
                        break;
                    case 4:
                        sortEditTextString_2();
                        switch (al.size()) {
                            case 1:
                                erDingPeiQu1(al.get(0));
                                break;
                            case 2:
                                erDingPeiQu2(al.get(0), al.get(1));
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
                break;
            case 2:
                switch (gameSet) {
                    case 1:
                        sanDingAll();
                        if (!et_qian.isEmpty() && et_bai.isEmpty() && et_shi.isEmpty() && et_ge.isEmpty())
                            sanDingChuQian(et_qian);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && et_ge.isEmpty())
                            sanDingChuBai(et_bai);
                        if (et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            sanDingChuShi(et_shi);
                        if (et_qian.isEmpty() && et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            sanDingChuGe(et_ge);

                        if (!et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && et_ge.isEmpty())
                            sanDingChuQianBai(et_qian, et_bai);
                        if (!et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            sanDingChuQianShi(et_qian, et_shi);
                        if (!et_qian.isEmpty() && et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            sanDingChuQianGe(et_qian, et_ge);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            sanDingChuBaiShi(et_bai, et_shi);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            sanDingChuBaiGe(et_bai, et_ge);
                        if (et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && !et_ge.isEmpty())
                            sanDingChuShiGe(et_shi, et_ge);

                        if (!et_qian.isEmpty() && !et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            sanDingChuQianBaiShi(et_qian, et_bai, et_shi);
                        if (!et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            sanDingChuQianBaiGe(et_qian, et_bai, et_ge);
                        if (!et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && !et_ge.isEmpty())
                            sanDingChuQianShiGe(et_qian, et_shi, et_ge);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && !et_shi.isEmpty() && !et_ge.isEmpty())
                            sanDingChuBaiShiGe(et_bai, et_shi, et_ge);
                        break;
                    case 2:
                        if (!et_qian.isEmpty() && et_bai.isEmpty() && et_shi.isEmpty() && et_ge.isEmpty())
                            sanDingQuQian(et_qian);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && et_ge.isEmpty())
                            sanDingQuBai(et_bai);
                        if (et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            sanDingQuShi(et_shi);
                        if (et_qian.isEmpty() && et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            sanDingQuGe(et_ge);

                        if (!et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && et_ge.isEmpty())
                            sanDingQuQianBai(et_qian, et_bai);
                        if (!et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            sanDingQuQianShi(et_qian, et_shi);
                        if (!et_qian.isEmpty() && et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            sanDingQuQianGe(et_qian, et_ge);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            sanDingQuBaiShi(et_bai, et_shi);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            sanDingQuBaiGe(et_bai, et_ge);
                        if (et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && !et_ge.isEmpty())
                            sanDingQuShiGe(et_shi, et_ge);

                        if (!et_qian.isEmpty() && !et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            sanDingQuQianBaiShi(et_qian, et_bai, et_shi);
                        if (!et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            sanDingQuQianBaiGe(et_qian, et_bai, et_ge);
                        if (!et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && !et_ge.isEmpty())
                            sanDingQuQianShiGe(et_qian, et_shi, et_ge);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && !et_shi.isEmpty() && !et_ge.isEmpty())
                            sanDingQuBaiShiGe(et_bai, et_shi, et_ge);
                        break;
                    case 3:
                        sortEditTextString_3();
                        sanDingAll();
                        switch (al.size()) {
                            case 1:
                                sanDingPeiChu1(al.get(0));
                                break;
                            case 2:
                                sanDingPeiChu2(al.get(0), al.get(1));
                                break;
                            case 3:
                                sanDingPeiChu3(al.get(0), al.get(1), al.get(2));
                                break;
                            default:
                                break;
                        }
                        break;
                    case 4:
                        sortEditTextString_3();
                        switch (al.size()) {
                            case 1:
                                sanDingPeiQu1(al.get(0));
                                break;
                            case 2:
                                sanDingPeiQu2(al.get(0), al.get(1));
                                break;
                            case 3:
                                sanDingPeiQu3(al.get(0), al.get(1), al.get(2));
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
                break;
            case 3:
                switch (gameSet) {
                    case 1:
                        siDingAll();
                        if (!et_qian.isEmpty() && et_bai.isEmpty() && et_shi.isEmpty() && et_ge.isEmpty())
                            siDingChuQian(et_qian);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && et_ge.isEmpty())
                            siDingChuBai(et_bai);
                        if (et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            siDingChuShi(et_shi);
                        if (et_qian.isEmpty() && et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            siDingChuGe(et_ge);

                        if (!et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && et_ge.isEmpty())
                            siDingChuQianBai(et_qian, et_bai);
                        if (!et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            siDingChuQianShi(et_qian, et_shi);
                        if (!et_qian.isEmpty() && et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            siDingChuQianGe(et_qian, et_ge);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            siDingChuBaiShi(et_bai, et_shi);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            siDingChuBaiGe(et_bai, et_ge);
                        if (et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && !et_ge.isEmpty())
                            siDingChuShiGe(et_shi, et_ge);

                        if (!et_qian.isEmpty() && !et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            siDingChuQianBaiShi(et_qian, et_bai, et_shi);
                        if (!et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            siDingChuQianBaiGe(et_qian, et_bai, et_ge);
                        if (!et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && !et_ge.isEmpty())
                            siDingChuQianShiGe(et_qian, et_shi, et_ge);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && !et_shi.isEmpty() && !et_ge.isEmpty())
                            siDingChuBaiShiGe(et_bai, et_shi, et_ge);

                        if (!et_qian.isEmpty() && !et_bai.isEmpty() && !et_shi.isEmpty() && !et_ge.isEmpty())
                            siDingChuQianBaiShiGe(et_qian, et_bai, et_shi, et_ge);
                        break;
                    case 2:
                        if (!et_qian.isEmpty() && et_bai.isEmpty() && et_shi.isEmpty() && et_ge.isEmpty())
                            siDingQuQian(et_qian);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && et_ge.isEmpty())
                            siDingQuBai(et_bai);
                        if (et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            siDingQuShi(et_shi);
                        if (et_qian.isEmpty() && et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            siDingQuGe(et_ge);

                        if (!et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && et_ge.isEmpty())
                            siDingQuQianBai(et_qian, et_bai);
                        if (!et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            siDingQuQianShi(et_qian, et_shi);
                        if (!et_qian.isEmpty() && et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            siDingQuQianGe(et_qian, et_ge);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            siDingQuBaiShi(et_bai, et_shi);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            siDingQuBaiGe(et_bai, et_ge);
                        if (et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && !et_ge.isEmpty())
                            siDingQuShiGe(et_shi, et_ge);

                        if (!et_qian.isEmpty() && !et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            siDingQuQianBaiShi(et_qian, et_bai, et_shi);
                        if (!et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            siDingQuQianBaiGe(et_qian, et_bai, et_ge);
                        if (!et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && !et_ge.isEmpty())
                            siDingQuQianShiGe(et_qian, et_shi, et_ge);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && !et_shi.isEmpty() && !et_ge.isEmpty())
                            siDingQuBaiShiGe(et_bai, et_shi, et_ge);

                        if (!et_qian.isEmpty() && !et_bai.isEmpty() && !et_shi.isEmpty() && !et_ge.isEmpty())
                            siDingQuQianBaiShiGe(et_qian, et_bai, et_shi, et_ge);
                        break;
                    case 3:
                        sortEditTextString_4();
                        siDingAll();
                        switch (al.size()) {
                            case 1:
                                siDingPeiChu1(al.get(0));
                                break;
                            case 2:
                                siDingPeiChu2(al.get(0), al.get(1));
                                break;
                            case 3:
                                siDingPeiChu3(al.get(0), al.get(1), al.get(2));
                                break;
                            case 4:
                                siDingPeiChu4(al.get(0), al.get(1), al.get(2), al.get(3));
                                break;
                            default:
                                break;
                        }
                        break;
                    case 4:
                        sortEditTextString_4();
                        switch (al.size()) {
                            case 1:
                                siDingPeiQu1(al.get(0));
                                break;
                            case 2:
                                siDingPeiQu2(al.get(0), al.get(1));
                                break;
                            case 3:
                                siDingPeiQu3(al.get(0), al.get(1), al.get(2));
                                break;
                            case 4:
                                siDingPeiQu4(al.get(0), al.get(1), al.get(2), al.get(3));
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
                break;
            case 4:
                switch (gameSet) {
                    case 5:
                        sortEditTextString_2();
                        erXianAll();
                        switch (al.size()) {
                            case 1:
                                erXianPeiChu1(al.get(0));
                                break;
                            case 2:
                                erXianPeiChu2(al.get(0), al.get(1));
                                break;
                            default:
                                break;
                        }
                        break;
                    case 6:
                        sortEditTextString_2();
                        switch (al.size()) {
                            case 1:
                                erXianPeiQu1(al.get(0));
                                break;
                            case 2:
                                erXianPeiQu2(al.get(0), al.get(1));
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
                break;
            case 5:
                switch (gameSet) {
                    case 5:
                        sortEditTextString_3();
                        sanXianAll();
                        switch (al.size()) {
                            case 1:
                                sanXianPeiChu1(al.get(0));
                                break;
                            case 2:
                                sanXianPeiChu2(al.get(0), al.get(1));
                                break;
                            case 3:
                                sanXianPeiChu3(al.get(0), al.get(1), al.get(2));
                                break;
                            default:
                                break;
                        }
                        break;
                    case 6:
                        sortEditTextString_3();
                        switch (al.size()) {
                            case 1:
                                sanXianPeiQu1(al.get(0));
                                break;
                            case 2:
                                sanXianPeiQu2(al.get(0), al.get(1));
                                break;
                            case 3:
                                sanXianPeiQu3(al.get(0), al.get(1), al.get(2));
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
                break;
            case 6:
                switch (gameSet) {
                    case 5:
                        sortEditTextString_4();
                        siXianAll();
                        switch (al.size()) {
                            case 1:
                                siXianPeiChu1(al.get(0));
                                break;
                            case 2:
                                siXianPeiChu2(al.get(0), al.get(1));
                                break;
                            case 3:
                                siXianPeiChu3(al.get(0), al.get(1), al.get(2));
                                break;
                            case 4:
                                siXianPeiChu4(al.get(0), al.get(1), al.get(2), al.get(3));
                                break;
                            default:
                                break;
                        }
                        break;
                    case 6:
                        sortEditTextString_4();
                        switch (al.size()) {
                            case 1:
                                siXianPeiQu1(al.get(0));
                                break;
                            case 2:
                                siXianPeiQu2(al.get(0), al.get(1));
                                break;
                            case 3:
                                siXianPeiQu3(al.get(0), al.get(1), al.get(2));
                                break;
                            case 4:
                                siXianPeiQu4(al.get(0), al.get(1), al.get(2), al.get(3));
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }

    //把數組放進list結構裡面使用
    public void sbToList(String s1, String s2, String s3, String s4) {
        sb.replace(0, 1, s1);
        sb.replace(1, 2, s2);
        sb.replace(2, 3, s3);
        sb.replace(3, 4, s4);

        list.add(sb.toString());
    }

    public void sbToList(String s1, String s2) {
        sb.replace(0, 1, s1);
        sb.replace(1, 2, s2);

        list.add(sb.toString());
    }

    public void sbToList(String s1, String s2, String s3) {
        sb.replace(0, 1, s1);
        sb.replace(1, 2, s2);
        sb.replace(2, 3, s3);

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

    public void sbRemoveList(String s1, String s2) {
        sb.replace(0, 1, s1);
        sb.replace(1, 2, s2);

        list.remove(sb.toString());
    }

    public void sbRemoveList(String s1, String s2, String s3) {
        sb.replace(0, 1, s1);
        sb.replace(1, 2, s2);
        sb.replace(2, 3, s3);

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

    //二字定配取一位
    public void erDingPeiQu1(String s) {
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

    //二字定配取二位
    public void erDingPeiQu2(String s_1, String s_2) {
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

    //二字定配除一位
    public void erDingPeiChu1(String s) {
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
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s) && s2.equals("X") && s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s) && s3.equals("X") && s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s2.equals(s) && s1.equals("X") && s3.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s2.equals(s) && s1.equals("X") && s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s2.equals(s) && s3.equals("X") && s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s3.equals(s) && s1.equals("X") && s2.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s3.equals(s) && s1.equals("X") && s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s3.equals(s) && s2.equals("X") && s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s4.equals(s) && s1.equals("X") && s2.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s4.equals(s) && s1.equals("X") && s3.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s4.equals(s) && s2.equals("X") && s3.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else {

                        }
                    }
                }
            }
        }
    }

    //二字定配除二位
    public void erDingPeiChu2(String s_1, String s_2) {
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
                        } else if (s1.equals(s_1) && s2.equals("X") && s3.equals(s_2) && s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals("X") && s3.equals("X") && s4.equals(s_2)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals(s_1) && s3.equals("X") && s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s2.equals(s_1) && s3.equals(s_2) && s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s2.equals(s_1) && s3.equals("X") && s4.equals(s_2)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals("X") && s3.equals(s_1) && s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s2.equals(s_2) && s3.equals(s_1) && s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s2.equals("X") && s3.equals(s_1) && s4.equals(s_2)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals("X") && s3.equals("X") && s4.equals(s_1)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s2.equals(s_2) && s3.equals("X") && s4.equals(s_1)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s2.equals("X") && s3.equals(s_2) && s4.equals(s_1)) {
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

                        } else if (!s1.equals("X") && !s2.equals("X") && !s3.equals("X") && s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (!s1.equals("X") && !s2.equals("X") && s3.equals("X") && !s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (!s1.equals("X") && s2.equals("X") && !s3.equals("X") && !s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && !s2.equals("X") && !s3.equals("X") && !s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else {

                        }
                    }
                }
            }
        }
    }

    //三字定取千
    public void sanDingQuQian(String s) {
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

                    if (!s2.equals("X") && !s3.equals("X") && s4.equals("X")) {
                        sbToList(s1, s2, s3, s4);
                    } else if (!s2.equals("X") && s3.equals("X") && !s4.equals("X")) {
                        sbToList(s1, s2, s3, s4);
                    } else if (s2.equals("X") && !s3.equals("X") && !s4.equals("X")) {
                        sbToList(s1, s2, s3, s4);
                    } else {

                    }

                }
            }
        }
    }

    //三字定取百
    public void sanDingQuBai(String s) {
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

                    if (!s1.equals("X") && !s3.equals("X") && s4.equals("X")) {
                        sbToList(s1, s2, s3, s4);
                    } else if (!s1.equals("X") && s3.equals("X") && !s4.equals("X")) {
                        sbToList(s1, s2, s3, s4);
                    } else if (s1.equals("X") && !s3.equals("X") && !s4.equals("X")) {
                        sbToList(s1, s2, s3, s4);
                    } else {

                    }
                }
            }
        }
    }

    //三字定取十
    public void sanDingQuShi(String s) {
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

                    if (!s1.equals("X") && !s2.equals("X") && s4.equals("X")) {
                        sbToList(s1, s2, s3, s4);
                    } else if (!s1.equals("X") && s2.equals("X") && !s4.equals("X")) {
                        sbToList(s1, s2, s3, s4);
                    } else if (s1.equals("X") && !s2.equals("X") && !s4.equals("X")) {
                        sbToList(s1, s2, s3, s4);
                    } else {

                    }
                }
            }
        }
    }

    //三字定取個
    public void sanDingQuGe(String s) {
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

                    if (!s1.equals("X") && !s2.equals("X") && s3.equals("X")) {
                        sbToList(s1, s2, s3, s4);
                    } else if (!s1.equals("X") && s2.equals("X") && !s3.equals("X")) {
                        sbToList(s1, s2, s3, s4);
                    } else if (s1.equals("X") && !s2.equals("X") && !s3.equals("X")) {
                        sbToList(s1, s2, s3, s4);
                    } else {

                    }
                }
            }
        }
    }

    //三字定取千+百
    public void sanDingQuQianBai(String s1, String s2) {
        String s3, s4;
        for (int c = 0; c <= 9; c++) {
            s3 = String.valueOf(c);
            s4 = "X";
            sbToList(s1, s2, s3, s4);
        }
        for (int d = 0; d <= 9; d++) {
            s3 = "X";
            s4 = String.valueOf(d);
            sbToList(s1, s2, s3, s4);
        }
    }

    //三字定取千+十
    public void sanDingQuQianShi(String s1, String s3) {
        String s2, s4;
        for (int b = 0; b <= 9; b++) {
            s2 = String.valueOf(b);
            s4 = "X";
            sbToList(s1, s2, s3, s4);
        }
        for (int d = 0; d <= 9; d++) {
            s2 = "X";
            s4 = String.valueOf(d);
            sbToList(s1, s2, s3, s4);
        }
    }

    //三字定取千+個
    public void sanDingQuQianGe(String s1, String s4) {
        String s2, s3;
        for (int b = 0; b <= 9; b++) {
            s2 = String.valueOf(b);
            s3 = "X";
            sbToList(s1, s2, s3, s4);
        }
        for (int c = 0; c <= 9; c++) {
            s2 = "X";
            s3 = String.valueOf(c);
            sbToList(s1, s2, s3, s4);
        }
    }

    //三字定取百+十
    public void sanDingQuBaiShi(String s2, String s3) {
        String s1, s4;
        for (int a = 0; a <= 9; a++) {
            s1 = String.valueOf(a);
            s4 = "X";
            sbToList(s1, s2, s3, s4);
        }
        for (int d = 0; d <= 9; d++) {
            s1 = "X";
            s4 = String.valueOf(d);
            sbToList(s1, s2, s3, s4);
        }
    }

    //三字定取百+個
    public void sanDingQuBaiGe(String s2, String s4) {
        String s1, s3;
        for (int a = 0; a <= 9; a++) {
            s1 = String.valueOf(a);
            s3 = "X";
            sbToList(s1, s2, s3, s4);
        }
        for (int c = 0; c <= 9; c++) {
            s1 = "X";
            s3 = String.valueOf(c);
            sbToList(s1, s2, s3, s4);
        }
    }

    //三字定取十+個
    public void sanDingQuShiGe(String s3, String s4) {
        String s1, s2;
        for (int a = 0; a <= 9; a++) {
            s1 = String.valueOf(a);
            s2 = "X";
            sbToList(s1, s2, s3, s4);
        }
        for (int b = 0; b <= 9; b++) {
            s1 = "X";
            s2 = String.valueOf(b);
            sbToList(s1, s2, s3, s4);
        }
    }

    //三字定取千+百+十
    public void sanDingQuQianBaiShi(String s1, String s2, String s3) {
        sbToList(s1, s2, s3, "X");
    }

    //三字定取千+百+個
    public void sanDingQuQianBaiGe(String s1, String s2, String s4) {
        sbToList(s1, s2, "X", s4);
    }

    //三字定取千+十+個
    public void sanDingQuQianShiGe(String s1, String s3, String s4) {
        sbToList(s1, "X", s3, s4);
    }

    //三字定取百+十+個
    public void sanDingQuBaiShiGe(String s2, String s3, String s4) {
        sbToList("X", s2, s3, s4);
    }

    //三字定配取一位
    public void sanDingPeiQu1(String s) {
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

                        } else if (s1.equals(s) && !s2.equals("X") && !s3.equals("X") && s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s) && !s2.equals("X") && s3.equals("X") && !s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s) && s2.equals("X") && !s3.equals("X") && !s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (!s1.equals("X") && s2.equals(s) && !s3.equals("X") && s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (!s1.equals("X") && s2.equals(s) && s3.equals("X") && !s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s2.equals(s) && !s3.equals("X") && !s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (!s1.equals("X") && !s2.equals("X") && s3.equals(s) && s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (!s1.equals("X") && s2.equals("X") && s3.equals(s) && !s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && !s2.equals("X") && s3.equals(s) && !s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (!s1.equals("X") && !s2.equals("X") && s3.equals("X") && s4.equals(s)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (!s1.equals("X") && s2.equals("X") && !s3.equals("X") && s4.equals(s)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && !s2.equals("X") && !s3.equals("X") && s4.equals(s)) {
                            sbToList(s1, s2, s3, s4);
                        } else {

                        }
                    }
                }
            }
        }
    }

    //三字定配取二位
    public void sanDingPeiQu2(String s_1, String s_2) {
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

                        } else if (!s1.equals("X") && s2.equals(s_1) && s3.equals(s_2) && s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (!s1.equals("X") && s2.equals(s_1) && s3.equals("X") && s4.equals(s_2)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (!s1.equals("X") && s2.equals("X") && s3.equals(s_1) && s4.equals(s_2)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && !s2.equals("X") && s3.equals(s_1) && s4.equals(s_2)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && !s2.equals("X") && s3.equals(s_2) && s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && !s2.equals("X") && s3.equals("X") && s4.equals(s_2)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals("X") && !s3.equals("X") && s4.equals(s_2)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s2.equals(s_1) && !s3.equals("X") && s4.equals(s_2)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals(s_2) && !s3.equals("X") && s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals(s_2) && s3.equals("X") && !s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals("X") && s3.equals(s_2) && !s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s2.equals(s_1) && s3.equals(s_2) && !s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (!s1.equals("X") && s2.equals(s_2) && s3.equals(s_1) && s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (!s1.equals("X") && s2.equals(s_2) && s3.equals("X") && s4.equals(s_1)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (!s1.equals("X") && s2.equals("X") && s3.equals(s_2) && s4.equals(s_1)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && !s2.equals("X") && s3.equals(s_2) && s4.equals(s_1)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && !s2.equals("X") && s3.equals(s_1) && s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && !s2.equals("X") && s3.equals("X") && s4.equals(s_1)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals("X") && !s3.equals("X") && s4.equals(s_1)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s2.equals(s_2) && !s3.equals("X") && s4.equals(s_1)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals(s_1) && !s3.equals("X") && s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals(s_1) && s3.equals("X") && !s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals("X") && s3.equals(s_1) && !s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s2.equals(s_2) && s3.equals(s_1) && !s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else {

                        }
                    }
                }
            }
        }
    }

    //三字定配取三位
    public void sanDingPeiQu3(String s_1, String s_2, String s_3) {
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

                        } else if (s1.equals(s_3) && s2.equals(s_1) && s3.equals(s_2) && s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_3) && s2.equals(s_1) && s3.equals("X") && s4.equals(s_2)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_3) && s2.equals("X") && s3.equals(s_1) && s4.equals(s_2)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s2.equals(s_3) && s3.equals(s_1) && s4.equals(s_2)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals(s_3) && s3.equals(s_2) && s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals(s_3) && s3.equals("X") && s4.equals(s_2)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals("X") && s3.equals(s_3) && s4.equals(s_2)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s2.equals(s_1) && s3.equals(s_3) && s4.equals(s_2)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals(s_2) && s3.equals(s_3) && s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals(s_2) && s3.equals("X") && s4.equals(s_3)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals("X") && s3.equals(s_2) && s4.equals(s_3)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s2.equals(s_1) && s3.equals(s_2) && s4.equals(s_3)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_3) && s2.equals(s_2) && s3.equals(s_1) && s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_3) && s2.equals(s_2) && s3.equals("X") && s4.equals(s_1)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_3) && s2.equals("X") && s3.equals(s_2) && s4.equals(s_1)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s2.equals(s_3) && s3.equals(s_2) && s4.equals(s_1)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals(s_3) && s3.equals(s_1) && s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals(s_3) && s3.equals("X") && s4.equals(s_1)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals("X") && s3.equals(s_3) && s4.equals(s_1)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s2.equals(s_2) && s3.equals(s_3) && s4.equals(s_1)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals(s_1) && s3.equals(s_3) && s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals(s_1) && s3.equals("X") && s4.equals(s_3)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals("X") && s3.equals(s_1) && s4.equals(s_3)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s2.equals(s_2) && s3.equals(s_1) && s4.equals(s_3)) {
                            sbToList(s1, s2, s3, s4);
                        } else {

                        }
                    }
                }
            }
        }
    }

    //三字定除千
    public void sanDingChuQian(String s) {
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

                        } else if (s1.equals(s) || s1.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else {

                        }
                    }
                }
            }
        }
    }

    //三字定除百
    public void sanDingChuBai(String s) {
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

                        } else if (s2.equals(s) || s2.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else {

                        }
                    }
                }
            }
        }
    }

    //三字定除十
    public void sanDingChuShi(String s) {
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

                        } else if (s3.equals(s) || s3.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else {

                        }
                    }
                }
            }
        }
    }

    //三字定除個
    public void sanDingChuGe(String s) {
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

                        } else if (s4.equals(s) || s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else {

                        }
                    }
                }
            }
        }
    }

    //三字定除千+百
    public void sanDingChuQianBai(String s_1, String s_2) {
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

                        } else if (s1.equals(s_1) && s2.equals(s_2)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s2.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else {

                        }
                    }
                }
            }
        }
    }

    //三字定除千+十
    public void sanDingChuQianShi(String s_1, String s_3) {
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

                        } else if (s1.equals(s_1) && s3.equals(s_3)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s3.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else {

                        }
                    }
                }
            }
        }
    }

    //三字定除千+個
    public void sanDingChuQianGe(String s_1, String s_4) {
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

                        } else if (s1.equals(s_1) && s4.equals(s_4)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else {

                        }
                    }
                }
            }
        }
    }

    //三字定除百+十
    public void sanDingChuBaiShi(String s_2, String s_3) {
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

                        } else if (s2.equals(s_2) && s3.equals(s_3)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s2.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s3.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else {

                        }
                    }
                }
            }
        }
    }

    //三字定除百+個
    public void sanDingChuBaiGe(String s_2, String s_4) {
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

                        } else if (s2.equals(s_2) && s4.equals(s_4)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s2.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else {

                        }
                    }
                }
            }
        }
    }

    //三字定除十+個
    public void sanDingChuShiGe(String s_3, String s_4) {
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

                        } else if (s3.equals(s_3) && s4.equals(s_4)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s3.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else {

                        }
                    }
                }
            }
        }
    }

    //三字定除千+百+十
    public void sanDingChuQianBaiShi(String s1, String s2, String s3) {
        sbRemoveList(s1, s2, s3, "X");
    }

    //三字定除千+百+個
    public void sanDingChuQianBaiGe(String s1, String s2, String s4) {
        sbRemoveList(s1, s2, "X", s4);
    }

    //三字定除千+十+個
    public void sanDingChuQianShiGe(String s1, String s3, String s4) {
        sbRemoveList(s1, "X", s3, s4);
    }

    //三自訂除百+十+個
    public void sanDingChuBaiShiGe(String s2, String s3, String s4) {
        sbRemoveList("X", s2, s3, s4);
    }

    //三字定配除一位
    public void sanDingPeiChu1(String s) {
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

                        } else if (s1.equals(s) && !s2.equals("X") && !s3.equals("X") && s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s) && !s2.equals("X") && s3.equals("X") && !s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s) && s2.equals("X") && !s3.equals("X") && !s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (!s1.equals("X") && s2.equals(s) && !s3.equals("X") && s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (!s1.equals("X") && s2.equals(s) && s3.equals("X") && !s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s2.equals(s) && !s3.equals("X") && !s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (!s1.equals("X") && !s2.equals("X") && s3.equals(s) && s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (!s1.equals("X") && s2.equals("X") && s3.equals(s) && !s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && !s2.equals("X") && s3.equals(s) && !s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (!s1.equals("X") && !s2.equals("X") && s3.equals("X") && s4.equals(s)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (!s1.equals("X") && s2.equals("X") && !s3.equals("X") && s4.equals(s)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && !s2.equals("X") && !s3.equals("X") && s4.equals(s)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else {

                        }
                    }
                }
            }
        }
    }

    //三字定配除二位
    public void sanDingPeiChu2(String s_1, String s_2) {
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

                        } else if (!s1.equals("X") && s2.equals(s_1) && s3.equals(s_2) && s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (!s1.equals("X") && s2.equals(s_1) && s3.equals("X") && s4.equals(s_2)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (!s1.equals("X") && s2.equals("X") && s3.equals(s_1) && s4.equals(s_2)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && !s2.equals("X") && s3.equals(s_1) && s4.equals(s_2)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && !s2.equals("X") && s3.equals(s_2) && s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && !s2.equals("X") && s3.equals("X") && s4.equals(s_2)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals("X") && !s3.equals("X") && s4.equals(s_2)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s2.equals(s_1) && !s3.equals("X") && s4.equals(s_2)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals(s_2) && !s3.equals("X") && s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals(s_2) && s3.equals("X") && !s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals("X") && s3.equals(s_2) && !s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s2.equals(s_1) && s3.equals(s_2) && !s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (!s1.equals("X") && s2.equals(s_2) && s3.equals(s_1) && s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (!s1.equals("X") && s2.equals(s_2) && s3.equals("X") && s4.equals(s_1)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (!s1.equals("X") && s2.equals("X") && s3.equals(s_2) && s4.equals(s_1)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && !s2.equals("X") && s3.equals(s_2) && s4.equals(s_1)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && !s2.equals("X") && s3.equals(s_1) && s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && !s2.equals("X") && s3.equals("X") && s4.equals(s_1)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals("X") && !s3.equals("X") && s4.equals(s_1)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s2.equals(s_2) && !s3.equals("X") && s4.equals(s_1)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals(s_1) && !s3.equals("X") && s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals(s_1) && s3.equals("X") && !s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals("X") && s3.equals(s_1) && !s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s2.equals(s_2) && s3.equals(s_1) && !s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else {

                        }
                    }
                }
            }
        }
    }

    //三字定配除三位
    public void sanDingPeiChu3(String s_1, String s_2, String s_3) {
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

                        } else if (s1.equals(s_3) && s2.equals(s_1) && s3.equals(s_2) && s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_3) && s2.equals(s_1) && s3.equals("X") && s4.equals(s_2)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_3) && s2.equals("X") && s3.equals(s_1) && s4.equals(s_2)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s2.equals(s_3) && s3.equals(s_1) && s4.equals(s_2)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals(s_3) && s3.equals(s_2) && s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals(s_3) && s3.equals("X") && s4.equals(s_2)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals("X") && s3.equals(s_3) && s4.equals(s_2)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s2.equals(s_1) && s3.equals(s_3) && s4.equals(s_2)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals(s_2) && s3.equals(s_3) && s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals(s_2) && s3.equals("X") && s4.equals(s_3)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals("X") && s3.equals(s_2) && s4.equals(s_3)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s2.equals(s_1) && s3.equals(s_2) && s4.equals(s_3)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_3) && s2.equals(s_2) && s3.equals(s_1) && s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_3) && s2.equals(s_2) && s3.equals("X") && s4.equals(s_1)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_3) && s2.equals("X") && s3.equals(s_2) && s4.equals(s_1)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s2.equals(s_3) && s3.equals(s_2) && s4.equals(s_1)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals(s_3) && s3.equals(s_1) && s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals(s_3) && s3.equals("X") && s4.equals(s_1)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals("X") && s3.equals(s_3) && s4.equals(s_1)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s2.equals(s_2) && s3.equals(s_3) && s4.equals(s_1)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals(s_1) && s3.equals(s_3) && s4.equals("X")) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals(s_1) && s3.equals("X") && s4.equals(s_3)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals("X") && s3.equals(s_1) && s4.equals(s_3)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s2.equals(s_2) && s3.equals(s_1) && s4.equals(s_3)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else {

                        }
                    }
                }
            }
        }
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

    //四字定取千
    public void siDingQuQian(String s) {
        String s1, s2, s3, s4;
        s1 = s;
        for (int b = 0; b <= 9; b++) {
            for (int c = 0; c <= 9; c++) {
                for (int d = 0; d <= 9; d++) {
                    s2 = String.valueOf(b);
                    s3 = String.valueOf(c);
                    s4 = String.valueOf(d);
                    sbToList(s1, s2, s3, s4);
                }
            }
        }
    }

    //四字定取百
    public void siDingQuBai(String s) {
        String s1, s2, s3, s4;
        s2 = s;
        for (int a = 0; a <= 9; a++) {
            for (int c = 0; c <= 9; c++) {
                for (int d = 0; d <= 9; d++) {
                    s1 = String.valueOf(a);
                    s3 = String.valueOf(c);
                    s4 = String.valueOf(d);
                    sbToList(s1, s2, s3, s4);
                }
            }
        }
    }

    //四字定取十
    public void siDingQuShi(String s) {
        String s1, s2, s3, s4;
        s3 = s;
        for (int a = 0; a <= 9; a++) {
            for (int b = 0; b <= 9; b++) {
                for (int d = 0; d <= 9; d++) {
                    s1 = String.valueOf(a);
                    s2 = String.valueOf(b);
                    s4 = String.valueOf(d);
                    sbToList(s1, s2, s3, s4);
                }
            }
        }
    }

    //四字定取個
    public void siDingQuGe(String s) {
        String s1, s2, s3, s4;
        s4 = s;
        for (int a = 0; a <= 9; a++) {
            for (int b = 0; b <= 9; b++) {
                for (int c = 0; c <= 9; c++) {
                    s1 = String.valueOf(a);
                    s2 = String.valueOf(b);
                    s3 = String.valueOf(c);
                    sbToList(s1, s2, s3, s4);
                }
            }
        }
    }

    //四字定取千+百
    public void siDingQuQianBai(String s1, String s2) {
        String s3, s4;
        for (int c = 0; c <= 9; c++) {
            for (int d = 0; d <= 9; d++) {
                s3 = String.valueOf(c);
                s4 = String.valueOf(d);
                sbToList(s1, s2, s3, s4);
            }
        }
    }

    //四字定取千+十
    public void siDingQuQianShi(String s1, String s3) {
        String s2, s4;
        for (int b = 0; b <= 9; b++) {
            for (int d = 0; d <= 9; d++) {
                s2 = String.valueOf(b);
                s4 = String.valueOf(d);
                sbToList(s1, s2, s3, s4);
            }
        }
    }

    //四字定取千+個
    public void siDingQuQianGe(String s1, String s4) {
        String s2, s3;
        for (int b = 0; b <= 9; b++) {
            for (int c = 0; c <= 9; c++) {
                s2 = String.valueOf(b);
                s3 = String.valueOf(c);
                sbToList(s1, s2, s3, s4);
            }
        }
    }

    //四字定取百+十
    public void siDingQuBaiShi(String s2, String s3) {
        String s1, s4;
        for (int a = 0; a <= 9; a++) {
            for (int d = 0; d <= 9; d++) {
                s1 = String.valueOf(a);
                s4 = String.valueOf(d);
                sbToList(s1, s2, s3, s4);
            }
        }
    }

    //四字定取百+個
    public void siDingQuBaiGe(String s2, String s4) {
        String s1, s3;
        for (int a = 0; a <= 9; a++) {
            for (int c = 0; c <= 9; c++) {
                s1 = String.valueOf(a);
                s3 = String.valueOf(c);
                sbToList(s1, s2, s3, s4);
            }
        }
    }

    //四字定取十+個
    public void siDingQuShiGe(String s3, String s4) {
        String s1, s2;
        for (int a = 0; a <= 9; a++) {
            for (int b = 0; b <= 9; b++) {
                s1 = String.valueOf(a);
                s2 = String.valueOf(b);
                sbToList(s1, s2, s3, s4);
            }
        }
    }

    //四字定取千+百+十
    public void siDingQuQianBaiShi(String s1, String s2, String s3) {
        String s4;
        for (int d = 0; d <= 9; d++) {
            s4 = String.valueOf(d);
            sbToList(s1, s2, s3, s4);
        }
    }

    //四字定取千+百+個
    public void siDingQuQianBaiGe(String s1, String s2, String s4) {
        String s3;
        for (int c = 0; c <= 9; c++) {
            s3 = String.valueOf(c);
            sbToList(s1, s2, s3, s4);
        }
    }

    //四字定取千+十+個
    public void siDingQuQianShiGe(String s1, String s3, String s4) {
        String s2;
        for (int b = 0; b <= 9; b++) {
            s2 = String.valueOf(b);
            sbToList(s1, s2, s3, s4);
        }
    }

    //四字定取百+十+個
    public void siDingQuBaiShiGe(String s2, String s3, String s4) {
        String s1;
        for (int a = 0; a <= 9; a++) {
            s1 = String.valueOf(a);
            sbToList(s1, s2, s3, s4);
        }
    }

    //四字定取千+百+十+個
    public void siDingQuQianBaiShiGe(String s1, String s2, String s3, String s4) {
        sbToList(s1, s2, s3, s4);
    }

    //四字定配取一位
    public void siDingPeiQu1(String s) {
        String s1, s2, s3, s4;
        for (int a = 0; a <= 9; a++) {
            for (int b = 0; b <= 9; b++) {
                for (int c = 0; c <= 9; c++) {
                    for (int d = 0; d <= 9; d++) {
                        s1 = String.valueOf(a);
                        s2 = String.valueOf(b);
                        s3 = String.valueOf(c);
                        s4 = String.valueOf(d);
                        if (s1.equals(s) || s2.equals(s) || s3.equals(s) || s4.equals(s)) {
                            sbToList(s1, s2, s3, s4);
                        }
                    }
                }
            }
        }
    }

    //四字定配取二位
    public void siDingPeiQu2(String s_1, String s_2) {
        String s1, s2, s3, s4;
        for (int a = 0; a <= 9; a++) {
            for (int b = 0; b <= 9; b++) {
                for (int c = 0; c <= 9; c++) {
                    for (int d = 0; d <= 9; d++) {
                        s1 = String.valueOf(a);
                        s2 = String.valueOf(b);
                        s3 = String.valueOf(c);
                        s4 = String.valueOf(d);
                        if (s2.equals(s_1) && s3.equals(s_2)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s2.equals(s_1) && s4.equals(s_2)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s3.equals(s_1) && s4.equals(s_2)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s3.equals(s_2)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s4.equals(s_2)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals(s_2)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s2.equals(s_2) && s3.equals(s_1)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s2.equals(s_2) && s4.equals(s_1)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s3.equals(s_2) && s4.equals(s_1)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s3.equals(s_1)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s4.equals(s_1)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals(s_1)) {
                            sbToList(s1, s2, s3, s4);
                        } else {

                        }
                    }
                }
            }
        }
    }

    //四字定配取三位
    public void siDingPeiQu3(String s_1, String s_2, String s_3) {
        String s1, s2, s3, s4;
        for (int a = 0; a <= 9; a++) {
            for (int b = 0; b <= 9; b++) {
                for (int c = 0; c <= 9; c++) {
                    for (int d = 0; d <= 9; d++) {
                        s1 = String.valueOf(a);
                        s2 = String.valueOf(b);
                        s3 = String.valueOf(c);
                        s4 = String.valueOf(d);
                        if (s1.equals(s_3) && s2.equals(s_1) && s3.equals(s_2)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_3) && s2.equals(s_1) && s4.equals(s_2)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_3) && s3.equals(s_1) && s4.equals(s_2)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s2.equals(s_3) && s3.equals(s_1) && s4.equals(s_2)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals(s_3) && s3.equals(s_2)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals(s_3) && s4.equals(s_2)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s3.equals(s_3) && s4.equals(s_2)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s2.equals(s_1) && s3.equals(s_3) && s4.equals(s_2)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals(s_2) && s3.equals(s_3)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals(s_2) && s4.equals(s_3)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s3.equals(s_2) && s4.equals(s_3)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s2.equals(s_1) && s3.equals(s_2) && s4.equals(s_3)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_3) && s2.equals(s_2) && s3.equals(s_1)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_3) && s2.equals(s_2) && s4.equals(s_1)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_3) && s3.equals(s_2) && s4.equals(s_1)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s2.equals(s_3) && s3.equals(s_2) && s4.equals(s_1)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals(s_3) && s3.equals(s_1)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals(s_3) && s4.equals(s_1)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s3.equals(s_3) && s4.equals(s_1)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s2.equals(s_2) && s3.equals(s_3) && s4.equals(s_1)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals(s_1) && s3.equals(s_3)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals(s_1) && s4.equals(s_3)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s3.equals(s_1) && s4.equals(s_3)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s2.equals(s_2) && s3.equals(s_1) && s4.equals(s_3)) {
                            sbToList(s1, s2, s3, s4);
                        } else {

                        }
                    }
                }
            }
        }
    }

    //四字定配取四位
    public void siDingPeiQu4(String s_1, String s_2, String s_3, String s_4) {
        String s1, s2, s3, s4;
        for (int a = 0; a <= 9; a++) {
            for (int b = 0; b <= 9; b++) {
                for (int c = 0; c <= 9; c++) {
                    for (int d = 0; d <= 9; d++) {
                        s1 = String.valueOf(a);
                        s2 = String.valueOf(b);
                        s3 = String.valueOf(c);
                        s4 = String.valueOf(d);
                        if (s1.equals(s_3) && s2.equals(s_1) && s3.equals(s_2) && s4.equals(s_4)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_3) && s2.equals(s_1) && s3.equals(s_4) && s4.equals(s_2)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_3) && s2.equals(s_4) && s3.equals(s_1) && s4.equals(s_2)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_4) && s2.equals(s_3) && s3.equals(s_1) && s4.equals(s_2)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals(s_3) && s3.equals(s_2) && s4.equals(s_4)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals(s_3) && s3.equals(s_4) && s4.equals(s_2)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals(s_4) && s3.equals(s_3) && s4.equals(s_2)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_4) && s2.equals(s_1) && s3.equals(s_3) && s4.equals(s_2)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals(s_2) && s3.equals(s_3) && s4.equals(s_4)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals(s_2) && s3.equals(s_4) && s4.equals(s_3)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals(s_4) && s3.equals(s_2) && s4.equals(s_3)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_4) && s2.equals(s_1) && s3.equals(s_2) && s4.equals(s_3)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_3) && s2.equals(s_2) && s3.equals(s_1) && s4.equals(s_4)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_3) && s2.equals(s_2) && s3.equals(s_4) && s4.equals(s_1)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_3) && s2.equals(s_4) && s3.equals(s_2) && s4.equals(s_1)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_4) && s2.equals(s_3) && s3.equals(s_2) && s4.equals(s_1)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals(s_3) && s3.equals(s_1) && s4.equals(s_4)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals(s_3) && s3.equals(s_4) && s4.equals(s_1)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals(s_4) && s3.equals(s_3) && s4.equals(s_1)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_4) && s2.equals(s_2) && s3.equals(s_3) && s4.equals(s_1)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals(s_1) && s3.equals(s_3) && s4.equals(s_4)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals(s_1) && s3.equals(s_4) && s4.equals(s_3)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals(s_4) && s3.equals(s_1) && s4.equals(s_3)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_4) && s2.equals(s_2) && s3.equals(s_1) && s4.equals(s_3)) {
                            sbToList(s1, s2, s3, s4);
                        } else {

                        }
                    }
                }
            }
        }
    }

    //四字定除千
    public void siDingChuQian(String s) {
        String s1, s2, s3, s4;
        s1 = s;
        for (int b = 0; b <= 9; b++) {
            for (int c = 0; c <= 9; c++) {
                for (int d = 0; d <= 9; d++) {
                    s2 = String.valueOf(b);
                    s3 = String.valueOf(c);
                    s4 = String.valueOf(d);
                    sbRemoveList(s1, s2, s3, s4);
                }
            }
        }
    }

    //四字定除百
    public void siDingChuBai(String s) {
        String s1, s2, s3, s4;
        s2 = s;
        for (int a = 0; a <= 9; a++) {
            for (int c = 0; c <= 9; c++) {
                for (int d = 0; d <= 9; d++) {
                    s1 = String.valueOf(a);
                    s3 = String.valueOf(c);
                    s4 = String.valueOf(d);
                    sbRemoveList(s1, s2, s3, s4);
                }
            }
        }
    }

    //四字定除十
    public void siDingChuShi(String s) {
        String s1, s2, s3, s4;
        s3 = s;
        for (int a = 0; a <= 9; a++) {
            for (int b = 0; b <= 9; b++) {
                for (int d = 0; d <= 9; d++) {
                    s1 = String.valueOf(a);
                    s2 = String.valueOf(b);
                    s4 = String.valueOf(d);
                    sbRemoveList(s1, s2, s3, s4);
                }
            }
        }
    }

    //四字定除個
    public void siDingChuGe(String s) {
        String s1, s2, s3, s4;
        s4 = s;
        for (int a = 0; a <= 9; a++) {
            for (int b = 0; b <= 9; b++) {
                for (int c = 0; c <= 9; c++) {
                    s1 = String.valueOf(a);
                    s2 = String.valueOf(b);
                    s3 = String.valueOf(c);
                    sbRemoveList(s1, s2, s3, s4);
                }
            }
        }
    }

    //四字定除千+百
    public void siDingChuQianBai(String s1, String s2) {
        String s3, s4;
        for (int c = 0; c <= 9; c++) {
            for (int d = 0; d <= 9; d++) {
                s3 = String.valueOf(c);
                s4 = String.valueOf(d);
                sbRemoveList(s1, s2, s3, s4);
            }
        }
    }

    //四字定除千+十
    public void siDingChuQianShi(String s1, String s3) {
        String s2, s4;
        for (int b = 0; b <= 9; b++) {
            for (int d = 0; d <= 9; d++) {
                s2 = String.valueOf(b);
                s4 = String.valueOf(d);
                sbRemoveList(s1, s2, s3, s4);
            }
        }
    }

    //四字定除千+個
    public void siDingChuQianGe(String s1, String s4) {
        String s2, s3;
        for (int b = 0; b <= 9; b++) {
            for (int c = 0; c <= 9; c++) {
                s2 = String.valueOf(b);
                s3 = String.valueOf(c);
                sbRemoveList(s1, s2, s3, s4);
            }
        }
    }

    //四字定除百+十
    public void siDingChuBaiShi(String s2, String s3) {
        String s1, s4;
        for (int a = 0; a <= 9; a++) {
            for (int d = 0; d <= 9; d++) {
                s1 = String.valueOf(a);
                s4 = String.valueOf(d);
                sbRemoveList(s1, s2, s3, s4);
            }
        }
    }

    //四字定除百+個
    public void siDingChuBaiGe(String s2, String s4) {
        String s1, s3;
        for (int a = 0; a <= 9; a++) {
            for (int c = 0; c <= 9; c++) {
                s1 = String.valueOf(a);
                s3 = String.valueOf(c);
                sbRemoveList(s1, s2, s3, s4);
            }
        }
    }

    //四字定除十+個
    public void siDingChuShiGe(String s3, String s4) {
        String s1, s2;
        for (int a = 0; a <= 9; a++) {
            for (int b = 0; b <= 9; b++) {
                s1 = String.valueOf(a);
                s2 = String.valueOf(b);
                sbRemoveList(s1, s2, s3, s4);
            }
        }
    }

    //四字定除千+百+十
    public void siDingChuQianBaiShi(String s1, String s2, String s3) {
        String s4;
        for (int d = 0; d <= 9; d++) {
            s4 = String.valueOf(d);
            sbRemoveList(s1, s2, s3, s4);
        }
    }

    //四字定除千+百+個
    public void siDingChuQianBaiGe(String s1, String s2, String s4) {
        String s3;
        for (int c = 0; c <= 9; c++) {
            s3 = String.valueOf(c);
            sbRemoveList(s1, s2, s3, s4);
        }
    }

    //四字定除千+十+個
    public void siDingChuQianShiGe(String s1, String s3, String s4) {
        String s2;
        for (int b = 0; b <= 9; b++) {
            s2 = String.valueOf(b);
            sbRemoveList(s1, s2, s3, s4);
        }
    }

    //四字定除百+十+個
    public void siDingChuBaiShiGe(String s2, String s3, String s4) {
        String s1;
        for (int a = 0; a <= 9; a++) {
            s1 = String.valueOf(a);
            sbRemoveList(s1, s2, s3, s4);
        }
    }

    //四字定除千+百+十+個
    public void siDingChuQianBaiShiGe(String s1, String s2, String s3, String s4) {
        sbRemoveList(s1, s2, s3, s4);
    }

    //四字定配除一位
    public void siDingPeiChu1(String s) {
        String s1, s2, s3, s4;
        for (int a = 0; a <= 9; a++) {
            for (int b = 0; b <= 9; b++) {
                for (int c = 0; c <= 9; c++) {
                    for (int d = 0; d <= 9; d++) {
                        s1 = String.valueOf(a);
                        s2 = String.valueOf(b);
                        s3 = String.valueOf(c);
                        s4 = String.valueOf(d);
                        if (s1.equals(s) || s2.equals(s) || s3.equals(s) || s4.equals(s)) {
                            sbRemoveList(s1, s2, s3, s4);
                        }
                    }
                }
            }
        }
    }

    //四字定配除二位
    public void siDingPeiChu2(String s_1, String s_2) {
        String s1, s2, s3, s4;
        for (int a = 0; a <= 9; a++) {
            for (int b = 0; b <= 9; b++) {
                for (int c = 0; c <= 9; c++) {
                    for (int d = 0; d <= 9; d++) {
                        s1 = String.valueOf(a);
                        s2 = String.valueOf(b);
                        s3 = String.valueOf(c);
                        s4 = String.valueOf(d);
                        if (s2.equals(s_1) && s3.equals(s_2)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s2.equals(s_1) && s4.equals(s_2)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s3.equals(s_1) && s4.equals(s_2)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s3.equals(s_2)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s4.equals(s_2)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals(s_2)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s2.equals(s_2) && s3.equals(s_1)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s2.equals(s_2) && s4.equals(s_1)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s3.equals(s_2) && s4.equals(s_1)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s3.equals(s_1)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s4.equals(s_1)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals(s_1)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else {

                        }
                    }
                }
            }
        }
    }

    //四字定配除三位
    public void siDingPeiChu3(String s_1, String s_2, String s_3) {
        String s1, s2, s3, s4;
        for (int a = 0; a <= 9; a++) {
            for (int b = 0; b <= 9; b++) {
                for (int c = 0; c <= 9; c++) {
                    for (int d = 0; d <= 9; d++) {
                        s1 = String.valueOf(a);
                        s2 = String.valueOf(b);
                        s3 = String.valueOf(c);
                        s4 = String.valueOf(d);
                        if (s1.equals(s_3) && s2.equals(s_1) && s3.equals(s_2)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_3) && s2.equals(s_1) && s4.equals(s_2)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_3) && s3.equals(s_1) && s4.equals(s_2)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s2.equals(s_3) && s3.equals(s_1) && s4.equals(s_2)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals(s_3) && s3.equals(s_2)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals(s_3) && s4.equals(s_2)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s3.equals(s_3) && s4.equals(s_2)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s2.equals(s_1) && s3.equals(s_3) && s4.equals(s_2)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals(s_2) && s3.equals(s_3)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals(s_2) && s4.equals(s_3)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s3.equals(s_2) && s4.equals(s_3)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s2.equals(s_1) && s3.equals(s_2) && s4.equals(s_3)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_3) && s2.equals(s_2) && s3.equals(s_1)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_3) && s2.equals(s_2) && s4.equals(s_1)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_3) && s3.equals(s_2) && s4.equals(s_1)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s2.equals(s_3) && s3.equals(s_2) && s4.equals(s_1)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals(s_3) && s3.equals(s_1)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals(s_3) && s4.equals(s_1)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s3.equals(s_3) && s4.equals(s_1)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s2.equals(s_2) && s3.equals(s_3) && s4.equals(s_1)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals(s_1) && s3.equals(s_3)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals(s_1) && s4.equals(s_3)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s3.equals(s_1) && s4.equals(s_3)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s2.equals(s_2) && s3.equals(s_1) && s4.equals(s_3)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else {

                        }
                    }
                }
            }
        }
    }

    //四字定配除四位
    public void siDingPeiChu4(String s_1, String s_2, String s_3, String s_4) {
        String s1, s2, s3, s4;
        for (int a = 0; a <= 9; a++) {
            for (int b = 0; b <= 9; b++) {
                for (int c = 0; c <= 9; c++) {
                    for (int d = 0; d <= 9; d++) {
                        s1 = String.valueOf(a);
                        s2 = String.valueOf(b);
                        s3 = String.valueOf(c);
                        s4 = String.valueOf(d);
                        if (s1.equals(s_3) && s2.equals(s_1) && s3.equals(s_2) && s4.equals(s_4)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_3) && s2.equals(s_1) && s3.equals(s_4) && s4.equals(s_2)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_3) && s2.equals(s_4) && s3.equals(s_1) && s4.equals(s_2)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_4) && s2.equals(s_3) && s3.equals(s_1) && s4.equals(s_2)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals(s_3) && s3.equals(s_2) && s4.equals(s_4)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals(s_3) && s3.equals(s_4) && s4.equals(s_2)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals(s_4) && s3.equals(s_3) && s4.equals(s_2)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_4) && s2.equals(s_1) && s3.equals(s_3) && s4.equals(s_2)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals(s_2) && s3.equals(s_3) && s4.equals(s_4)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals(s_2) && s3.equals(s_4) && s4.equals(s_3)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals(s_4) && s3.equals(s_2) && s4.equals(s_3)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_4) && s2.equals(s_1) && s3.equals(s_2) && s4.equals(s_3)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_3) && s2.equals(s_2) && s3.equals(s_1) && s4.equals(s_4)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_3) && s2.equals(s_2) && s3.equals(s_4) && s4.equals(s_1)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_3) && s2.equals(s_4) && s3.equals(s_2) && s4.equals(s_1)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_4) && s2.equals(s_3) && s3.equals(s_2) && s4.equals(s_1)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals(s_3) && s3.equals(s_1) && s4.equals(s_4)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals(s_3) && s3.equals(s_4) && s4.equals(s_1)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals(s_4) && s3.equals(s_3) && s4.equals(s_1)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_4) && s2.equals(s_2) && s3.equals(s_3) && s4.equals(s_1)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals(s_1) && s3.equals(s_3) && s4.equals(s_4)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals(s_1) && s3.equals(s_4) && s4.equals(s_3)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_2) && s2.equals(s_4) && s3.equals(s_1) && s4.equals(s_3)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_4) && s2.equals(s_2) && s3.equals(s_1) && s4.equals(s_3)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else {

                        }
                    }
                }
            }
        }
    }

    //二字現全組合
    public void erXianAll() {
        String s1, s2;
        for (int a = 0; a <= 9; a++) {
            for (int b = a; b <= 9; b++) {
                s1 = String.valueOf(a);
                s2 = String.valueOf(b);
                sbToList(s1, s2);
            }
        }
    }

    //二字現配取一位
    public void erXianPeiQu1(String s) {
        String s1, s2;
        for (int a = 0; a <= 9; a++) {
            for (int b = a; b <= 9; b++) {
                s1 = String.valueOf(a);
                s2 = String.valueOf(b);
                if (s1.equals(s) || s2.equals(s)) {
                    sbToList(s1, s2);
                }
            }
        }
    }

    //二字現配取二位
    public void erXianPeiQu2(String s1, String s2) {
        sbToList(s1, s2);
    }

    //二字現配除一位
    public void erXianPeiChu1(String s) {
        String s1, s2;
        for (int a = 0; a <= 9; a++) {
            for (int b = a; b <= 9; b++) {
                s1 = String.valueOf(a);
                s2 = String.valueOf(b);
                if (s1.equals(s) || s2.equals(s)) {
                    sbRemoveList(s1, s2);
                }
            }
        }
    }

    //二字現配除二位
    public void erXianPeiChu2(String s1, String s2) {
        sbRemoveList(s1, s2);
    }

    //三字現全組合
    public void sanXianAll() {
        String s1, s2, s3;
        for (int a = 0; a <= 9; a++) {
            for (int b = a; b <= 9; b++) {
                for (int c = b; c <= 9; c++) {
                    s1 = String.valueOf(a);
                    s2 = String.valueOf(b);
                    s3 = String.valueOf(c);
                    sbToList(s1, s2, s3);
                }
            }
        }
    }

    //三字現配取一位
    public void sanXianPeiQu1(String s) {
        String s1, s2, s3;
        for (int a = 0; a <= 9; a++) {
            for (int b = a; b <= 9; b++) {
                for (int c = b; c <= 9; c++) {
                    s1 = String.valueOf(a);
                    s2 = String.valueOf(b);
                    s3 = String.valueOf(c);
                    if (s1.equals(s) || s2.equals(s) || s3.equals(s)) {
                        sbToList(s1, s2, s3);
                    }
                }
            }
        }
    }

    //三字現配取二位
    public void sanXianPeiQu2(String s_1, String s_2) {
        String s1, s2, s3;
        for (int a = 0; a <= 9; a++) {
            for (int b = a; b <= 9; b++) {
                for (int c = b; c <= 9; c++) {
                    s1 = String.valueOf(a);
                    s2 = String.valueOf(b);
                    s3 = String.valueOf(c);
                    if (s2.equals(s_1) && s3.equals(s_2)) {
                        sbToList(s1, s2, s3);
                    } else if (s1.equals(s_1) && s2.equals(s_2)) {
                        sbToList(s1, s2, s3);
                    } else if (s1.equals(s_1) && s3.equals(s_2)) {
                        sbToList(s1, s2, s3);
                    } else {

                    }
                }
            }
        }
    }

    //三字現配取三位
    public void sanXianPeiQu3(String s1, String s2, String s3) {
        sbToList(s1, s2, s3);
    }

    //三字現配除一位
    public void sanXianPeiChu1(String s) {
        String s1, s2, s3;
        for (int a = 0; a <= 9; a++) {
            for (int b = a; b <= 9; b++) {
                for (int c = b; c <= 9; c++) {
                    s1 = String.valueOf(a);
                    s2 = String.valueOf(b);
                    s3 = String.valueOf(c);
                    if (s1.equals(s) || s2.equals(s) || s3.equals(s)) {
                        sbRemoveList(s1, s2, s3);
                    }
                }
            }
        }
    }

    //三字現配除二位
    public void sanXianPeiChu2(String s_1, String s_2) {
        String s1, s2, s3;
        for (int a = 0; a <= 9; a++) {
            for (int b = a; b <= 9; b++) {
                for (int c = b; c <= 9; c++) {
                    s1 = String.valueOf(a);
                    s2 = String.valueOf(b);
                    s3 = String.valueOf(c);
                    if (s2.equals(s_1) && s3.equals(s_2)) {
                        sbRemoveList(s1, s2, s3);
                    } else if (s1.equals(s_1) && s2.equals(s_2)) {
                        sbRemoveList(s1, s2, s3);
                    } else if (s1.equals(s_1) && s3.equals(s_2)) {
                        sbRemoveList(s1, s2, s3);
                    } else {

                    }
                }
            }
        }
    }

    //三字現配除三位
    public void sanXianPeiChu3(String s1, String s2, String s3) {
        sbRemoveList(s1, s2, s3);
    }

    //四字現全組合
    public void siXianAll() {
        String s1, s2, s3, s4;
        for (int a = 0; a <= 9; a++) {
            for (int b = a; b <= 9; b++) {
                for (int c = b; c <= 9; c++) {
                    for (int d = c; d <= 9; d++) {
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

    //四字現配取一位
    public void siXianPeiQu1(String s) {
        String s1, s2, s3, s4;
        for (int a = 0; a <= 9; a++) {
            for (int b = a; b <= 9; b++) {
                for (int c = b; c <= 9; c++) {
                    for (int d = c; d <= 9; d++) {
                        s1 = String.valueOf(a);
                        s2 = String.valueOf(b);
                        s3 = String.valueOf(c);
                        s4 = String.valueOf(d);
                        if (s1.equals(s) || s2.equals(s) || s3.equals(s) || s4.equals(s)) {
                            sbToList(s1, s2, s3, s4);
                        }
                    }
                }
            }
        }
    }

    //四字現配取二位
    public void siXianPeiQu2(String s_1, String s_2) {
        String s1, s2, s3, s4;
        for (int a = 0; a <= 9; a++) {
            for (int b = a; b <= 9; b++) {
                for (int c = b; c <= 9; c++) {
                    for (int d = c; d <= 9; d++) {
                        s1 = String.valueOf(a);
                        s2 = String.valueOf(b);
                        s3 = String.valueOf(c);
                        s4 = String.valueOf(d);
                        if (s1.equals(s_1) && s2.equals(s_2)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s3.equals(s_2)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s4.equals(s_2)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s2.equals(s_1) && s3.equals(s_2)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s2.equals(s_1) && s4.equals(s_2)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s3.equals(s_1) && s4.equals(s_2)) {
                            sbToList(s1, s2, s3, s4);
                        } else {

                        }
                    }
                }
            }
        }
    }

    //四字現配取三位
    public void siXianPeiQu3(String s_1, String s_2, String s_3) {
        String s1, s2, s3, s4;
        for (int a = 0; a <= 9; a++) {
            for (int b = a; b <= 9; b++) {
                for (int c = b; c <= 9; c++) {
                    for (int d = c; d <= 9; d++) {
                        s1 = String.valueOf(a);
                        s2 = String.valueOf(b);
                        s3 = String.valueOf(c);
                        s4 = String.valueOf(d);
                        if (s1.equals(s_1) && s2.equals(s_2) && s3.equals(s_3)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals(s_2) && s4.equals(s_3)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s3.equals(s_2) && s4.equals(s_3)) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s2.equals(s_1) && s3.equals(s_2) && s4.equals(s_3)) {
                            sbToList(s1, s2, s3, s4);
                        } else {

                        }
                    }
                }
            }
        }
    }

    //四字現配取四位
    public void siXianPeiQu4(String s1, String s2, String s3, String s4) {
        sbToList(s1, s2, s3, s4);
    }

    //四字現配除一位
    public void siXianPeiChu1(String s) {
        String s1, s2, s3, s4;
        for (int a = 0; a <= 9; a++) {
            for (int b = a; b <= 9; b++) {
                for (int c = b; c <= 9; c++) {
                    for (int d = c; d <= 9; d++) {
                        s1 = String.valueOf(a);
                        s2 = String.valueOf(b);
                        s3 = String.valueOf(c);
                        s4 = String.valueOf(d);
                        if (s1.equals(s) || s2.equals(s) || s3.equals(s) || s4.equals(s)) {
                            sbRemoveList(s1, s2, s3, s4);
                        }
                    }
                }
            }
        }
    }

    //四字現配除二位
    public void siXianPeiChu2(String s_1, String s_2) {
        String s1, s2, s3, s4;
        for (int a = 0; a <= 9; a++) {
            for (int b = a; b <= 9; b++) {
                for (int c = b; c <= 9; c++) {
                    for (int d = c; d <= 9; d++) {
                        s1 = String.valueOf(a);
                        s2 = String.valueOf(b);
                        s3 = String.valueOf(c);
                        s4 = String.valueOf(d);
                        if (s1.equals(s_1) && s2.equals(s_2)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s3.equals(s_2)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s4.equals(s_2)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s2.equals(s_1) && s3.equals(s_2)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s2.equals(s_1) && s4.equals(s_2)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s3.equals(s_1) && s4.equals(s_2)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else {

                        }
                    }
                }
            }
        }
    }

    //四字現配除三位
    public void siXianPeiChu3(String s_1, String s_2, String s_3) {
        String s1, s2, s3, s4;
        for (int a = 0; a <= 9; a++) {
            for (int b = a; b <= 9; b++) {
                for (int c = b; c <= 9; c++) {
                    for (int d = c; d <= 9; d++) {
                        s1 = String.valueOf(a);
                        s2 = String.valueOf(b);
                        s3 = String.valueOf(c);
                        s4 = String.valueOf(d);
                        if (s1.equals(s_1) && s2.equals(s_2) && s3.equals(s_3)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s2.equals(s_2) && s4.equals(s_3)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s1.equals(s_1) && s3.equals(s_2) && s4.equals(s_3)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else if (s2.equals(s_1) && s3.equals(s_2) && s4.equals(s_3)) {
                            sbRemoveList(s1, s2, s3, s4);
                        } else {

                        }
                    }
                }
            }
        }
    }

    //四字現配除四位
    public void siXianPeiChu4(String s1, String s2, String s3, String s4) {
        sbRemoveList(s1, s2, s3, s4);
    }

    public void Toast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    public void Log(String s) {
        Log.i("troy", s);
    }
}
