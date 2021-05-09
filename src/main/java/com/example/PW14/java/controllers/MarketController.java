package com.example.PW14.java.controllers;

import java.util.List;

import com.example.PW14.java.enities.Market;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/market")
public class MarketController {
    @Autowired
    SessionFactory sessionsFactory;

    @GetMapping("/create")
    public void createMarket(String name, String address) {
        Session session = sessionsFactory.getCurrentSession();
        session.save(new Market(name, address));
    }
    @GetMapping("/delete")
    public void closeMarket(String name) {
        Session session = sessionsFactory.getCurrentSession();
        org.hibernate.Transaction trs = session.beginTransaction();
        session.createQuery("delete from Market where name=:name").setParameter("name", name).executeUpdate();
        trs.commit();
    }
    @GetMapping("/show")
    public String showAll() {
        Session session = sessionsFactory.getCurrentSession();
        List<Market>market_list = session.createQuery("FROM Market").list();
        String result = "";
        for (int i=0;i<market_list.size();i++) {
            result += market_list.get(i).getId() + " " + market_list.get(i).getName() + " " + market_list.get(i).getAddress() +"</br>";
        }
        return result;
    }
}