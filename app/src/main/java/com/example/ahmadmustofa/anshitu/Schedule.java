package com.example.ahmadmustofa.anshitu;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Schedule extends ActionBarActivity {

    List<Schedules> Jadwals = new ArrayList<Schedules>();
    ListView listView;
    DatabaseHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        listView = (ListView) findViewById(R.id.schedulesView);
        dbHandler = new DatabaseHandler(getApplicationContext());
        List<Schedules> fromDatabase = dbHandler.getAllSchedules();
        int count = dbHandler.getScheduleCount();
        for(int i = 0; i < count; i++)
            Jadwals.add(fromDatabase.get(i));
        if(count != 0)
            populateList();
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

    public void populateList()
    {
        ArrayAdapter<Schedules> adapter = new SchedulesListAdapter();
        listView.setAdapter(adapter);
    }
    private class SchedulesListAdapter extends ArrayAdapter<Schedules> {
        public SchedulesListAdapter(){
            //super(Schedule.this,R.layout.listview_item,Jadwals);
            super(Schedule.this,R.layout.listview_item,Jadwals);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null)
                convertView = getLayoutInflater().inflate(R.layout.listview_item,parent,false);
            Schedules currentSchedule = Jadwals.get(position);
            TextView nama = (TextView) convertView.findViewById(R.id.name);
            nama.setText(currentSchedule.getNamaJadwal());
            TextView start = (TextView) convertView.findViewById(R.id.startTime);
            start.setText(currentSchedule.getJamMulai()+" : "+currentSchedule.getMenitMulai());
            TextView end = (TextView) convertView.findViewById(R.id.endTime);
            end.setText(currentSchedule.getJamSelesai()+" : "+currentSchedule.getMenitSelesai());
            CheckBox checkRepeat = (CheckBox) convertView.findViewById(R.id.ulang);
            LinearLayout detailUlang = (LinearLayout) convertView.findViewById(R.id.repeat);
            final LinearLayout detail = (LinearLayout)convertView.findViewById(R.id.detail);
            if(currentSchedule.getRepeat() == 1)
            {
                checkRepeat.setChecked(true);
                detailUlang.setVisibility(View.VISIBLE);
                TextView sen = (TextView) convertView.findViewById(R.id.mon);
                TextView sel = (TextView) convertView.findViewById(R.id.tue);
                TextView rab = (TextView) convertView.findViewById(R.id.wed);
                TextView kam = (TextView) convertView.findViewById(R.id.thu);
                TextView jum = (TextView) convertView.findViewById(R.id.fri);
                TextView sab = (TextView) convertView.findViewById(R.id.sat);
                TextView min = (TextView) convertView.findViewById(R.id.sun);
                if(currentSchedule.getSenin() == 0)
                    sen.setTextColor(Color.GRAY);
                else
                    sen.setTextColor(Color.BLACK);
                if(currentSchedule.getSelasa() == 0)
                    sel.setTextColor(Color.GRAY);
                else
                    sel.setTextColor(Color.BLACK);
                if(currentSchedule.getRabu() == 0)
                    rab.setTextColor(Color.GRAY);
                else
                    rab.setTextColor(Color.BLACK);
                if(currentSchedule.getKamis() == 0)
                    kam.setTextColor(Color.GRAY);
                else
                    kam.setTextColor(Color.BLACK);
                if(currentSchedule.getJumat() == 0)
                    jum.setTextColor(Color.GRAY);
                else
                    jum.setTextColor(Color.BLACK);
                if(currentSchedule.getSabtu() == 0)
                    sab.setTextColor(Color.GRAY);
                else
                    sab.setTextColor(Color.BLACK);
                if(currentSchedule.getMinggu() == 0)
                    min.setTextColor(Color.GRAY);
                else
                    min.setTextColor(Color.BLACK);
            }
            else
            {
                checkRepeat.setChecked(false);
                detailUlang.setVisibility(View.GONE);
            }
            LinearLayout pusat = (LinearLayout) convertView.findViewById(R.id.pusat);
            pusat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(detail.getVisibility() == View.GONE)
                        detail.setVisibility(View.VISIBLE);
                    else if(detail.getVisibility() == View.VISIBLE)
                        detail.setVisibility(View.GONE);
                }
            });
            return convertView;
        }
    }
}
