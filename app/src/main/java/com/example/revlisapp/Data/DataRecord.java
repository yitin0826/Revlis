package com.example.revlisapp.Data;

public class DataRecord {
    private String date,time,heartbeat,index,brain;

    public DataRecord(String date,String time,String heartbeat,String index,String brain){
        this.date = date;
        this.time = time;
        this.heartbeat = heartbeat;
        this.index = index;
        this.brain = brain;
    }

    public String getDate(){
        return date;
    }

    public String getTime(){
        return time;
    }

    public String getHeartbeat(){
        return heartbeat;
    }

    public String getIndex(){
        return index;
    }

    public String getBrain(){
        return brain;
    }
}
