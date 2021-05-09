package com.example.PW14.java.controllers;

import java.util.List;

import com.example.PW14.java.enities.Product;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    SessionFactory sessionsFactory;

    @GetMapping("/create")
    public void createProduct(String name, int price) {
        Session session = sessionsFactory.getCurrentSession();
        session.save(new Product(name, price));
    }
    @GetMapping("/delete")
    public void closeProduct(String name) {
        Session session = sessionsFactory.getCurrentSession();
        org.hibernate.Transaction trs = session.beginTransaction();
        session.createQuery("delete from Product where name=:name").setParameter("name", name).executeUpdate();
        trs.commit();
    }
    @GetMapping("/show")
    public String showAll() {
        Session session = sessionsFactory.getCurrentSession();
        List<Product>product_list = session.createQuery("FROM Product").list();
        String result = "";
        for (int i=0;i<product_list.size();i++) {
            result += product_list.get(i).getId() + " " + product_list.get(i).getName() + " " + product_list.get(i).getPrice() +"</br>";
        }
        return result;
    }
}