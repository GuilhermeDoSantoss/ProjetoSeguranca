package com.guilhermesantos.authdemo.controller;

import com.guilhermesantos.authdemo.dto.LoginDTO;
import com.guilhermesantos.authdemo.model.User;
import com.guilhermesantos.authdemo.security.AuthToken;
import com.guilhermesantos.authdemo.security.TokenUtil;
import com.guilhermesantos.authdemo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {

    @Autowired
    private IUserService service;

    @PostMapping("/login")
    public ResponseEntity<AuthToken> realizarLogin(@RequestBody LoginDTO dadosLogin){
            AuthToken tk = service.login(dadosLogin);
            if (tk != null) {
                return ResponseEntity.ok(tk);

        }
        return ResponseEntity.status(403).build();
    }

    @PostMapping("/users")
    public ResponseEntity<User> addNewUser(@RequestBody User user){
        return ResponseEntity.status(201).body(service.create(user));
    }

}
