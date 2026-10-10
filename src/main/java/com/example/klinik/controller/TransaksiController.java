package com.example.klinik.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import com.example.klinik.entity.Pasien;
import com.example.klinik.entity.Dokter;
import com.example.klinik.entity.Transaksi;
import com.example.klinik.repository.TransaksiRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class TransaksiController {

    @Autowired
    private TransaksiRepository transaksiRepository;

    @PostMapping("/transaksi/simpan")
    public String simpanTransaksi(@RequestParam("uangBayar") long uangBayar,
            HttpSession session,
            Model model) {

        //Mengambil seluruh data dari sesi
        Pasien pasien = (Pasien) session.getAttribute("Pasien");
        Dokter dokter = (Dokter) session.getAttribute("dokter");
        List<String> keluhanList = (List<String>) session.getAttribute("keluhanList");

        if (pasien == null) {
            return "redirect:/login";
        }

        //jika ada data yang tidak ditemukan, maka transaksi tidak vali==tidak bisa dilanjutkan
        if (dokter == null || keluhanList == null || keluhanList.isEmpty()) {
            model.addAttribute("error", "Data transaksi tidak lengkap.");
            return "error";
        }

        //proses perhitungan total biaya
        int jumlahKeluhan = keluhanList.size();
        long tarif = dokter.getTarif();
        long totalBiaya = jumlahKeluhan * tarif;

        // check nominal uang bayar
        if (uangBayar < totalBiaya) {
            model.addAttribute("pasien", pasien);
            model.addAttribute("dokter", dokter);
            model.addAttribute("keluhanList", keluhanList);
            model.addAttribute("jumlahKeluhan", jumlahKeluhan);
            model.addAttribute("tarif", tarif);
            model.addAttribute("totalBiaya", totalBiaya);
            //Dikembalikan ke halaman bayar
            model.addAttribute("error", "Uang bayar kurang dari total biaya. Silakan masukkan lagi.");
            return "bayar";
        }

        // Menyimpan transaksi ke database
        Transaksi transaksi = new Transaksi();
        transaksi.setPasien(pasien);
        transaksi.setDokter(dokter);
        transaksi.setKeluhan(keluhanList);
        transaksi.setTotalBiaya(totalBiaya);
        transaksi.setUangBayar(uangBayar);

        Transaksi saved = transaksiRepository.save(transaksi);

        //Membersihkan keluhanList dan dokter dari sesi
        session.removeAttribute("keluhanList");
        session.removeAttribute("dokter");

        return "redirect:/transaksi/struk/" + saved.getIdTransaksi();
    }

    @GetMapping("/transaksi/struk/{id}")
    public String strukTransaksi(@PathVariable("id") Long id,
            HttpSession session,
            Model model) {

        Pasien pasienSession = (Pasien) session.getAttribute("Pasien");
        if (pasienSession == null) {
            return "redirect:/login"; // Arahkan ke login jika belum ada Pasien di sesi
        }

        //Mengambil data transaksi
        Transaksi transaksi = transaksiRepository.findById(id).orElse(null);

        if (transaksi == null) {
            model.addAttribute("error", "Transaksi tidak ditemukan.");
            return "error";
        }

        //Jika transaksi yang diambil tidak ditemukan sesuai id yg dikirim ke url, error
        if (!transaksi.getPasien().getIdInsan().equals(pasienSession.getIdInsan())) {
            model.addAttribute("error", "Akses ditolak. Transaksi bukan milik Anda.");
            return "error";
        }

        //Proses hitung kembalian
        long kembalian = transaksi.getUangBayar() - transaksi.getTotalBiaya();

        //Mengirim ke view
        model.addAttribute("transaksi", transaksi);
        model.addAttribute("kembalian", kembalian);

        return "struk";
    }

}