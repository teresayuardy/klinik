package com.example.klinik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import org.springframework.ui.Model;

import com.example.klinik.entity.Pasien;
import com.example.klinik.entity.Transaksi;
import com.example.klinik.repository.TransaksiRepository;
import com.example.klinik.service.PasienService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class InformasiAkunController {
    @Autowired
    private TransaksiRepository transaksiRepository;

    @Autowired
    private PasienService pasienService;

    @PostMapping("/akun/update")
    public String pasienEdit(
            @RequestParam Long idInsan,
            @RequestParam String nama,
            @RequestParam int umur,
            @RequestParam String telp,
            @RequestParam String alergi,
            HttpServletRequest request) {

        // Mengambil data pasien lama menggunakan idInsan
        Pasien pasienLama = pasienService.getPasienById(idInsan);

        // Jika pasien tidak ditemukan
        if (pasienLama == null) {
            return "redirect:/informasiakun?error=notfound";
        }

        // Melakukan update
        pasienLama.setNama(nama);
        pasienLama.setUmur(umur);
        pasienLama.setTelp(telp);
        pasienLama.setAlergi(alergi);

        // Update ke database
        Pasien pasienDiupdate = pasienService.savePasien(pasienLama);
        // Update sesi
        request.getSession().setAttribute("Pasien", pasienDiupdate);

        return "redirect:/informasiakun?success=updated";
    }

    // Delete akun
    @PostMapping(value = "/akun/submit/{id}", params = { "delete" })
    public String pasienDelete(
            @PathVariable("id") Long idInsan,
            Model model) {

        // Jika delete berhasil, maka akan logout
        try {
            pasienService.deletePasienDanDataTerkait(idInsan);
            return "redirect:/logout";
        } // Jika delete gagal, akan tampil pesan error
        catch (Exception e) {
            model.addAttribute("error", "Gagal menghapus akun: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/informasiakun")
    public String informasiAkun(HttpServletRequest request, Model model) {
        Pasien pasien = (Pasien) request.getSession().getAttribute("Pasien");
        if (pasien == null) {
            return "redirect:/login";
        }

        // Ambil semua data transaksi milik pasien
        List<Transaksi> transaksiList = transaksiRepository.findByPasien(pasien);

        // Mengirim data untuk ditampilkan di view
        model.addAttribute("pasien", pasien);
        model.addAttribute("transaksiList", transaksiList);

        return "informasiakun";
    }

}
