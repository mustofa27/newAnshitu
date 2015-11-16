package com.example.ahmadmustofa.anshitu;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Ahmad Mustofa on 14/11/2015.
 */
public class ScheduleChecker {
    DatabaseHandler databaseHandler;
    PrayTimeCounter prayTimeCounter;
    Calendar[] prayTime;
    int jamMulai,menitMulai,jamSelesai,menitSelesai;
    List<Schedules> jadwalHariIni;
    public ScheduleChecker()
    {
        databaseHandler = new DatabaseHandler(Anshitu.getApp().getApplicationContext());
        jadwalHariIni = databaseHandler.getAllSchedules();
        //prayTime = prayTimeCounter.getPrayTime();
        for (int i = 0; i < prayTime.length; i++)
        {
            prayTime[i].setTimeInMillis(prayTime[i].getTimeInMillis()+Anshitu.getApp().getJeda());
            jamMulai = prayTime[i].HOUR_OF_DAY;
            menitMulai = prayTime[i].MINUTE;
            prayTime[i].setTimeInMillis(prayTime[i].getTimeInMillis()+Anshitu.getApp().getDuration());
            jamSelesai = prayTime[i].HOUR_OF_DAY;
            menitSelesai = prayTime[i].MINUTE;
            Schedules schedules = new Schedules(0,String.valueOf(i),jamMulai,menitMulai,jamSelesai,menitSelesai,0,0,0,0,0,0,0,0);
            jadwalHariIni.add(schedules);
        }
        
    }

}
