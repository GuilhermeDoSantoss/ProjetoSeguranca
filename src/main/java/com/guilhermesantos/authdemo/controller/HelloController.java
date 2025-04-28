package com.guilhermesantos.authdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello Word from Spring Security";
    }

    @GetMapping("/auth")
    public String sayHelloAuth(){
        return "Hello Word from Spring Security Authentication Endpoint";
    }

    @GetMapping("/user")
    public String sayHelloToUser(){
        return "Hello World to User";
    }
    @GetMapping("/admin")
    public String sayHelloToAdmin(){
        return "Hello World to Admin";
    }

    @GetMapping("/master")
    public String sayHelloToMaster(){
        return "Hello World to Master";
    }
}
