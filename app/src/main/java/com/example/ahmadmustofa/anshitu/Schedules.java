package com.example.ahmadmustofa.anshitu;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ahmad Mustofa on 04/11/2015.
 */
public class Schedules implements Serializable {
    private String namaJadwal;
    private int id, jamMulai, menitMulai, jamSelesai, menitSelesai, repeat, senin, selasa, rabu, kamis, jumat, sabtu, minggu;

    public Schedules(int _id, String nameJadwal, int h, int m, int hs, int ms, int repeat, int sen, int sel, int rab, int kam, int jum, int sab, int ming) {
        id = _id;
        namaJadwal = nameJadwal;
        jamMulai = h;
        menitMulai = m;
        jamSelesai = hs;
        menitSelesai = ms;
        this.repeat = repeat;
        senin = sen;
        selasa = sel;
        rabu = rab;
        kamis = kam;
        jumat = jum;
        sabtu = sab;
        minggu = ming;
    }

    //getter
    public int getId() {
        return id;
    }

    public String getNamaJadwal() {
        return namaJadwal;
    }

    public int getJamMulai() {
        return jamMulai;
    }

    public int getMenitMulai() {
        return menitMulai;
    }

    public int getJamSelesai() {
        return jamSelesai;
    }

    public int getMenitSelesai() {
        return menitSelesai;
    }

    public int getRepeat() {
        return repeat;
    }

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

    //setter
    public void setId(int id) {
        this.id = id;
    }

    public void setNamaJadwal(String namaJadwal) {
        this.namaJadwal = namaJadwal;
    }

    public void setJamMulai(int jamMulai) {
        this.jamMulai = jamMulai;
    }

    public void setMenitMulai(int menitMulai) {
        this.menitMulai = menitMulai;
    }

    public void setJamSelesai(int jamSelesai) {
        this.jamSelesai = jamSelesai;
    }

    public void setMenitSelesai(int menitSelesai) {
        this.menitSelesai = menitSelesai;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    public void setSenin(int senin) {
        this.senin = senin;
    }

    public void setSelasa(int selasa) {
        this.selasa = selasa;
    }

    public void setRabu(int rabu) {
        this.rabu = rabu;
    }

    public void setKamis(int kamis) {
        this.kamis = kamis;
    }

    public void setJumat(int jumat) {
        this.jumat = jumat;
    }

    public void setSabtu(int sabtu) {
        this.sabtu = sabtu;
    }

    public void setMinggu(int minggu) {
        this.minggu = minggu;
    }
}
