package com.example.klinik.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.klinik.entity.Insan;
import com.example.klinik.repository.InsanRepository;

@Service
public class InsanService {
    @Autowired
    private InsanRepository insanRepository;

    //Mengambil seluruh data insan dari database
    public List<Insan> getAllInsan(){
        return insanRepository.findAll();
    }

    public Insan addInsan(Insan obj){
        //Kalau idnya null, akan digenerate id
        Long idInsan = null;
        obj.setIdInsan(idInsan);
        return insanRepository.save(obj);
    }

    //Mengambil data dari database berdasarkan id_insan, jika tidak ada akan return null
    public Insan getInsanById(long idInsan){
        return insanRepository.findById(idInsan).orElse(null);
    }

    //Update insan berdasarkan id
    public Insan updateInsan(long idInsan, Insan obj) {
        obj.setIdInsan(idInsan);
        return insanRepository.save(obj);
    }

    //Delete insan berdasarkan id
    public void deleteInsan(long idInsan) {
        insanRepository.deleteById(idInsan);
    }
}
