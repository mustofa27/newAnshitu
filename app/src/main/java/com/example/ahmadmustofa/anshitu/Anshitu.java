package com.example.ahmadmustofa.anshitu;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

/**
 * Created by Ahmad Mustofa on 17/09/2015.
 */
public class Anshitu extends Application {
    static Anshitu anshitu;
    public int flag;
    public int ringerMode;
    @Override
    public void onCreate() {
        super.onCreate();
        flag = 0;
        anshitu = this;
    }
    public static Anshitu getApp()
    {
        return  anshitu;
    }
    public void startAlarmService(Calendar calendar)
    {
        Intent myIntent = new Intent(Anshitu.anshitu, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(Anshitu.anshitu, 0, myIntent, 0);
        AlarmManager alarmManager = (AlarmManager) Anshitu.getApp().getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis()+30000, pendingIntent);
    }
}
