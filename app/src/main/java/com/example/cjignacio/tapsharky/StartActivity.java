package com.example.cjignacio.tapsharky;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    Button play;
    MediaPlayer mp;
    Dialog myDialog;
    TextView txtclose;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        myDialog = new Dialog(this);
        // For Background Music
        play = (Button) findViewById(R.id.button_play);
//        mp = MediaPlayer.create(StartActivity.this, R.raw.babyshark);
//        mp.start();
//        mp.setLooping(true);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.setContentView(R.layout.activity_settings);
                txtclose = (TextView) myDialog.findViewById(R.id.closetxt);
                btnSave = (Button) myDialog.findViewById(R.id.saveBtn);
                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });
                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                myDialog.show();
//                if (mp.isPlaying()) {
//                    mp.pause();
//                    play.setBackgroundResource(R.drawable.mute);
//                } else {
//                    ;
//                    mp.start();
//                    play.setBackgroundResource(R.drawable.music);
//                }
            }
        });

    }
    // Double Backpress will exit the app
    boolean twice = false;

    @Override
    public void onBackPressed() {
        if (twice == true) {
            this.getIntent().setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            this.finish();
            System.exit(0);
        }
        Toast.makeText(StartActivity.this, "Press BACK again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                twice = false;
            }
        }, 2000);
        twice = true;
    }

    public void startGame(View view) {
//        mp.stop();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    public void scoreHigh(View view) {
        startActivity(new Intent(getApplicationContext(), HighscoreActivity.class));
    }

    public void donateNow(View view) {
        startActivity(new Intent(getApplicationContext(), DonateActivity.class));
    }

    public void howTo(View view) {

        startActivity(new Intent(getApplicationContext(), OnBoardingAppActivity.class));
    }
}


