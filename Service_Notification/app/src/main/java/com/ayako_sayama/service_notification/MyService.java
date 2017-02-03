package com.ayako_sayama.service_notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.provider.AlarmClock;
import android.widget.Toast;

public class MyService extends Service {

    MediaPlayer mediaPlayer;
    AlarmClock alarmClock;

    public MyService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
        mediaPlayer = MediaPlayer.create(this, R.raw.jazz);
        mediaPlayer.start();

        Notification.Builder notification = new Notification.Builder(this);
        notification.setContentText("Service");
        notification.setContentText("This is a body text");
        notification.setSmallIcon(R.mipmap.ic_launcher);
        notification.setAutoCancel(true);
        notification.setDefaults(Notification.DEFAULT_ALL);

        //getActivity is a static method of the class PendingIntent, and does not require an instance of PendingIntent to be invoked
        //so you don't need to do "new"
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,new Intent(this,MainActivity.class), PendingIntent.FLAG_CANCEL_CURRENT);
        notification.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            notification.setPriority(Notification.PRIORITY_MAX);
            notificationManager.notify(0,notification.build());
        }
        notificationManager.notify(0,notification.getNotification());

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service Ended", Toast.LENGTH_SHORT).show();
        if (mediaPlayer != null){
            mediaPlayer.pause();
        }
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
