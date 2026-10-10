package com.example.klinik.entity;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Transaksi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransaksi;

    @ManyToOne
    private Pasien pasien;

    @ManyToOne
    private Dokter dokter;

    @ElementCollection
    private List<String> keluhan;

    private long totalBiaya;
    private long uangBayar;

    //Constructor
    public Transaksi() {}

    public Transaksi(Pasien pasien, Dokter dokter, List<String> keluhan, long totalBiaya, long uangBayar) {
        this.pasien = pasien;
        this.dokter = dokter;
        this.keluhan = keluhan;
        this.totalBiaya = totalBiaya;
        this.uangBayar = uangBayar;
    }

    // Setter & Getter
    public void setPasien(Pasien pasien) {
        this.pasien = pasien;
    }

    public void setDokter(Dokter dokter) {
        this.dokter = dokter;
    }

    public void setKeluhan(List<String> keluhan) {
        this.keluhan = keluhan;
    }

    public void setTotalBiaya(long totalBiaya) {
        this.totalBiaya = totalBiaya;
    }

    public void setUangBayar(long uangBayar) {
        this.uangBayar = uangBayar;
    }

    public Long getIdTransaksi() {
        return idTransaksi;
    }

    public Pasien getPasien() {
        return pasien;
    }

    public Dokter getDokter() {
        return dokter;
    }

    public List<String> getKeluhan() {
        return keluhan;
    }

    public long getTotalBiaya() {
        return totalBiaya;
    }

    public long getUangBayar() {
        return uangBayar;
    }

}
