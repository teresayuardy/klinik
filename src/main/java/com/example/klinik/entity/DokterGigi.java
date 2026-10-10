package com.example.klinik.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "dokter_gigi") //nama table di database
public class DokterGigi extends Dokter {

    // Deklarasi variabel
    private boolean bedahMulut;

    // Constructor
    public DokterGigi() {}

    public DokterGigi(String tipe, String Id, String nama, int umur, String telp, long gaji, long tarif,
            String ruangPraktik, boolean bedahMulut) {
        super(tipe, Id, nama, umur, telp, gaji, tarif, ruangPraktik);
        this.bedahMulut = bedahMulut;
    }

    // Setter & Getter
    public void setBedahMulut(boolean bedahMulut) {
        this.bedahMulut = bedahMulut;
    }

    public boolean getbedahMulut() {
        return bedahMulut;
    }

    public String getSBedah() {
        return bedahMulut ? "BISA" : "TIDAK BISA";
    }

    // public void tampilkanSpesialisasi() {
    //     super.tampilkanSpesialisasi();
    //     System.out.println("Spesialisasi: Ahli dalam masalah gigi dan mulut.");
    //     System.out.println("bedahMulut: " + getSBedah() + "\n\n");
    // }

}