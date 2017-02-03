package com.ayako_sayama.mycustombroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


public class AirplaneMode extends BroadcastReceiver {
    public AirplaneMode() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        StringBuilder sb = new StringBuilder();
        sb.append("Action: Airplane Mode!! " + intent.getAction() + "\n");
        String log = sb.toString();
        Toast.makeText(context, log, Toast.LENGTH_LONG).show();

    }
}
