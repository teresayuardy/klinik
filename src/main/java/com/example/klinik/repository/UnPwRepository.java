package com.example.klinik.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.klinik.entity.UnPw;
// import java.util.Optional;

public interface UnPwRepository extends JpaRepository<UnPw, Long> {
    UnPw findByUsername(String username);
    Long deleteByInsanIdInsan(Long idInsan);
}