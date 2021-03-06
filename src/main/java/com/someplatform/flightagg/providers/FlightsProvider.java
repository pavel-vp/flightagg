package com.someplatform.flightagg.providers;

import com.someplatform.flightagg.model.FlightData;
import com.someplatform.flightagg.model.RespProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

public abstract class FlightsProvider {

    protected RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public abstract List<RespProvider> loadRawData();

    public List<FlightData> decodeData(List<RespProvider> rawData) {
        return rawData.stream()
                .map(RespProvider::toFlightData)
                .collect(Collectors.toList());
    }

    public List<FlightData> getFlights() {
        return decodeData(loadRawData());
    }

    public abstract String getUrl();
}
