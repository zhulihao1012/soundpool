package com.example.json.gamevoice;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;

import java.util.HashMap;

//import com.uhf.uhf.R;

/**
 * Created by Administrator on 7/6/2017.
 */
//蜂鸣
public class Beeper  {
    public static final int BEEPER = 1;
    public static final int BEEPER_SHORT = 2;
    private static boolean mQuite = false;
    private static boolean mBeepInventoried = false;
    private static boolean mBeepPerTag = false;
    private static final SoundPool mSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC,5);
    private static MediaPlayer mMediaPlayer = new MediaPlayer();
    public enum BeepMode{QUITE,BEEP_INVENTORIED,BEEP_PER_TAG};
    public static void  init(Context context) {


        /**
         * Must use SoundPool;
         */
       /* mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                player.seekTo(0);
            }
        });
        try {
        AssetFileDescriptor file = context.getResources().openRawResourceFd(
                R.raw.beeper);
            mMediaPlayer.setDataSource(file.getFileDescriptor(),
                    file.getStartOffset(), file.getLength());
            file.close();
            mMediaPlayer.setVolume(1, 1);
            mMediaPlayer.prepare();
        } catch (IOException ioe) {
            mMediaPlayer = null;
            return;
        }*/
        mBeepInventoried = mQuite = mBeepPerTag = false;
        mSoundPool.load(context, R.raw.musictest,1);
  ///      mSoundPool.load(context,R.raw.beeper,BEEPER);
///        mSoundPool.load(context, R.raw.beeper_short,BEEPER_SHORT);
    }
    public static void  beep(int soundID){
        if (mQuite) {
            return;
        }
        if (soundID == BEEPER && mBeepInventoried) {
            mSoundPool.play(BEEPER,1, 1, 0, 0, 1);
            mBeepInventoried = false;
            Log.e("11","111");
        } else if (soundID == BEEPER_SHORT && mBeepPerTag) {
            mSoundPool.play(BEEPER_SHORT,1, 1, 0, 0, 1);
        } else if (soundID == BEEPER_SHORT && !mBeepPerTag){
            mBeepInventoried = true;
        } else {

        }

        if (soundID==1){

            mSoundPool.play(BEEPER,1,1,0,0,1);
            Log.e("11","1112");
        }
       /*if (mMediaPlayer != null) {
            mMediaPlayer.start();
        }*/
    }



    /**
     * Set the beep mode.
     * @param beepMode
     */
    public static void setBeepMode(BeepMode beepMode) {
        switch (beepMode) {
            case QUITE:
                mQuite = true;
                mBeepInventoried = mBeepPerTag = false;
                break;
            case BEEP_INVENTORIED:
                mBeepInventoried = mQuite = mBeepPerTag = false;
                break;
            case BEEP_PER_TAG:
                mBeepPerTag = true;
                mQuite = mBeepInventoried = false;
                break;
            default:
                mBeepInventoried = mQuite = mBeepPerTag = false;
                break;
        }

    }

    public static void release() {
        /*if (mMediaPlayer != null) {
            mMediaPlayer.release();
        }*/
        if (mSoundPool != null) {
            mSoundPool.release();
        }

    }
}
