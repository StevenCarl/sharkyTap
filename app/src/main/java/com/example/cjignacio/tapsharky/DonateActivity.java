package com.example.cjignacio.tapsharky;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DonateActivity extends AppCompatActivity {
    TextView l1,l2,l3,l4,l5;
    ImageView l6;
    Animation uptodown, downtoup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);

        l1 = (TextView) findViewById(R.id.l1);
        l2 = (TextView) findViewById(R.id.l2);
        l3 = (TextView) findViewById(R.id.l3);
        l4 = (TextView) findViewById(R.id.l4);
        l5 = (TextView) findViewById(R.id.l5);
        l6 = (ImageView) findViewById(R.id.l6);

        // Load Animation
        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this,R.anim.downtoup);

        //Set Animation
        l1.setAnimation(uptodown);
        l2.setAnimation(uptodown);
        l3.setAnimation(uptodown);
        l4.setAnimation(uptodown);
        l5.setAnimation(uptodown);
        l6.setAnimation(downtoup);
    }
}
