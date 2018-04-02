package com.example.rpm.sing;

/**
 * Created by RPM on 2018/4/1.
 */

public class WorkSong {
    private String songName;
    private String songRecordDate;

    public WorkSong(String songName,String songRecordDate){
        this.songName=songName;
        this.songRecordDate=songRecordDate;
    }

    public String getSongRecordDate() {
        return songRecordDate;
    }

    public String getSongName() {
        return songName;
    }
}
