package com.nuance.speechkitsample;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

/**
 * Screen to display extra info about SpeechKit.
 *
 * Copyright (c) 2015 Nuance Communications. All rights reserved.
 */
public class AboutActivity extends DetailActivity {

    private TextView version;
    private TextView docs;
    private TextView questions;
    private TextView components;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        version = (TextView)findViewById(R.id.version);
        version.setText(Html.fromHtml(getResources().getString(R.string.app_version)));

        docs = (TextView)findViewById(R.id.docs);
        docs.setText(Html.fromHtml(getResources().getString(R.string.about_docs)));

        questions = (TextView)findViewById(R.id.questions);
        questions.setText(Html.fromHtml(getResources().getString(R.string.about_questions)));

        components = (TextView)findViewById(R.id.components);
        components.setText(Html.fromHtml(getResources().getString(R.string.about_components) +
                                       getResources().getString(R.string.bullet_point) + getResources().getString(R.string.sdk_version)));
    }
}
