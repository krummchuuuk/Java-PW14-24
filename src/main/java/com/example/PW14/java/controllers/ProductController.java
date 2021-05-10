package com.example.PW14.java.controllers;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.example.PW14.java.enities.Market;
import com.example.PW14.java.enities.Product;

import org.hibernate.Hibernate;
import org.hibernate.Query;
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
    public void createProduct(String name, int price, int id) {
        Session session = sessionsFactory.getCurrentSession();
        session.save(new Product(name, price, getByID(id)));
    }
    @GetMapping("/delete")
    public void closeProduct(String name) {
        Session session = sessionsFactory.getCurrentSession();
        org.hibernate.Transaction trs = session.beginTransaction();
        session.createQuery("delete from Product where name=:name").setParameter("name", name).executeUpdate();
        trs.commit();
    }
    @GetMapping("show")
    public String showBy(String by, String criteria) {
        if (criteria == null) {
            Session session = sessionsFactory.getCurrentSession();
            List<Product>products_list = session.createQuery("FROM Product").list();
            String result = "";
            for (int i=0;i<products_list.size();i++) {
            result += products_list.get(i).getId() + " " + products_list.get(i).getName() + " " + products_list.get(i).getPrice() + " " + products_list.get(i).getMarketID() +"</br>";
            }
            return result;
        }
        else {
            Session session = sessionsFactory.getCurrentSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Product> productCriteriaQuery = builder.createQuery(Product.class);
            Root<Product> root = productCriteriaQuery.from(Product.class);
            productCriteriaQuery.select(root).where(builder.equal(root.get(by), criteria));
            Query<Product> query = session.createQuery(productCriteriaQuery);
            List<Product> products_list = query.getResultList();
            String result = "";
            for (int i=0;i<products_list.size();i++) {
                result += products_list.get(i).toString() + "</br>";
            }
            return result;
        }
    }
    public Market getByID(int id) {
        Session session = sessionsFactory.getCurrentSession();
        Market marketID = session.get(Market.class, id);
        return marketID;
    }
}