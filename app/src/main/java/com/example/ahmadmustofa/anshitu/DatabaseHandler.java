package com.example.ahmadmustofa.anshitu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ahmad Mustofa on 08/11/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Jadwal",
            TABLE_JADWAL = "jadwal",
            KEY_ID = "id_jadwal",
            KEY_NAMA = "nama_jadwal",
            KEY_JAM = "jam_waktu_mulai",
            KEY_MENIT = "menit_waktu_mulai",
            KEY_DURASI = "durasi",
            KEY_REPEAT = "repeat",
            KEY_SEN = "senin",
            KEY_SEL = "selasa",
            KEY_RAB = "rabu",
            KEY_KAM = "kamis",
            KEY_JUM = "jumat",
            KEY_SAB = "sabtu",
            KEY_MIN = "minggu";
    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE" + TABLE_JADWAL +
                "(" + KEY_ID + "INTEGER PRIMARY KEY AUTOINCRMENT NOT NULL,"
                + KEY_NAMA + " TEXT NOT NULL,"
                + KEY_JAM + "INTEGER NOT NULL,"
                + KEY_MENIT + "INTEGER NOT NULL,"
                + KEY_DURASI + "INTEGER NOT NULL DEFAULT 10,"
                + KEY_REPEAT + "INTEGER NOT NULL DEFAULT 0,"
                + KEY_SEN + "INTEGER NOT NULL DEFAULT 0,"
                + KEY_SEL + "INTEGER NOT NULL DEFAULT 0,"
                + KEY_RAB + "INTEGER NOT NULL DEFAULT 0,"
                + KEY_KAM + "INTEGER NOT NULL DEFAULT 0,"
                + KEY_JUM + "INTEGER NOT NULL DEFAULT 0,"
                + KEY_SAB + "INTEGER NOT NULL DEFAULT 0,"
                + KEY_MIN + ",INTEGER NOT NULL DEFAULT 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST "+TABLE_JADWAL);
        onCreate(db);
    }
    public void createSchedule(Schedules schedules)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAMA,schedules.getNamaJadwal());
        values.put(KEY_JAM,schedules.getJamMulai());
        values.put(KEY_MENIT,schedules.getMenitMulai());
        values.put(KEY_DURASI,schedules.getDurasi());
        values.put(KEY_REPEAT,schedules.getRepeat());
        values.put(KEY_SEN,schedules.getSenin());
        values.put(KEY_SEL,schedules.getSelasa());
        values.put(KEY_RAB,schedules.getRabu());
        values.put(KEY_KAM,schedules.getKamis());
        values.put(KEY_JUM,schedules.getJumat());
        values.put(KEY_SAB,schedules.getSabtu());
        values.put(KEY_MIN,schedules.getMinggu());
        db.insert(TABLE_JADWAL, null, values);
        db.close();
    }
    public Schedules getSchedule(int id)
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_JADWAL,new String[]{KEY_ID,KEY_NAMA,KEY_JAM,KEY_MENIT,KEY_DURASI,KEY_REPEAT,KEY_SEN,KEY_SEL,KEY_RAB,KEY_KAM,KEY_JUM,KEY_SAB,KEY_MIN},KEY_ID + "=?",new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor!=null)
            cursor.moveToFirst();
        Schedules schedules = new Schedules(Integer.parseInt(cursor.getString(0)),cursor.getString(1),Integer.parseInt(cursor.getString(2)),Integer.parseInt(cursor.getString(3)),Integer.parseInt(cursor.getString(4)),Integer.parseInt(cursor.getString(5)),Integer.parseInt(cursor.getString(6)),Integer.parseInt(cursor.getString(7)),Integer.parseInt(cursor.getString(8)),Integer.parseInt(cursor.getString(9)),Integer.parseInt(cursor.getString(10)),Integer.parseInt(cursor.getString(11)),Integer.parseInt(cursor.getString(12)));
        return schedules;
    }
}
