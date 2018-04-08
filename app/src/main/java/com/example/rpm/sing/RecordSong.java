package com.example.rpm.sing;

/**
 * Created by RPM on 2018/4/1.
 */

public class RecordSong {
    private String songName;
    private String songSinger;
    private String songDifficult;
    private String songFile;
    private String songLrc;

    public RecordSong(String songName,String songSinger,String songDifficult,
                      String songFile,String songLrc){
        this.songName=songName;
        this.songSinger=songSinger;
        this.songDifficult=songDifficult;
        this.songFile=songFile;
        this.songLrc=songLrc;
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

    public String getSongLrc() {
        return songLrc;
    }
}
