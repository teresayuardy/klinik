package com.example.klinik.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

@Entity
@Table(name = "insan") //nama table di database
@Inheritance(strategy = InheritanceType.JOINED)
public class Insan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_insan")
    private Long idInsan;

    // Child akan terhapus bila parent dihapus
    @OneToOne(mappedBy = "insan", cascade = { CascadeType.REMOVE },
            orphanRemoval = true)
    private UnPw unPw;
    
    @NotNull
    @Size(min = 1, max = 50)
    private String nama;

    @Min(1)
    @Max(99)
    private int umur;

    @Size(min = 11, max = 12)
    private String telp;

    // Constructor
    public Insan() {
    }

    public Insan(String nama, int umur, String telp) {
        this.nama = nama;
        this.umur = umur;
        this.telp = telp;
    }

    // public Insan(Scanner sc) {
    // // Meminta user input untuk setiap variabel dan dimasukkan ke setter
    // System.out.println("Masukkan nama: ");
    // nama = sc.nextLine();
    // System.out.println("Masukkan umur: ");
    // umur = sc.nextInt();
    // System.out.println("Masukkan nomor telepon: ");
    // telp = sc.nextLine();
    // }

    // Setter & Getter
    public void setIdInsan(Long idInsan) { // Ganti setId
        this.idInsan = idInsan;
    }

    public Long getIdInsan() { // Ganti getId
        return idInsan;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }

    public int getUmur() {
        return umur;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getTelp() {
        return telp;
    }

    // public void tampilkanSpesialisasi() {
    // System.out.println("Nama: " + nama);
    // System.out.println("Umur: " + umur);
    // System.out.println("Nomor Telepon: " + telp);
    // }
}
