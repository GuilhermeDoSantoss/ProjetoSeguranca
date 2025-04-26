package com.guilhermesantos.authdemo.service;

import com.guilhermesantos.authdemo.dto.LoginDTO;
import com.guilhermesantos.authdemo.model.User;
import com.guilhermesantos.authdemo.security.AuthToken;

public interface IUserService {

    public User create(User usuario);
    public AuthToken login(LoginDTO dto);
}
