package com.example.rpm.sing;


import android.media.MediaRecorder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;

import com.czt.mp3recorder.Mp3Recorder;
import com.czt.mp3recorder.Mp3RecorderUtil;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class PlayActivity extends AppCompatActivity {

    Mp3Recorder mp3Recorder;
    ImageButton imageButton;
    //MediaRecorder recorder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        CircleImageView circleImageView = (CircleImageView) findViewById(R.id.play_picture);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.img_animation);
        LinearInterpolator lin = new LinearInterpolator();
        animation.setInterpolator(lin);
        circleImageView.setAnimation(animation);

//        recorder=new MediaRecorder();
//        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//        recorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
//        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
//        recorder.setOutputFile(getDataDir().getPath()+"/sound.amr");
//        try {
//            recorder.prepare();
//            recorder.start();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//
//
//        Mp3RecorderUtil.init(this,true);
//        mp3Recorder=new Mp3Recorder();
//        mp3Recorder.setOutputFile(getFilesDir().getPath()+"/sound.mp3");
//
//        mp3Recorder.start();
//
//        imageButton=(ImageButton)findViewById(R.id.play_pause);
//        imageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mp3Recorder.stop(Mp3Recorder.ACTION_STOP_ONLY);
//                mp3Recorder.reset();
//            }
//        });

    }
}
