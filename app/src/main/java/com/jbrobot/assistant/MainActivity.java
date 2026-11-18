
package com.jbrobot.assistant;

import android.Manifest;
import android.app.*;
import android.content.*;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.*;
import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity {
    private static final String USERNAME = "joki.robot69";

    @Override
    public void onCreate(Bundle saved) {
        super.onCreate(saved);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(1);
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(24, 30, 24, 30);

        TextView title = new TextView(this);
        title.setText("JB TikTok Assistant");
        title.setTextSize(26);
        layout.addView(title);

        TextView info = new TextView(this);
        info.setText("@joki.robot69\nPengingat setiap 15 menit");
        info.setTextSize(18);
        layout.addView(info);

        Button start = new Button(this);
        start.setText("AKTIFKAN PENGINGAT");
        start.setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= 33 &&
                checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                    new String[]{Manifest.permission.POST_NOTIFICATIONS}, 10);
            }
            Intent intent = new Intent(this, ReminderReceiver.class);
            PendingIntent pi = PendingIntent.getBroadcast(
                this, 15, intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
            AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
            am.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() + 900000,
                900000, pi);
            Toast.makeText(this, "Pengingat diaktifkan", Toast.LENGTH_SHORT).show();
        });
        layout.addView(start);

        Button open = new Button(this);
        open.setText("BUKA PROFIL TIKTOK");
        open.setOnClickListener(v -> {
            startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.tiktok.com/@" + USERNAME)));
        });
        layout.addView(open);

        setContentView(layout);
    }
}
