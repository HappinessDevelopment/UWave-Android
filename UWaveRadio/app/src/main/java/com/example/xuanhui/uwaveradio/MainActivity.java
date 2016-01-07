package com.example.xuanhui.uwaveradio;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Intent used for starting the MusicService
        final Intent musicServiceIntent = new Intent(getApplicationContext(),
                MusicService.class);

        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View src) {

                // Start the MusicService using the Intent
                startService(musicServiceIntent);

            }
        });
    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//    public void buttonOnClick(View v) {
//        Button button = (Button) v;
//        ((Button) v).setText("clicked");
//        try {
//            MediaPlayer player = new MediaPlayer();
//            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
//            player.setDataSource("http://live.uwave.fm:8000/listen-128.mp3");
//            //player.prepare();
//            //player.start();
//            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
//                public void onPrepared(MediaPlayer mp)
//                {
//                    mp.start();
//                }
//            });
//            player.prepareAsync();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
//}
//}
