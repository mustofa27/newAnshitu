package com.example.ahmadmustofa.anshitu;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class PrayTime extends ActionBarActivity {

    TextView subuh,terbit,duhur,ashar,maghrib,isya,lokasi;
    List<Address> addresses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pray_time);
        subuh = (TextView) findViewById(R.id.subuh);
        terbit = (TextView) findViewById(R.id.terbit);
        duhur = (TextView) findViewById(R.id.dzuhur);
        ashar = (TextView) findViewById(R.id.ashar);
        maghrib = (TextView) findViewById(R.id.maghrib);
        isya = (TextView) findViewById(R.id.isya);
        lokasi = (TextView) findViewById(R.id.location);
        CobaHitung();
        lokasi.setText(Anshitu.getApp().getLokasi());
    }

    public void CobaHitung()
    {
        /*double latitude = -37.823689;
        double longitude = 145.121597;*/


        PrayTimeCounter prayers = new PrayTimeCounter();

        prayers.setTimeFormat(prayers.getTime24());
        prayers.setCalcMethod(prayers.getMWL());
        prayers.setAsrJuristic(prayers.getShafii());
        prayers.setAdjustHighLats(prayers.getAngleBased());
        int[] offsets = {0, 0, 0, 0, 0, 0, 0}; // {Fajr,Sunrise,Dhuhr,Asr,Sunset,Maghrib,Isha}
        prayers.tune(offsets);

        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);

        ArrayList<String> prayerTimes = prayers.getPrayerTimes(cal,
                Anshitu.getApp().getLatitude(), Anshitu.getApp().getLongitude(), Anshitu.getApp().getTimezone());
        subuh.setText(prayerTimes.get(0));
        terbit.setText(prayerTimes.get(1));
        duhur.setText(prayerTimes.get(2));
        ashar.setText(prayerTimes.get(3));
        maghrib.setText(prayerTimes.get(4));
        isya.setText(prayerTimes.get(prayerTimes.size()-1));
        /*ArrayList<String> prayerNames = prayers.getTimeNames();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,prayerNames);
        jeda.setAdapter(adapter);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,prayerTimes);
        durasi.setAdapter(adapter1);*/
    }
}
