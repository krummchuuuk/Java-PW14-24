package com.example.PW14.java.enities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.Hibernate;

@Entity
@Table(name="products_one2many")
public class Product {
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private int price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="market_id")
    private Market market;

    public Product(String name, int price, Market store) {
        this.name = name;
        this.price = price;
        this.market = store;
    }
    public Product() {}
    public void setPrice(int price) {
        this.price = price;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getPrice() {
        return price;
    }
    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
    public int getMarketID() {
        return market.getId();
    }
}
