package com.someplatform.flightagg;

import com.someplatform.flightagg.model.FieldSpec;
import com.someplatform.flightagg.model.FlightData;
import com.someplatform.flightagg.model.SortedObject;
import com.someplatform.flightagg.providers.IFlightaggProvider;
import com.someplatform.flightagg.service.FlightService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FlightaggOrderTest {

    private List<FlightData> flightData = new ArrayList<>();
    private FlightService flightService;
    private IFlightaggProvider flightaggProvider = () -> flightData;

    @Before
    public void setUp() {
        flightData.add(new FlightData("3", "New York", "Paris", 30000, 40000));
        flightData.add(new FlightData("5", "Bangkok", "Singapore", 50000, 60000));
        flightData.add(new FlightData("1", "Moscow", "Rome", 10000, 20000));
        flightData.add(new FlightData("2", "Rome", "Milan", 20000, 30000));
        flightData.add(new FlightData("4", "Barcelona", "Malaga", 40000, 50000));
        flightService = new FlightService();
        flightService.setFlightaggProvider(flightaggProvider);
    }

    @Test
    public void order_id_test() {
        SortedObject sortedObject = new SortedObject(FieldSpec.DEP, true);
        List<FlightData> result = flightService.getFlights(sortedObject, null);
        System.out.println(result);
    }

}
