package com.example.ahmadmustofa.anshitu;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.Date;

public class Silencer{
    private Date subuh,dzuhur,ashar,maghrib,isya,currentDate;
    public Silencer() {
        subuh = new Date(2015,5,26,4,13,0);
        dzuhur = new Date(2015,5,26,11,28,0);
        ashar = new Date(2015,5,26,14,49,0);
        maghrib = new Date(2015,5,26,17,20,0);
        isya = new Date(2015,5,26,18,34,0);
    }
}
