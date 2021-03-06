package com.example.ahmadmustofa.anshitu;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


public class TambahJadwal extends ActionBarActivity {

    private int jam,menit,jamS,menitS,repeat,minggu,senin,selasa,rabu,kamis,jumat,sabtu,status = 0;
    static final int TIME_DIALOG_ID = 0;
    DatabaseHandler dbHandler;
    EditText nama;
    CheckBox ulang;
    TextView startTime,endTime,et;
    Schedules schedules;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            if(status == 0)
            {
                jam = hourOfDay;
                menit = minute;
            }
            else
            {
                jamS = hourOfDay;
                menitS = minute;
            }
            updateDisplay();
        }
    };
    private void updateDisplay()
    {
        if (status == 0) {
            //set starttime
            startTime.setText(jam + " : " + menit);
        }
        else if (status == 1) {
            //set endtime
            endTime.setText(jamS + " : " + menitS);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_jadwal);
        final Calendar cal = Calendar.getInstance();
        jam = cal.get(Calendar.HOUR_OF_DAY);
        menit = cal.get(Calendar.MINUTE);
        minggu = senin = selasa = rabu = kamis = jumat = sabtu = 0;
        dbHandler = new DatabaseHandler(getApplicationContext());
        nama = (EditText) findViewById(R.id.nama_jadwal);
        startTime = (TextView) findViewById(R.id.startTime);
        endTime = (TextView) findViewById(R.id.endTime);
        ulang = (CheckBox) findViewById(R.id.ulang);
        schedules = (Schedules)getIntent().getSerializableExtra("editJadwal");
        if(schedules != null)
        {
            nama.setText(schedules.getNamaJadwal());
            startTime.setText(schedules.getJamMulai() + " : " + schedules.getMenitMulai());
            endTime.setText(schedules.getJamSelesai() + " : " + schedules.getMenitSelesai());
            if(schedules.getRepeat() == 1)
            {
                ulang.setChecked(true);
                findViewById(R.id.repeat).setVisibility(View.VISIBLE);
                if(schedules.getSenin() == 0)
                {
                    et = (TextView)findViewById(R.id.mon);
                    et.setTextColor(Color.GRAY);
                }
                if(schedules.getSelasa() == 0)
                {
                    et = (TextView)findViewById(R.id.tue);
                    et.setTextColor(Color.GRAY);
                }
                if(schedules.getRabu() == 0)
                {
                    et = (TextView)findViewById(R.id.wed);
                    et.setTextColor(Color.GRAY);
                }
                if(schedules.getKamis() == 0)
                {
                    et = (TextView)findViewById(R.id.thu);
                    et.setTextColor(Color.GRAY);
                }
                if(schedules.getJumat() == 0)
                {
                    et = (TextView)findViewById(R.id.sat);
                    et.setTextColor(Color.GRAY);
                }
                if(schedules.getSabtu() == 0)
                {
                    et = (TextView)findViewById(R.id.sat);
                    et.setTextColor(Color.GRAY);
                }
                if(schedules.getMinggu() == 0)
                {
                    et.setTextColor(Color.GRAY);
                    et = (TextView)findViewById(R.id.sun);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tambah_jadwal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }
*/
        return super.onOptionsItemSelected(item);
    }
    public void timePicker(View view)
    {
        status = 0;
        showDialog(TIME_DIALOG_ID);
    }
    public void timePicker1(View view)
    {
        status = 1;
        showDialog(TIME_DIALOG_ID);
    }
    protected Dialog onCreateDialog(int id)
    {
        switch(id)
        {
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this,mTimeSetListener,jam,menit,false);
        }
        return null;
    }
    public void checkRepeat(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            findViewById(R.id.repeat).setVisibility(View.VISIBLE);
            repeat = 1;
            minggu = senin = selasa = rabu = kamis = jumat = sabtu = 1;
        }
        else {
            findViewById(R.id.repeat).setVisibility(View.GONE);
            repeat = 0;
            //minggu = senin = selasa = rabu = kamis = jumat = sabtu = 0;
        }
    }
    public void setRepeat(View v)
    {
        if(v.getId() == R.id.sun)
        {
            et = (TextView)findViewById(R.id.sun);
            if(minggu == 1)
            {
                et.setTextColor(Color.GRAY);
                minggu = 0;
            }
            else
            {
                et.setTextColor(Color.BLACK);
                minggu = 1;
            }
        }
        else if(v.getId() == R.id.mon)
        {
            et = (TextView)findViewById(R.id.mon);
            if(senin == 1)
            {
                et.setTextColor(Color.GRAY);
                senin = 0;
            }
            else
            {
                et.setTextColor(Color.BLACK);
                senin = 1;
            }
        }
        else if(v.getId() == R.id.tue)
        {
            et = (TextView)findViewById(R.id.tue);
            if(selasa == 1)
            {
                et.setTextColor(Color.GRAY);
                selasa = 0;
            }
            else
            {
                et.setTextColor(Color.BLACK);
                selasa = 1;
            }
        }
        else if(v.getId() == R.id.wed)
        {
            et = (TextView)findViewById(R.id.wed);
            if(rabu == 1)
            {
                et.setTextColor(Color.GRAY);
                rabu = 0;
            }
            else
            {
                et.setTextColor(Color.BLACK);
                rabu = 1;
            }
        }
        else if(v.getId() == R.id.thu)
        {
            et = (TextView)findViewById(R.id.thu);
            if(kamis == 1)
            {
                et.setTextColor(Color.GRAY);
                kamis = 0;
            }
            else
            {
                et.setTextColor(Color.BLACK);
                kamis = 1;
            }
        }
        else if(v.getId() == R.id.fri)
        {
            et = (TextView)findViewById(R.id.fri);
            if(jumat == 1)
            {
                et.setTextColor(Color.GRAY);
                jumat = 0;
            }
            else
            {
                et.setTextColor(Color.BLACK);
                jumat = 1;
            }
        }
        else if(v.getId() == R.id.sat)
        {
            et = (TextView)findViewById(R.id.sat);
            if(sabtu == 1)
            {
                et.setTextColor(Color.GRAY);
                sabtu = 0;
            }
            else
            {
                et.setTextColor(Color.BLACK);
                sabtu = 1;
            }
        }
    }

    public void simpanData(View v)
    {
        //saving to database
        if(schedules == null)
        {
            schedules = new Schedules(dbHandler.getScheduleCount(),nama.getText().toString(),jam,menit,jamS,menitS,repeat,senin,selasa,rabu,kamis,jumat,sabtu,minggu);
            dbHandler.createSchedule(schedules);
            Toast.makeText(getApplicationContext(),nama.getText().toString()+" has been added",Toast.LENGTH_SHORT).show();
        }
        else if (schedules != null)
        {
            schedules.setNamaJadwal(nama.getText().toString());
            schedules.setJamMulai(jam);
            schedules.setMenitMulai(menit);
            schedules.setJamSelesai(jamS);
            schedules.setMenitSelesai(menitS);
            schedules.setRepeat(repeat);
            schedules.setSenin(senin);
            schedules.setSelasa(selasa);
            schedules.setRabu(rabu);
            schedules.setKamis(kamis);
            schedules.setJumat(jumat);
            schedules.setSabtu(sabtu);
            schedules.setMinggu(minggu);
            dbHandler.updateSchedule(schedules);
            Toast.makeText(getApplicationContext(),"Schedule has been Updated",Toast.LENGTH_SHORT).show();
        }
        Intent alarmIntent = new Intent(getApplicationContext(),AlarmReceiver.class);
        alarmIntent.putExtra("jadwal", schedules);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,schedules.getJamMulai());
        calendar.set(Calendar.MINUTE,schedules.getMenitMulai());
        Toast.makeText(getApplicationContext(),"Alarm Time : "+String.valueOf(calendar.getTime()),Toast.LENGTH_LONG).show();
        Anshitu.getApp().startAlarmService(alarmIntent,calendar);
        Intent intent = new Intent(getApplicationContext(),Schedule.class);
        startActivity(intent);
    }
}