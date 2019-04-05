package com.someplatform.flightagg.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FlightData {
    private String id;
    private String departure;
    private String arrival;
    private long departureTime;
    private long arrivalTime;

    public FlightData(String id, String departure, String arrival, long departureTime, long arrivalTime) {
        this.id = id;
        this.departure = departure;
        this.arrival = arrival;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }
}
