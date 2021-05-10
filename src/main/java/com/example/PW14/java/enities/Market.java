package com.example.PW14.java.enities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.Hibernate;

@Entity
@Table(name="markets_one2many")
public class Market {
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @OneToMany(mappedBy="market", fetch = FetchType.LAZY)
    private Set<Product> products;

    public Market(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Market() {}

    public void setAddress(String address) {
        this.address = address;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getAddress() {
        return address;
    }
    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
    public Set<Product> getProducts() {
        return products;
    }
}
