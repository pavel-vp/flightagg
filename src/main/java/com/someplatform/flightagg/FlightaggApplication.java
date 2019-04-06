package com.someplatform.flightagg;

import com.someplatform.flightagg.providers.FlightaggProvider;
import com.someplatform.flightagg.providers.IFlightaggProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class FlightaggApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlightaggApplication.class, args);
    }


    @Bean
    public RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> list = new ArrayList<>();
        list.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(list);
        return restTemplate;
    }

    @Bean
    public IFlightaggProvider getFlightaggProvider() {
        return new FlightaggProvider();
    }

}
