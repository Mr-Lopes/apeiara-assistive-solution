package com.example.ifsp.apeiara.misc;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;

import com.example.ifsp.apeiara.R;
import com.example.ifsp.apeiara.ResultActivity;

public class NotifyUser  {


    public static void showNotification(Context context){

    //Creates a notification
     NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.logo))
                        .setSmallIcon(R.drawable.visual)
                        .setContentTitle("Apeiara Service")
                        .setContentText("You got requests!")
                        .setAutoCancel(true)
                        .setLights(Color.RED,250,250)
                        .setVibrate(new long[] {100, 1000, 100, 1000, 100, 1000, 100, 100000, 100, 100000})
                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));

    //When notification clicked
    Intent intent =  new Intent(context, ResultActivity.class) ;
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
    PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, 0);

    mBuilder.setContentIntent(contentIntent);

    Notification mNotification = mBuilder.build();
    mNotification.flags = Notification.DEFAULT_LIGHTS | Notification.FLAG_AUTO_CANCEL | Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE;

    NotificationManager mNotifyMgr = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
    mNotifyMgr.notify(666, mNotification);

    }
}
