package com.nuance.speechkitsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Initial screen.
 *
 * Copyright (c) 2015 Nuance Communications. All rights reserved.
 */
public class MainActivity extends Activity implements View.OnClickListener {

    View asrButton = null;
    View nluButton = null;
    View textNluButton = null;
    View ttsButton = null;

    View audioButton = null;

    View configButton = null;
    View aboutButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout mainContent = (LinearLayout) findViewById(R.id.main_content);

        LinearLayout coreTech = inflateCategoryView("CORE TECHNOLOGIES", mainContent);

        asrButton = inflateRowView("Speech Recognition", "Cloud based ASR", coreTech);
        nluButton = inflateRowView("Speech and Natural Language", "Cloud based ASR with NLU", coreTech);
        textNluButton = inflateRowView("Text and Natural Language", "Cloud based NLU (text input)", coreTech);
        ttsButton = inflateRowView("Speech Synthesis", "Cloud based TTS", coreTech);

        LinearLayout utils = inflateCategoryView("UTILITIES", mainContent);

        audioButton = inflateRowView("Audio Playback", "Loading and playing a resource", utils);

        LinearLayout misc = inflateCategoryView("MISCELLANEOUS", mainContent);

        configButton = inflateRowView("Configuration", "Host URL, App ID, etc", misc);
        aboutButton = inflateRowView("About", "Learn more about SpeechKit", misc);

    }

    @Override
    public void onClick(View v) {

        Intent intent = null;
        if(v == asrButton) {
            intent = new Intent(this, ASRActivity.class);
        } else if(v == nluButton) {
            intent = new Intent(this, NLUActivity.class);
        } else if(v == textNluButton) {
            intent = new Intent(this, TextNLUActivity.class);
        } else if(v == ttsButton) {
            intent = new Intent(this, TTSActivity.class);
        } else if(v == audioButton) {
            intent = new Intent(this, AudioActivity.class);
        } else if(v == configButton) {
            intent = new Intent(this, ConfigActivity.class);
        } else if(v == aboutButton) {
            intent = new Intent(this, AboutActivity.class);
        }

        if(intent != null) {
            startActivity(intent);
            overridePendingTransition(R.anim.enter_left, R.anim.exit_left);
        }
    }

    private LinearLayout inflateCategoryView(String title, LinearLayout parent) {
        View v = (View) getLayoutInflater().inflate(R.layout.activity_main_category, null);
        ((TextView)v.findViewById(R.id.title)).setText(title);
        parent.addView(v);
        return ((LinearLayout)v.findViewById(R.id.list));
    }

    private View inflateRowView(String mainText, String subText, LinearLayout parent) {
        View v = (View) getLayoutInflater().inflate(R.layout.activity_main_row, null);
        ((TextView)v.findViewById(R.id.mainText)).setText(mainText);
        ((TextView)v.findViewById(R.id.subText)).setText(subText);
        parent.addView(v);
        v.setOnClickListener(this);
        return v;
    }
}
