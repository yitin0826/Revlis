package com.example.myapplication.Data;

public class DataProfile {
    /** Profile的資料列 **/

    private String profile_title,profile_data;

    public DataProfile(String title, String data){
        this.profile_title = title;
        this.profile_data = data;
    }

    public String getProfile_title(){
        return profile_title;
    }

    public String getProfile_data(){
        return profile_data;
    }
}
