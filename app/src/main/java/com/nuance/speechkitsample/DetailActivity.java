package com.nuance.speechkitsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

/**
 * Helper Activity for transition animations
 *
 * Copyright (c) 2015 Nuance Communications. All rights reserved.
 */
public class DetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_right, R.anim.exit_right);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            overridePendingTransition(R.anim.enter_right, R.anim.exit_right);
            return true;
        }
        return false;
    }
}
