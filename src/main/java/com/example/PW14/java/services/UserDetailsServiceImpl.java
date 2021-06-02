package com.example.PW14.java.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.PW14.java.entities.Client;
import com.example.PW14.java.repositories.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    ClientRepository cRepo;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client cli = cRepo.findByUsername(username);
        if (cli == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return cli;
    }
    public UserDetails findUserById(int id) {
        return cRepo.findById(id);
    }
    public List<Client> getUsers() {
        return cRepo.findAll();
    }
    public void saveOrUpdate(Client cli) {
        cRepo.save(cli);
    }
    public boolean saveClient(Client cli) {
        if (cRepo.findByUsername(cli.getUsername())==null) {
            cli.setPassword(bCryptPasswordEncoder.encode(cli.getPassword()));
            cRepo.save(cli);
            return true;
        }
        else return false;
    }
}