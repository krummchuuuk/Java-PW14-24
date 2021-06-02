package com.example.PW14.java.repositories;

import com.example.PW14.java.entities.UserRole;

import org.springframework.data.repository.CrudRepository;


public interface RoleRepo extends CrudRepository<UserRole, Long>{
    
}

