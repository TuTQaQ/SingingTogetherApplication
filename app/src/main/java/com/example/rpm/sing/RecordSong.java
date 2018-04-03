package com.example.rpm.sing;

/**
 * Created by RPM on 2018/4/1.
 */

public class RecordSong {
    private String songName;
    private String songSinger;
    private String songDifficult;
    private String songFile;

    public RecordSong(String songName,String songSinger,String songDifficult,String songFile){
        this.songName=songName;
        this.songSinger=songSinger;
        this.songDifficult=songDifficult;
        this.songFile=songFile;
    }

    public String getSongName() {
        return songName;
    }

    public String getSongSinger() {
        return songSinger;
    }

    public String getSongDifficult() {
        return songDifficult;
    }

    public String getSongFile() {
        return songFile;
    }
}
