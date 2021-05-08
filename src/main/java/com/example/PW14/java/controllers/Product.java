package com.example.PW14.java.controllers;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class Product {
    private HashMap<String, String> products = new HashMap<>();
    private String name;
    private int price;

    @GetMapping("/create")
    public String newProduct(String name, int price) {
        this.name = name;
        this.price = price;
        products.put(this.name, String.format("%d", this.price));
        return "You bought " + this.name + " for " + String.format("%d", this.price)+ "\n";
    }

    @GetMapping("/delete")
    public String sell(String name) {
        products.remove(name);
        return "You sold " + name + "\n";
    }

    @GetMapping("/show")
    public String showAll() {
        String result = "";
        for (String product: products.keySet()) {
            result += product + " is " + products.get(product) + ";\n";
        }
        return result;
    }
}
