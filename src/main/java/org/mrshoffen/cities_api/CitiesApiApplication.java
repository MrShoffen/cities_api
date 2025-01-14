package org.mrshoffen.cities_api;

import org.mrshoffen.cities_api.repository.CitiesRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CitiesApiApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CitiesApiApplication.class, args);


        CitiesRepository rep = context.getBean(CitiesRepository.class);


        System.out.println();
    }



}
