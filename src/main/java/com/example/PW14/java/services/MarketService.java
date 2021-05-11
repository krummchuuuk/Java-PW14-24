package com.example.PW14.java.services;

import java.util.List;

import javax.transaction.Transactional;

import com.example.PW14.java.entities.Market;
import com.example.PW14.java.repositories.MarketRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

interface marketService {
    public void createNewMarket(String name, String address);
    public void closeAnyMarket(String name);
    public String showByCriteria(String by, String criteria);
}

@Service
public class MarketService implements marketService{
    @Autowired
    MarketRepository marketRepo;
    @Autowired
    ProductService productService;
    
    @Transactional
    public void createNewMarket(String name, String address) {
        marketRepo.save(new Market(name, address));
    }

    @Transactional
    public void closeAnyMarket(String name) {
        marketRepo.deleteByName(name);
    }

    @Transactional
    public String showByCriteria(String by, String criteria) {
        if (criteria == null) {
            List<Market>market_list = marketRepo.findAll();
            String result = "";
            for (int i=0;i<market_list.size();i++) {
            result += market_list.get(i).getId() + " " + market_list.get(i).getName() + " " + market_list.get(i).getAddress() +"</br>";
            }
            return result;
        }
        else {
            List<Market> market_list;
            if (by == "id") {
                return marketRepo.findById(Integer.parseInt(criteria)).toString();
            }
            else if (by == "name") {
                market_list = marketRepo.findByName(criteria);
            }
            else if (by == "address") {
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
