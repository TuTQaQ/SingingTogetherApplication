package com.example.rpm.sing;


import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


import de.hdodenhof.circleimageview.CircleImageView;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView titleText;
    private TextView dateText;
    private CircleImageView circleImageView;
    private SeekBar seekBar;
    private TextView currentText;
    private TextView timeText;
    private ImageButton prevButton;
    private ImageButton pauseButton;
    private ImageButton nextButton;
    private MediaPlayer bgmPlayer;
    private MediaPlayer recordPlayer;
    private String[] songNames;
    private int positon;
    private List<WorkSong> workSongList;
    private boolean isPlay;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);


        bgmPlayer = new MediaPlayer();
        recordPlayer=new MediaPlayer();

        getIntentDate();
        initViews();
        isPlay = true;
        setViews();

        //changeIcon();
        changeSongs();

    }

    private void playOrPuase() {
        Log.i("xxxx", "playOrPuase"+Boolean.toString(isPlay));
        if (!isPlay) {
            bgmPlayer.pause();
            recordPlayer.pause();
        } else {
            bgmPlayer.start();
            recordPlayer.start();
        }
        changeIcon();
    }

    private void setViews() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.img_animation);
        LinearInterpolator lin = new LinearInterpolator();
        animation.setInterpolator(lin);
        circleImageView.setAnimation(animation);
        titleText.setText(workSongList.get(positon).getSongName());
        dateText.setText(workSongList.get(positon).getSongRecordDate());
        seekBar.setProgress(0);

        try {
            InputStream is = getAssets().open("pic/" +
                    workSongList.get(positon).getSongName() + ".jpg");
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            circleImageView.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            int mp = 0;
            for (int i = 0; i < songNames.length; i++) {
                if (songNames[i].split("_")[0].equals(workSongList.get(positon).getSongName())) {
                    mp = i;
                    break;
                }
            }
            AssetFileDescriptor songAfd = getAssets().openFd("song/" + songNames[mp]);
            bgmPlayer.setDataSource(songAfd.getFileDescriptor(),
                    songAfd.getStartOffset(), songAfd.getLength());
            bgmPlayer.prepare();

            timeText.setText(Integer.toString((int) bgmPlayer.getDuration() / 60000) + ":"
                    + Integer.toString((int) (bgmPlayer.getDuration() / 1000) % 60));

            recordPlayer.setDataSource(getExternalFilesDir("").getAbsolutePath() + "/" +
                    workSongList.get(positon).getSongName() + "_" + workSongList.get(positon).getSongRecordDate() + ".mp3");
            recordPlayer.setLooping(false);
            recordPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTimer();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.play_prev:
                positon = (positon - 1 + workSongList.size()) % workSongList.size();
                changeSongs();
                break;
            case R.id.play_pause:
                isPlay = !isPlay;
                playOrPuase();
                break;
            case R.id.play_next:
                positon = (positon + 1) % workSongList.size();
                changeSongs();
                break;
            default:
                break;
        }
    }

    private void changeSongs() {
        Log.i("xxxx", "changeSong"+Boolean.toString(isPlay));
        bgmPlayer.stop();
        recordPlayer.stop();
        bgmPlayer.reset();
        recordPlayer.reset();
        isPlay=true;
        setViews();
        changeIcon();
        bgmPlayer.start();
        recordPlayer.start();

    }

    private void changeIcon() {
        Log.i("xxxx", "changeIcon"+Boolean.toString(isPlay));
        if (isPlay) {
            pauseButton.setBackgroundResource(R.drawable.stop);
        } else {
            pauseButton.setBackgroundResource(R.drawable.start);
        }
    }

    private void setTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int songTime = bgmPlayer.getDuration();
                        int currentTime = bgmPlayer.getCurrentPosition();

                        if ((int) (songTime / 1000) == (int) (currentTime / 1000)) {
                            positon = (positon + 1) % workSongList.size();
                            timer.cancel();
                            timer = null;
                            changeSongs();
                        }

                        //set seekbar and lyrics
                        seekBar.setProgress((int) (currentTime * 100 / songTime));
                        currentText.setText(Integer.toString((int) currentTime / 60000) + ":"
                                + Integer.toString((int) (currentTime / 1000) % 60));
                    }
                });

            }
        }, 0, 200);
    }

    private void initViews() {
        titleText = (TextView) findViewById(R.id.play_title);
        dateText = (TextView) findViewById(R.id.play_date);
        circleImageView = (CircleImageView) findViewById(R.id.play_picture);
        seekBar = (SeekBar) findViewById(R.id.play_seekbar);
        currentText = (TextView) findViewById(R.id.play_current);
        timeText = (TextView) findViewById(R.id.play_time);
        prevButton = (ImageButton) findViewById(R.id.play_prev);
        pauseButton = (ImageButton) findViewById(R.id.play_pause);
        nextButton = (ImageButton) findViewById(R.id.play_next);

        pauseButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
    }

    private void getIntentDate() {
        Intent intent = getIntent();
        positon = intent.getIntExtra("position", 0);
        String recordString = intent.getStringExtra("workSongList");
        Type type = new TypeToken<List<WorkSong>>() {
        }.getType();
        workSongList = new Gson().fromJson(recordString, type);

        AssetManager assetManager = getAssets();
        try {
            songNames = assetManager.list("song");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recordPlayer.stop();
        bgmPlayer.stop();
        timer.cancel();
        timer = null;
    }
}
