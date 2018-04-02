package com.example.rpm.sing;

/**
 * Created by RPM on 2018/4/1.
 */

public class RecordSong {
    private String songName;
    private String songSinger;
    private String songDifficult;

    public RecordSong(String songName,String songSinger,String songDifficult){
        this.songName=songName;
        this.songSinger=songSinger;
        this.songDifficult=songDifficult;
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
}
