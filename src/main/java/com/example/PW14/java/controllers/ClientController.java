package com.example.PW14.java.controllers;

import javax.transaction.Transactional;

import com.example.PW14.java.entities.Client;
import com.example.PW14.java.services.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Transactional
public class ClientController {
    @Autowired
    UserDetailsServiceImpl cService;
    
    @GetMapping("/registration") 
    public String Registration() {
        return "html/registrationForm.html";
    }
    @PostMapping("/registration")
    public String newClient(Model model, @RequestParam("name") String name, @RequestParam("surname") String surname, @RequestParam("email") String email, @RequestParam("username") String username, @RequestParam("password") String password) {
        cService.saveClient(new Client(name, surname, email, username, password));
        return "redirect:/";
    }
    @GetMapping("/login")
    public String Login() {
        return "html/loginForm.html";
    }
}