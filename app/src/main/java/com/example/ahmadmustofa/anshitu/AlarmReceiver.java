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
        //choose whether silencing or back to normal
        if(Anshitu.anshitu.flag == 0){
            Anshitu.anshitu.ringerMode = audioManager.getRingerMode();
            audioManager.setRingerMode(0);
            Calendar calendar = Calendar.getInstance();
            Anshitu.getApp().startAlarmService(calendar);
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