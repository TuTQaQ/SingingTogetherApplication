package com.example.rpm.sing;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.zhengken.lyricview.LyricView;

public class RecordActivity extends AppCompatActivity {

    private List<RecordSong> recordSongList;
    private int positon;
    private MediaPlayer player;
    private SeekBar seekBar;
    private TextView titleText;
    private TextView currentText;
    private TextView songText;
    private Timer timer;
    private ImageButton imageButton;

    private boolean isSongPlay;

    private LyricView lyricView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);


        //seekBar.setEnabled(false);
        getIntentDate();
        initViews();
        setViews();

    }

    private void setViews() {
        titleText.setText(recordSongList.get(positon).getSongName());
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSongPlay == false) {
                    playSong();
                    imageButton.setBackground(getResources().getDrawable(R.drawable.stop));
                    isSongPlay = true;
                } else {
                    player.stop();
                    imageButton.setBackground(getResources().getDrawable(R.drawable.start));
                    isSongPlay = false;
                }

            }
        });
        String text=null;
        File file=null;
        try {
            InputStream is = getAssets().open("lrc/"+recordSongList.get(positon).getSongLrc());
            text= readTextFromSDcard(is);
            file=new File(getFilesDir().getPath()+"text.txt");
            FileOutputStream outputStream=new FileOutputStream(file);
            outputStream.write(text.getBytes());
            outputStream.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        lyricView.setLyricFile(file);
        //lyricView.setCurrentTimeMillis(30);

    }

    private String readTextFromSDcard(InputStream is) throws Exception {
        InputStreamReader reader = new InputStreamReader(is);
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuffer buffer = new StringBuffer("");
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
            buffer.append("\n");
        }
        return buffer.toString();
    }
    private void initViews() {
        isSongPlay = false;
        seekBar = (SeekBar) findViewById(R.id.seek_bar);
        titleText = (TextView) findViewById(R.id.record_name);
        currentText = (TextView) findViewById(R.id.recort_current_text);
        songText = (TextView) findViewById(R.id.record_time_text);
        imageButton = (ImageButton) findViewById(R.id.record_start);
        lyricView = (LyricView) findViewById(R.id.lyric_view);
    }

    private void getIntentDate() {
        Intent intent = getIntent();
        positon = intent.getIntExtra("position", 0);
        String recordString = intent.getStringExtra("recordSongList");
        Type type = new TypeToken<List<RecordSong>>() {
        }.getType();
        recordSongList = new Gson().fromJson(recordString, type);
    }

    private void playSong() {
        try {
            AssetManager assetManager = this.getAssets();
            AssetFileDescriptor songAfd = assetManager.openFd("song/" + recordSongList.get(positon).getSongFile());

            player = new MediaPlayer();
            player.setDataSource(songAfd.getFileDescriptor(), songAfd.getStartOffset(), songAfd.getLength());
            player.prepare();
            player.start();

            songText.setText(Integer.toString((int) player.getDuration() / 60000) + ":"
                    + Integer.toString((int) (player.getDuration() / 1000) % 60));

        } catch (Exception e) {
            e.printStackTrace();
        }
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int songTime = player.getDuration();
                        int currentTime = player.getCurrentPosition();
                        Log.d("xxx", "songtime" + Integer.toString(songTime) + "  current" + Integer.toString(currentTime));
                        Log.d("xx", Integer.toString((int) (currentTime * 100 / songTime)));
                        seekBar.setProgress((int) (currentTime * 100 / songTime));
                        currentText.setText(Integer.toString((int) currentTime / 60000) + ":"
                                + Integer.toString((int) (currentTime / 1000) % 60));

                        lyricView.setCurrentTimeMillis(currentTime);
                    }
                });

            }
        }, 0, 200);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.stop();
        timer.cancel();
        timer = null;

    }

}
