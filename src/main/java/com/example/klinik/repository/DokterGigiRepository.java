package com.example.klinik.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.klinik.entity.DokterGigi;

public interface DokterGigiRepository extends JpaRepository<DokterGigi, String> {
}