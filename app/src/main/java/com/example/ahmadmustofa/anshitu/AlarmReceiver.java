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
        //this will update the UI with message
        AudioManager audioManager = (AudioManager)Anshitu.getApp().getSystemService(Context.AUDIO_SERVICE);
        if(Anshitu.anshitu.flag == 0){
            Anshitu.anshitu.ringerMode = audioManager.getRingerMode();
            audioManager.setRingerMode(0);
            Calendar calendar = Calendar.getInstance();
            Intent myIntent = new Intent(Anshitu.anshitu, AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(Anshitu.anshitu, 0, myIntent, 0);
            AlarmManager alarmManager = (AlarmManager) Anshitu.getApp().getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis()+30000, pendingIntent);
            Anshitu.anshitu.flag = 1;
        }
        else if(Anshitu.anshitu.flag == 1)
        {
            audioManager.setRingerMode(Anshitu.getApp().ringerMode);
            Anshitu.anshitu.flag = 0;
        }
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