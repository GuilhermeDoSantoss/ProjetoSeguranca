package com.guilhermesantos.authdemo.controller;

import com.guilhermesantos.authdemo.dto.LoginDTO;
import com.guilhermesantos.authdemo.security.AuthToken;
import com.guilhermesantos.authdemo.security.TokenUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<AuthToken> realizarLogin(@RequestBody LoginDTO dadosLogin){
        if (dadosLogin.login().equals("guilherme") && dadosLogin.password().equals("1234")){
            AuthToken tk = TokenUtil.encode(dadosLogin);
            if (tk != null) {
                return ResponseEntity.ok(tk);
            }
        }
        return ResponseEntity.status(403).build();
    }
}
