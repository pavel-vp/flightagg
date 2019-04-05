package com.someplatform.flightagg;

import com.someplatform.flightagg.model.FlightData;
import com.someplatform.flightagg.model.RespProvider;
import com.someplatform.flightagg.providers.FlightsProviderBusiness;
import com.someplatform.flightagg.providers.FlightsProviderCheap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class FlightaggUnitTests {

    FlightsProviderCheap providerCheap;
    FlightsProviderBusiness providerBusiness;

    @Before
    public void setup() {
        providerCheap = new FlightsProviderCheap();
        providerCheap.setUrl("https://obscure-caverns-79008.herokuapp.com/cheap");

        providerBusiness = new FlightsProviderBusiness();
        providerBusiness.setUrl("https://obscure-caverns-79008.herokuapp.com/business");

        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> list = new ArrayList<>();
        list.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(list);

        providerCheap.setRestTemplate(restTemplate);
        providerBusiness.setRestTemplate(restTemplate);
    }

    @Test
    public void load_cheap_data_test() {
        List<RespProvider> data = providerCheap.loadRawData();
        Assert.assertTrue(data.size() > 0);
    }

    @Test
    public void parse_cheap_test() {
        List<RespProvider> data = providerCheap.loadRawData();
        List<FlightData> flightData = providerCheap.decodeData(data);
        Assert.assertTrue(flightData.size() > 0);
    }

    @Test
    public void load_business_data_test() {
        List<RespProvider> data = providerBusiness.loadRawData();
        Assert.assertTrue(data.size() > 0);
    }

    @Test
    public void parse_business_test() {
        List<RespProvider> data = providerBusiness.loadRawData();
        List<FlightData> flightData = providerBusiness.decodeData(data);
        Assert.assertTrue(flightData.size() > 0);
    }

}
