package info.androidhive.materialtabs.activity;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;


import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import info.androidhive.materialtabs.R;

/**
 * Created by rahulranjansinha on 02-07-2017.
 */

public class NotificationFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Intent intent=new Intent(this,NotificationListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder notificationBuider=new NotificationCompat.Builder(this);
        notificationBuider.setContentTitle("IT Placement");
        notificationBuider.setContentText(remoteMessage.getNotification().getBody());
        notificationBuider.setAutoCancel(true);
        notificationBuider.setSmallIcon(R.mipmap.it_placements);
        notificationBuider.setContentIntent(pendingIntent);
        NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notificationBuider.build());
    }
}
