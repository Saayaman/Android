package com.ayako_sayama.quiz_ayako;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    TextView textView;
    BroadcastReceiver broadcastReceiver;
    Intent intent;
    PendingIntent pendingIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.i(TAG, "onReceive: "+ intent.getAction());
                Toast.makeText(context, "Received a BroadCast!", Toast.LENGTH_SHORT).show();

            }
        };
        IntentFilter intentfilter = new IntentFilter();
        intentfilter.addAction("ayakoIntent");
        registerReceiver(broadcastReceiver,intentfilter);
        Log.i(TAG, "onCreate: BroadCast");

    }

    public void sendBroadCast(View view) {

        textView.setText("Broad Cast is Sent!");

        intent = new Intent();
        intent.setAction("ayakoIntent");
        sendBroadcast(intent);

        startActivity(new Intent(this, Main2Activity.class));

        Log.i(TAG, "sendBroadCast: "+intent.getAction());

    }


    public void credit(View view) {
        intent = new Intent(this, Main3Activity.class);
        startActivity(intent);
    }

    public void sendNotification(View view) {

        NotificationCompat.Builder notification = new NotificationCompat.Builder(this);
        notification.setSmallIcon(R.mipmap.ic_launcher);
        notification.setContentTitle("Notify");
        notification.setContentText("This is a Test");


        Intent intent3 = new Intent(this, MainActivity.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
            stackBuilder.addParentStack(MainActivity.class);
            stackBuilder.addNextIntent(intent3);
            pendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_CANCEL_CURRENT);
        }

        notification.setContentIntent(pendingIntent);

        NotificationManager managerNotify = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        managerNotify.notify(0,notification.build());

    }
}
