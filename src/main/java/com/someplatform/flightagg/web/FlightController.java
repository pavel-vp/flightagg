package com.someplatform.flightagg.web;

import com.someplatform.flightagg.model.FlightData;
import com.someplatform.flightagg.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "flights")
public class FlightController {

    @Autowired
    FlightService flightService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<FlightData> getAllFlights() {
        return flightService.getAllFlights();
    }

    @RequestMapping(value = "/q", method = RequestMethod.GET)
    @ResponseBody
    public List<FlightData> getFlights(@RequestParam(required = false) Integer offset, @RequestParam(required = false) Integer limit,
                                       @RequestParam(required = false) String sorted,
                                       HttpServletRequest request) {
        return flightService.getFlights(offset, limit, sorted, request.getParameterMap());
    }






}
