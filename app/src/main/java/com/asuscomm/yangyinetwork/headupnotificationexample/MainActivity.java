package com.asuscomm.yangyinetwork.headupnotificationexample;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int NOTIFICATION_ID = 1;

    private NotificationManager mNotificationManager;
    private Button mShowNotificationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mNotificationManager = (NotificationManager) this.getSystemService(Context
                .NOTIFICATION_SERVICE);

        mShowNotificationButton = (Button) findViewById(R.id.show_notification_button);
        mShowNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNotificationManager.notify(NOTIFICATION_ID, createNotification(
                        true));
                Toast.makeText(MainActivity.this, "Show Notification clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Notification createNotification(boolean makeHeadsUpNotification) {
        Notification.Builder notificationBuilder = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher_notification)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setContentTitle("Sample Notification")
                .setContentText("This is a normal notification.");
        if (makeHeadsUpNotification) {
            Intent push = new Intent();
            push.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            push.setClass(this, MainActivity.class);

            PendingIntent fullScreenPendingIntent = PendingIntent.getActivity(this, 0,
                    push, PendingIntent.FLAG_CANCEL_CURRENT);
            notificationBuilder
                    .setContentText("Heads-Up Notification on Android L or above.")
                    .setFullScreenIntent(fullScreenPendingIntent, true);
        }
        return notificationBuilder.build();
    }


}
