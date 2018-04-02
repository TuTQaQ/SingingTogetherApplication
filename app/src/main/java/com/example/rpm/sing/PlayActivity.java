package com.example.rpm.sing;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;

import java.io.IOException;

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
