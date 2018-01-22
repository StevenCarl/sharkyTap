package com.example.cjignacio.tapsharky;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private TextView scoreLabel;
    private TextView startLabel;
    private TextView timeLabel;
    private ImageView shark;
    private ImageView fishy1;
    private ImageView fishy2;
    private ImageView fishy3;
    private ImageView fishy4;
    private ImageView fishy5;
    private ImageView fishy6;
    private ImageView fishy7;
    private ImageView fishy8;
    private ImageView fishy9;
    private ImageView puffer;
    private ImageView poison;
    private ImageView bubble;

    //Moving Background Images
    private ImageView silfish2;
    private ImageView silfish3;
    private ImageView silfish4;

    //Movement of Background
    private float silfish2X;
    private float silfish2Y;
    private float silfish3X;
    private float silfish3Y;
    private float silfish4X;
    private float silfish4Y;

    // Size
    private int frameHeight;
    private int sharkSize;
    private int screenWidth;
    private int screenHeight;

    // Positioning

    private int sharkY;
    private int fishy1X;
    private int fishy1Y;
    private int fishy2X;
    private int fishy2Y;
    private int fishy3X;
    private int fishy3Y;
    private int fishy4X;
    private int fishy4Y;
    private int fishy5X;
    private int fishy5Y;
    private int fishy6X;
    private int fishy6Y;
    private int fishy7X;
    private int fishy7Y;
    private int fishy8X;
    private int fishy8Y;
    private int fishy9X;
    private int fishy9Y;
    private int pufferX;
    private int pufferY;
    private int poisonX;
    private int poisonY;

    //Speed
    private int sharkSpeed;
    private int fishy1Speed;
    private int fishy2Speed;
    private int fishy3Speed;
    private int fishy4Speed;
    private int fishy5Speed;
    private int fishy6Speed;
    private int fishy7Speed;
    private int fishy8Speed;
    private int fishy9Speed;
    private int pufferSpeed;
    private int poisonSpeed;

    // Score
    private int score = 0;

    // Initialize Class
    private Handler handler = new Handler();
    private Timer timer = new Timer();
    private SoundPlayer sound;

    // Status Check
    private boolean action_flg = false;
    private boolean start_flg = false;
    private boolean pause_flg = false;

    private CountDownTimer countDownTimer;

    private static final long MINUTE = 1;
    private static final long MILLIS = MINUTE * 1000 * 60;

    MediaPlayer sp;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = this.getSharedPreferences("com.example.cjignacio.tapsharky",
                Context.MODE_PRIVATE);

        sound = new SoundPlayer(this, preferences.getBoolean("sound", true));

        scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        startLabel = (TextView) findViewById(R.id.startLabel);
        timeLabel = (TextView) findViewById(R.id.timeLabel);
        shark = (ImageView) findViewById(R.id.shark);
        fishy1 = (ImageView) findViewById(R.id.fishy1);
        fishy2 = (ImageView) findViewById(R.id.fishy2);
        fishy3 = (ImageView) findViewById(R.id.fishy3);
        fishy4 = (ImageView) findViewById(R.id.fishy4);
        fishy5 = (ImageView) findViewById(R.id.fishy5);
        fishy6 = (ImageView) findViewById(R.id.fishy6);
        fishy7 = (ImageView) findViewById(R.id.fishy7);
        fishy8 = (ImageView) findViewById(R.id.fishy8);
        fishy9 = (ImageView) findViewById(R.id.fishy9);
        puffer = (ImageView) findViewById(R.id.puffer);
        poison = (ImageView) findViewById(R.id.poison);

        //Moving Background Call
        silfish2 = (ImageView) findViewById(R.id.silfish2);
        silfish3 = (ImageView) findViewById(R.id.silfish3);
        silfish4 = (ImageView) findViewById(R.id.silfish4);
        bubble = (ImageView) findViewById(R.id.bub);


        // Get Screen Size
        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);

        screenWidth = size.x;
        screenHeight = size.y;

        //Start the Timer
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        changePosBackground();
                    }
                });
            }
        }, 0, 20);


        // Move to out of screen
        fishy1.setX(-80);
        fishy1.setY(-80);
        fishy2.setX(-80);
        fishy2.setY(-80);
        fishy3.setX(-80);
        fishy3.setY(-80);
        fishy4.setX(-80);
        fishy4.setY(-80);
        fishy5.setX(-80);
        fishy5.setY(-80);
        fishy6.setX(-80);
        fishy6.setY(-80);
        fishy7.setX(-80);
        fishy7.setY(-80);
        fishy8.setX(-80);
        fishy8.setY(-80);
        fishy9.setX(-80);
        fishy9.setY(-80);
        puffer.setX(-80);
        puffer.setY(-80);
        poison.setX(-80);
        poison.setY(-80);

        //Background Image positioning
        silfish2.setX(-80.0F);
        silfish2.setY(-80.0F);


        //Now for Nexus4 Screenwidth: 768 and height:1184
        // speed Shark:20,fishy1:12,fishy2:20,poison:16

        sharkSpeed = Math.round(screenHeight / 60F);    // 1184/60 19.7333.... =>20
        fishy1Speed = Math.round(screenWidth / 60F);      // 768/60= 12.8 => 13
        fishy2Speed = Math.round(screenWidth / 36F);      // 768/36= 21.333 =>21
        fishy3Speed = Math.round(screenWidth / 54F);      // 768/60= 12.8 => 14
        fishy4Speed = Math.round(screenWidth / 81F);      // 768/36= 21.333 =>9
        fishy5Speed = Math.round(screenWidth / 72F);      // 768/45 = 17.06...=>11
        fishy6Speed = Math.round(screenWidth / 60F);      // 768/60= 12.8 => 13
        fishy7Speed = Math.round(screenWidth / 36F);      // 768/36= 21.333 =>21
        fishy8Speed = Math.round(screenWidth / 45F);      // 768/45 = 17.06...=>17
        fishy9Speed = Math.round(screenWidth / 54F);      // 768/60= 12.8 => 14
        poisonSpeed = Math.round(screenWidth / 45F);      // 768/45 = 17.06...=>17
        pufferSpeed = Math.round(screenWidth / 60F);      // 768/45 = 17.06...=>17


