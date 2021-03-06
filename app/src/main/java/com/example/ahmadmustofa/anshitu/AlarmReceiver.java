package com.example.ahmadmustofa.anshitu;


/**
 * Created by Ahmad Mustofa on 7/27/2015.
 */
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.support.v4.content.WakefulBroadcastReceiver;

import java.util.Calendar;

public class AlarmReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
        //this is the silencing business part
        AudioManager audioManager = (AudioManager)Anshitu.getApp().getSystemService(Context.AUDIO_SERVICE);
        //get the schedules object
        Schedules schedules = (Schedules)intent.getSerializableExtra("jadwal");
        Anshitu.getApp().ringerMode = audioManager.getRingerMode();
        audioManager.setRingerMode(0);
        Calendar calendar = Calendar.getInstance();
        Intent alarmIntent = new Intent(Anshitu.getApp(),AlarmReceiverNormal.class);
        calendar.set(Calendar.HOUR_OF_DAY,schedules.getJamSelesai());
        calendar.set(Calendar.MINUTE, schedules.getMenitSelesai());
        Anshitu.getApp().startAlarmService(alarmIntent,calendar);
        Anshitu.getApp().flag = 1;
        //this will sound the alarm tone
        //this will sound the alarm once, if you wish to
        //raise alarm in loop continuously then use MediaPlayer and setLooping(true)
        /*Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
        ringtone.play();*/

        //this will send a notification message
        ComponentName comp = new ComponentName(context.getPackageName(),AlarmService.class.getName());
        startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);
    }
}