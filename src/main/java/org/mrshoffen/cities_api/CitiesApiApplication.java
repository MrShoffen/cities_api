package org.mrshoffen.cities_api;

import com.ibm.icu.text.Transliterator;
import org.mrshoffen.cities_api.repository.CitiesRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CitiesApiApplication {

    public static void main(String[] args) {
SpringApplication.run(CitiesApiApplication.class, args);
    }



}
