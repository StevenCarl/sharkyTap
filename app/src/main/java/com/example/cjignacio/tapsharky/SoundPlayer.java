package com.example.cjignacio.tapsharky;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.provider.MediaStore;

/**
 * Created by CJIgnacio on 12/16/2017.
 */

public class SoundPlayer {

    private AudioAttributes audioAttributes;
    final int SOUND_POOL_MAX = 2;
    private static SoundPool soundPool;
    private static int hitSound;
    private static int eatSound;
    private boolean isSoundOn = true;

    public SoundPlayer (Context context, boolean isSoundOn){

        this.isSoundOn = isSoundOn;

        // Sound pool is deprecated for Android version 21. (Lollipop)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

            audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setAudioAttributes(audioAttributes)
                    .setMaxStreams(SOUND_POOL_MAX)
                    .build();
        } else {
            //SoundPool (int maxStreams,int streamType,int srcQuality)

            soundPool = new SoundPool(SOUND_POOL_MAX, AudioManager.STREAM_MUSIC,0);
        }


        hitSound = soundPool.load(context, R.raw.over,1);
        eatSound = soundPool.load(context, R.raw.chomp,1);
    }

    public void playhitSound(){
        //play(int soundID, float leftVolume, float rightVolume, int priority, int loop, float rate)
        if (isSoundOn)
            soundPool.play(hitSound,1.0f,1.0f,1,0,1.0f);
    }
    public void playeatSound(){
        if (isSoundOn)
            soundPool.play(eatSound,1.0f,1.0f,1,0,1.0f);
    }

}
