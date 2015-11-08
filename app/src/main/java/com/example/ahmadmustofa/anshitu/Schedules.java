package com.example.ahmadmustofa.anshitu;

import java.util.Date;

/**
 * Created by Ahmad Mustofa on 04/11/2015.
 */
public class Schedules {
    private String namaJadwal;
    private int id,jamMulai,menitMulai,durasi,repeat,senin,selasa,rabu,kamis,jumat,sabtu,minggu;
    public Schedules(int _id,String nameJadwal,int h, int m, int duration, int repeat, int sen, int sel, int rab, int kam, int jum, int sab, int ming)
    {
        id = _id;
        namaJadwal = nameJadwal;
        jamMulai = h;
        menitMulai = m;
        durasi = duration;
        this.repeat = repeat;
        senin = sen;
        selasa = sel;
        rabu = rab;
        kamis = kam;
        jumat = jum;
        sabtu = sab;
        minggu = ming;
    }

    public int getId() { return id; }
    public String getNamaJadwal() {
        return namaJadwal;
    }
    public int getJamMulai() {
        return jamMulai;
    }
    public int getMenitMulai() {
        return menitMulai;
    }
    public int getDurasi() {
        return durasi;
    }
    public int getRepeat() { return repeat; }
    public int getSenin() {
        return senin;
    }
    public int getSelasa() {
        return selasa;
    }
    public int getRabu() {
        return rabu;
    }
    public int getKamis() {
        return kamis;
    }
    public int getJumat() {
        return jumat;
    }
    public int getSabtu() {
        return sabtu;
    }
    public int getMinggu() {
        return minggu;
    }
}
