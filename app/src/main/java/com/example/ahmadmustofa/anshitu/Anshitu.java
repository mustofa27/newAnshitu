package com.example.ahmadmustofa.anshitu;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Ahmad Mustofa on 17/09/2015.
 */
public class Anshitu extends Application {
    private static Anshitu anshitu;
    public int flag;
    public int ringerMode;
    private int duration,jeda;
    private String lokasi;
    private double timezone,latitude,longitude;
    @Override
    public void onCreate() {
        super.onCreate();
        flag = 0;
        anshitu = this;
        duration = 10;
        jeda = 5;
        TimeZone tz = TimeZone.getDefault();
        String gmt = TimeZone.getTimeZone(tz.getID()).getDisplayName(false,
                TimeZone.SHORT);
        String z1 = gmt.substring(4);

        String z = z1.replaceAll(":", ".");
        setTimezone(Double.parseDouble(z));
        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        setLongitude(location.getLongitude());
        setLatitude(location.getLatitude());
        Geocoder geoCoder = new Geocoder(this, Locale.getDefault());
        StringBuilder builder = new StringBuilder();
        try
        {
            List<Address> address = geoCoder.getFromLocation(latitude, longitude, 1);
            int maxLines = address.get(0).getMaxAddressLineIndex();
            for (int i=0; i<maxLines; i++) {
                String addressStr = address.get(0).getAddressLine(i);
                builder.append(addressStr);
                builder.append(" ");
            }
            String finalAddress = builder.toString(); //This is the complete address.
            setLokasi(finalAddress);
        }
        catch (IOException e) {

        }
        catch (NullPointerException e) {

        }
    }
    public static Anshitu getApp()
    {
        return  anshitu;
    }
    public void startAlarmService(Intent alarmIntent,Calendar calendar)
    {
        PendingIntent pendingIntent = PendingIntent.getBroadcast(Anshitu.anshitu, 0, alarmIntent, 0);
        AlarmManager alarmManager = (AlarmManager) Anshitu.getApp().getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
    }

    public int getDuration() {
        return duration*60*1000;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getJeda() {
        return jeda;
    }

    public void setJeda(int jeda) {
        this.jeda = jeda*60*1000;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setTimezone(double timezone) {
        this.timezone = timezone;
    }

    public double getTimezone() {
        return timezone;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getLokasi() {
        return lokasi;
    }
}