//        Log.v("SPEED_SHARK",sharkSpeed+"");
//        Log.v("SPEED_FISHY1",fishy1Speed+"");
//        Log.v("SPEED_FISHY2",fishy2Speed+"");
//        Log.v("SPEED_POISON",poisonSpeed+"");

        if (preferences.getBoolean("trivia", true)) {
            triviaShow();
        }

        scoreLabel.setText("Score: 0");
        createTimer();

    }

    // Where the dialog box is created
//    @Override
//    public void onBackPressed() {
//            timer.cancel();
//            AlertDialog alertDialog = new AlertDialog.Builder(this)
//                    .setTitle("Stop Playing?")
//                    .setMessage("Are you sure you want to exit?")
//                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            System.exit(0);
//                        }
//                    })
//                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.cancel();
//                            timer = new Timer();
//                            timer.schedule(new TimerTask() {
//                                @Override
//                                public void run() {
//                                    handler.post(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            changePos();
//                                            changePosBackground();
//                                        }
//                                    });
//                                }
//                            }, 0,20);
//                        }
//                    })
//                    .show();
//    }

    // Press twice to exit
    boolean twice = false;

    @Override
    public void onBackPressed() {
        if (twice == true) {

            Intent intent = new Intent(getApplicationContext(), StartActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();

            System.exit(0);
        }
        Toast.makeText(MainActivity.this, "Press BACK again to Quit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                twice = false;
            }
        }, 2000);
        twice = true;
    }


    private void createTimer() {

        final DecimalFormat df = new DecimalFormat("00");

        timeLabel.setText("Time: " + df.format(MINUTE) + ":00");

        countDownTimer = new CountDownTimer(MILLIS, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                long minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                millisUntilFinished -= minutes * 60 * 1000;
                long seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);

                // mm:ss
                timeLabel.setText("Time: " + df.format(minutes) + ":" + df.format(seconds));
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("SCORE", score);

                startActivity(intent);


            }
        };

    }

    public void changePos() {

        hitCheck();

        // Fishy1
        fishy1X -= fishy1Speed;
        if (fishy1X < 0) {
            fishy1X = screenWidth + 20;
            fishy1Y = (int) Math.floor(Math.random() * (frameHeight - fishy1.getHeight()));
        }
        fishy1.setX(fishy1X);
        fishy1.setY(fishy1Y);

        // Poison
        poisonX -= poisonSpeed;
        if (poisonX < 0) {
            poisonX = screenWidth + 10;
            poisonY = (int) Math.floor(Math.random() * (frameHeight - poison.getHeight()));
        }
        poison.setX(poisonX);
        poison.setY(poisonY);

        //Fishy2
        fishy2X -= fishy2Speed;
        if (fishy2X < 0) {
            fishy2X = screenWidth + 5000;
            fishy2Y = (int) Math.floor(Math.random() * (frameHeight - fishy2.getHeight()));
        }
        fishy2.setX(fishy2X);
        fishy2.setY(fishy2Y);

        //Fishy3
        fishy3X -= fishy3Speed;
        if (fishy3X < 0) {
            fishy3X = screenWidth + 200;
            fishy3Y = (int) Math.floor(Math.random() * (frameHeight - fishy3.getHeight()));
        }
        fishy3.setX(fishy3X);
        fishy3.setY(fishy3Y);

        //Fishy4
        fishy4X -= fishy4Speed;
        if (fishy4X < 0) {
            fishy4X = screenWidth + 500;
            fishy4Y = (int) Math.floor(Math.random() * (frameHeight - fishy4.getHeight()));
        }
        fishy4.setX(fishy4X);
        fishy4.setY(fishy4Y);

        //Fishy5
        fishy5X -= fishy5Speed;
        if (fishy5X < 0) {
            fishy5X = screenWidth + 1000;
            fishy5Y = (int) Math.floor(Math.random() * (frameHeight - fishy5.getHeight()));
        }
        fishy5.setX(fishy5X);
        fishy5.setY(fishy5Y);

        //Fishy6
        fishy6X -= fishy6Speed;
        if (fishy6X < 0) {
            fishy6X = screenWidth + 3000;
            fishy6Y = (int) Math.floor(Math.random() * (frameHeight - fishy6.getHeight()));
        }
        fishy6.setX(fishy6X);
        fishy6.setY(fishy6Y);

        //Fishy7
        fishy7X -= fishy7Speed;
        if (fishy7X < 0) {
            fishy7X = screenWidth + 700;
            fishy7Y = (int) Math.floor(Math.random() * (frameHeight - fishy7.getHeight()));
        }
        fishy7.setX(fishy7X);
        fishy7.setY(fishy7Y);

        //Fishy8
        fishy8X -= fishy8Speed;
        if (fishy8X < 0) {
            fishy8X = screenWidth + 900;
            fishy8Y = (int) Math.floor(Math.random() * (frameHeight - fishy8.getHeight()));
        }
        fishy8.setX(fishy8X);
        fishy8.setY(fishy8Y);

        //Fishy9
        fishy9X -= fishy9Speed;
        if (fishy9X < 0) {
            fishy9X = screenWidth + 2100;
            fishy9Y = (int) Math.floor(Math.random() * (frameHeight - fishy9.getHeight()));
        }
        fishy9.setX(fishy9X);
        fishy9.setY(fishy9Y);

        //Puffer
        pufferX -= pufferSpeed;
        if (pufferX < 0) {
            pufferX = screenWidth + 1000;
            pufferY = (int) Math.floor(Math.random() * (frameHeight - puffer.getHeight()));
        }
        puffer.setX(pufferX);
        puffer.setY(pufferY);

        // Move Sharky

        if (action_flg == true) {
            // Touching
            sharkY -= sharkSpeed;
        } else {
            // Releasing
            sharkY += 20;
        }

        // Checkbox position
        if (sharkY < 0) sharkY = 0;

        if (sharkY > frameHeight - sharkSize) sharkY = frameHeight - sharkSize;

        shark.setY(sharkY);

        scoreLabel.setText("Score: " + score);

    }

    public void hitCheck() {
        // If the center of the fishy is at the box it counts as a hit...

        //Fishy1

        int fishy1CenterX = fishy1X + fishy1.getWidth() / 2;
        int fishy1CenterY = fishy1Y + fishy1.getHeight() / 2;

        // 0 <= fishyCenterX <= sharkWidth
        // sharkY <= fishyCenterX <= sharkY + sharkHeight

        if (0 <= fishy1CenterX && fishy1CenterX <= sharkSize &&
                sharkY <= fishy1CenterY && fishy1CenterY <= sharkY + sharkSize) {
            score += 5;
            fishy1X = -10;
            sound.playeatSound();
        }

        // Fishy2
        int fishy2CenterX = fishy2X + fishy2.getWidth() / 2;
        int fishy2CenterY = fishy2Y + fishy2.getHeight() / 2;

        if (0 <= fishy2CenterX && fishy2CenterX <= sharkSize &&
                sharkY <= fishy2CenterY && fishy2CenterY <= sharkY + sharkSize) {
            score += 20;
            fishy2X = -10;
            sound.playeatSound();
        }

        //Fishy3

        int fishy3CenterX = fishy3X + fishy3.getWidth() / 2;
        int fishy3CenterY = fishy3Y + fishy3.getHeight() / 2;

        // 0 <= fishyCenterX <= sharkWidth
        // sharkY <= fishyCenterX <= sharkY + sharkHeight

        if (0 <= fishy3CenterX && fishy3CenterX <= sharkSize &&
                sharkY <= fishy3CenterY && fishy3CenterY <= sharkY + sharkSize) {
            score += 15;
            fishy3X = -10;
            sound.playeatSound();
        }

        //Fishy4

        int fishy4CenterX = fishy4X + fishy4.getWidth() / 2;
        int fishy4CenterY = fishy4Y + fishy4.getHeight() / 2;

        // 0 <= fishyCenterX <= sharkWidth
        // sharkY <= fishyCenterX <= sharkY + sharkHeight

        if (0 <= fishy4CenterX && fishy4CenterX <= sharkSize &&
                sharkY <= fishy4CenterY && fishy4CenterY <= sharkY + sharkSize) {
            score += 1;
            fishy4X = -10;
            sound.playeatSound();
        }

        //Fishy5

        int fishy5CenterX = fishy5X + fishy5.getWidth() / 2;
        int fishy5CenterY = fishy5Y + fishy5.getHeight() / 2;

        // 0 <= fishyCenterX <= sharkWidth
        // sharkY <= fishyCenterX <= sharkY + sharkHeight

        if (0 <= fishy5CenterX && fishy5CenterX <= sharkSize &&
                sharkY <= fishy5CenterY && fishy5CenterY <= sharkY + sharkSize) {
            score += 10;
            fishy5X = -10;
            sound.playeatSound();
        }

        //Fishy6

        int fishy6CenterX = fishy6X + fishy6.getWidth() / 2;
        int fishy6CenterY = fishy6Y + fishy6.getHeight() / 2;

        // 0 <= fishyCenterX <= sharkWidth
        // sharkY <= fishyCenterX <= sharkY + sharkHeight

        if (0 <= fishy6CenterX && fishy6CenterX <= sharkSize &&
                sharkY <= fishy6CenterY && fishy6CenterY <= sharkY + sharkSize) {
            score += 30;
            fishy6X = -10;
            sound.playeatSound();
        }

        //Fishy7

        int fishy7CenterX = fishy7X + fishy7.getWidth() / 2;
        int fishy7CenterY = fishy7Y + fishy7.getHeight() / 2;

        // 0 <= fishyCenterX <= sharkWidth
        // sharkY <= fishyCenterX <= sharkY + sharkHeight

        if (0 <= fishy7CenterX && fishy7CenterX <= sharkSize &&
                sharkY <= fishy7CenterY && fishy7CenterY <= sharkY + sharkSize) {
            score += 25;
            fishy7X = -10;
            sound.playeatSound();
        }

        //Fishy8

        int fishy8CenterX = fishy8X + fishy8.getWidth() / 2;
        int fishy8CenterY = fishy8Y + fishy8.getHeight() / 2;

        // 0 <= fishyCenterX <= sharkWidth
        // sharkY <= fishyCenterX <= sharkY + sharkHeight

        if (0 <= fishy8CenterX && fishy8CenterX <= sharkSize &&
                sharkY <= fishy8CenterY && fishy8CenterY <= sharkY + sharkSize) {
            score += 35;
            fishy8X = -10;
            sound.playeatSound();
        }

        //Fishy9

        int fishy9CenterX = fishy9X + fishy9.getWidth() / 2;
        int fishy9CenterY = fishy9Y + fishy9.getHeight() / 2;

        // 0 <= fishyCenterX <= sharkWidth
        // sharkY <= fishyCenterX <= sharkY + sharkHeight

        if (0 <= fishy9CenterX && fishy9CenterX <= sharkSize &&
                sharkY <= fishy9CenterY && fishy9CenterY <= sharkY + sharkSize) {
            score += 40;
            fishy9X = -10;
            sound.playeatSound();
        }

        //Poison
        int poisonCenterX = poisonX + poison.getWidth() / 2;
        int poisonCenterY = poisonY + poison.getHeight() / 2;

        if (0 <= poisonCenterX && poisonCenterX <= sharkSize &&
                sharkY <= poisonCenterY && poisonCenterY <= sharkY + sharkSize) {

            //Temporary!
            //            score +=10;
            //            poisonX = -10;
            timer.cancel();
            // timer = null;

            sound.playhitSound();

            sp.stop();

            // Show ResultActivity
            Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
            intent.putExtra("SCORE", score);

            startActivity(intent);

        }

        //Puffer
        int pufferCenterX = pufferX + puffer.getWidth() / 2;
        int pufferCenterY = pufferY + puffer.getHeight() / 2;

        if (0 <= pufferCenterX && pufferCenterX <= sharkSize &&
                sharkY <= pufferCenterY && pufferCenterY <= sharkY + sharkSize) {

            //Temporary!
            //            score +=10;
            //            poisonX = -10;
            timer.cancel();
            // timer = null;

            sound.playhitSound();

            sp.stop();

            // Show ResultActivity
            Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
            intent.putExtra("SCORE", score);

            startActivity(intent);

        }


    }


    public boolean onTouchEvent(MotionEvent me) {
        if (start_flg == false) {
            blink();
//                Set Background Music
//                MediaPlayer player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
//                player.setLooping(true);
//                player.StartActivity();
            sp = MediaPlayer.create(MainActivity.this, R.raw.play);
            sp.setLooping(true);

            if (preferences.getBoolean("music", true)) {
                sp.start();
            }

            start_flg = true;
                 /* Why get frame height and box height here?
                    Because the screen size is not set in onCreate()*/
            FrameLayout frame = (FrameLayout) findViewById(R.id.frame);
            frameHeight = frame.getHeight();

            sharkY = (int) shark.getY();

            // The box is a square (height and width) are same...
            sharkSize = shark.getHeight();

            countDownTimer.start();

            startLabel.setVisibility(View.GONE);

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changePos();
                        }
                    });
                }
            }, 0, 20);
        } else {
            if (me.getAction() == MotionEvent.ACTION_DOWN) {
                action_flg = true;
            } else if (me.getAction() == MotionEvent.ACTION_UP) {
                action_flg = false;
            }
        }


        return true;
    }

    // Background Image goes here
    public void changePosBackground() {
        // Left Movement
        silfish2X -= 5;
        if (silfish2.getX() + silfish2.getWidth() < 0) {
            silfish2X = screenWidth + 100.0f;
            silfish2Y = 100;
        }
        silfish2.setX(silfish2X);
        silfish2.setY(silfish2Y);

        silfish3X -= 5;
        if (silfish3.getX() + silfish3.getWidth() < 0) {
            silfish3X = screenWidth + 100.0f;
            silfish3Y = 200;
        }
        silfish3.setX(silfish3X);
        silfish3.setY(silfish3Y);

        silfish4X -= 5;
        if (silfish4.getX() + silfish4.getWidth() < 0) {
            silfish4X = screenWidth + 100.0f;
            silfish4Y = 500;
        }
        silfish4.setX(silfish4X);
        silfish4.setY(silfish4Y);

    }

    public void blink() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        bubble.startAnimation(animation);
    }

    public void triviaShow() {

        // Random Call on Trivia in different class
        int triviaSize = Trivia.trivias.length;
        int randomNum = (int) (Math.random() * triviaSize);
        //
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.trivia, null);

        // TextView for Trivia
        TextView triviaView = (TextView) mView.findViewById(R.id.textView);

        // Fetching info in Trivia class
        triviaView.setText(Trivia.trivias[randomNum]);
        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();
        dialog.show();

        //Toast for Play
        Toast.makeText(MainActivity.this,"Tap or Press Back to Play", Toast.LENGTH_SHORT).show();
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//    }
}






