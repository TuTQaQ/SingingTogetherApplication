package com.example.rpm.sing;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.czt.mp3recorder.Mp3Recorder;
import com.czt.mp3recorder.Mp3RecorderUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Calendar;
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
    private TextView titleReady;
    private boolean isSongPlay;
    private ImageView readyImage;
    private LyricView lyricView;
    private Mp3Recorder mp3Recorder;

    private Calendar calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        getIntentDate();
        initViews();
        setPlayAndRecordSong();
        setViews();
    }

    private void setViews() {
        titleText.setText(recordSongList.get(positon).getSongName());
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
<<<<<<< HEAD
                if (isSongPlay == false) {
                    playSong();
                    imageButton.setBackground(getResources().getDrawable(R.drawable.stop));
                    isSongPlay = true;
                } else {
                    player.stop();
                    imageButton.setBackground(getResources().getDrawable(R.drawable.start));
                    isSongPlay = false;
                }

||||||| merged common ancestors
                if (isSongPlay==false){
                    playSong();
                    imageButton.setBackground(getResources().getDrawable(R.drawable.stop));
                    isSongPlay=true;
                }else {
                    player.stop();
                    imageButton.setBackground(getResources().getDrawable(R.drawable.start));
                    isSongPlay=false;
                }

=======
                imgButtonClick();
>>>>>>> 68928087bf5de52923106228c77d8f03f89240ae
            }
        });

        //set lyrics
        String text = null;
        File file = null;
        try {
            InputStream is = getAssets().open("lrc/" + recordSongList.get(positon).getSongLrc());
            text = readTextFromSDcard(is);
            file = new File(getFilesDir().getPath() + "text.txt");
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(text.getBytes());
            outputStream.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        readyImage.setVisibility(View.INVISIBLE);
        lyricView.setLyricFile(file);
    }

    private void imgButtonClick() {

        if (!isSongPlay) {
            //setPlayAndRecordSong();
            mp3Recorder.start();
            player.start();
            imageButton.setBackground(getResources().getDrawable(R.drawable.stop));

            readyImage.setVisibility(View.VISIBLE);
            Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
            animation.setDuration(500); // duration - half a second
            animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
            animation.setRepeatCount(Animation.INFINITE); // Repeat animation infinitely
            animation.setRepeatMode(Animation.REVERSE); //
            readyImage.setAnimation(animation);


            titleReady.setText("recording");
            isSongPlay = true;
        } else {
            player.stop();
            mp3Recorder.stop(Mp3Recorder.ACTION_STOP_ONLY);
            Toast.makeText(getApplicationContext(),"finish record!",Toast.LENGTH_SHORT).show();
            mp3Recorder.reset();
            imageButton.setBackground(getResources().getDrawable(R.drawable.start));
            readyImage.clearAnimation();
            readyImage.setVisibility(View.INVISIBLE);
            titleReady.setText("ready");
            isSongPlay = false;
        }

    }
