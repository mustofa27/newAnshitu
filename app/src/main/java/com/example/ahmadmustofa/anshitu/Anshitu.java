package com.example.ahmadmustofa.anshitu;

import android.app.Application;

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
}
