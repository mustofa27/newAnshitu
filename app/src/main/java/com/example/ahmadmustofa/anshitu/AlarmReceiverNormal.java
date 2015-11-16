package com.example.ahmadmustofa.anshitu;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.support.v4.content.WakefulBroadcastReceiver;

/**
 * Created by Ahmad Mustofa on 14/11/2015.
 */
public class AlarmReceiverNormal extends WakefulBroadcastReceiver{
    @Override
    public void onReceive(final Context context, Intent intent) {
        //this is the silencing business part
        AudioManager audioManager = (AudioManager)Anshitu.getApp().getSystemService(Context.AUDIO_SERVICE);
        audioManager.setRingerMode(Anshitu.getApp().ringerMode);
        Anshitu.getApp().flag = 0;
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
