package com.example.PW14.java.enities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class Product {
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
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
}
