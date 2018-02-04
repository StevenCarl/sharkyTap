package com.example.cjignacio.tapsharky;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Adapter;
import android.widget.AdapterViewFlipper;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

/**
 * Created by CJIgnacio on 1/23/2018.
 */

public class CreditsActivity extends AppCompatActivity {

    Animation in,out;
    ViewFlipper viewFlipper;
    MediaPlayer mp;
    boolean isMusicOn;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        viewFlipper = (ViewFlipper)findViewById(R.id.viewFlipper);
        in = AnimationUtils.loadAnimation(this,R.anim.fade_in);
        out = AnimationUtils.loadAnimation(this,R.anim.fade_out);
        viewFlipper.setInAnimation(in);
        viewFlipper.setOutAnimation(out);
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);

        // For Background Music
        mp = MediaPlayer.create(CreditsActivity.this, R.raw.babyshark);
        mp.setLooping(true);

        isMusicOn = getIntent().getBooleanExtra("isMusicOn", true);

        if (isMusicOn) {
            mp.start();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mp.isPlaying())
            mp.pause();

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isMusicOn) {
            mp.start();
        }
    }

}
