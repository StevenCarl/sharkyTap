package com.example.cjignacio.tapsharky;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    private TextView playAgainLabel;

    //Status Check
    private boolean start_flg=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        TextView highScoreLabel = (TextView) findViewById(R.id.highScoreLabel);
        playAgainLabel = (TextView) findViewById(R.id.playAgainLabel);

        int score = getIntent().getIntExtra("SCORE",0);
        scoreLabel.setText(score + "");

        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int highScore = settings.getInt("HIGH_SCORE",0);

        if (score > highScore){
            highScoreLabel.setText("New High Score:" + score);

            //Save
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SCORE",score);
            editor.commit();
        } else {
            highScoreLabel.setText("High Score:" + highScore);
        }
    }

    public boolean onTouchEvent(MotionEvent me){

        if (start_flg == false){
            start_flg = true;
            playAgainLabel.setVisibility(View.INVISIBLE);
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
        return true;
    }

//    // Disable Return Button
//    @Override
//
//    public boolean dispatchKeyEvent(KeyEvent event) {
//        if (event.getAction() == KeyEvent.ACTION_DOWN) {
//            switch (event.getKeyCode()) {
//                case KeyEvent.KEYCODE_BACK:
//                    return true;
//            }
//        }
//        return super.dispatchKeyEvent(event);
//    }

    // Double Backpress will exit the app
    boolean twice = false;
    @Override
    public void onBackPressed() {
        if (twice == true){
            Intent intent = new Intent(getApplicationContext(), StartActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
        Toast.makeText(ResultActivity.this,"Press BACK again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                twice = false;
            }
        }, 2000);
        twice = true;
    }
}


