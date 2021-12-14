package kz.iitu.gateway.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/auth")
public class AuthContoller {

    @PostMapping("/login")
    public void login(@RequestParam String username, @RequestParam String password) {
        System.out.println("login");
    }

}
