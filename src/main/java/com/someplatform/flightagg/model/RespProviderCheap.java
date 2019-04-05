package com.someplatform.flightagg.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RespProviderCheap implements RespProvider {
    private long id;
    private String departure;
    private String arrival;
    private long departureTime;
    private long arrivalTime;


    @Override
    public FlightData toFlightData() {
        return new FlightData(String.valueOf(id), departure, arrival, departureTime, arrivalTime);
    }
}
