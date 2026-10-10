package com.example.klinik.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.klinik.entity.Dokter;
import com.example.klinik.entity.DokterGigi;
import com.example.klinik.entity.DokterUmum;
import com.example.klinik.entity.Pasien;

import com.example.klinik.service.DokterGigiService;
import com.example.klinik.service.DokterUmumService;

import jakarta.servlet.http.HttpSession;

@Controller
public class DokterController {
    @Autowired
    private DokterUmumService dokterUmumService;
    @Autowired
    private DokterGigiService dokterGigiService;

    // Halaman awal pemilihan spesialisasi
    @GetMapping("/dokter")
    public String dokterPage(HttpSession session) {
        Pasien pasien = (Pasien) session.getAttribute("Pasien");
        if (pasien == null) {
            return "redirect:/login";
        }
        return "dokter";
    }

    // Proses pilihan spesialisasi
    @GetMapping("/dokter/pilih")
    public String prosesPilihanSpesialis(@RequestParam("spesialis") String spesialis, HttpSession session) {
        Pasien pasien = (Pasien) session.getAttribute("Pasien");
        if (pasien == null) {
            return "redirect:/login";
        }
        // user akan dilempar ke halaman dokter spesialis sesuai pilihannya
        if ("gigi".equalsIgnoreCase(spesialis)) {
            return "redirect:/doktergigi";
        } else if ("umum".equalsIgnoreCase(spesialis)) {
            return "redirect:/dokterumum";
        } else {
            return "redirect:/dokter?error=true";
        }
    }

    // Tampilkan daftar dokter umum
    @GetMapping("/dokterumum")
    public String listDokterUmum(Model model, HttpSession session) {
        Pasien pasien = (Pasien) session.getAttribute("Pasien");
        if (pasien == null) {
            return "redirect:/login";
        }
        // Menampilkan seluruh dokter umum
        List<DokterUmum> list = dokterUmumService.findAll();
        model.addAttribute("dokterList", list);
        return "dokterumum";
    }

    // Tampilkan daftar dokter gigi
    @GetMapping("/doktergigi")
    public String listDokterGigi(Model model, HttpSession session) {
        Pasien pasien = (Pasien) session.getAttribute("Pasien");
        if (pasien == null) {
            return "redirect:/login";
        }
        // Menampilkan seluruh dokter umum
        List<DokterGigi> list = dokterGigiService.findAll();
        model.addAttribute("dokterList", list);
        return "doktergigi";
    }

    // Input keluhan untuk dokter yang dipilih
    @GetMapping("/keluhan")
    public String formKeluhan(@RequestParam("idDokter") String idDokter,
            HttpSession session,
            Model model) {
        Pasien pasien = (Pasien) session.getAttribute("Pasien");
        if (pasien == null) {
            return "redirect:/login";
        }
        // Mencari berdasarkan id Dokter, apakah dokter gigi? bila null dicari di dokter
        // umum
        Dokter dokter = dokterGigiService.findById(idDokter);
        if (dokter == null) {
            dokter = dokterUmumService.findById(idDokter);
        }
        // Menampilkan error bila id tsb tidak ditemukan
        if (dokter == null) {
            model.addAttribute("error", "Dokter tidak ditemukan.");
            return "error";
        }
        // Add data untuk dikirim ke HTML
        model.addAttribute("dokter", dokter);
        model.addAttribute("pasien", pasien);
        // Menyimpan dokter pilihan ke sesi saat itu
        session.setAttribute("dokter", dokter);

        return "keluhan";
    }

    // Tampilkan detail dokter (opsional)
    @GetMapping("/dokter/detail")
    public String detailDokter(@RequestParam("idDokter") String idDokter, Model model) {
        Dokter dokter = dokterGigiService.findById(idDokter);
        if (dokter == null) {
            dokter = dokterUmumService.findById(idDokter);
        }

        if (dokter == null) {
            model.addAttribute("error", "Dokter tidak ditemukan.");
            return "error";
        }

        model.addAttribute("dokter", dokter);
        return "detaildokter";
    }

