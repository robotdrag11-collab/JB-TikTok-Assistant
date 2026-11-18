
package com.jbrobot.assistant;

import android.app.*;
import android.content.*;
import android.os.Build;

public class ReminderReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String channel = "jb_tiktok_reminders";

        NotificationManager manager =
            (NotificationManager) context.getSystemService(
                Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= 26) {
            manager.createNotificationChannel(
                new NotificationChannel(
                    channel,
                    "Pengingat TikTok",
                    NotificationManager.IMPORTANCE_DEFAULT));
        }

        Intent open = new Intent(
            Intent.ACTION_VIEW,
            android.net.Uri.parse(
                "https://www.tiktok.com/@joki.robot69"));

        PendingIntent pending = PendingIntent.getActivity(
            context, 1, open,
            PendingIntent.FLAG_UPDATE_CURRENT |
            PendingIntent.FLAG_IMMUTABLE);

        Notification notification =
            new Notification.Builder(context, channel)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Waktunya cek TikTok!")
                .setContentText("Cek video baru @joki.robot69")
                .setContentIntent(pending)
                .setAutoCancel(true)
                .build();

        manager.notify(15, notification);
    }
}
