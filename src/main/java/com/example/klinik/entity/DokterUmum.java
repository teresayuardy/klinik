package com.example.klinik.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "dokter_umum") //nama table di database
public class DokterUmum extends Dokter {

    // Deklarasi variabel
    private boolean bisaMerujuk;

    // Constructor
    public DokterUmum() {
    }

    public DokterUmum(String tipe, String id, String nama, int umur, String telp, long gaji, long tarif,
            String ruangPraktik, boolean bisaMerujuk) {
        super(tipe, id, nama, umur, telp, gaji, tarif, ruangPraktik);
        this.bisaMerujuk = bisaMerujuk;
    }

    // Setter & Getter
    public void setBisaMerujuk(boolean bisaMerujuk) {
        this.bisaMerujuk = bisaMerujuk;
    }

    public boolean getBisaMerujuk() {
        return bisaMerujuk;
    }

    public String getSMerujuk() {
        return bisaMerujuk ? "BISA" : "TIDAK BISA";
    }

    // public String setSMerujuk() {
    // if (bisaMerujuk) {
    // return "BISA";
    // } else {
    // return "TIDAK BISA";
    // }
    // }

    // public void tampilkanSpesialisasi() {
    // super.tampilkanSpesialisasi();
    // System.out.println("Spesialisasi: Mampu menangani berbagai penyakit umum.");
    // System.out.println("Bisa merujuk: " + setSMerujuk());
    // }
}