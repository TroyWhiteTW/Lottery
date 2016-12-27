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
    private ArrayList<String> al, al1, al2, al3, al4, al5, al6, al7, al8, al9, al10, al11, al12, al13;
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

        treeSbRt();

        btn_sendGameSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendGameSet();
            }
        });
    }

    public void treeSbRt() {
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
        al1 = new ArrayList<>(linkedHashMap.values());
        for (int i = 0; i < al1.size(); i++) {
            Log("al1[" + i + "] = " + al1.get(i));
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
        al2 = new ArrayList<>(linkedHashMap2.values());
        for (int i = 0; i < al2.size(); i++) {
            Log("al2[" + i + "] = " + al2.get(i));
        }

        TreeMap<Integer, String> treeMap3 = new TreeMap<>();
        for (int i = 0; i < sb_et_shi.length(); i++) {
            treeMap3.put(i, sb_et_shi.substring(i, i + 1));
        }
        ArrayList<Map.Entry<Integer, String>> entryList3 = new ArrayList<>(treeMap3.entrySet());
        Comparator<Map.Entry> sortByValue3 = new Comparator<Map.Entry>() {
            @Override
            public int compare(Map.Entry o1, Map.Entry o2) {
                return ((String) o1.getValue()).compareTo((String) o2.getValue());
            }
        };
        Collections.sort(entryList3, sortByValue3);
        LinkedHashMap<Integer, String> linkedHashMap3 = new LinkedHashMap<>();
        for (Map.Entry e : entryList3)
            linkedHashMap3.put((Integer) e.getKey(), (String) e.getValue());
        Log("linkedHashMap3 = " + linkedHashMap3.toString());
        al3 = new ArrayList<>(linkedHashMap3.values());
        for (int i = 0; i < al3.size(); i++) {
            Log("al3[" + i + "] = " + al3.get(i));
        }

        TreeMap<Integer, String> treeMap4 = new TreeMap<>();
        for (int i = 0; i < sb_et_ge.length(); i++) {
            treeMap4.put(i, sb_et_ge.substring(i, i + 1));
        }
        ArrayList<Map.Entry<Integer, String>> entryList4 = new ArrayList<>(treeMap4.entrySet());
        Comparator<Map.Entry> sortByValue4 = new Comparator<Map.Entry>() {
            @Override
            public int compare(Map.Entry o1, Map.Entry o2) {
                return ((String) o1.getValue()).compareTo((String) o2.getValue());
            }
        };
        Collections.sort(entryList4, sortByValue4);
        LinkedHashMap<Integer, String> linkedHashMap4 = new LinkedHashMap<>();
        for (Map.Entry e : entryList4)
            linkedHashMap4.put((Integer) e.getKey(), (String) e.getValue());
        Log("linkedHashMap4 = " + linkedHashMap4.toString());
        al4 = new ArrayList<>(linkedHashMap4.values());
        for (int i = 0; i < al4.size(); i++) {
            Log("al4[" + i + "] = " + al4.get(i));
        }

        TreeMap<Integer, String> treeMap5 = new TreeMap<>();
        for (int i = 0; i < sb_et_21.length(); i++) {
            treeMap5.put(i, sb_et_21.substring(i, i + 1));
        }
        ArrayList<Map.Entry<Integer, String>> entryList5 = new ArrayList<>(treeMap5.entrySet());
        Comparator<Map.Entry> sortByValue5 = new Comparator<Map.Entry>() {
            @Override
            public int compare(Map.Entry o1, Map.Entry o2) {
                return ((String) o1.getValue()).compareTo((String) o2.getValue());
            }
        };
        Collections.sort(entryList5, sortByValue5);
        LinkedHashMap<Integer, String> linkedHashMap5 = new LinkedHashMap<>();
        for (Map.Entry e : entryList5)
            linkedHashMap5.put((Integer) e.getKey(), (String) e.getValue());
        Log("linkedHashMap5 = " + linkedHashMap5.toString());
        al5 = new ArrayList<>(linkedHashMap5.values());
        for (int i = 0; i < al5.size(); i++) {
            Log("al5[" + i + "] = " + al5.get(i));
        }

        TreeMap<Integer, String> treeMap6 = new TreeMap<>();
        for (int i = 0; i < sb_et_22.length(); i++) {
            treeMap6.put(i, sb_et_22.substring(i, i + 1));
        }
        ArrayList<Map.Entry<Integer, String>> entryList6 = new ArrayList<>(treeMap6.entrySet());
        Comparator<Map.Entry> sortByValue6 = new Comparator<Map.Entry>() {
            @Override
            public int compare(Map.Entry o1, Map.Entry o2) {
                return ((String) o1.getValue()).compareTo((String) o2.getValue());
            }
        };
        Collections.sort(entryList6, sortByValue6);
        LinkedHashMap<Integer, String> linkedHashMap6 = new LinkedHashMap<>();
        for (Map.Entry e : entryList6)
            linkedHashMap6.put((Integer) e.getKey(), (String) e.getValue());
        Log("linkedHashMap6 = " + linkedHashMap6.toString());
        al6 = new ArrayList<>(linkedHashMap6.values());
        for (int i = 0; i < al6.size(); i++) {
            Log("al6[" + i + "] = " + al6.get(i));
        }

        TreeMap<Integer, String> treeMap7 = new TreeMap<>();
        for (int i = 0; i < sb_et_31.length(); i++) {
            treeMap7.put(i, sb_et_31.substring(i, i + 1));
        }
        ArrayList<Map.Entry<Integer, String>> entryList7 = new ArrayList<>(treeMap7.entrySet());
        Comparator<Map.Entry> sortByValue7 = new Comparator<Map.Entry>() {
            @Override
            public int compare(Map.Entry o1, Map.Entry o2) {
                return ((String) o1.getValue()).compareTo((String) o2.getValue());
            }
        };
        Collections.sort(entryList7, sortByValue7);
        LinkedHashMap<Integer, String> linkedHashMap7 = new LinkedHashMap<>();
        for (Map.Entry e : entryList7)
            linkedHashMap7.put((Integer) e.getKey(), (String) e.getValue());
        Log("linkedHashMap7 = " + linkedHashMap7.toString());
        al7 = new ArrayList<>(linkedHashMap7.values());
        for (int i = 0; i < al7.size(); i++) {
            Log("al7[" + i + "] = " + al7.get(i));
        }

        TreeMap<Integer, String> treeMap8 = new TreeMap<>();
        for (int i = 0; i < sb_et_32.length(); i++) {
            treeMap8.put(i, sb_et_32.substring(i, i + 1));
        }
        ArrayList<Map.Entry<Integer, String>> entryList8 = new ArrayList<>(treeMap8.entrySet());
        Comparator<Map.Entry> sortByValue8 = new Comparator<Map.Entry>() {
            @Override
            public int compare(Map.Entry o1, Map.Entry o2) {
                return ((String) o1.getValue()).compareTo((String) o2.getValue());
            }
        };
        Collections.sort(entryList8, sortByValue8);
        LinkedHashMap<Integer, String> linkedHashMap8 = new LinkedHashMap<>();
        for (Map.Entry e : entryList8)
            linkedHashMap8.put((Integer) e.getKey(), (String) e.getValue());
        Log("linkedHashMap8 = " + linkedHashMap8.toString());
        al8 = new ArrayList<>(linkedHashMap8.values());
        for (int i = 0; i < al8.size(); i++) {
            Log("al8[" + i + "] = " + al8.get(i));
        }

        TreeMap<Integer, String> treeMap9 = new TreeMap<>();
        for (int i = 0; i < sb_et_33.length(); i++) {
            treeMap9.put(i, sb_et_33.substring(i, i + 1));
        }
        ArrayList<Map.Entry<Integer, String>> entryList9 = new ArrayList<>(treeMap9.entrySet());
        Comparator<Map.Entry> sortByValue9 = new Comparator<Map.Entry>() {
            @Override
            public int compare(Map.Entry o1, Map.Entry o2) {
                return ((String) o1.getValue()).compareTo((String) o2.getValue());
            }
        };
        Collections.sort(entryList9, sortByValue9);
        LinkedHashMap<Integer, String> linkedHashMap9 = new LinkedHashMap<>();
        for (Map.Entry e : entryList9)
            linkedHashMap9.put((Integer) e.getKey(), (String) e.getValue());
        Log("linkedHashMap9 = " + linkedHashMap9.toString());
        al9 = new ArrayList<>(linkedHashMap9.values());
        for (int i = 0; i < al9.size(); i++) {
            Log("al9[" + i + "] = " + al9.get(i));
        }

        TreeMap<Integer, String> treeMap10 = new TreeMap<>();
        for (int i = 0; i < sb_et_41.length(); i++) {
            treeMap10.put(i, sb_et_41.substring(i, i + 1));
        }
        ArrayList<Map.Entry<Integer, String>> entryList10 = new ArrayList<>(treeMap10.entrySet());
        Comparator<Map.Entry> sortByValue10 = new Comparator<Map.Entry>() {
            @Override
            public int compare(Map.Entry o1, Map.Entry o2) {
                return ((String) o1.getValue()).compareTo((String) o2.getValue());
            }
        };
        Collections.sort(entryList10, sortByValue10);
        LinkedHashMap<Integer, String> linkedHashMap10 = new LinkedHashMap<>();
        for (Map.Entry e : entryList10)
            linkedHashMap10.put((Integer) e.getKey(), (String) e.getValue());
        Log("linkedHashMap10 = " + linkedHashMap10.toString());
        al10 = new ArrayList<>(linkedHashMap10.values());
        for (int i = 0; i < al10.size(); i++) {
            Log("al10[" + i + "] = " + al10.get(i));
        }

        TreeMap<Integer, String> treeMap11 = new TreeMap<>();
        for (int i = 0; i < sb_et_42.length(); i++) {
            treeMap11.put(i, sb_et_42.substring(i, i + 1));
        }
        ArrayList<Map.Entry<Integer, String>> entryList11 = new ArrayList<>(treeMap11.entrySet());
        Comparator<Map.Entry> sortByValue11 = new Comparator<Map.Entry>() {
            @Override
            public int compare(Map.Entry o1, Map.Entry o2) {
                return ((String) o1.getValue()).compareTo((String) o2.getValue());
            }
        };
        Collections.sort(entryList11, sortByValue11);
        LinkedHashMap<Integer, String> linkedHashMap11 = new LinkedHashMap<>();
        for (Map.Entry e : entryList11)
            linkedHashMap11.put((Integer) e.getKey(), (String) e.getValue());
        Log("linkedHashMap11 = " + linkedHashMap11.toString());
        al11 = new ArrayList<>(linkedHashMap11.values());
        for (int i = 0; i < al11.size(); i++) {
            Log("al11[" + i + "] = " + al11.get(i));
        }

        TreeMap<Integer, String> treeMap12 = new TreeMap<>();
        for (int i = 0; i < sb_et_43.length(); i++) {
            treeMap12.put(i, sb_et_43.substring(i, i + 1));
        }
        ArrayList<Map.Entry<Integer, String>> entryList12 = new ArrayList<>(treeMap12.entrySet());
        Comparator<Map.Entry> sortByValue12 = new Comparator<Map.Entry>() {
            @Override
            public int compare(Map.Entry o1, Map.Entry o2) {
                return ((String) o1.getValue()).compareTo((String) o2.getValue());
            }
        };
        Collections.sort(entryList12, sortByValue12);
        LinkedHashMap<Integer, String> linkedHashMap12 = new LinkedHashMap<>();
        for (Map.Entry e : entryList12)
            linkedHashMap12.put((Integer) e.getKey(), (String) e.getValue());
        Log("linkedHashMap12 = " + linkedHashMap12.toString());
        al12 = new ArrayList<>(linkedHashMap12.values());
        for (int i = 0; i < al12.size(); i++) {
            Log("al12[" + i + "] = " + al12.get(i));
        }

        TreeMap<Integer, String> treeMap13 = new TreeMap<>();
        for (int i = 0; i < sb_et_44.length(); i++) {
            treeMap13.put(i, sb_et_44.substring(i, i + 1));
        }
        ArrayList<Map.Entry<Integer, String>> entryList13 = new ArrayList<>(treeMap13.entrySet());
        Comparator<Map.Entry> sortByValue13 = new Comparator<Map.Entry>() {
            @Override
            public int compare(Map.Entry o1, Map.Entry o2) {
                return ((String) o1.getValue()).compareTo((String) o2.getValue());
            }
        };
        Collections.sort(entryList13, sortByValue13);
        LinkedHashMap<Integer, String> linkedHashMap13 = new LinkedHashMap<>();
        for (Map.Entry e : entryList13)
            linkedHashMap13.put((Integer) e.getKey(), (String) e.getValue());
        Log("linkedHashMap13 = " + linkedHashMap13.toString());
        al13 = new ArrayList<>(linkedHashMap13.values());
        for (int i = 0; i < al13.size(); i++) {
            Log("al13[" + i + "] = " + al13.get(i));
        }

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

    public void sortEditTextString_2(String a, String b) {
        HashMap<Integer, String> hashMap = new HashMap<>();
        if (!a.equals("")) hashMap.put(0, a);
        if (!b.equals("")) hashMap.put(1, b);
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

    public void sortEditTextString_3(String a, String b, String c) {
        HashMap<Integer, String> hashMap = new HashMap<>();
        if (!a.equals("")) hashMap.put(0, a);
        if (!b.equals("")) hashMap.put(1, b);
        if (!c.equals("")) hashMap.put(2, c);
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

    public void sortEditTextString_4(String a, String b, String c, String d) {
        HashMap<Integer, String> hashMap = new HashMap<>();
        if (!a.equals("")) hashMap.put(0, a);
        if (!b.equals("")) hashMap.put(1, b);
        if (!c.equals("")) hashMap.put(2, c);
        if (!d.equals("")) hashMap.put(3, d);
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
                            for (int i = 0; i < al1.size(); i++) {
                                erDingChuQian(al1.get(i));
                            }
//                            erDingChuQian(et_qian);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && et_ge.isEmpty())
                            for (int i = 0; i < al2.size(); i++) {
                                erDingChuBai(al2.get(i));
                            }
//                            erDingChuBai(et_bai);
                        if (et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            for (int i = 0; i < al3.size(); i++) {
                                erDingChuShi(al3.get(i));
                            }
//                            erDingChuShi(et_shi);
                        if (et_qian.isEmpty() && et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            for (int i = 0; i < al4.size(); i++) {
                                erDingChuGe(al4.get(i));
                            }
//                            erDingChuGe(et_ge);

                        if (!et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && et_ge.isEmpty())
                            for (int i = 0; i < al1.size(); i++) {
                                for (int j = 0; j < al2.size(); j++) {
                                    erDingChuQianBai(al1.get(i), al2.get(j));
                                }
                            }
//                            erDingChuQianBai(et_qian, et_bai);
                        if (!et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            for (int i = 0; i < al1.size(); i++) {
                                for (int j = 0; j < al3.size(); j++) {
                                    erDingChuQianShi(al1.get(i), al3.get(j));
                                }
                            }
//                            erDingChuQianShi(et_qian, et_shi);
                        if (!et_qian.isEmpty() && et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            for (int i = 0; i < al1.size(); i++) {
                                for (int j = 0; j < al4.size(); j++) {
                                    erDingChuQianGe(al1.get(i), al4.get(j));
                                }
                            }
//                            erDingChuQianGe(et_qian, et_ge);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            for (int i = 0; i < al2.size(); i++) {
                                for (int j = 0; j < al3.size(); j++) {
                                    erDingChuBaiShi(al2.get(i), al3.get(j));
                                }
                            }
//                            erDingChuBaiShi(et_bai, et_shi);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            for (int i = 0; i < al2.size(); i++) {
                                for (int j = 0; j < al4.size(); j++) {
                                    erDingChuBaiGe(al2.get(i), al4.get(j));
                                }
                            }
//                            erDingChuBaiGe(et_bai, et_ge);
                        if (et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && !et_ge.isEmpty())
                            for (int i = 0; i < al3.size(); i++) {
                                for (int j = 0; j < al4.size(); j++) {
                                    erDingChuShiGe(al3.get(i), al4.get(j));
                                }
                            }
//                            erDingChuShiGe(et_shi, et_ge);
                        break;
                    case 2:
                        if (!et_qian.isEmpty() && et_bai.isEmpty() && et_shi.isEmpty() && et_ge.isEmpty())
                            for (int i = 0; i < al1.size(); i++) {
                                erDingQuQian(al1.get(i));
                            }
//                            erDingQuQian(et_qian);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && et_ge.isEmpty())
                            for (int i = 0; i < al2.size(); i++) {
                                erDingQuBai(al2.get(i));
                            }
//                            erDingQuBai(et_bai);
                        if (et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            for (int i = 0; i < al3.size(); i++) {
                                erDingQuShi(al3.get(i));
                            }
//                            erDingQuShi(et_shi);
                        if (et_qian.isEmpty() && et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            for (int i = 0; i < al4.size(); i++) {
                                erDingQuGe(al4.get(i));
                            }
//                            erDingQuGe(et_ge);

                        if (!et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && et_ge.isEmpty())
                            for (int i = 0; i < al1.size(); i++) {
                                for (int j = 0; j < al2.size(); j++) {
                                    erDingQuQianBai(al1.get(i), al2.get(j));
                                }
                            }
//                            erDingQuQianBai(et_qian, et_bai);
                        if (!et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            for (int i = 0; i < al1.size(); i++) {
                                for (int j = 0; j < al3.size(); j++) {
                                    erDingQuQianShi(al1.get(i), al3.get(j));
                                }
                            }
//                            erDingQuQianShi(et_qian, et_shi);
                        if (!et_qian.isEmpty() && et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            for (int i = 0; i < al1.size(); i++) {
                                for (int j = 0; j < al4.size(); j++) {
                                    erDingQuQianGe(al1.get(i), al4.get(j));
                                }
                            }
//                            erDingQuQianGe(et_qian, et_ge);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            for (int i = 0; i < al2.size(); i++) {
                                for (int j = 0; j < al3.size(); j++) {
                                    erDingQuBaiShi(al2.get(i), al3.get(j));
                                }
                            }
//                            erDingQuBaiShi(et_bai, et_shi);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            for (int i = 0; i < al2.size(); i++) {
                                for (int j = 0; j < al4.size(); j++) {
                                    erDingQuBaiGe(al2.get(i), al4.get(j));
                                }
                            }
//                            erDingQuBaiGe(et_bai, et_ge);
                        if (et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && !et_ge.isEmpty())
                            for (int i = 0; i < al3.size(); i++) {
                                for (int j = 0; j < al4.size(); j++) {
                                    erDingQuShiGe(al3.get(i), al4.get(j));
                                }
                            }
//                            erDingQuShiGe(et_shi, et_ge);
                        break;
                    case 3:
                        erDingAll();
                        if (al5.size() != 0 && al6.size() == 0) {
                            for (int i = 0; i < al5.size(); i++) {
                                sortEditTextString_2(al5.get(i), "");
                                erDingPeiChu1(al.get(0));
                            }
                        } else if (al5.size() == 0 && al6.size() != 0) {
                            for (int j = 0; j < al6.size(); j++) {
                                sortEditTextString_2("", al6.get(j));
                                erDingPeiChu1(al.get(0));
                            }
                        } else if (al5.size() != 0 && al6.size() != 0) {
                            for (int i = 0; i < al5.size(); i++) {
                                for (int j = 0; j < al6.size(); j++) {
                                    sortEditTextString_2(al5.get(i), al6.get(j));
                                    erDingPeiChu2(al.get(0), al.get(1));
                                }
                            }
                        }
                        break;
                    case 4:
                        if (al5.size() != 0 && al6.size() == 0) {
                            for (int i = 0; i < al5.size(); i++) {
                                sortEditTextString_2(al5.get(i), "");
                                erDingPeiQu1(al.get(0));
                            }
                        } else if (al5.size() == 0 && al6.size() != 0) {
                            for (int j = 0; j < al6.size(); j++) {
                                sortEditTextString_2("", al6.get(j));
                                erDingPeiQu1(al.get(0));
                            }
                        } else if (al5.size() != 0 && al6.size() != 0) {
                            for (int i = 0; i < al5.size(); i++) {
                                for (int j = 0; j < al6.size(); j++) {
                                    sortEditTextString_2(al5.get(i), al6.get(j));
                                    erDingPeiQu2(al.get(0), al.get(1));
                                }
                            }
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
                            for (int i = 0; i < al1.size(); i++) {
                                sanDingChuQian(al1.get(i));
                            }
//                            sanDingChuQian(et_qian);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && et_ge.isEmpty())
                            for (int i = 0; i < al2.size(); i++) {
                                sanDingChuBai(al2.get(i));
                            }
//                            sanDingChuBai(et_bai);
                        if (et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            for (int i = 0; i < al3.size(); i++) {
                                sanDingChuShi(al3.get(i));
                            }
//                            sanDingChuShi(et_shi);
                        if (et_qian.isEmpty() && et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            for (int i = 0; i < al4.size(); i++) {
                                sanDingChuGe(al4.get(i));
                            }
//                            sanDingChuGe(et_ge);

                        if (!et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && et_ge.isEmpty())
                            for (int i = 0; i < al1.size(); i++) {
                                for (int j = 0; j < al2.size(); j++) {
                                    sanDingChuQianBai(al1.get(i), al2.get(j));
                                }
                            }
//                            sanDingChuQianBai(et_qian, et_bai);
                        if (!et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            for (int i = 0; i < al1.size(); i++) {
                                for (int j = 0; j < al3.size(); j++) {
                                    sanDingChuQianShi(al1.get(i), al3.get(j));
                                }
                            }
//                            sanDingChuQianShi(et_qian, et_shi);
                        if (!et_qian.isEmpty() && et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            for (int i = 0; i < al1.size(); i++) {
                                for (int j = 0; j < al4.size(); j++) {
                                    sanDingChuQianGe(al1.get(i), al4.get(j));
                                }
                            }
//                            sanDingChuQianGe(et_qian, et_ge);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            for (int i = 0; i < al2.size(); i++) {
                                for (int j = 0; j < al3.size(); j++) {
                                    sanDingChuBaiShi(al2.get(i), al3.get(j));
                                }
                            }
//                            sanDingChuBaiShi(et_bai, et_shi);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            for (int i = 0; i < al2.size(); i++) {
                                for (int j = 0; j < al4.size(); j++) {
                                    sanDingChuBaiGe(al2.get(i), al4.get(j));
                                }
                            }
//                            sanDingChuBaiGe(et_bai, et_ge);
                        if (et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && !et_ge.isEmpty())
                            for (int i = 0; i < al3.size(); i++) {
                                for (int j = 0; j < al4.size(); j++) {
                                    sanDingChuShiGe(al3.get(i), al4.get(j));
                                }
                            }
//                            sanDingChuShiGe(et_shi, et_ge);

                        if (!et_qian.isEmpty() && !et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            for (int i = 0; i < al1.size(); i++) {
                                for (int j = 0; j < al2.size(); j++) {
                                    for (int k = 0; k < al3.size(); k++) {
                                        sanDingChuQianBaiShi(al1.get(i), al2.get(j), al3.get(k));
                                    }
                                }
                            }
//                        sanDingChuQianBaiShi(et_qian, et_bai, et_shi);
                        if (!et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            for (int i = 0; i < al1.size(); i++) {
                                for (int j = 0; j < al2.size(); j++) {
                                    for (int k = 0; k < al4.size(); k++) {
                                        sanDingChuQianBaiGe(al1.get(i), al2.get(j), al4.get(k));
                                    }
                                }
                            }
//                            sanDingChuQianBaiGe(et_qian, et_bai, et_ge);
                        if (!et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && !et_ge.isEmpty())
                            for (int i = 0; i < al1.size(); i++) {
                                for (int j = 0; j < al3.size(); j++) {
                                    for (int k = 0; k < al4.size(); k++) {
                                        sanDingChuQianShiGe(al1.get(i), al3.get(j), al4.get(k));
                                    }
                                }
                            }
//                            sanDingChuQianShiGe(et_qian, et_shi, et_ge);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && !et_shi.isEmpty() && !et_ge.isEmpty())
                            for (int i = 0; i < al2.size(); i++) {
                                for (int j = 0; j < al3.size(); j++) {
                                    for (int k = 0; k < al4.size(); k++) {
                                        sanDingChuBaiShiGe(al2.get(i), al3.get(j), al4.get(k));
                                    }
                                }
                            }
                        sanDingChuBaiShiGe(et_bai, et_shi, et_ge);
                        break;
                    case 2:
                        if (!et_qian.isEmpty() && et_bai.isEmpty() && et_shi.isEmpty() && et_ge.isEmpty())
                            for (int i = 0; i < al1.size(); i++) {
                                sanDingQuQian(al1.get(i));
                            }
//                            sanDingQuQian(et_qian);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && et_ge.isEmpty())
                            for (int i = 0; i < al2.size(); i++) {
                                sanDingQuBai(al2.get(i));
                            }
//                            sanDingQuBai(et_bai);
                        if (et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            for (int i = 0; i < al3.size(); i++) {
                                sanDingQuShi(al3.get(i));
                            }
//                            sanDingQuShi(et_shi);
                        if (et_qian.isEmpty() && et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            for (int i = 0; i < al4.size(); i++) {
                                sanDingQuGe(al4.get(i));
                            }
//                            sanDingQuGe(et_ge);

                        if (!et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && et_ge.isEmpty())
                            for (int i = 0; i < al1.size(); i++) {
                                for (int j = 0; j < al2.size(); j++) {
                                    sanDingQuQianBai(al1.get(i), al2.get(j));
                                }
                            }
//                            sanDingQuQianBai(et_qian, et_bai);
                        if (!et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            for (int i = 0; i < al1.size(); i++) {
                                for (int j = 0; j < al3.size(); j++) {
                                    sanDingQuQianShi(al1.get(i), al3.get(j));
                                }
                            }
//                            sanDingQuQianShi(et_qian, et_shi);
                        if (!et_qian.isEmpty() && et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            for (int i = 0; i < al1.size(); i++) {
                                for (int j = 0; j < al4.size(); j++) {
                                    sanDingQuQianGe(al1.get(i), al4.get(j));
                                }
                            }
//                            sanDingQuQianGe(et_qian, et_ge);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            for (int i = 0; i < al2.size(); i++) {
                                for (int j = 0; j < al3.size(); j++) {
                                    sanDingQuBaiShi(al2.get(i), al3.get(j));
                                }
                            }
//                            sanDingQuBaiShi(et_bai, et_shi);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            for (int i = 0; i < al2.size(); i++) {
                                for (int j = 0; j < al4.size(); j++) {
                                    sanDingQuBaiGe(al2.get(i), al4.get(j));
                                }
                            }
//                            sanDingQuBaiGe(et_bai, et_ge);
                        if (et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && !et_ge.isEmpty())
                            for (int i = 0; i < al3.size(); i++) {
                                for (int j = 0; j < al4.size(); j++) {
                                    sanDingQuShiGe(al3.get(i), al4.get(j));
                                }
                            }
//                            sanDingQuShiGe(et_shi, et_ge);

                        if (!et_qian.isEmpty() && !et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            for (int i = 0; i < al1.size(); i++) {
                                for (int j = 0; j < al2.size(); j++) {
                                    for (int k = 0; k < al3.size(); k++) {
                                        sanDingQuQianBaiShi(al1.get(i), al2.get(j), al3.get(k));
                                    }
                                }
                            }
//                            sanDingQuQianBaiShi(et_qian, et_bai, et_shi);
                        if (!et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            for (int i = 0; i < al1.size(); i++) {
                                for (int j = 0; j < al2.size(); j++) {
                                    for (int k = 0; k < al4.size(); k++) {
                                        sanDingQuQianBaiGe(al1.get(i), al2.get(j), al4.get(k));
                                    }
                                }
                            }
//                        sanDingQuQianBaiGe(et_qian, et_bai, et_ge);
                        if (!et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && !et_ge.isEmpty())
                            for (int i = 0; i < al1.size(); i++) {
                                for (int j = 0; j < al3.size(); j++) {
                                    for (int k = 0; k < al4.size(); k++) {
                                        sanDingQuQianShiGe(al1.get(i), al3.get(j), al4.get(k));
                                    }
                                }
                            }
//                            sanDingQuQianShiGe(et_qian, et_shi, et_ge);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && !et_shi.isEmpty() && !et_ge.isEmpty())
                            for (int i = 0; i < al2.size(); i++) {
                                for (int j = 0; j < al3.size(); j++) {
                                    for (int k = 0; k < al4.size(); k++) {
                                        sanDingQuBaiShiGe(al2.get(i), al3.get(j), al4.get(k));
                                    }
                                }
                            }
//                            sanDingQuBaiShiGe(et_bai, et_shi, et_ge);
                        break;
                    case 3:
                        sanDingAll();
                        if (al7.size() != 0 && al8.size() == 0 && al9.size() == 0) {
                            for (int i = 0; i < al7.size(); i++) {
                                sortEditTextString_3(al7.get(i), "", "");
                                sanDingPeiChu1(al.get(0));
                            }
                        } else if (al7.size() == 0 && al8.size() != 0 && al9.size() == 0) {
                            for (int j = 0; j < al8.size(); j++) {
                                sortEditTextString_3("", al8.get(j), "");
                                sanDingPeiChu1(al.get(0));
                            }
                        } else if (al7.size() == 0 && al8.size() == 0 && al9.size() != 0) {
                            for (int k = 0; k < al9.size(); k++) {
                                sortEditTextString_3("", "", al9.get(k));
                                sanDingPeiChu1(al.get(0));
                            }
                        } else if (al7.size() == 0 && al8.size() != 0 && al9.size() != 0) {
                            for (int j = 0; j < al8.size(); j++) {
                                for (int k = 0; k < al9.size(); k++) {
                                    sortEditTextString_3("", al8.get(j), al9.get(k));
                                    sanDingPeiChu2(al.get(0), al.get(1));
                                }
                            }
                        } else if (al7.size() != 0 && al8.size() == 0 && al9.size() != 0) {
                            for (int i = 0; i < al7.size(); i++) {
                                for (int k = 0; k < al9.size(); k++) {
                                    sortEditTextString_3(al7.get(i), "", al9.get(k));
                                    sanDingPeiChu2(al.get(0), al.get(1));
                                }
                            }
                        } else if (al7.size() != 0 && al8.size() != 0 && al9.size() == 0) {
                            for (int i = 0; i < al7.size(); i++) {
                                for (int j = 0; j < al8.size(); j++) {
                                    sortEditTextString_3(al7.get(i), al8.get(j), "");
                                    sanDingPeiChu2(al.get(0), al.get(1));
                                }
                            }
                        } else if (al7.size() != 0 && al8.size() != 0 && al9.size() != 0) {
                            for (int i = 0; i < al7.size(); i++) {
                                for (int j = 0; j < al8.size(); j++) {
                                    for (int k = 0; k < al9.size(); k++) {
                                        sortEditTextString_3(al7.get(i), al8.get(j), al9.get(k));
                                        sanDingPeiChu3(al.get(0), al.get(1), al.get(2));
                                    }
                                }
                            }
                        }
                        break;
                    case 4:
                        if (al7.size() != 0 && al8.size() == 0 && al9.size() == 0) {
                            for (int i = 0; i < al7.size(); i++) {
                                sortEditTextString_3(al7.get(i), "", "");
                                sanDingPeiQu1(al.get(0));
                            }
                        } else if (al7.size() == 0 && al8.size() != 0 && al9.size() == 0) {
                            for (int j = 0; j < al8.size(); j++) {
                                sortEditTextString_3("", al8.get(j), "");
                                sanDingPeiQu1(al.get(0));
                            }
                        } else if (al7.size() == 0 && al8.size() == 0 && al9.size() != 0) {
                            for (int k = 0; k < al9.size(); k++) {
                                sortEditTextString_3("", "", al9.get(k));
                                sanDingPeiQu1(al.get(0));
                            }
                        } else if (al7.size() == 0 && al8.size() != 0 && al9.size() != 0) {
                            for (int j = 0; j < al8.size(); j++) {
                                for (int k = 0; k < al9.size(); k++) {
                                    sortEditTextString_3("", al8.get(j), al9.get(k));
                                    sanDingPeiQu2(al.get(0), al.get(1));
                                }
                            }
                        } else if (al7.size() != 0 && al8.size() == 0 && al9.size() != 0) {
                            for (int i = 0; i < al7.size(); i++) {
                                for (int k = 0; k < al9.size(); k++) {
                                    sortEditTextString_3(al7.get(i), "", al9.get(k));
                                    sanDingPeiQu2(al.get(0), al.get(1));
                                }
                            }
                        } else if (al7.size() != 0 && al8.size() != 0 && al9.size() == 0) {
                            for (int i = 0; i < al7.size(); i++) {
                                for (int j = 0; j < al8.size(); j++) {
                                    sortEditTextString_3(al7.get(i), al8.get(j), "");
                                    sanDingPeiQu2(al.get(0), al.get(1));
                                }
                            }
                        } else if (al7.size() != 0 && al8.size() != 0 && al9.size() != 0) {
                            for (int i = 0; i < al7.size(); i++) {
                                for (int j = 0; j < al8.size(); j++) {
                                    for (int k = 0; k < al9.size(); k++) {
                                        sortEditTextString_3(al7.get(i), al8.get(j), al9.get(k));
                                        sanDingPeiQu3(al.get(0), al.get(1), al.get(2));
                                    }
                                }
                            }
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
                            for (int i = 0; i < al1.size(); i++) {
                                siDingChuQian(al1.get(i));
                            }
//                            siDingChuQian(et_qian);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && et_ge.isEmpty())
                            for (int i = 0; i < al2.size(); i++) {
                                siDingChuBai(al2.get(i));
                            }
//                            siDingChuBai(et_bai);
                        if (et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            for (int i = 0; i < al3.size(); i++) {
                                siDingChuShi(al3.get(i));
                            }
//                            siDingChuShi(et_shi);
                        if (et_qian.isEmpty() && et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            for (int i = 0; i < al4.size(); i++) {
                                siDingChuGe(al4.get(i));
                            }
//                            siDingChuGe(et_ge);

                        if (!et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && et_ge.isEmpty())
                            for (int i = 0; i < al1.size(); i++) {
                                for (int j = 0; j < al2.size(); j++) {
                                    siDingChuQianBai(al1.get(i), al2.get(j));
                                }
                            }
//                            siDingChuQianBai(et_qian, et_bai);
                        if (!et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            for (int i = 0; i < al1.size(); i++) {
                                for (int j = 0; j < al3.size(); j++) {
                                    siDingChuQianShi(al1.get(i), al3.get(j));
                                }
                            }
//                            siDingChuQianShi(et_qian, et_shi);
                        if (!et_qian.isEmpty() && et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            for (int i = 0; i < al1.size(); i++) {
                                for (int j = 0; j < al4.size(); j++) {
                                    siDingChuQianGe(al1.get(i), al4.get(j));
                                }
                            }
//                            siDingChuQianGe(et_qian, et_ge);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            for (int i = 0; i < al2.size(); i++) {
                                for (int j = 0; j < al3.size(); j++) {
                                    siDingChuBaiShi(al2.get(i), al3.get(j));
                                }
                            }
//                            siDingChuBaiShi(et_bai, et_shi);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            for (int i = 0; i < al2.size(); i++) {
                                for (int j = 0; j < al4.size(); j++) {
                                    siDingChuBaiGe(al2.get(i), al4.get(j));
                                }
                            }
//                            siDingChuBaiGe(et_bai, et_ge);
                        if (et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && !et_ge.isEmpty())
                            for (int i = 0; i < al3.size(); i++) {
                                for (int j = 0; j < al4.size(); j++) {
                                    siDingChuShiGe(al3.get(i), al4.get(j));
                                }
                            }
//                            siDingChuShiGe(et_shi, et_ge);

                        if (!et_qian.isEmpty() && !et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            for (int i = 0; i < al1.size(); i++) {
                                for (int j = 0; j < al2.size(); j++) {
                                    for (int k = 0; k < al3.size(); k++) {
                                        siDingChuQianBaiShi(al1.get(i), al2.get(j), al3.get(k));
                                    }
                                }
                            }
//                            siDingChuQianBaiShi(et_qian, et_bai, et_shi);
                        if (!et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            for (int i = 0; i < al1.size(); i++) {
                                for (int j = 0; j < al2.size(); j++) {
                                    for (int k = 0; k < al4.size(); k++) {
                                        siDingChuQianBaiGe(al1.get(i), al2.get(j), al4.get(k));
                                    }
                                }
                            }
//                            siDingChuQianBaiGe(et_qian, et_bai, et_ge);
                        if (!et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && !et_ge.isEmpty())
                            for (int i = 0; i < al1.size(); i++) {
                                for (int j = 0; j < al3.size(); j++) {
                                    for (int k = 0; k < al4.size(); k++) {
                                        siDingChuQianShiGe(al1.get(i), al3.get(j), al4.get(k));
                                    }
                                }
                            }
//                            siDingChuQianShiGe(et_qian, et_shi, et_ge);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && !et_shi.isEmpty() && !et_ge.isEmpty())
                            for (int i = 0; i < al2.size(); i++) {
                                for (int j = 0; j < al3.size(); j++) {
                                    for (int k = 0; k < al4.size(); k++) {
                                        siDingChuBaiShiGe(al2.get(i), al3.get(j), al4.get(k));
                                    }
                                }
                            }
//                            siDingChuBaiShiGe(et_bai, et_shi, et_ge);

                        if (!et_qian.isEmpty() && !et_bai.isEmpty() && !et_shi.isEmpty() && !et_ge.isEmpty())
                            for (int i = 0; i < al1.size(); i++) {
                                for (int j = 0; j < al2.size(); j++) {
                                    for (int k = 0; k < al3.size(); k++) {
                                        for (int l = 0; l < al4.size(); l++) {
                                            siDingChuQianBaiShiGe(al1.get(i), al2.get(j), al3.get(k), al4.get(l));
                                        }
                                    }
                                }
                            }
//                        siDingChuQianBaiShiGe(et_qian, et_bai, et_shi, et_ge);
                        break;
                    case 2:
                        if (!et_qian.isEmpty() && et_bai.isEmpty() && et_shi.isEmpty() && et_ge.isEmpty())
                            for (int i = 0; i < al1.size(); i++) {
                                siDingQuQian(al1.get(i));
                            }
//                            siDingQuQian(et_qian);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && et_ge.isEmpty())
                            for (int i = 0; i < al2.size(); i++) {
                                siDingQuBai(al2.get(i));
                            }
//                            siDingQuBai(et_bai);
                        if (et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            for (int i = 0; i < al3.size(); i++) {
                                siDingQuShi(al3.get(i));
                            }
//                            siDingQuShi(et_shi);
                        if (et_qian.isEmpty() && et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            for (int i = 0; i < al4.size(); i++) {
                                siDingQuGe(al4.get(i));
                            }
//                            siDingQuGe(et_ge);

                        if (!et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && et_ge.isEmpty())
                            for (int i = 0; i < al1.size(); i++) {
                                for (int j = 0; j < al2.size(); j++) {
                                    siDingQuQianBai(al1.get(i), al2.get(j));
                                }
                            }
//                            siDingQuQianBai(et_qian, et_bai);
                        if (!et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            for (int i = 0; i < al1.size(); i++) {
                                for (int j = 0; j < al3.size(); j++) {
                                    siDingQuQianShi(al1.get(i), al3.get(j));
                                }
                            }
//                            siDingQuQianShi(et_qian, et_shi);
                        if (!et_qian.isEmpty() && et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            for (int i = 0; i < al1.size(); i++) {
                                for (int j = 0; j < al4.size(); j++) {
                                    siDingQuQianGe(al1.get(i), al4.get(j));
                                }
                            }
//                            siDingQuQianGe(et_qian, et_ge);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            for (int i = 0; i < al2.size(); i++) {
                                for (int j = 0; j < al3.size(); j++) {
                                    siDingQuBaiShi(al2.get(i), al3.get(j));
                                }
                            }
//                            siDingQuBaiShi(et_bai, et_shi);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            for (int i = 0; i < al2.size(); i++) {
                                for (int j = 0; j < al4.size(); j++) {
                                    siDingQuBaiGe(al2.get(i), al4.get(j));
                                }
                            }
//                            siDingQuBaiGe(et_bai, et_ge);
                        if (et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && !et_ge.isEmpty())
                            for (int i = 0; i < al3.size(); i++) {
                                for (int j = 0; j < al4.size(); j++) {
                                    siDingQuShiGe(al3.get(i), al4.get(j));
                                }
                            }
//                            siDingQuShiGe(et_shi, et_ge);

                        if (!et_qian.isEmpty() && !et_bai.isEmpty() && !et_shi.isEmpty() && et_ge.isEmpty())
                            for (int i = 0; i < al1.size(); i++) {
                                for (int j = 0; j < al2.size(); j++) {
                                    for (int k = 0; k < al3.size(); k++) {
                                        siDingQuQianBaiShi(al1.get(i), al2.get(j), al3.get(k));
                                    }
                                }
                            }
//                            siDingQuQianBaiShi(et_qian, et_bai, et_shi);
                        if (!et_qian.isEmpty() && !et_bai.isEmpty() && et_shi.isEmpty() && !et_ge.isEmpty())
                            for (int i = 0; i < al1.size(); i++) {
                                for (int j = 0; j < al2.size(); j++) {
                                    for (int k = 0; k < al4.size(); k++) {
                                        siDingQuQianBaiGe(al1.get(i), al2.get(j), al4.get(k));
                                    }
                                }
                            }
//                            siDingQuQianBaiGe(et_qian, et_bai, et_ge);
                        if (!et_qian.isEmpty() && et_bai.isEmpty() && !et_shi.isEmpty() && !et_ge.isEmpty())
                            for (int i = 0; i < al1.size(); i++) {
                                for (int j = 0; j < al3.size(); j++) {
                                    for (int k = 0; k < al4.size(); k++) {
                                        siDingQuQianShiGe(al1.get(i), al3.get(j), al4.get(k));
                                    }
                                }
                            }
//                            siDingQuQianShiGe(et_qian, et_shi, et_ge);
                        if (et_qian.isEmpty() && !et_bai.isEmpty() && !et_shi.isEmpty() && !et_ge.isEmpty())
                            for (int i = 0; i < al2.size(); i++) {
                                for (int j = 0; j < al3.size(); j++) {
                                    for (int k = 0; k < al4.size(); k++) {
                                        siDingQuBaiShiGe(al2.get(i), al3.get(j), al4.get(k));
                                    }
                                }
                            }
//                            siDingQuBaiShiGe(et_bai, et_shi, et_ge);

                        if (!et_qian.isEmpty() && !et_bai.isEmpty() && !et_shi.isEmpty() && !et_ge.isEmpty())
                            for (int i = 0; i < al1.size(); i++) {
                                for (int j = 0; j < al2.size(); j++) {
                                    for (int k = 0; k < al3.size(); k++) {
                                        for (int l = 0; l < al4.size(); l++) {
                                            siDingQuQianBaiShiGe(al1.get(i), al2.get(j), al3.get(k), al4.get(l));
                                        }
                                    }
                                }
                            }
                        siDingQuQianBaiShiGe(et_qian, et_bai, et_shi, et_ge);
                        break;
                    case 3:
                        siDingAll();
                        if (al10.size() == 0 && al11.size() != 0 && al12.size() != 0 && al13.size() != 0) {
                            for (int j = 0; j < al11.size(); j++) {
                                for (int k = 0; k < al12.size(); k++) {
                                    for (int l = 0; l < al13.size(); l++) {
                                        sortEditTextString_4("", al11.get(j), al12.get(k), al13.get(l));
                                        siDingPeiChu3(al.get(0), al.get(1), al.get(2));
                                    }
                                }
                            }
                        } else if (al10.size() != 0 && al11.size() == 0 && al12.size() != 0 && al13.size() != 0) {
                            for (int i = 0; i < al10.size(); i++) {
                                for (int k = 0; k < al12.size(); k++) {
                                    for (int l = 0; l < al13.size(); l++) {
                                        sortEditTextString_4(al10.get(i), "", al12.get(k), al13.get(l));
                                        siDingPeiChu3(al.get(0), al.get(1), al.get(2));
                                    }
                                }
                            }
                        } else if (al10.size() != 0 && al11.size() != 0 && al12.size() == 0 && al13.size() != 0) {
                            for (int i = 0; i < al10.size(); i++) {
                                for (int j = 0; j < al11.size(); j++) {
                                    for (int l = 0; l < al13.size(); l++) {
                                        sortEditTextString_4(al10.get(i), al11.get(j), "", al13.get(l));
                                        siDingPeiChu3(al.get(0), al.get(1), al.get(2));
                                    }
                                }
                            }
                        } else if (al10.size() != 0 && al11.size() != 0 && al12.size() != 0 && al13.size() == 0) {
                            for (int i = 0; i < al10.size(); i++) {
                                for (int j = 0; j < al11.size(); j++) {
                                    for (int k = 0; k < al12.size(); k++) {
                                        sortEditTextString_4(al10.get(i), al11.get(j), al12.get(k), "");
                                        siDingPeiChu3(al.get(0), al.get(1), al.get(2));
                                    }
                                }
                            }
                        } else if (al10.size() == 0 && al11.size() == 0 && al12.size() != 0 && al13.size() != 0) {
                            for (int k = 0; k < al12.size(); k++) {
                                for (int l = 0; l < al13.size(); l++) {
                                    sortEditTextString_4("", "", al12.get(k), al13.get(l));
                                    siDingPeiChu2(al.get(0), al.get(1));
                                }
                            }
                        } else if (al10.size() == 0 && al11.size() != 0 && al12.size() == 0 && al13.size() != 0) {
                            for (int j = 0; j < al11.size(); j++) {
                                for (int l = 0; l < al13.size(); l++) {
                                    sortEditTextString_4("", al11.get(j), "", al13.get(l));
                                    siDingPeiChu2(al.get(0), al.get(1));
                                }
                            }
                        } else if (al10.size() == 0 && al11.size() != 0 && al12.size() != 0 && al13.size() == 0) {
                            for (int j = 0; j < al11.size(); j++) {
                                for (int k = 0; k < al12.size(); k++) {
                                    sortEditTextString_4("", al11.get(j), al12.get(k), "");
                                    siDingPeiChu2(al.get(0), al.get(1));
                                }
                            }
                        } else if (al10.size() != 0 && al11.size() == 0 && al12.size() == 0 && al13.size() != 0) {
                            for (int i = 0; i < al10.size(); i++) {
                                for (int l = 0; l < al13.size(); l++) {
                                    sortEditTextString_4(al10.get(i), "", "", al13.get(l));
                                    siDingPeiChu2(al.get(0), al.get(1));
                                }
                            }
                        } else if (al10.size() != 0 && al11.size() == 0 && al12.size() != 0 && al13.size() == 0) {
                            for (int i = 0; i < al10.size(); i++) {
                                for (int k = 0; k < al12.size(); k++) {
                                    sortEditTextString_4(al10.get(i), "", al12.get(k), "");
                                    siDingPeiChu2(al.get(0), al.get(1));
                                }
                            }
                        } else if (al10.size() != 0 && al11.size() != 0 && al12.size() == 0 && al13.size() == 0) {
                            for (int i = 0; i < al10.size(); i++) {
                                for (int j = 0; j < al11.size(); j++) {
                                    sortEditTextString_4(al10.get(i), al11.get(j), "", "");
                                    siDingPeiChu2(al.get(0), al.get(1));
                                }
                            }
                        } else if (al10.size() == 0 && al11.size() == 0 && al12.size() == 0 && al13.size() != 0) {
                            for (int l = 0; l < al13.size(); l++) {
                                sortEditTextString_4("", "", "", al13.get(l));
                                siDingPeiChu1(al.get(0));
                            }
                        } else if (al10.size() == 0 && al11.size() == 0 && al12.size() != 0 && al13.size() == 0) {
                            for (int k = 0; k < al12.size(); k++) {
                                sortEditTextString_4("", "", al12.get(k), "");
                                siDingPeiChu1(al.get(0));
                            }
                        } else if (al10.size() == 0 && al11.size() != 0 && al12.size() == 0 && al13.size() == 0) {
                            for (int j = 0; j < al11.size(); j++) {
                                sortEditTextString_4("", al11.get(j), "", "");
                                siDingPeiChu1(al.get(0));
                            }
                        } else if (al10.size() != 0 && al11.size() == 0 && al12.size() == 0 && al13.size() == 0) {
                            for (int i = 0; i < al10.size(); i++) {
                                sortEditTextString_4(al10.get(i), "", "", "");
                                siDingPeiChu1(al.get(0));
                            }
                        } else if (al10.size() != 0 && al11.size() != 0 && al12.size() != 0 && al13.size() != 0) {
                            for (int i = 0; i < al10.size(); i++) {
                                for (int j = 0; j < al11.size(); j++) {
                                    for (int k = 0; k < al12.size(); k++) {
                                        for (int l = 0; l < al13.size(); l++) {
                                            sortEditTextString_4(al10.get(i), al11.get(j), al12.get(k), al13.get(l));
                                            siDingPeiChu4(al.get(0), al.get(1), al.get(2), al.get(3));
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    case 4:
                        if (al10.size() == 0 && al11.size() != 0 && al12.size() != 0 && al13.size() != 0) {
                            for (int j = 0; j < al11.size(); j++) {
                                for (int k = 0; k < al12.size(); k++) {
                                    for (int l = 0; l < al13.size(); l++) {
                                        sortEditTextString_4("", al11.get(j), al12.get(k), al13.get(l));
                                        siDingPeiQu3(al.get(0), al.get(1), al.get(2));
                                    }
                                }
                            }
                        } else if (al10.size() != 0 && al11.size() == 0 && al12.size() != 0 && al13.size() != 0) {
                            for (int i = 0; i < al10.size(); i++) {
                                for (int k = 0; k < al12.size(); k++) {
                                    for (int l = 0; l < al13.size(); l++) {
                                        sortEditTextString_4(al10.get(i), "", al12.get(k), al13.get(l));
                                        siDingPeiQu3(al.get(0), al.get(1), al.get(2));
                                    }
                                }
                            }
                        } else if (al10.size() != 0 && al11.size() != 0 && al12.size() == 0 && al13.size() != 0) {
                            for (int i = 0; i < al10.size(); i++) {
                                for (int j = 0; j < al11.size(); j++) {
                                    for (int l = 0; l < al13.size(); l++) {
                                        sortEditTextString_4(al10.get(i), al11.get(j), "", al13.get(l));
                                        siDingPeiQu3(al.get(0), al.get(1), al.get(2));
                                    }
                                }
                            }
                        } else if (al10.size() != 0 && al11.size() != 0 && al12.size() != 0 && al13.size() == 0) {
                            for (int i = 0; i < al10.size(); i++) {
                                for (int j = 0; j < al11.size(); j++) {
                                    for (int k = 0; k < al12.size(); k++) {
                                        sortEditTextString_4(al10.get(i), al11.get(j), al12.get(k), "");
                                        siDingPeiQu3(al.get(0), al.get(1), al.get(2));
                                    }
                                }
                            }
                        } else if (al10.size() == 0 && al11.size() == 0 && al12.size() != 0 && al13.size() != 0) {
                            for (int k = 0; k < al12.size(); k++) {
                                for (int l = 0; l < al13.size(); l++) {
                                    sortEditTextString_4("", "", al12.get(k), al13.get(l));
                                    siDingPeiQu2(al.get(0), al.get(1));
                                }
                            }
                        } else if (al10.size() == 0 && al11.size() != 0 && al12.size() == 0 && al13.size() != 0) {
                            for (int j = 0; j < al11.size(); j++) {
                                for (int l = 0; l < al13.size(); l++) {
                                    sortEditTextString_4("", al11.get(j), "", al13.get(l));
                                    siDingPeiQu2(al.get(0), al.get(1));
                                }
                            }
                        } else if (al10.size() == 0 && al11.size() != 0 && al12.size() != 0 && al13.size() == 0) {
                            for (int j = 0; j < al11.size(); j++) {
                                for (int k = 0; k < al12.size(); k++) {
                                    sortEditTextString_4("", al11.get(j), al12.get(k), "");
                                    siDingPeiQu2(al.get(0), al.get(1));
                                }
                            }
                        } else if (al10.size() != 0 && al11.size() == 0 && al12.size() == 0 && al13.size() != 0) {
                            for (int i = 0; i < al10.size(); i++) {
                                for (int l = 0; l < al13.size(); l++) {
                                    sortEditTextString_4(al10.get(i), "", "", al13.get(l));
                                    siDingPeiQu2(al.get(0), al.get(1));
                                }
                            }
                        } else if (al10.size() != 0 && al11.size() == 0 && al12.size() != 0 && al13.size() == 0) {
                            for (int i = 0; i < al10.size(); i++) {
                                for (int k = 0; k < al12.size(); k++) {
                                    sortEditTextString_4(al10.get(i), "", al12.get(k), "");
                                    siDingPeiQu2(al.get(0), al.get(1));
                                }
                            }
                        } else if (al10.size() != 0 && al11.size() != 0 && al12.size() == 0 && al13.size() == 0) {
                            for (int i = 0; i < al10.size(); i++) {
                                for (int j = 0; j < al11.size(); j++) {
                                    sortEditTextString_4(al10.get(i), al11.get(j), "", "");
                                    siDingPeiQu2(al.get(0), al.get(1));
                                }
                            }
                        } else if (al10.size() == 0 && al11.size() == 0 && al12.size() == 0 && al13.size() != 0) {
                            for (int l = 0; l < al13.size(); l++) {
                                sortEditTextString_4("", "", "", al13.get(l));
                                siDingPeiQu1(al.get(0));
                            }
                        } else if (al10.size() == 0 && al11.size() == 0 && al12.size() != 0 && al13.size() == 0) {
                            for (int k = 0; k < al12.size(); k++) {
                                sortEditTextString_4("", "", al12.get(k), "");
                                siDingPeiQu1(al.get(0));
                            }
                        } else if (al10.size() == 0 && al11.size() != 0 && al12.size() == 0 && al13.size() == 0) {
                            for (int j = 0; j < al11.size(); j++) {
                                sortEditTextString_4("", al11.get(j), "", "");
                                siDingPeiQu1(al.get(0));
                            }
                        } else if (al10.size() != 0 && al11.size() == 0 && al12.size() == 0 && al13.size() == 0) {
                            for (int i = 0; i < al10.size(); i++) {
                                sortEditTextString_4(al10.get(i), "", "", "");
                                siDingPeiQu1(al.get(0));
                            }
                        } else if (al10.size() != 0 && al11.size() != 0 && al12.size() != 0 && al13.size() != 0) {
                            for (int i = 0; i < al10.size(); i++) {
                                for (int j = 0; j < al11.size(); j++) {
                                    for (int k = 0; k < al12.size(); k++) {
                                        for (int l = 0; l < al13.size(); l++) {
                                            sortEditTextString_4(al10.get(i), al11.get(j), al12.get(k), al13.get(l));
                                            siDingPeiQu4(al.get(0), al.get(1), al.get(2), al.get(3));
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    default:
                        break;
                }
                break;
            case 4:
                switch (gameSet) {
                    case 5:
                        erXianAll();
                        if (al5.size() != 0 && al6.size() == 0) {
                            for (int i = 0; i < al5.size(); i++) {
                                sortEditTextString_2(al5.get(i), "");
                                erXianPeiChu1(al.get(0));
                            }
                        } else if (al5.size() == 0 && al6.size() != 0) {
                            for (int j = 0; j < al6.size(); j++) {
                                sortEditTextString_2("", al6.get(j));
                                erXianPeiChu1(al.get(0));
                            }
                        } else if (al5.size() != 0 && al6.size() != 0) {
                            for (int i = 0; i < al5.size(); i++) {
                                for (int j = 0; j < al6.size(); j++) {
                                    sortEditTextString_2(al5.get(i), al6.get(j));
                                    erXianPeiChu2(al.get(0), al.get(1));
                                }
                            }
                        }
                        break;
                    case 6:
                        if (al5.size() != 0 && al6.size() == 0) {
                            for (int i = 0; i < al5.size(); i++) {
                                sortEditTextString_2(al5.get(i), "");
                                erXianPeiQu1(al.get(0));
                            }
                        } else if (al5.size() == 0 && al6.size() != 0) {
                            for (int j = 0; j < al6.size(); j++) {
                                sortEditTextString_2("", al6.get(j));
                                erXianPeiQu1(al.get(0));
                            }
                        } else if (al5.size() != 0 && al6.size() != 0) {
                            for (int i = 0; i < al5.size(); i++) {
                                for (int j = 0; j < al6.size(); j++) {
                                    sortEditTextString_2(al5.get(i), al6.get(j));
                                    erXianPeiQu2(al.get(0), al.get(1));
                                }
                            }
                        }
                        break;
                    default:
                        break;
                }
                break;
            case 5:
                switch (gameSet) {
                    case 5:
                        sanXianAll();
                        if (al7.size() != 0 && al8.size() == 0 && al9.size() == 0) {
                            for (int i = 0; i < al7.size(); i++) {
                                sortEditTextString_3(al7.get(i), "", "");
                                sanXianPeiChu1(al.get(0));
                            }
                        } else if (al7.size() == 0 && al8.size() != 0 && al9.size() == 0) {
                            for (int j = 0; j < al8.size(); j++) {
                                sortEditTextString_3("", al8.get(j), "");
                                sanXianPeiChu1(al.get(0));
                            }
                        } else if (al7.size() == 0 && al8.size() == 0 && al9.size() != 0) {
                            for (int k = 0; k < al9.size(); k++) {
                                sortEditTextString_3("", "", al9.get(k));
                                sanXianPeiChu1(al.get(0));
                            }
                        } else if (al7.size() == 0 && al8.size() != 0 && al9.size() != 0) {
                            for (int j = 0; j < al8.size(); j++) {
                                for (int k = 0; k < al9.size(); k++) {
                                    sortEditTextString_3("", al8.get(j), al9.get(k));
                                    sanXianPeiChu2(al.get(0), al.get(1));
                                }
                            }
                        } else if (al7.size() != 0 && al8.size() == 0 && al9.size() != 0) {
                            for (int i = 0; i < al7.size(); i++) {
                                for (int k = 0; k < al9.size(); k++) {
                                    sortEditTextString_3(al7.get(i), "", al9.get(k));
                                    sanXianPeiChu2(al.get(0), al.get(1));
                                }
                            }
                        } else if (al7.size() != 0 && al8.size() != 0 && al9.size() == 0) {
                            for (int i = 0; i < al7.size(); i++) {
                                for (int j = 0; j < al8.size(); j++) {
                                    sortEditTextString_3(al7.get(i), al8.get(j), "");
                                    sanXianPeiChu2(al.get(0), al.get(1));
                                }
                            }
                        } else if (al7.size() != 0 && al8.size() != 0 && al9.size() != 0) {
                            for (int i = 0; i < al7.size(); i++) {
                                for (int j = 0; j < al8.size(); j++) {
                                    for (int k = 0; k < al9.size(); k++) {
                                        sortEditTextString_3(al7.get(i), al8.get(j), al9.get(k));
                                        sanXianPeiChu3(al.get(0), al.get(1), al.get(2));
                                    }
                                }
                            }
                        }
                        break;
                    case 6:
                        if (al7.size() != 0 && al8.size() == 0 && al9.size() == 0) {
                            for (int i = 0; i < al7.size(); i++) {
                                sortEditTextString_3(al7.get(i), "", "");
                                sanXianPeiQu1(al.get(0));
                            }
                        } else if (al7.size() == 0 && al8.size() != 0 && al9.size() == 0) {
                            for (int j = 0; j < al8.size(); j++) {
                                sortEditTextString_3("", al8.get(j), "");
                                sanXianPeiQu1(al.get(0));
                            }
                        } else if (al7.size() == 0 && al8.size() == 0 && al9.size() != 0) {
                            for (int k = 0; k < al9.size(); k++) {
                                sortEditTextString_3("", "", al9.get(k));
                                sanXianPeiQu1(al.get(0));
                            }
                        } else if (al7.size() == 0 && al8.size() != 0 && al9.size() != 0) {
                            for (int j = 0; j < al8.size(); j++) {
                                for (int k = 0; k < al9.size(); k++) {
                                    sortEditTextString_3("", al8.get(j), al9.get(k));
                                    sanXianPeiQu2(al.get(0), al.get(1));
                                }
                            }
                        } else if (al7.size() != 0 && al8.size() == 0 && al9.size() != 0) {
                            for (int i = 0; i < al7.size(); i++) {
                                for (int k = 0; k < al9.size(); k++) {
                                    sortEditTextString_3(al7.get(i), "", al9.get(k));
                                    sanXianPeiQu2(al.get(0), al.get(1));
                                }
                            }
                        } else if (al7.size() != 0 && al8.size() != 0 && al9.size() == 0) {
                            for (int i = 0; i < al7.size(); i++) {
                                for (int j = 0; j < al8.size(); j++) {
                                    sortEditTextString_3(al7.get(i), al8.get(j), "");
                                    sanXianPeiQu2(al.get(0), al.get(1));
                                }
                            }
                        } else if (al7.size() != 0 && al8.size() != 0 && al9.size() != 0) {
                            for (int i = 0; i < al7.size(); i++) {
                                for (int j = 0; j < al8.size(); j++) {
                                    for (int k = 0; k < al9.size(); k++) {
                                        sortEditTextString_3(al7.get(i), al8.get(j), al9.get(k));
                                        sanXianPeiQu3(al.get(0), al.get(1), al.get(2));
                                    }
                                }
                            }
                        }
                        break;
                    default:
                        break;
                }
                break;
            case 6:
                switch (gameSet) {
                    case 5:
                        siXianAll();
                        if (al10.size() == 0 && al11.size() != 0 && al12.size() != 0 && al13.size() != 0) {
                            for (int j = 0; j < al11.size(); j++) {
                                for (int k = 0; k < al12.size(); k++) {
                                    for (int l = 0; l < al13.size(); l++) {
                                        sortEditTextString_4("", al11.get(j), al12.get(k), al13.get(l));
                                        siXianPeiChu3(al.get(0), al.get(1), al.get(2));
                                    }
                                }
                            }
                        } else if (al10.size() != 0 && al11.size() == 0 && al12.size() != 0 && al13.size() != 0) {
                            for (int i = 0; i < al10.size(); i++) {
                                for (int k = 0; k < al12.size(); k++) {
                                    for (int l = 0; l < al13.size(); l++) {
                                        sortEditTextString_4(al10.get(i), "", al12.get(k), al13.get(l));
                                        siXianPeiChu3(al.get(0), al.get(1), al.get(2));
                                    }
                                }
                            }
                        } else if (al10.size() != 0 && al11.size() != 0 && al12.size() == 0 && al13.size() != 0) {
                            for (int i = 0; i < al10.size(); i++) {
                                for (int j = 0; j < al11.size(); j++) {
                                    for (int l = 0; l < al13.size(); l++) {
                                        sortEditTextString_4(al10.get(i), al11.get(j), "", al13.get(l));
                                        siXianPeiChu3(al.get(0), al.get(1), al.get(2));
                                    }
                                }
                            }
                        } else if (al10.size() != 0 && al11.size() != 0 && al12.size() != 0 && al13.size() == 0) {
                            for (int i = 0; i < al10.size(); i++) {
                                for (int j = 0; j < al11.size(); j++) {
                                    for (int k = 0; k < al12.size(); k++) {
                                        sortEditTextString_4(al10.get(i), al11.get(j), al12.get(k), "");
                                        siXianPeiChu3(al.get(0), al.get(1), al.get(2));
                                    }
                                }
                            }
                        } else if (al10.size() == 0 && al11.size() == 0 && al12.size() != 0 && al13.size() != 0) {
                            for (int k = 0; k < al12.size(); k++) {
                                for (int l = 0; l < al13.size(); l++) {
                                    sortEditTextString_4("", "", al12.get(k), al13.get(l));
                                    siXianPeiChu2(al.get(0), al.get(1));
                                }
                            }
                        } else if (al10.size() == 0 && al11.size() != 0 && al12.size() == 0 && al13.size() != 0) {
                            for (int j = 0; j < al11.size(); j++) {
                                for (int l = 0; l < al13.size(); l++) {
                                    sortEditTextString_4("", al11.get(j), "", al13.get(l));
                                    siXianPeiChu2(al.get(0), al.get(1));
                                }
                            }
                        } else if (al10.size() == 0 && al11.size() != 0 && al12.size() != 0 && al13.size() == 0) {
                            for (int j = 0; j < al11.size(); j++) {
                                for (int k = 0; k < al12.size(); k++) {
                                    sortEditTextString_4("", al11.get(j), al12.get(k), "");
                                    siXianPeiChu2(al.get(0), al.get(1));
                                }
                            }
                        } else if (al10.size() != 0 && al11.size() == 0 && al12.size() == 0 && al13.size() != 0) {
                            for (int i = 0; i < al10.size(); i++) {
                                for (int l = 0; l < al13.size(); l++) {
                                    sortEditTextString_4(al10.get(i), "", "", al13.get(l));
                                    siXianPeiChu2(al.get(0), al.get(1));
                                }
                            }
                        } else if (al10.size() != 0 && al11.size() == 0 && al12.size() != 0 && al13.size() == 0) {
                            for (int i = 0; i < al10.size(); i++) {
                                for (int k = 0; k < al12.size(); k++) {
                                    sortEditTextString_4(al10.get(i), "", al12.get(k), "");
                                    siXianPeiChu2(al.get(0), al.get(1));
                                }
                            }
                        } else if (al10.size() != 0 && al11.size() != 0 && al12.size() == 0 && al13.size() == 0) {
                            for (int i = 0; i < al10.size(); i++) {
                                for (int j = 0; j < al11.size(); j++) {
                                    sortEditTextString_4(al10.get(i), al11.get(j), "", "");
                                    siXianPeiChu2(al.get(0), al.get(1));
                                }
                            }
                        } else if (al10.size() == 0 && al11.size() == 0 && al12.size() == 0 && al13.size() != 0) {
                            for (int l = 0; l < al13.size(); l++) {
                                sortEditTextString_4("", "", "", al13.get(l));
                                siXianPeiChu1(al.get(0));
                            }
                        } else if (al10.size() == 0 && al11.size() == 0 && al12.size() != 0 && al13.size() == 0) {
                            for (int k = 0; k < al12.size(); k++) {
                                sortEditTextString_4("", "", al12.get(k), "");
                                siXianPeiChu1(al.get(0));
                            }
                        } else if (al10.size() == 0 && al11.size() != 0 && al12.size() == 0 && al13.size() == 0) {
                            for (int j = 0; j < al11.size(); j++) {
                                sortEditTextString_4("", al11.get(j), "", "");
                                siXianPeiChu1(al.get(0));
                            }
                        } else if (al10.size() != 0 && al11.size() == 0 && al12.size() == 0 && al13.size() == 0) {
                            for (int i = 0; i < al10.size(); i++) {
                                sortEditTextString_4(al10.get(i), "", "", "");
                                siXianPeiChu1(al.get(0));
                            }
                        } else if (al10.size() != 0 && al11.size() != 0 && al12.size() != 0 && al13.size() != 0) {
                            for (int i = 0; i < al10.size(); i++) {
                                for (int j = 0; j < al11.size(); j++) {
                                    for (int k = 0; k < al12.size(); k++) {
                                        for (int l = 0; l < al13.size(); l++) {
                                            sortEditTextString_4(al10.get(i), al11.get(j), al12.get(k), al13.get(l));
                                            siXianPeiChu4(al.get(0), al.get(1), al.get(2), al.get(3));
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    case 6:
                        if (al10.size() == 0 && al11.size() != 0 && al12.size() != 0 && al13.size() != 0) {
                            for (int j = 0; j < al11.size(); j++) {
                                for (int k = 0; k < al12.size(); k++) {
                                    for (int l = 0; l < al13.size(); l++) {
                                        sortEditTextString_4("", al11.get(j), al12.get(k), al13.get(l));
                                        siXianPeiQu3(al.get(0), al.get(1), al.get(2));
                                    }
                                }
                            }
                        } else if (al10.size() != 0 && al11.size() == 0 && al12.size() != 0 && al13.size() != 0) {
                            for (int i = 0; i < al10.size(); i++) {
                                for (int k = 0; k < al12.size(); k++) {
                                    for (int l = 0; l < al13.size(); l++) {
                                        sortEditTextString_4(al10.get(i), "", al12.get(k), al13.get(l));
                                        siXianPeiQu3(al.get(0), al.get(1), al.get(2));
                                    }
                                }
                            }
                        } else if (al10.size() != 0 && al11.size() != 0 && al12.size() == 0 && al13.size() != 0) {
                            for (int i = 0; i < al10.size(); i++) {
                                for (int j = 0; j < al11.size(); j++) {
                                    for (int l = 0; l < al13.size(); l++) {
                                        sortEditTextString_4(al10.get(i), al11.get(j), "", al13.get(l));
                                        siXianPeiQu3(al.get(0), al.get(1), al.get(2));
                                    }
                                }
                            }
                        } else if (al10.size() != 0 && al11.size() != 0 && al12.size() != 0 && al13.size() == 0) {
                            for (int i = 0; i < al10.size(); i++) {
                                for (int j = 0; j < al11.size(); j++) {
                                    for (int k = 0; k < al12.size(); k++) {
                                        sortEditTextString_4(al10.get(i), al11.get(j), al12.get(k), "");
                                        siXianPeiQu3(al.get(0), al.get(1), al.get(2));
                                    }
                                }
                            }
                        } else if (al10.size() == 0 && al11.size() == 0 && al12.size() != 0 && al13.size() != 0) {
                            for (int k = 0; k < al12.size(); k++) {
                                for (int l = 0; l < al13.size(); l++) {
                                    sortEditTextString_4("", "", al12.get(k), al13.get(l));
                                    siXianPeiQu2(al.get(0), al.get(1));
                                }
                            }
                        } else if (al10.size() == 0 && al11.size() != 0 && al12.size() == 0 && al13.size() != 0) {
                            for (int j = 0; j < al11.size(); j++) {
                                for (int l = 0; l < al13.size(); l++) {
                                    sortEditTextString_4("", al11.get(j), "", al13.get(l));
                                    siXianPeiQu2(al.get(0), al.get(1));
                                }
                            }
                        } else if (al10.size() == 0 && al11.size() != 0 && al12.size() != 0 && al13.size() == 0) {
                            for (int j = 0; j < al11.size(); j++) {
                                for (int k = 0; k < al12.size(); k++) {
                                    sortEditTextString_4("", al11.get(j), al12.get(k), "");
                                    siXianPeiQu2(al.get(0), al.get(1));
                                }
                            }
                        } else if (al10.size() != 0 && al11.size() == 0 && al12.size() == 0 && al13.size() != 0) {
                            for (int i = 0; i < al10.size(); i++) {
                                for (int l = 0; l < al13.size(); l++) {
                                    sortEditTextString_4(al10.get(i), "", "", al13.get(l));
                                    siXianPeiQu2(al.get(0), al.get(1));
                                }
                            }
                        } else if (al10.size() != 0 && al11.size() == 0 && al12.size() != 0 && al13.size() == 0) {
                            for (int i = 0; i < al10.size(); i++) {
                                for (int k = 0; k < al12.size(); k++) {
                                    sortEditTextString_4(al10.get(i), "", al12.get(k), "");
                                    siXianPeiQu2(al.get(0), al.get(1));
                                }
                            }
                        } else if (al10.size() != 0 && al11.size() != 0 && al12.size() == 0 && al13.size() == 0) {
                            for (int i = 0; i < al10.size(); i++) {
                                for (int j = 0; j < al11.size(); j++) {
                                    sortEditTextString_4(al10.get(i), al11.get(j), "", "");
                                    siXianPeiQu2(al.get(0), al.get(1));
                                }
                            }
                        } else if (al10.size() == 0 && al11.size() == 0 && al12.size() == 0 && al13.size() != 0) {
                            for (int l = 0; l < al13.size(); l++) {
                                sortEditTextString_4("", "", "", al13.get(l));
                                siXianPeiQu1(al.get(0));
                            }
                        } else if (al10.size() == 0 && al11.size() == 0 && al12.size() != 0 && al13.size() == 0) {
                            for (int k = 0; k < al12.size(); k++) {
                                sortEditTextString_4("", "", al12.get(k), "");
                                siXianPeiQu1(al.get(0));
                            }
                        } else if (al10.size() == 0 && al11.size() != 0 && al12.size() == 0 && al13.size() == 0) {
                            for (int j = 0; j < al11.size(); j++) {
                                sortEditTextString_4("", al11.get(j), "", "");
                                siXianPeiQu1(al.get(0));
                            }
                        } else if (al10.size() != 0 && al11.size() == 0 && al12.size() == 0 && al13.size() == 0) {
                            for (int i = 0; i < al10.size(); i++) {
                                sortEditTextString_4(al10.get(i), "", "", "");
                                siXianPeiQu1(al.get(0));
                            }
                        } else if (al10.size() != 0 && al11.size() != 0 && al12.size() != 0 && al13.size() != 0) {
                            for (int i = 0; i < al10.size(); i++) {
                                for (int j = 0; j < al11.size(); j++) {
                                    for (int k = 0; k < al12.size(); k++) {
                                        for (int l = 0; l < al13.size(); l++) {
                                            sortEditTextString_4(al10.get(i), al11.get(j), al12.get(k), al13.get(l));
                                            siXianPeiQu4(al.get(0), al.get(1), al.get(2), al.get(3));
                                        }
                                    }
                                }
                            }
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
