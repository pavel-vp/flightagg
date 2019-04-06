package com.someplatform.flightagg.providers;

import com.someplatform.flightagg.model.FlightData;

import java.util.List;

public interface IFlightaggProvider {
    List<FlightData> getAllFlights();
}
