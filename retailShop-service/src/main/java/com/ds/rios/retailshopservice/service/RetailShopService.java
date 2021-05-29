package com.ds.rios.retailshopservice.service;


import com.ds.rios.retailshopservice.dto.RetailShopNotFoundException;

import com.ds.rios.retailshopservice.model.RetailShop;
import com.ds.rios.retailshopservice.repositery.RetailShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public RetailShop updateRetailShop(RetailShop retailShop, long retailShopId) {
        RetailShop preRetailShop = retailShopRepository.findById(retailShopId).orElseThrow(() -> new RetailShopNotFoundException(retailShopId));
        preRetailShop.setAddress(retailShop.getAddress());
        preRetailShop.setContactNumber(retailShop.getContactNumber());
        preRetailShop.setShopName(retailShop.getShopName());
        return retailShopRepository.save(preRetailShop);
    }


}
