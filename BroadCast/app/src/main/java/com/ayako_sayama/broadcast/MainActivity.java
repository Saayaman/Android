package com.ayako_sayama.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "BroadCast";
    BroadcastReceiver broadcastReceiver;
    IntentFilter intentFilter;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.i(TAG, "onReceive: " + intent.getAction());
                Toast.makeText(context, "Received!"+ intent.getStringExtra("Ayako"), Toast.LENGTH_SHORT).show();
            }
        };

        intentFilter = new IntentFilter();
        intentFilter.addAction("Ayako");
        registerReceiver(broadcastReceiver,intentFilter);

        Log.i(TAG, "onCreate: Created Intentfilter");
    }

    public void sendBroadCast(View view) {
        Intent intent = new Intent();
        intent.putExtra("Ayako", "This is the String");
        intent.setAction("com.ayako_sayama.broadcast.AYAKO_INTENT");
        sendBroadcast(intent);
        Log.i(TAG, "sendBroadCast: Sent!");

//       sendBroadcast(new Intent().setAction("Ayako"));

    }
}
