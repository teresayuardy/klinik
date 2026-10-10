package com.example.klinik.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.klinik.entity.DokterUmum;

public interface DokterUmumRepository extends JpaRepository<DokterUmum, String> {
}