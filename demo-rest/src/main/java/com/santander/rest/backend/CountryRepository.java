package com.santander.rest.backend;

import com.santander.springsoap.gen.Country;
import com.santander.springsoap.gen.Currency;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class CountryRepository {

    private static final Map<String, Country> countries = new HashMap<>();

    @PostConstruct
    public void initData() {
        // initialize countries map
        Country o_country = new Country();
        o_country.setName("Spain");
        o_country.setCapital("Madrid");
        o_country.setPopulation(40000000);
        o_country.setCurrency(Currency.EUR);

        
        countries.put("Spain",o_country);
    }

    public Country findCountry(String name) {
        return countries.get(name);
    }
}