    // Input keluhan
    @PostMapping("/keluhan")
    public String tambahKeluhan(@RequestParam("idInsan") Long idInsan,
            @RequestParam("keluhan") String keluhan,
            HttpSession session,
            Model model,
            @RequestParam(value = "add", required = false) String add) {
        Pasien pasien = (Pasien) session.getAttribute("Pasien");
        if (pasien == null) {
            return "redirect:/login";
        }

        // Simpan keluhan sementara di session
        List<String> keluhanList = (List<String>) session.getAttribute("keluhanList");
        if (keluhanList == null) {
            keluhanList = new java.util.ArrayList<>();
        }

        // Merapikan input, untuk dicek nantinya supaya tak ada duplikasi
        String trimmedKeluhan = keluhan.trim();
        if (keluhanList.contains(trimmedKeluhan)) {
            // Jika keluhan sudah ada, set error message
            model.addAttribute("errorAlert",
                    "Keluhan '" + trimmedKeluhan + "' sudah pernah dimasukkan. Keluhan harus unik.");

            // Tambahkan kembali atribut yang dibutuhkan view (untuk HTML)
            model.addAttribute("pasien", pasien);
            model.addAttribute("keluhanList", keluhanList);

            // Kembali ke halaman form keluhan
            return "keluhan";
        }

        //ketika tombol add ditekan, maka add != null
        if (add != null) {
            //Keluhan dimasukkan ke list dan disimpakn ke session
            keluhanList.add(trimmedKeluhan);
            session.setAttribute("keluhanList", keluhanList);
            model.addAttribute("pasien", pasien);
            model.addAttribute("successAlert", "Keluhan berhasil ditambahkan.");
            model.addAttribute("keluhanList", keluhanList);
            // Mengembalikan ke halaman keluhan lagi
            return "keluhan";
        } else {
            // Jika yang ditekan tombol next, berarti add == null
            if (!trimmedKeluhan.isEmpty() && !keluhanList.contains(trimmedKeluhan)) {
                //Input disimpan
                keluhanList.add(trimmedKeluhan);
                session.setAttribute("keluhanList", keluhanList);
            }
            // Lanjut ke page bayar
            return "redirect:/bayar";
        }
    }

    //Page bayar untuk proses pembayaran
    @GetMapping("/bayar")
    public String halamanBayar(HttpSession session, Model model) {
        Pasien pasien = (Pasien) session.getAttribute("Pasien");
        if (pasien == null) {
            return "redirect:/login";
        }

        //Mengambil data dokter dari sesi
        Dokter dokter = (Dokter) session.getAttribute("dokter");
        if (dokter == null) {
            model.addAttribute("error", "Dokter tidak ditemukan.");
            return "error";
        }

        //Mengambil keluhan dari sesi
        List<String> keluhanList = (List<String>) session.getAttribute("keluhanList");
        //form keluhan required, maka sebenarnya tidak mungkin null
        if (keluhanList == null || keluhanList.isEmpty()) {
            model.addAttribute("error", "Keluhan belum diisi.");
            return "error";
        }

        int jumlahKeluhan = keluhanList.size();//Menghitung jumlah keluhan yang dimasukkan
        long tarif = dokter.getTarif();//Mengambil tarif dokter
        long totalBiaya = jumlahKeluhan * tarif;//Menghitung total biaya

        //Mengirim semua data ke View (bayar.html)
        model.addAttribute("pasien", pasien);
        model.addAttribute("dokter", dokter);
        model.addAttribute("keluhanList", keluhanList);
        model.addAttribute("jumlahKeluhan", jumlahKeluhan);
        model.addAttribute("tarif", tarif);
        model.addAttribute("totalBiaya", totalBiaya);

        return "bayar";
    }

}
