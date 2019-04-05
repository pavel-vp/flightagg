package com.someplatform.flightagg.providers;

import com.someplatform.flightagg.model.FlightData;
import com.someplatform.flightagg.model.RespProvider;
import com.someplatform.flightagg.model.RespProviderBusiness;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlightsProviderBusiness extends FlightsProvider {

        public void setUrl(@Value("${url.providerbusiness}") String url) {
            this.url = url;
        }

        @Override
        public List<RespProvider> loadRawData() {
            List<RespProvider> result = new ArrayList<>();
            try {
                ResponseEntity<RespProviderBusiness[]> rateResponse  = restTemplate.getForEntity(
                        new URI(url),
                        RespProviderBusiness[].class
                );
                result.addAll(Arrays.asList(rateResponse.getBody()));

            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            return result;
        }

}
