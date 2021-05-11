package com.example.PW14.java.services;

import java.util.List;

import javax.transaction.Transactional;

import com.example.PW14.java.entities.Market;
import com.example.PW14.java.entities.Product;
import com.example.PW14.java.repositories.MarketRepository;
import com.example.PW14.java.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

interface productService {
    public void createNewProduct(String name, int price, int id);
    public void soldAnyProduct(String name);
    public String showByCriteria(String by, String criteria);
}

@Service
public class ProductService implements productService {
    @Autowired
    ProductRepository productRepo;
    @Autowired
    MarketRepository marketRepository;
    
    @Transactional
    public void createNewProduct(String name, int price, int id) {
        productRepo.save(new Product(name, price, getByID(id)));
    }

    @Transactional
    public void soldAnyProduct(String name) {
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
            return result;
        }
        else {
            List<Product> products_list;
            if (by == "id") {
                return productRepo.findById(Integer.parseInt(criteria)).toString();
            }
            else if (by == "name") {
                products_list = productRepo.findByName(criteria);
            }
            if (by == "price") {
                products_list = productRepo.findByPrice(Integer.parseInt(criteria));
            }
            if (by == "marketId") {
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
