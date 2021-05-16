package com.ds.rios.retailshopservice.service;


import com.ds.rios.retailshopservice.dto.RetailShopNotFoundException;

import com.ds.rios.retailshopservice.model.RetailShop;
import com.ds.rios.retailshopservice.repositery.RetailShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RetailShopService {

    @Autowired
    private RetailShopRepository retailShopRepository;

    public List<RetailShop> getAllRetailShops(){ return  retailShopRepository.findAll();}

    public RetailShop addRetailShop(RetailShop retailShop){
        return retailShopRepository.save(retailShop);
    }

    public void deleteRetailShopById(long id) {
        retailShopRepository.deleteById(id);
    }

    public RetailShop getRetailShopById(long id) {
        return retailShopRepository.findById(id).orElseThrow(() -> new RetailShopNotFoundException(id));
    }



}
