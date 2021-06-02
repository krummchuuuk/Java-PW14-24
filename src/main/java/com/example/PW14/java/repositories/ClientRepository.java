package com.example.PW14.java.repositories;

import java.util.List;

import com.example.PW14.java.entities.Client;

import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {
    Client findByUsername(String username);
    Client findById(int id);
    List<Client> findAll();
}