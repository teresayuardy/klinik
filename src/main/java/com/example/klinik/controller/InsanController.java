package com.example.klinik.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.klinik.entity.Insan;
import com.example.klinik.entity.Pasien;
import com.example.klinik.service.InsanService;
import com.example.klinik.service.PasienService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class InsanController {
    @Autowired
    private InsanService insanService;
    @Autowired
    private PasienService pasienService;

    @GetMapping(value = { "/insan", "/insan/" })
    public String InsanPage(Model model) {
        @SuppressWarnings("unused")
        //Mengambil semua data insan
        List<Insan> insanList = insanService.getAllInsan();
        //Mengirim untuk ditampilkan di view
        model.addAttribute("insanList", insanService.getAllInsan());
        model.addAttribute("insanInfo", new Insan());
        return "insan.html";
    }

    //Mengambil insan sesuai dengan id
    @GetMapping("/insan/{id}")
    public String insanGetRec(Model model, @PathVariable("id") long id) {
        List<Insan> insanList = insanService.getAllInsan();
        //Mengambil data salah satu insan berdasarkan id
        Insan insanRec = insanService.getInsanById(id);
        //Data dikirim ke view
        model.addAttribute("insanList", insanList);
        model.addAttribute("insanRec", insanRec);
        return "insan";
    }

    //Menambah insan baru
    @PostMapping(value = { "/insan/submit/", "/insan/submit/{id}" }, params = { "add" })
    public String insanAdd(@ModelAttribute("insanInfo") Insan insanInfo) {
        insanService.addInsan(insanInfo);
        return "redirect:/insan";
    }

    //Update data insan
    @PostMapping(value = "/insan/submit/{id}", params = { "edit" })
    public String insanEdit(@ModelAttribute("insanInfo") Insan insanInfo,
            @PathVariable("id") long id) {
        insanService.updateInsan(id, insanInfo);
        return "redirect:/insan";
    }

    //Delete data insan
    @PostMapping(value = "/insan/submit/{id}", params = { "delete" })
    public String insanDelete(@PathVariable("id") long id) {
        insanService.deleteInsan(id);
        return "redirect:/insan";
    }

    @GetMapping(value = { "/pasien", "/pasien/" })
    public String PasienPage(Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("Pasien") != null) {
            //Mengambil semua data pasien
            List<Pasien> pasienList = pasienService.getAllPasien();
            //Mengirim ke view
            model.addAttribute("pasienList", pasienList);
            model.addAttribute("pasienInfo", new Pasien()); //Objek kosong agar bisa diinput
            model.addAttribute("logPasien", request.getSession().getAttribute("Pasien")); //Data pasien saat ini
            return "pasien";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/pasien/{id}")
    public String pasienGetRec(Model model, @PathVariable("id") long id, HttpServletRequest request) {
        if (request.getSession().getAttribute("Pasien") != null) {
            List<Pasien> pasienList = pasienService.getAllPasien();
            //Mengambil data berdasarkan id
            //Detail pasien disimpan
            Pasien pasienRec = pasienService.getPasienById(id);
            model.addAttribute("pasienList", pasienList);
            model.addAttribute("pasienRec", pasienRec);
            model.addAttribute("pasienInfo", new Pasien());
            model.addAttribute("logPasien", request.getSession().getAttribute("Pasien"));
            return "pasien";
        } else {
            return "redirect:/login";
        }
    }

    //Menambah pasien baru
    @PostMapping(value = { "/pasien/submit", "/pasien/submit/{id}" }, params = { "add" })
    public String pasienAdd(@ModelAttribute("pasienInfo") Pasien pasienInfo) {
        pasienService.addPasien(pasienInfo);
        return "redirect:/pasien";
    }

    //Update pasien
    @PostMapping(value = "/pasien/submit/{id}", params = { "edit" })
    public String pasienEdit(@ModelAttribute("pasienInfo") Pasien pasienInfo,
            @PathVariable("id") long id) {
        pasienService.updatePasien(id, pasienInfo);
        return "redirect:/pasien";
    }

    //Delete pasien
    @PostMapping(value = "/pasien/submit/{id}", params = { "delete" })
    public String pasienDelete(@PathVariable("id") long id) {
        pasienService.deleteByIdPasien(id);
        return "redirect:/pasien";
    }

    @GetMapping("/informasiakun/delete")
    public String deleteAkun(HttpServletRequest request) {
        //Menyimpan data pasien ke sesi saat login
        Pasien logged = (Pasien) request.getSession().getAttribute("Pasien");
        //Data dihapus berdasarkan id
        pasienService.deleteByIdPasien(logged.getIdInsan());
        return "redirect:/welcome";
    }

    @GetMapping("/informasiakun/update")
    public String updateAkun(Model model, HttpServletRequest request) {
        Pasien logged = (Pasien) request.getSession().getAttribute("Pasien");
        if (logged == null)
            return "redirect:/login";
        //Mengirim data pasien login ke view
        model.addAttribute("pasien", logged);
        return "updateakun";
    }

    //Menyimpan update
    @PostMapping("/informasiakun/submit")
    public String submitUpdate(@ModelAttribute Pasien updateData, HttpServletRequest request) {
        Pasien logged = (Pasien) request.getSession().getAttribute("Pasien");
        pasienService.updatePasien(logged.getIdInsan(), updateData);
        return "redirect:/informasiakun";
    }
}