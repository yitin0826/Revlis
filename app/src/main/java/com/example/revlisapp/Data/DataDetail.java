package com.example.revlisapp.Data;

import java.util.ArrayList;

public class DataDetail {
    /** Detail的資料列 **/

    public static final int PARENT_ITEM = 0;
    public static final int CHILD_ITEM = 1;
    private String title;
    private String value;
    private String meaning;

    public DataDetail(String title, String value){
        this.title = title;
        this.value = value;
    }

    public String getTitle(){
        return title;
    }

    public String getValue(){
        return value;
    }

    public static ArrayList<DataDetail> getList_AI(){
        ArrayList<DataDetail> list = new ArrayList<>();
        list.add(new DataDetail("心臟年紀 Heart Age","35"));
        list.add(new DataDetail("情緒AI Emotion AI","155"));
        return list;
    }

    public static ArrayList<DataDetail> getList_Emotional(){
        ArrayList<DataDetail> list = new ArrayList<>();
        list.add(new DataDetail("活力指數","35"));
        list.add(new DataDetail("壓力","155"));
        list.add(new DataDetail("交感","155"));
        list.add(new DataDetail("情緒","155"));
        return list;
    }

    public static ArrayList<DataDetail> getList_Body_AI(){
        ArrayList<DataDetail> list = new ArrayList<>();
        list.add(new DataDetail("舒張壓","35"));
        list.add(new DataDetail("收縮壓","155"));
        list.add(new DataDetail("血糖","155"));
        list.add(new DataDetail("糖尿病前期","155"));
        list.add(new DataDetail("用藥","155"));
        list.add(new DataDetail("用餐","155"));
        return list;
    }

    public static ArrayList<DataDetail> getList_RSP(){
        ArrayList<DataDetail> list = new ArrayList<>();
        list.add(new DataDetail("RSP","35"));
        list.add(new DataDetail("PRQ","155"));
        list.add(new DataDetail("SPO2","155"));
        return list;
    }

    public static ArrayList<DataDetail> getList_Disease(){
        ArrayList<DataDetail> list = new ArrayList<>();
        list.add(new DataDetail("AF","35"));
        list.add(new DataDetail("PVC","155"));
        list.add(new DataDetail("Arr","155"));
        list.add(new DataDetail("心肌梗塞","155"));
        return list;
    }

    public static ArrayList<DataDetail> getList_Body_index(){
        ArrayList<DataDetail> list = new ArrayList<>();
        list.add(new DataDetail("健康分數","35"));
        return list;
    }

    public static ArrayList<DataDetail> getList_HRV(){
        ArrayList<DataDetail> list = new ArrayList<>();
        list.add(new DataDetail("SDNN","35"));
        list.add(new DataDetail("RMSSD","155"));
        list.add(new DataDetail("LF/HF","155"));
        list.add(new DataDetail("LFn","155"));
        list.add(new DataDetail("HFn","155"));
        return list;
    }

    public static ArrayList<DataDetail> getList_Heartbeat(){
        ArrayList<DataDetail> list = new ArrayList<>();
        list.add(new DataDetail("最大心率","35"));
        list.add(new DataDetail("最小心率","155"));
        list.add(new DataDetail("平均心率","155"));
        return list;
    }

    public static ArrayList<DataDetail> getList_signal(){
        ArrayList<DataDetail> list = new ArrayList<>();
        list.add(new DataDetail("紅三燈","35"));
        list.add(new DataDetail("訊號分數","155"));
        return list;
    }
}
