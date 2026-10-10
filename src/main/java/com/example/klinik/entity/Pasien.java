package com.example.klinik.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.Size;

@Entity
@PrimaryKeyJoinColumn(name = "id_insan")
public class Pasien extends Insan {
    @OneToOne
    @JoinColumn(name = "id_insan")
    private Insan insan;
    @Size(min = 1, max = 50)
    private String alergi;

    // Constructor
    public Pasien() {
    }

    public Pasien(String nama, int umur, String telp, String alergi) {
        super(nama, umur, telp);
        this.alergi = alergi;
    }

    // Setter & Getter
    public void setAlergi(String alergi) {
        this.alergi = alergi;
    }

    public String getAlergi() {
        return alergi;
    }
}