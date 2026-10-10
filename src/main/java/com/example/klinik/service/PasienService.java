package com.example.klinik.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.klinik.entity.Pasien;
import com.example.klinik.repository.PasienRepository;
import com.example.klinik.repository.TransaksiRepository;

@Service
public class PasienService {

    @Autowired
    private PasienRepository pasienRepository;

    @Autowired
    private TransaksiRepository transaksiRepository; // Asumsikan Anda punya ini

    @Transactional
    public void deletePasienDanDataTerkait(Long idInsan) {
        //Menghapus seluruh transaksi, lalu hapus insan
        transaksiRepository.deleteByPasien_IdInsan(idInsan);
        pasienRepository.deleteById(idInsan);
    }

    //Mengambil seluruh data pasien dari database
    public List<Pasien> getAllPasien() {
        return pasienRepository.findAll();
    }

    //Menambahkan pasien dari object
    public Pasien addPasien(Pasien obj) {
        obj.setIdInsan(null);
        return pasienRepository.save(obj);
    }

    //Mengambil data pasien berdasarkan id
    public Pasien getPasienById(long idInsan) {
        return pasienRepository.findById(idInsan).orElse(null);
    }

    public Pasien findByIdPasien(Long id) {
        return pasienRepository.findById(id).orElse(null);
    }

    //Menyimpan pasien baru ke database
    public Pasien savePasien(Pasien pasien) {
        return pasienRepository.save(pasien); // Mengembalikan objek Pasien yang sudah tersimpan
    }

    //Update data pasien berdasarkan id
    public Pasien updatePasien(Long id, Pasien newData) {
        newData.setIdInsan(id);
        return pasienRepository.save(newData);
    }

    //Hapus data pasien berdasarkan id
    public void deleteByIdPasien(Long id) {
        pasienRepository.deleteById(id);
    }

    //Cari pasien berdasarkan nama
    public Pasien findByNama(String nama) {
        return pasienRepository.findByNama(nama);
    }
}
