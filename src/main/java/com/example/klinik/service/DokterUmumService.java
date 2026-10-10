package com.example.klinik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.klinik.entity.DokterUmum;
import com.example.klinik.repository.DokterUmumRepository;

import java.util.List;

@Service
public class DokterUmumService {

    @Autowired
    private DokterUmumRepository dokterUmumRepository;

    //Mengambil semua data dokter umum dari database
    public List<DokterUmum> findAll() {
        return dokterUmumRepository.findAll();
    }

    //Mengambil data dokter umum berdasarkan id
    public DokterUmum findById(String id) {
        return dokterUmumRepository.findById(id).orElse(null);
    }

    // public DokterUmum save(DokterUmum dokter) {
    //     return dokterUmumRepository.save(dokter);
    // }

    // public DokterUmum findById(String id) {
    //     return dokterUmumRepository.findById(id).orElse(null);
    // }

    // public void delete(String id) {
    //     dokterUmumRepository.deleteById(id);
    // }
}
