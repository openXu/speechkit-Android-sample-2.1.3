package com.nuance.speechkitsample;

import android.net.Uri;

import com.nuance.speechkit.PcmFormat;

/**
 * All Nuance Developers configuration parameters can be set here.
 *
 * Copyright (c) 2015 Nuance Communications. All rights reserved.
 */
public class Configuration {

    //All fields are required.
    //Your credentials can be found in your Nuance Developers portal, under "Manage My Apps".
    public static final String APP_KEY = "fb7d2661b7d08d1e45020b32c96e3f61410621c897f5e0cc56c83e4a1c5c1417d93a79900b06af5008ba82d128c2326ac853325f49122b9fda69e9f6c5fd0e9e";
    public static final String APP_ID = "NMDPPRODUCTION_xu_open_speechkitsample_20160614035804";
    public static final String SERVER_HOST = "jgh.nmdp.nuancemobility.net";
    public static final String SERVER_PORT = "443";

    public static final Uri SERVER_URI = Uri.parse("nmsps://" + APP_ID + "@" + SERVER_HOST + ":" + SERVER_PORT);

    //Only needed if using NLU
    public static final String CONTEXT_TAG = "!NLU_CONTEXT_TAG!";

    public static final PcmFormat PCM_FORMAT = new PcmFormat(PcmFormat.SampleFormat.SignedLinear16, 16000, 1);
}

