package com.example.klinik.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.klinik.entity.Pasien;

public interface PasienRepository extends JpaRepository<Pasien, Long> {
    //Mengambil data berdasarkan nama
    Pasien findByNama(String nama);
}