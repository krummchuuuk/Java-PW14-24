package com.example.PW14.java.services;

import java.util.List;

import javax.transaction.Transactional;

import com.example.PW14.java.entities.Market;
import com.example.PW14.java.entities.Product;
import com.example.PW14.java.repositories.MarketRepository;
import com.example.PW14.java.repositories.ProductRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

interface productService {
    public void createNewProduct(String name, int price, int id);
    public void soldAnyProduct(String name);
    public String showByCriteria(String by, String criteria);
}

@Service
@Slf4j
public class ProductService implements productService {
    @Autowired
    ProductRepository productRepo;
    @Autowired
    MarketRepository marketRepository;
    private static final Logger log = LoggerFactory.getLogger(MarketService.class);
    
    @Transactional
    public void createNewProduct(String name, int price, int id) {
        log.info("Save product with name {}", name);
        productRepo.save(new Product(name, price, getByID(id)));
    }

    @Transactional
    public void soldAnyProduct(String name) {
        log.info("Sold product with name {}", name);
        productRepo.deleteByName(name);
    }

    @Transactional
    public String showByCriteria(String by, String criteria) {
        if (criteria == null) {
            List<Product>products_list = productRepo.findAll();
            String result = "";
            for (int i=0;i<products_list.size();i++) {
            result += products_list.get(i).getId() + " " + products_list.get(i).getName() + " " + products_list.get(i).getPrice() + " " + products_list.get(i).getMarketID() +"</br>";
            }
            log.info("Show all products");
            return result;
        }
        else {
            List<Product> products_list;
            if (by == "id") {
                log.info("Show product where id is {}", criteria);
                return productRepo.findById(Integer.parseInt(criteria)).toString();
            }
            else if (by == "name") {
                log.info("Show products where name is {}", criteria);
                products_list = productRepo.findByName(criteria);
            }
            if (by == "price") {
                log.info("Show products where price is {}", criteria);
                products_list = productRepo.findByPrice(Integer.parseInt(criteria));
            }
            if (by == "marketId") {
                log.info("Show products where marketId is {}", criteria);
                products_list = productRepo.findByMarketId(Integer.parseInt(criteria));
            }
            else {
                products_list = null;
            }
            String result = "";
            for (int i=0;i<products_list.size();i++) {
                result += products_list.get(i).toString() + "</br>";
            }
            return result;
        }
    }
    public Market getByID(int id) {
        return marketRepository.findById(id);
    }
}
