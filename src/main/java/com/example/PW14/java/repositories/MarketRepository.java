package com.example.PW14.java.repositories;

import java.util.List;

import com.example.PW14.java.entities.Market;

import org.springframework.data.repository.CrudRepository;

public interface MarketRepository extends CrudRepository<Market, Long> {
    void deleteByName (String name);
    List<Market> findAll();
    List<Market> findByName(String name);
    List<Market> findByAddress(String address);
    Market findById(int id);
}