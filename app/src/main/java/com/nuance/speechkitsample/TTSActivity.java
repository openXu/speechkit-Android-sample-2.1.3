package com.nuance.speechkitsample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nuance.speechkit.Audio;
import com.nuance.speechkit.AudioPlayer;
import com.nuance.speechkit.Language;
import com.nuance.speechkit.Session;
import com.nuance.speechkit.Transaction;
import com.nuance.speechkit.TransactionException;

/**
 * This Activity is built to demonstrate how to perform TTS.
 *
 * TTS is the transformation of text into speech.
 *
 * When performing speech synthesis with SpeechKit, you have a variety of options. Here we demonstrate
 * Language. But you can also specify the Voice. If you do not, then the default voice will be used
 * for the given language.
 *
 * Supported languages and voices can be found here:
 * http://developer.nuance.com/public/index.php?task=supportedLanguages
 *
 * Copyright (c) 2015 Nuance Communications. All rights reserved.
 */
public class TTSActivity extends DetailActivity implements View.OnClickListener, AudioPlayer.Listener {

    private EditText ttsText;
    private EditText language;

    private TextView logs;
    private Button clearLogs;

    private Button toggleTTS;

    private Session speechSession;
    private Transaction ttsTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tts);

        ttsText = (EditText)findViewById(R.id.tts_text);
        language = (EditText)findViewById(R.id.language);

        logs = (TextView)findViewById(R.id.logs);
        clearLogs = (Button)findViewById(R.id.clear_logs);
        clearLogs.setOnClickListener(this);

        toggleTTS = (Button)findViewById(R.id.toggle_tts);
        toggleTTS.setOnClickListener(this);

        //Create a session
        speechSession = Session.Factory.session(this, Configuration.SERVER_URI, Configuration.APP_KEY);
        speechSession.getAudioPlayer().setListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == clearLogs) {
            logs.setText("");
        } else if(v == toggleTTS) {
            toggleTTS();
        }
    }

    /* TTS transactions */

    private void toggleTTS() {
        //If we are not loading TTS from the server, then we should do so.
        if(ttsTransaction == null) {
            toggleTTS.setText(getResources().getString(R.string.cancel));
            synthesize();
        }
        //Otherwise lets attempt to cancel that transaction
        else {
            toggleTTS.setText(getResources().getString(R.string.speak_string));
            stop();
        }
    }

    /**
     * Speak the text that is in the ttsText EditText, using the language in the language EditText.
     */
    private void synthesize() {
        //Setup our TTS transaction options.
        Transaction.Options options = new Transaction.Options();
        options.setLanguage(new Language(language.getText().toString()));
        //options.setVoice(new Voice(Voice.SAMANTHA)); //optionally change the Voice of the speaker, but will use the default if omitted.

        //Start a TTS transaction
        ttsTransaction = speechSession.speakString(ttsText.getText().toString(), options, new Transaction.Listener() {
            @Override
            public void onAudio(Transaction transaction, Audio audio) {
                logs.append("\nonAudio");

                //The TTS audio has returned from the server, and has begun auto-playing.
                ttsTransaction = null;
                toggleTTS.setText(getResources().getString(R.string.speak_string));
            }

            @Override
            public void onSuccess(Transaction transaction, String s) {
                logs.append("\nonSuccess");

                //Notification of a successful transaction. Nothing to do here.
            }

            @Override
            public void onError(Transaction transaction, String s, TransactionException e) {
                logs.append("\nonError: " + e.getMessage() + ". " + s);

                //Something went wrong. Check Configuration.java to ensure that your settings are correct.
                //The user could also be offline, so be sure to handle this case appropriately.

                ttsTransaction = null;
            }
        });
    }

    /**
     * Cancel the TTS transaction.
     * This will only cancel if we have not received the audio from the server yet.
     */
    private void stop() {
        ttsTransaction.cancel();
    }

    @Override
    public void onBeginPlaying(AudioPlayer audioPlayer, Audio audio) {
        logs.append("\nonBeginPlaying");

        //The TTS Audio will begin playing.
    }

    @Override
    public void onFinishedPlaying(AudioPlayer audioPlayer, Audio audio) {
        logs.append("\nonFinishedPlaying");

        //The TTS Audio has finished playing
    }
}
