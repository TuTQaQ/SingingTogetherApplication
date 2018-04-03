package com.example.rpm.sing;

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

import java.lang.reflect.Type;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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
    }

    private void initViews() {
        isSongPlay = false;
        seekBar = findViewById(R.id.seek_bar);
        titleText = findViewById(R.id.record_name);
        currentText = findViewById(R.id.recort_current_text);
        songText = findViewById(R.id.record_time_text);
        imageButton = findViewById(R.id.record_start);
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
            AssetFileDescriptor afd = assetManager.openFd(recordSongList.get(positon).getSongFile());
            player = new MediaPlayer();
            player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
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
                    }
                });

            }
        }, 0, 500);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.stop();
        timer.cancel();
        timer = null;

    }
}
