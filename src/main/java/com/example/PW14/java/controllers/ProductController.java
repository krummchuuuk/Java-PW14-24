package com.example.PW14.java.controllers;

import com.example.PW14.java.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService service;
    @GetMapping("/create")
    public void createProduct(String name, int price, int id) {
        service.createNewProduct(name, price, id);
    }
    @GetMapping("/delete")
    public void soldProduct(String name) {
        service.soldAnyProduct(name);
    }
    @GetMapping("show")
    public String showBy(String by, String criteria) {
        return service.showByCriteria(by, criteria);
    }
}