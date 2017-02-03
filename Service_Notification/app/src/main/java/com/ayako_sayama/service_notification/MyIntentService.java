package com.ayako_sayama.service_notification;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;


public class MyIntentService extends IntentService {

    private static final String TAG = "MyIntentService";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "onHandleIntent: " + intent.getStringExtra("action"));
    }

}
