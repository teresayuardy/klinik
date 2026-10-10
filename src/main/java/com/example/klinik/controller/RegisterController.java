package com.example.klinik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.klinik.entity.UnPw;
import com.example.klinik.entity.Pasien;

import com.example.klinik.service.UnPwService;
import com.example.klinik.service.PasienService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class RegisterController {

    @Autowired
    private UnPwService unPwService;

    @Autowired
    private PasienService pasienService;

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/pvalidateRegister")
    public String validateRegister(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String nama,
            @RequestParam int umur,
            @RequestParam String telp,
            @RequestParam String alergi,
            HttpServletRequest request) {

        //Cek apakah username sudah pernah digunakan
        if (unPwService.existsByUsername(username)) {
            return "redirect:/register?error=true";
        }

        //Membuat pasien baru
        Pasien pasien = new Pasien();
        pasien.setNama(nama);
        pasien.setUmur(umur);
        pasien.setTelp(telp);
        pasien.setAlergi(alergi);

        //Simpan ke database, pasien akan mendapatkan id
        Pasien pasienTersimpan = pasienService.savePasien(pasien);

        //Buat akun UnPw dan dihubungkan dengan pasien
        UnPw akunBaru = new UnPw();
        akunBaru.setUsername(username);
        akunBaru.setPassword(password);
        akunBaru.setInsan(pasienTersimpan);

        //Save akun
        unPwService.save(akunBaru);

        return "redirect:/welcome";
    }
}