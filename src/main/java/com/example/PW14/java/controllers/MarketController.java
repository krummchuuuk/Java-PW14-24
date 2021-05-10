package com.example.PW14.java.controllers;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.example.PW14.java.enities.Market;

import org.hibernate.Query;
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

    @GetMapping("show")
    public String showBy(String by, String criteria) {
        if (criteria == null) {
            Session session = sessionsFactory.getCurrentSession();
            List<Market>market_list = session.createQuery("FROM Market").list();
            String result = "";
            for (int i=0;i<market_list.size();i++) {
            result += market_list.get(i).getId() + " " + market_list.get(i).getName() + " " + market_list.get(i).getAddress() +"</br>";
            }
            return result;
        }
        else {
            Session session = sessionsFactory.getCurrentSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Market> marketCriteriaQuery = builder.createQuery(Market.class);
            Root<Market> root = marketCriteriaQuery.from(Market.class);
            marketCriteriaQuery.select(root).where(builder.equal(root.get(by), criteria));
            Query<Market> query = session.createQuery(marketCriteriaQuery);
            List<Market> market_list = query.getResultList();
            String result = "";
            for (int i=0;i<market_list.size();i++) {
                result += market_list.get(i).toString() + "</br>";
            }
            return result;
        }
    }
}