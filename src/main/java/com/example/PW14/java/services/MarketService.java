package com.example.PW14.java.services;

import java.util.List;

import javax.transaction.Transactional;

import com.example.PW14.java.entities.Market;
import com.example.PW14.java.repositories.MarketRepository;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

interface marketService {
    public void createNewMarket(String name, String address);
    public void closeAnyMarket(String name);
    public String showByCriteria(String by, String criteria);
}

@Service
@Slf4j
public class MarketService implements marketService{
    @Autowired
    MarketRepository marketRepo;
    @Autowired
    ProductService productService;
    @Autowired
    MailService mService;

    private static final Logger log = LoggerFactory.getLogger(MarketService.class);
    
    @Transactional
    @Async
    public void createNewMarket(String name, String address) {
        log.info("Save market with name {}", name);
        mService.sendMessage("New object was created!", "You created new market " + name + " with address = " + address);
        marketRepo.save(new Market(name, address));
    }

    @Transactional
    public void closeAnyMarket(String name) {
        log.info("Save market with name {}", name);
        marketRepo.deleteByName(name);
    }
    @Transactional
    public List<Market> getAll() {
        return marketRepo.findAll();
    }

    @Transactional
    public Market findById(int id) {
        return marketRepo.findById(id);
    }

    @Transactional
    public String showByCriteria(String by, String criteria) {
        if (criteria == null) {
            List<Market>market_list = marketRepo.findAll();
            String result = "";
            for (int i=0;i<market_list.size();i++) {
            result += market_list.get(i).getId() + " " + market_list.get(i).getName() + " " + market_list.get(i).getAddress() +"</br>";
            }
            log.info("Show all markets");
            return result;
        }
        else {
            List<Market> market_list;
            if (by == "id") {
                log.info("Show market where id is  {}", criteria);
                return marketRepo.findById(Integer.parseInt(criteria)).toString();
            }
            else if (by == "name") {
                log.info("Show market where name is  {}", criteria);
                market_list = marketRepo.findByName(criteria);
            }
            else if (by == "address") {
                log.info("Show market where address is  {}", criteria);
                market_list = marketRepo.findByAddress(criteria);
            }
            else {
                market_list = null;
            }
            String result = "";
            for (int i=0;i<market_list.size();i++) {
                result += market_list.get(i).toString() + "</br>";
            }
            return result;
        }
    }
}
