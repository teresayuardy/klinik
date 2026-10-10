package com.example.klinik.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.klinik.entity.Dokter;

public interface DokterRepository extends JpaRepository<Dokter, Long> {
}