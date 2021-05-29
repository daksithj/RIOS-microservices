package com.ds.rios.retailshopservice;


import com.ds.rios.retailshopservice.model.RetailShop;

import com.ds.rios.retailshopservice.repositery.RetailShopRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableEurekaClient
public class RetailShopServiceApplication {

    private static final Logger log = LoggerFactory.getLogger(RetailShopServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RetailShopServiceApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }


    @Bean
    public CommandLineRunner demo(RetailShopRepository retailShopRepository) {
        return (args) -> {

            RetailShop r1= new RetailShop("Ranjapaksha TNS","+9478678900","Kaluara,South");
            RetailShop r2= new RetailShop("Jayantha TNS","+9478678900","Panadura,South");

            retailShopRepository.save(r1);
            retailShopRepository.save(r2);

        };
    }

}


