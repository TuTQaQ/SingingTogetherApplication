package com.example.rpm.sing;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class PlayActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        CircleImageView circleImageView=(CircleImageView)findViewById(R.id.play_picture);

        Animation animation= AnimationUtils.loadAnimation(this,R.anim.img_animation);
        LinearInterpolator lin=new LinearInterpolator();
        animation.setInterpolator(lin);
        circleImageView.setAnimation(animation);

        AssetManager assetManager=getAssets();
        String[] fileList=null;
        try {
            fileList=assetManager.list("");
            AssetFileDescriptor afd=assetManager.openFd(fileList[1]);
            mediaPlayer=new MediaPlayer();
            mediaPlayer.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            mediaPlayer.prepare();
            mediaPlayer.start();
        }catch (IOException e){
            e.printStackTrace();
        }
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.i("xxx",Integer.toString(mediaPlayer.getDuration()));
                Log.i("xxx",Integer.toString(mediaPlayer.getCurrentPosition()));
            }
        },0,1000);
        //mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.da_ds);
//        try {
//            mediaPlayer.prepare();
//        }catch (IllegalStateException e){
//            e.printStackTrace();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        mediaPlayer.start();
    }
}
