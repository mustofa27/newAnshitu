package com.example.ahmadmustofa.anshitu;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;


public class Schedule extends ActionBarActivity {

    List<Schedules> Jadwals = new ArrayList<Schedules>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_schedule, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(id == R.id.action_add)
        {
            openTambah();
            return true;
        }
        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    private void openTambah() {
        Intent intent = new Intent(getApplicationContext(),TambahJadwal.class);
        startActivity(intent);
    }
    private class SchedulesListAdapter extends ArrayAdapter<Schedules>{
        public SchedulesListAdapter(){
            super(Schedule.this,R.layout.listview_item,Jadwals);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null)
                convertView = getLayoutInflater().inflate(R.layout.listview_item,parent,false);
            Schedules currentSchedule = Jadwals.get(position);
            return super.getView(position, convertView, parent);
        }
    }
}
