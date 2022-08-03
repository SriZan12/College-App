package Admin.Event.Notifications;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.relianceinternationalcollege.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Objects;
import java.util.Random;

import Admin.Event.Event;


@SuppressLint("MissingFirebaseInstanceTokenRefresh")
public class MyFirebaseMessagingService extends FirebaseMessagingService{
    private final String CHANNEL_ID ="COLLEGE_APP";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);

        Intent intent = new Intent(this, Event.class);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        int notificationId = new Random().nextInt();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(notificationManager);
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivities(getApplicationContext(),0,new Intent[]{intent},PendingIntent.FLAG_ONE_SHOT);


        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        Notification notification = new NotificationCompat.Builder(this,CHANNEL_ID)
//                .setContentTitle(message.getData().get("title"))
//                .setContentText(message.getData().get("message"))
//                .setAutoCancel(true)
//                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
//                .setContentIntent(pendingIntent)
//                .build();

        String title = message.getData().get("EventTitle");
        String body = message.getData().get("EventDescription");

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setContentIntent(pendingIntent);


        notificationManager.notify(notificationId,builder.build());

    }

    private void createNotificationChannel(NotificationManager notificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "Reliance", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription("My Notification");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(android.R.color.white);

            notificationManager.createNotificationChannel(notificationChannel);
        }

    }
}
