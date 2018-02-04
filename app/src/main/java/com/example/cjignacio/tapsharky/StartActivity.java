package com.example.cjignacio.tapsharky;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    Button play;
    MediaPlayer mp;
    Dialog myDialog;
    TextView txtclose;
    Button btnSave, resetSettingsBtn;
    Switch musicSwitch, soundSwitch, triviaSwitch;
    SharedPreferences settingsPreferences;
    ImageView musicImageView, soundImageView, triviaImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        settingsPreferences = this.getSharedPreferences("com.example.cjignacio.tapsharky",
                Context.MODE_PRIVATE);

        myDialog = new Dialog(this);
        play = (Button) findViewById(R.id.button_play);

        // For Background Music
        mp = MediaPlayer.create(StartActivity.this, R.raw.babyshark);
        mp.setLooping(true);

        startMusic();

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowPopup();
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


    // Settings button
    private void ShowPopup() {

        myDialog.setContentView(R.layout.activity_settings);

        txtclose = myDialog.findViewById(R.id.closetxt);
        btnSave = myDialog.findViewById(R.id.saveBtn);
        resetSettingsBtn = myDialog.findViewById(R.id.resetSettingsBtn);

        musicSwitch = myDialog.findViewById(R.id.musicSwitch);
        soundSwitch = myDialog.findViewById(R.id.soundSwitch);
        triviaSwitch = myDialog.findViewById(R.id.triviaSwitch);

        musicImageView = myDialog.findViewById(R.id.musicImageView);
        soundImageView = myDialog.findViewById(R.id.soundImageView);
        triviaImageView = myDialog.findViewById(R.id.triviaImageView);

        getSavedSettings();

        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSavedSettings();
                myDialog.dismiss();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setSavedSettings();
                myDialog.dismiss();

                startMusic();
            }
        });

        musicSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mp.start();
                } else {
                    mp.pause();
                }
                changeImageSettings();
            }
        });

        soundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeImageSettings();
            }
        });

        triviaSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeImageSettings();
            }
        });

        resetSettingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                musicSwitch.setChecked(true);
                soundSwitch.setChecked(true);
                triviaSwitch.setChecked(true);

                setSavedSettings();
                startMusic();

            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();

    }

    private void getSavedSettings() {

        boolean isMusicOn = settingsPreferences.getBoolean("music", true);
        boolean isSoundOn = settingsPreferences.getBoolean("sound", true);
        boolean isTriviaOn = settingsPreferences.getBoolean("trivia", true);

        musicSwitch.setChecked(isMusicOn);
        soundSwitch.setChecked(isSoundOn);
        triviaSwitch.setChecked(isTriviaOn);

        changeImageSettings();

    }

    private void changeImageSettings() {

        musicImageView.setImageResource(R.drawable.quiet);
        soundImageView.setImageResource(R.drawable.mute);
        triviaImageView.setImageResource(R.drawable.off);

        if (musicSwitch.isChecked())
            musicImageView.setImageResource(R.drawable.musicaly);

        if (soundSwitch.isChecked())
            soundImageView.setImageResource(R.drawable.audio);

        if (triviaSwitch.isChecked())
            triviaImageView.setImageResource(R.drawable.bulb);

    }

    private void setSavedSettings() {

        settingsPreferences.edit()
                .putBoolean("music", musicSwitch.isChecked())
                .putBoolean("sound", soundSwitch.isChecked())
                .putBoolean("trivia", triviaSwitch.isChecked())
                .apply();
    }

    private void startMusic() {
        if (settingsPreferences.getBoolean("music", true)) {
            mp.start();
        } else {
            if (mp.isPlaying())
                mp.pause();
        }
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
        mp.stop();
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

    public void creditTo(View view) {
        startActivity(new Intent(getApplicationContext(), CreditsActivity.class));
    }
    
}
