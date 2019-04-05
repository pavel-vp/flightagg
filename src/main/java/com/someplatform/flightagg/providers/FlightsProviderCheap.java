package com.someplatform.flightagg.providers;

import com.someplatform.flightagg.model.RespProvider;
import com.someplatform.flightagg.model.RespProviderCheap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FlightsProviderCheap extends FlightsProvider {


    public void setUrl(@Value("${url.providercheap}") String url) {
        this.url = url;
    }

    @Override
    public List<RespProvider> loadRawData() {
        List<RespProvider> result = new ArrayList<>();
        try {
            ResponseEntity<RespProviderCheap[]> rateResponse  = restTemplate.getForEntity(
                    new URI(url),
                    RespProviderCheap[].class
            );
            result.addAll(Arrays.asList(rateResponse.getBody()));

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return result;
    }

}
