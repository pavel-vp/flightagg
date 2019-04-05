package com.someplatform.flightagg.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Getter
@Setter
@ToString
public class RespProviderBusiness implements RespProvider {

    private static final String PATH_DELIMITER = " -> ";

    // "uuid":"363e664b-9b28-4b83-b05c-3497fc29ff67","flight":"Victoria -> Villa Canas","departure":"2019-04-05T17:54:00.597Z","arrival":"2019-04-05T19:13:18.701Z"
    private String uuid;
    private String flight;
    private String departure;
    private String arrival;

    @Override
    public FlightData toFlightData() {
        try {
            String[] path = flight.split(PATH_DELIMITER);

            return new FlightData(uuid, path[0], path[1], Instant.parse(departure).toEpochMilli(), Instant.parse(arrival).toEpochMilli());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
