package com.someplatform.flightagg.providers;

import com.someplatform.flightagg.model.FlightData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightaggProvider implements IFlightaggProvider {

    @Autowired
    FlightsProviderCheap flightsProviderCheap;
    @Autowired
    FlightsProviderBusiness flightsProviderBusiness;

    @Override
    public List<FlightData> getAllFlights() {
        List<FlightData> flightData = flightsProviderCheap.getFlights();
        flightData.addAll(flightsProviderBusiness.getFlights());
        return flightData;
    }
}
