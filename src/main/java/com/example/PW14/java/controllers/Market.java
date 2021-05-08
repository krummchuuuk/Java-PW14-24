package com.example.PW14.java.controllers;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/market")
public class Market {
    private HashMap<String, String> markets = new HashMap<>();
    private String name;
    private String address;

    @GetMapping("/create")
    public String createMarket(String name, String address) {
        this.name = name;
        this.address = address;
        markets.put(this.name, this.address);
        return("You opened " + this.name + " on " + this.address+"\n");
    }
    @GetMapping("/delete")
    public String closeMarket(String name) {
        markets.remove(name);
        return("You closed " + name + "\n");
    }
    @GetMapping("/show")
    public String showAll() {
        String result = "";
        for (String market: markets.keySet()) {
            result += market + " on " + markets.get(market) + ";\n";
        }
        return result;
    }
}