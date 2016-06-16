package com.nuance.speechkitsample;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Arrays;

/**
 * Read-only screen to view configuration parameters set in Configuration.java
 *
 * Copyright (c) 2015 Nuance Communications. All rights reserved.
 */
public class ConfigActivity extends DetailActivity {

    private TextView contextTag;
    private TextView serverHost;
    private TextView serverPort;
    private TextView appKey;
    private TextView appId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        contextTag = (TextView)findViewById(R.id.context_tag);
        serverHost = (TextView)findViewById(R.id.server_host);
        serverPort = (TextView)findViewById(R.id.server_port);
        appKey = (TextView)findViewById(R.id.app_key);
        appId = (TextView)findViewById(R.id.app_id);

        contextTag.setText(Configuration.CONTEXT_TAG);
        serverHost.setText(Configuration.SERVER_HOST);
        serverPort.setText(Configuration.SERVER_PORT);
        appId.setText(Configuration.APP_ID);
        appKey.setText(Configuration.APP_KEY);
    }
}
