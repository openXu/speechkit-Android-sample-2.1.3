package com.nuance.speechkitsample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nuance.speechkit.Audio;
import com.nuance.speechkit.AudioPlayer;
import com.nuance.speechkit.PcmFormat;
import com.nuance.speechkit.Session;

/**
 * This Activity is built to demonstrate how to play an Audio file.
 *
 * SpeechKit gives you the ability to play an audio file that is packaged with you application. This
 * is especially useful for playing earcons. Earcons are what notify the user of the listening state.
 *
 * In this example we play a file stored in '/res/raw/'. This can be done easily by providing
 * the resourceID.
 *
 * You can also provide a Uri that specifies the location of the file.
 *
 * Note: We are using the same technique to play earcons in ASRActivity.java and NLUActivity.java
 *
 * Copyright (c) 2015 Nuance Communications. All rights reserved.
 */
public class AudioActivity extends DetailActivity implements View.OnClickListener, AudioPlayer.Listener {

    private EditText repetitions;

    private TextView logs;
    private Button clearLogs;

    private Button togglePlay;

    private Session speechSession;
    private Audio audio;
    private Integer playCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        repetitions = (EditText)findViewById(R.id.repetitions);

        logs = (TextView)findViewById(R.id.logs);
        clearLogs = (Button)findViewById(R.id.clear_logs);
        clearLogs.setOnClickListener(this);

        togglePlay = (Button)findViewById(R.id.toggle_play);
        togglePlay.setOnClickListener(this);

        //Create a session
        speechSession = Session.Factory.session(this, Configuration.SERVER_URI, Configuration.APP_KEY);
        speechSession.getAudioPlayer().setListener(this);

        //Create an Audio and load it from disk
        audio = new Audio(this, R.raw.sk_start, Configuration.PCM_FORMAT);
    }

    @Override
    public void onClick(View v) {
        if(v == clearLogs) {
            logs.setText("");
        } else if(v == togglePlay) {
            play();
        }
    }

    /* Audio playback transactions */

    private void play() {
        //Disable the enqueue button
        togglePlay.setEnabled(false);
        togglePlay.setText(getResources().getString(R.string.playing));

        //Tell the AudioPlayer to play Audios when they are queued
        speechSession.getAudioPlayer().play();

        //Enqueue the Audio z-times
        playCount = new Integer(repetitions.getText().toString());
        for(int z = 0; z < playCount; z++) {
            speechSession.getAudioPlayer().enqueue(audio);
        }
    }

    @Override
    public void onBeginPlaying(AudioPlayer audioPlayer, Audio audio) {
        logs.append("\nonBeginPlaying");

        //The Audio has begun playing
    }

    @Override
    public void onFinishedPlaying(AudioPlayer audioPlayer, Audio audio) {
        logs.append("\nonFinishedPlaying");

        //The Audio has finished playing

        //If the last Audio is finished playing then stop the AudioPlayer to free resources
        //and re-enable the enqueue button.
        if(--playCount == 0) {
            speechSession.getAudioPlayer().stop();
            togglePlay.setEnabled(true);
            togglePlay.setText(getResources().getString(R.string.enqueue));
        }
    }
}
