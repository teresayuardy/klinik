package com.example.klinik.entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class UnPw {

    // Deklarasi variabel
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_akun")
    private Long idAkun;
    private String username, password;

    @OneToOne
    @MapsId // Menggunakan id Insan sebagai PK/FK
    @JoinColumn(name = "id_insan")
    private Insan insan;

    // Constructor
    public UnPw() {
    }

    public UnPw(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Setter & Getter
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setInsan(Insan insan) {
        this.insan = insan;
    }

    public Insan getInsan() {
        return insan;
    }

    public Long getIdAkun() {
        return idAkun;
    }
}