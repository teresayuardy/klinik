package com.example.klinik.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.klinik.entity.Insan;

public interface InsanRepository extends JpaRepository<Insan, Long> {
}