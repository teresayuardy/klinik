package com.example.klinik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.klinik.entity.Insan;
import com.example.klinik.entity.Pasien;
import com.example.klinik.service.PasienService;
import com.example.klinik.entity.UnPw;
import com.example.klinik.service.UnPwService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    private UnPwService unPwService;
    @Autowired
    private PasienService pasienService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/pvalidateLogin")
    public String validateLogin(
            @RequestParam String username,
            @RequestParam String password,
            HttpServletRequest request) {

        //Memastikan apakah username dan password ada di database dan cocok
        UnPw akun = unPwService.login(username, password);

        if (akun != null) {
            //Mengambil data insan jika akun ditemukan (username, pw)
            Insan insan = akun.getInsan();

            //Memastikan apakah insan tersebut berisi data
            if (insan != null) {
                //Mengambil pasien berdasarkan id insan
                Pasien pasien = pasienService.getPasienById(insan.getIdInsan());

                //Memastikan apakah pasien ditemukan
                if (pasien != null) {
                    //Pasien disimpan ke sesi
                    request.getSession().setAttribute("Pasien", pasien);
                    return "redirect:/dashboard";
                } else {
                    // Jika Insan ada, tapi tidak ditemukan Pasien-nya (data tidak lengkap)
                    return "redirect:/login?error=role_missing";
                }
            }
            // Jika akun ditemukan, tapi tidak memiliki relasi Insan (data database tidak valid)
            return "redirect:/login?error=data_invalid";
        }

        // Login gagal (username/password salah)
        return "redirect:/login?error=true";
    }

    // Mengakhiri sesi jika sudah logout
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/welcome?logout=true";
    }

    // Direct ke dashboard jika berhasil login
    @GetMapping("/dashboard")
    public String dashboard(HttpServletRequest request, Model model) {
        // Menyimpan data pasien yang sedang login
        Object pasien = request.getSession().getAttribute("Pasien");

        if (pasien == null) {
            return "redirect:/login";
        }

        // Mengirim data pasien yang sedang login ke view
        model.addAttribute("logPasien", pasien);
        return "dashboard";
    }

}
