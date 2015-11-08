package com.example.ahmadmustofa.anshitu;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;


public class Home extends ActionBarActivity {

    private static Home inst;
    AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    Button onOff;
    Silencer silencer;
    int i = 0;
    public static Home instance() {
        return inst;
    }

    @Override
    protected void onStart() {
        super.onStart();
        inst = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        /*onOff = (Button) findViewById(R.id.onOff);
        onOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ubahText();
                /*if(i == 0)
                    i++;
                else
                    i--;/
                //silencer = new Silencer();
            }
        })  ;*/
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    }


    public void ubahText(View v)
    {
        //silent function
        Calendar calendar = Calendar.getInstance();
        //choose the receiver
        Intent myIntent = new Intent(Home.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(Home.this, 0, myIntent, 0);
        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis()+30000, pendingIntent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_help) {
            openHelp();
            return true;
        }
        if (id == R.id.action_about) {
            openAbout();
            return true;
        }
        if (id == R.id.action_feedback) {
            openFeedback();
            return true;
        }
        if (id == R.id.action_schedule) {
            openSchedule();
            return true;
        }
        if (id == R.id.action_setting) {
            openSetting();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void openSchedule() {
        Intent intent = new Intent(getApplicationContext(), Schedule.class);
        startActivity(intent);
    }

    private void openSetting() {
        Intent intent = new Intent(getApplicationContext(), Setting.class);
        startActivity(intent);
    }

    private void openHelp()
    {
        //final Context context = this;
        Intent intent = new Intent(getApplicationContext(), Help.class);
        startActivity(intent);
    }
    private void openAbout()
    {
        //final Context context = this;
        Intent intent = new Intent(getApplicationContext(), About.class);
        startActivity(intent);
    }
    private void openFeedback()
    {
        //final Context context = this;
        Intent intent = new Intent(getApplicationContext(), Feedback.class);
        startActivity(intent);
    }
    public void silencer()
    {
        AudioManager audio = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        //int vol = audio.getRingerMode();
        //if(vol != 0)
            audio.setRingerMode(0);
        //else
          //  audio.setRingerMode(2);

    }
    public void collapse(View view)
    {
        LinearLayout linLay = (LinearLayout)findViewById(view.getId()+1);
        if(linLay.getVisibility() == View.VISIBLE)
            linLay.setVisibility(View.GONE);
        else
            linLay.setVisibility(View.VISIBLE);
    }
}
