package com.example.ahmadmustofa.anshitu;

import android.app.Dialog;
import android.app.TimePickerDialog;
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

import java.util.Calendar;


public class TambahJadwal extends ActionBarActivity {

    private int jam,menit,status = 0,minggu,senin,selasa,rabu,kamis,jumat,sabtu;
    static final int TIME_DIALOG_ID = 0;
    TextView et;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            jam = hourOfDay;
            menit = minute;
            updateDisplay();
        }
    };
    private void updateDisplay()
    {
        if (status == 0)
            et = (TextView) findViewById(R.id.startTime);
        else if (status == 1)
            et = (TextView) findViewById(R.id.endTime);
        et.setText(jam + " : " + menit);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_jadwal);
        final Calendar cal = Calendar.getInstance();
        jam = cal.get(Calendar.HOUR_OF_DAY);
        menit = cal.get(Calendar.MINUTE);
        minggu = senin = selasa = rabu = kamis = jumat = sabtu = 0;
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
        if(checked)
            findViewById(R.id.repeat).setVisibility(View.VISIBLE);
        else
            findViewById(R.id.repeat).setVisibility(View.GONE);
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
}