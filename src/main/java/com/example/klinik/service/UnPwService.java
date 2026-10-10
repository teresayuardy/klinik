package com.example.klinik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.klinik.entity.UnPw;
import com.example.klinik.repository.UnPwRepository;

@Service
public class UnPwService {

    @Autowired
    private UnPwRepository unPwRepository;

    @Transactional
    //Delete insan berdasarkan id
    public void deleteByInsanId(Long idInsan) {
        unPwRepository.deleteByInsanIdInsan(idInsan);
    }

    //Mengambil data akun berdasarkan username
    public UnPw login(String username, String password) {
        UnPw akun = unPwRepository.findByUsername(username);

        if (akun != null && akun.getPassword().equals(password)) {
            return akun;
        }

        return null;
    }

    // Mengecek apakah username sudah ada
    public boolean existsByUsername(String username) {
        UnPw akun = unPwRepository.findByUsername(username);
        return akun != null;
    }

    // Menyimpan akun baru
    public void save(UnPw akun) {
        unPwRepository.save(akun);
    }
}
