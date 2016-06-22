package com.zdmedia.salahnotify.model;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

/**
 * Created by JavaFreak on 18/06/2016.
 */
public class AudioHandler {
    public static final String COM_ZDMEDIA_AUDIO = "com.zdmedia.audio";
    private Context context;
    private String audioURL;
    private MediaPlayer mediaPlayer;
    public AudioHandler(Context context, String audioURL) {
        this.context = context;
        this.audioURL = audioURL;
        mediaPlayer = new MediaPlayer();
    }
    public void generateSound(String type) {
        switch (type) {
            case "PLAY":
                playAudio();
                break;
            case "PAUSE":
                pauseAudio();
                break;
        }
    }
    private void playAudio() {
        try {
            mediaPlayer.setDataSource(audioURL);
            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void pauseAudio() {
        Log.v(COM_ZDMEDIA_AUDIO, " Pause: " + mediaPlayer.isPlaying());
        try {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