<<<<<<< HEAD

    private void initViews() {
        isSongPlay = false;
        seekBar = findViewById(R.id.seek_bar);
        titleText = findViewById(R.id.record_name);
        currentText = findViewById(R.id.recort_current_text);
        songText = findViewById(R.id.record_time_text);
        imageButton = findViewById(R.id.record_start);
||||||| merged common ancestors
    private void initViews(){
        isSongPlay=false;
        seekBar=(SeekBar)findViewById(R.id.seek_bar);
        titleText=(TextView)findViewById(R.id.record_name);
        currentText=(TextView)findViewById(R.id.recort_current_text);
        songText=(TextView)findViewById(R.id.record_time_text);
        imageButton=(ImageButton)findViewById(R.id.record_start);
=======

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
        titleReady=(TextView)findViewById(R.id.title_ready_text);
        readyImage=(ImageView)findViewById(R.id.ready_img);
>>>>>>> 68928087bf5de52923106228c77d8f03f89240ae
    }

    private void getIntentDate() {
        Intent intent = getIntent();
        positon = intent.getIntExtra("position", 0);
        String recordString = intent.getStringExtra("recordSongList");
        Type type = new TypeToken<List<RecordSong>>() {
        }.getType();
        recordSongList = new Gson().fromJson(recordString, type);
    }

    private void setPlayAndRecordSong() {
        try {
            AssetManager assetManager = this.getAssets();
            AssetFileDescriptor songAfd = assetManager.openFd("song/" + recordSongList.get(positon).getSongFile());

            //set player
            player = new MediaPlayer();
            player.setDataSource(songAfd.getFileDescriptor(), songAfd.getStartOffset(), songAfd.getLength());
            player.prepare();

<<<<<<< HEAD
            songText.setText(Integer.toString((int) player.getDuration() / 60000) + ":"
                    + Integer.toString((int) (player.getDuration() / 1000) % 60));
||||||| merged common ancestors
            songText.setText(Integer.toString((int) player.getDuration()/60000)+":"
                    +Integer.toString((int)(player.getDuration()/1000)%60));
=======

            //set recorder
            Mp3RecorderUtil.init(this, true);
            mp3Recorder = new Mp3Recorder();

            mp3Recorder.setOutputFile(this.getExternalFilesDir("").getAbsolutePath()+"/"+
                    recordSongList.get(positon).getSongName() + "_"+getTime()+".mp3");
            songText.setText(Integer.toString((int) player.getDuration() / 60000) + ":"
                    + Integer.toString((int) (player.getDuration() / 1000) % 60));
>>>>>>> 68928087bf5de52923106228c77d8f03f89240ae

        } catch (Exception e) {
            e.printStackTrace();
        }
<<<<<<< HEAD
        timer = new Timer();
||||||| merged common ancestors
        timer=new Timer();
=======
        setTimer();
    }

    private String getTime() {
        calendar = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        String date = year + "年" + month + "月" + day + "日" + hour + ":" + minute + ":" + second;

        return date;
    }

    private void setTimer() {
        timer = new Timer();
>>>>>>> 68928087bf5de52923106228c77d8f03f89240ae
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
<<<<<<< HEAD
                        int songTime = player.getDuration();
                        int currentTime = player.getCurrentPosition();
                        Log.d("xxx", "songtime" + Integer.toString(songTime) + "  current" + Integer.toString(currentTime));
                        Log.d("xx", Integer.toString((int) (currentTime * 100 / songTime)));
                        seekBar.setProgress((int) (currentTime * 100 / songTime));
                        currentText.setText(Integer.toString((int) currentTime / 60000) + ":"
                                + Integer.toString((int) (currentTime / 1000) % 60));
||||||| merged common ancestors
                        int songTime=player.getDuration();
                        int currentTime=player.getCurrentPosition();
                        Log.d("xxx", "songtime"+Integer.toString(songTime)+"  current"+Integer.toString(currentTime));
                        Log.d("xx",Integer.toString((int)(currentTime*100/songTime)));
                        seekBar.setProgress((int)(currentTime*100/songTime));
                        currentText.setText(Integer.toString((int)currentTime/60000)+":"
                                +Integer.toString((int)(currentTime/1000)%60));
=======
                        int songTime = player.getDuration();
                        int currentTime = player.getCurrentPosition();

                        if(currentTime>songTime){
                            mp3Recorder.stop(Mp3Recorder.ACTION_STOP_ONLY);
                            mp3Recorder.reset();
                            Toast.makeText(getApplicationContext(),"finish record!",Toast.LENGTH_SHORT).show();
                        }

                        //set seekbar and lyrics
                        seekBar.setProgress((int) (currentTime * 100 / songTime));
                        currentText.setText(Integer.toString((int) currentTime / 60000) + ":"
                                + Integer.toString((int) (currentTime / 1000) % 60));

                        lyricView.setCurrentTimeMillis(currentTime);
>>>>>>> 68928087bf5de52923106228c77d8f03f89240ae
                    }
                });

            }
<<<<<<< HEAD
        }, 0, 500);

||||||| merged common ancestors
        },0,500);

=======
        }, 0, 200);
>>>>>>> 68928087bf5de52923106228c77d8f03f89240ae
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.stop();
        timer.cancel();
        timer = null;

    }

}
