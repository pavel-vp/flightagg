package com.someplatform.flightagg.providers;

import com.someplatform.flightagg.model.FlightData;
import com.someplatform.flightagg.model.RespProvider;
import com.someplatform.flightagg.model.RespProviderBusiness;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class FlightsProviderBusiness extends FlightsProvider {

    @Value("${url.providerbusiness}")
    private String urlBusiness;

    public void setUrl(String url) {
        this.urlBusiness = url;
    }

    @Override
    public String getUrl() {
        return urlBusiness;
    }

    @Override
    public List<RespProvider> loadRawData() {
        List<RespProvider> result = new ArrayList<>();
        try {
            ResponseEntity<RespProviderBusiness[]> rateResponse = restTemplate.getForEntity(
                    new URI(getUrl()),
                    RespProviderBusiness[].class
            );
            result.addAll(Arrays.asList(rateResponse.getBody()));

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return result;
    }


}
