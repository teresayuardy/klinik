package com.example.klinik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.klinik.entity.DokterGigi;
import com.example.klinik.repository.DokterGigiRepository;

import java.util.List;

@Service
public class DokterGigiService {

    @Autowired
    private DokterGigiRepository dokterGigiRepository;

    //Mengambil semua data dokter gigi dari database
    public List<DokterGigi> findAll() {
        return dokterGigiRepository.findAll();
    }

    //Mengambil data dokter gigi berdasarkan id
    public DokterGigi findById(String id) {
        return dokterGigiRepository.findById(id).orElse(null);
    }
}
