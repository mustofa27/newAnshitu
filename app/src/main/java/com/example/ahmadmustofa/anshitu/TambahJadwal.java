package com.example.ahmadmustofa.anshitu;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;


public class TambahJadwal extends ActionBarActivity {

    private int jam,menit,status = 0;
    static final int TIME_DIALOG_ID = 0;
    EditText et;
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
            et = (EditText) findViewById(R.id.startTime);
        else if (status == 1)
            et = (EditText) findViewById(R.id.endTime);
        et.setText(jam + " : " + menit);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_jadwal);
        final Calendar cal = Calendar.getInstance();
        jam = cal.get(Calendar.HOUR_OF_DAY);
        menit = cal.get(Calendar.MINUTE);
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
        /*if(view.getId() == R.id.startTime)
            status = 0;
        else if(view.getId() == R.id.endTime)
            status = 1;
        showDialog(TIME_DIALOG_ID);*/
        et = (EditText) findViewById(R.id.startTime);
        et.setText("oke");
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
}
