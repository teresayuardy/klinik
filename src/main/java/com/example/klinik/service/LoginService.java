package com.example.klinik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.klinik.entity.Login;
import com.example.klinik.entity.UnPw;
import com.example.klinik.repository.UnPwRepository;
// import java.util.Optional;

@Service
public class LoginService implements Login {

    @Autowired
    private UnPwRepository unPwRepository;

    @Override
    public boolean SignIn(String username, String password) {
        UnPw data = unPwRepository.findByUsername(username);

        //Memastikkan data tidak null dan password sesuai
        if (data != null && data.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }
}
