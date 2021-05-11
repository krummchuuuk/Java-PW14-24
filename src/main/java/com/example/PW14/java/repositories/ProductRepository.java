package com.example.PW14.java.repositories;

import java.util.List;

import com.example.PW14.java.entities.Product;

import org.springframework.data.repository.CrudRepository;


public interface ProductRepository extends CrudRepository<Product, Long> {
    void deleteByName (String name);
    List<Product> findAll();
    List<Product> findByName(String name);
    List<Product> findByPrice(int price);
    Product findById(int id);
    List<Product> findByMarketId(int id);
}