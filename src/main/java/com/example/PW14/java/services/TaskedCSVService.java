package com.example.PW14.java.services;

import com.example.PW14.java.entities.Market;
import com.example.PW14.java.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Service
@EnableAsync
public class TaskedCSVService implements TaskedCSVServiceMBean  {
    @Autowired
    MarketService mService;
    @Autowired
    ProductService pService;

    @Override
    public void writeData() throws IOException {
        Writer marketWriter = new FileWriter("../DBtoCSV/markets");
        Writer productWriter = new FileWriter("../DBtoCSV/products");

        List<Market> marketList = mService.getAll();
        List<Product> productList = pService.getAll();


        for (Market market:marketList) {
            marketWriter.write(market.toString() + "\n");
        }

        for (Product product:productList) {
            productWriter.write(product.toString() + "\n");
        }
        marketWriter.close();
        productWriter.close();
    }

    @Scheduled(fixedDelay = (1000*60)*5)
    @Async
    @Override
    public void execute() {
        File file = new File("../DBtoCSV");
        if (!file.exists()) {
            file.mkdir();
        }
        File[] directory = file.listFiles();
        for (File f:directory) {
            f.delete();
        }
        try { 
            writeData();
        } 
        catch (IOException exception){
            System.out.println("Error writing data");
        }
    }
}