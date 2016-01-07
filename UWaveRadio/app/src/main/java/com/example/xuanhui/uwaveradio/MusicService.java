package com.example.xuanhui.uwaveradio;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;

import java.io.IOException;

/**
 * Created by Xuan Hui on 11/8/2015.
 */
public class MusicService extends Service{

    private final String TAG = "MusicService";

    private static final int NOTIFICATION_ID = 1;
    private MediaPlayer mPlayer;
    private int mStartID;

    @Override
    public void onCreate() {
        super.onCreate();
        mPlayer = new MediaPlayer();
        // Set up the Media Player
//        Uri musicAddress = Uri.parse("http://live.uwave.fm:8000/listen-128.mp3");
//        mPlayer = MediaPlayer.create(this, musicAddress);

        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
//            mPlayer.setDataSource("http://live.uwave.fm:8000/listen-128.mp3");
            mPlayer.setDataSource("http://icecast.timlradio.co.uk/nonuk.mp3");
            mPlayer.prepare();

        } catch (IOException e) {
            e.printStackTrace();
        }

        mPlayer.start();
        if (null != mPlayer) {
            mPlayer.setLooping(false);

            MediaPlayer.OnCompletionListener listener = new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopSelf(mStartID);
                }
            };

            mPlayer.setOnCompletionListener(listener);
        }


        /**
         * Create a notification area notification so the user
         * can get back to the MusicServiceClient
         */
        final Intent notificationIntenet = new Intent(getApplicationContext(),
                MusicService.class);
        final PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntenet, 0);
        final Notification notification = new Notification.Builder(getApplicationContext())
                .setSmallIcon(android.R.drawable.ic_media_play)
                .setOngoing(true)
                .setContentTitle("UWave Playing")
                .setContentText("Click to Access UWave")
                .setContentIntent(pendingIntent).build();

        // put this service in a foreground state, so it won't
        // readily be likely by the system
        startForeground(NOTIFICATION_ID, notification);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startid) {

        if (null != mPlayer) {

            // ID for this start command
            mStartID = startid;

            if (mPlayer.isPlaying()) {

                // Rewind to beginning of song
                mPlayer.seekTo(0);

            } else {

                // Start playing song
                mPlayer.start();

            }

        }

        // Don't automatically restart this Service if it is killed
        return START_NOT_STICKY;
    }
    @Override
    public void onDestroy() {

        if (null != mPlayer) {

            mPlayer.stop();
            mPlayer.release();

        }
    }

    // Can't bind to this Service
    @Override
    public IBinder onBind(Intent intent) {

        return null;

    }
}
