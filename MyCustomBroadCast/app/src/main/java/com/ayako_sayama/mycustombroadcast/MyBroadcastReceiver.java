package com.ayako_sayama.mycustombroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by ayako_sayama on 2017/01/30.
 */
    public class MyBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "broadcast";

    public MyBroadcastReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String broadCastText = intent.getStringExtra("data");
        Log.i(TAG , "onReceive: " + broadCastText);
        Toast.makeText(context, broadCastText, Toast.LENGTH_LONG).show();
    }
}
