package com.example.cjignacio.tapsharky;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HighscoreActivity extends AppCompatActivity {

    Button btnBeat;
    SharedPreferences settingsPreferences;
    MediaPlayer mp;
    boolean isMusicOn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        settingsPreferences = this.getSharedPreferences("com.example.cjignacio.tapsharky",
                Context.MODE_PRIVATE);

        TextView scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        TextView highScoreLabel = (TextView) findViewById(R.id.highScoreLabel);

        int score = getIntent().getIntExtra("SCORE", 0);
        scoreLabel.setText("Beat High Score ?");

        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int highScore = settings.getInt("HIGH_SCORE", 0);

        if (score > highScore) {
            highScoreLabel.setText("" + score);

            //Save
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SCORE", score);
            editor.commit();
        } else {
            highScoreLabel.setText("" + highScore);
        }

        // For Background Music
        mp = MediaPlayer.create(HighscoreActivity.this, R.raw.babyshark);
        mp.setLooping(true);

        isMusicOn = getIntent().getBooleanExtra("isMusicOn", true);

        if (isMusicOn) {
            mp.start();
        }

    }

    public void tryAgain(View view){
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        btnBeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMusic();
            }
        });
    }

    private void startMusic() {
        mp = MediaPlayer.create(HighscoreActivity.this, R.raw.babyshark);
        if (settingsPreferences.getBoolean("music", true)) {
            mp.start();
        } else {
            if (mp.isPlaying())
                mp.pause();
        }
    }

    public void scoreHigh(View view){
        startActivity(new Intent(getApplicationContext(), HighscoreActivity.class));
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