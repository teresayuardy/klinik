package com.example.klinik.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.klinik.entity.Transaksi;
import com.example.klinik.entity.Pasien;
import java.util.List;


public interface TransaksiRepository extends JpaRepository<Transaksi, Long> {
    List<Transaksi> findByPasien(Pasien pasien); //Mengambil seluruh data transaksi berdasarkan objek pasien
    void deleteByPasien_IdInsan(Long idInsan); //delete semua transaksi berdasarkan id_insan
}


