package com.example.PW14.java.controllers;

import com.example.PW14.java.services.MarketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/market")
public class MarketController {
    @Autowired
    private MarketService service;
    @GetMapping("/create")
    public void createMarket(String name, String address) {
        service.createNewMarket(name, address);
    }
    @GetMapping("/delete")
    public void closeMarket(String name) {
        service.closeAnyMarket(name);
    }
    @GetMapping("show")
    public String show(String by, String criteria) {
        return service.showByCriteria(by, criteria);
    }
}