package com.guilhermesantos.authdemo.service;

import com.guilhermesantos.authdemo.dto.LoginDTO;
import com.guilhermesantos.authdemo.model.User;
import com.guilhermesantos.authdemo.repository.UserRepo;
import com.guilhermesantos.authdemo.security.AuthToken;
import com.guilhermesantos.authdemo.security.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserServiceImpl implements IUserService{


    @Autowired
    private UserRepo repo;

    @Override
    public User create(User usuario) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String novaSenha = encoder.encode(usuario.getPassword());
        usuario.setPassword(novaSenha);
        return repo.save(usuario);
    }

    @Override
    public AuthToken login(LoginDTO dto) {
        User res = repo.findByLogin(dto.login());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(dto.password(), res.getPassword())){
            return TokenUtil.encode(dto);
        }
        return null;
    }
}
