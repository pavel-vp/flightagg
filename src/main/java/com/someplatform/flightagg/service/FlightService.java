package com.someplatform.flightagg.service;

import com.someplatform.flightagg.model.FilterObject;
import com.someplatform.flightagg.model.FlightData;
import com.someplatform.flightagg.model.SortedObject;
import com.someplatform.flightagg.providers.IFlightaggProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FlightService {
    @Autowired
    FieldExtractService fieldExtractService;

    private IFlightaggProvider flightaggProvider;

    @Autowired
    public void setFlightaggProvider(IFlightaggProvider flightaggProvider) {
        this.flightaggProvider = flightaggProvider;
    }

    public List<FlightData> getFlights(Integer offset, Integer limit, String sorted, Map<String, String[]> parameterMap) {
        List<FilterObject> filters = fieldExtractService.buildFilters(parameterMap);
        SortedObject sortedObject = fieldExtractService.buildSortedObject(sorted);
        List<FlightData> result = getFlights(offset, sortedObject, filters);
        if (limit != null) {
            return result.stream()
                    .limit(limit)
                    .collect(Collectors.toList());
        }
        return result;
    }

    private List<FlightData> getFlights(Integer offset, SortedObject sortedObject, List<FilterObject> filters) {
        return getFlights(sortedObject, filters).stream()
                .skip(offset == null ? 0 : offset)
                .collect(Collectors.toList());
    }

    public List<FlightData> getFlights(SortedObject sortedObject, List<FilterObject> filters) {
        return getFlights(filters).stream()
                .sorted((o1, o2) -> {
                    if (sortedObject == null)
                        return 0;
                    switch (sortedObject.getFieldSpec()) {
                        case ID:
                            if (sortedObject.isAsc()) {
                                return o1.getId().compareTo(o2.getId());
                            } else {
                                return o2.getId().compareTo(o1.getId());
                            }
                        case DEP:
                            if (sortedObject.isAsc()) {
                                return o1.getDeparture().compareTo(o2.getDeparture());
                            } else {
                                return o2.getDeparture().compareTo(o1.getDeparture());
                            }
                        case ARR:
                            if (sortedObject.isAsc()) {
                                return o1.getArrival().compareTo(o2.getArrival());
                            } else {
                                return o2.getArrival().compareTo(o1.getArrival());
                            }
                        case DEPTIME:
                            if (sortedObject.isAsc()) {
                                return o1.getDepartureTime() > o2.getDepartureTime() ? 1 : -1;
                            } else {
                                return o2.getDepartureTime() > o1.getDepartureTime() ? 1 : -1;
                            }
                        case ARRTIME:
                            if (sortedObject.isAsc()) {
                                return o1.getArrivalTime() > o2.getArrivalTime() ? 1 : -1;
                            } else {
                                return o2.getArrivalTime() > o1.getArrivalTime() ? 1 : -1;
                            }
                    }
                    return 0;
                })
                .collect(Collectors.toList());
    }

    private List<FlightData> getFlights(List<FilterObject> filters) {
        return flightaggProvider.getAllFlights().stream()
                .filter(flightData -> {
                    if (filters == null || filters.size() == 0)
                        return true;
                    for (FilterObject filterObject : filters) {
                        switch (filterObject.getFieldSpec()) {
                            case ID:
                                switch (filterObject.getEquisionType()) {
                                    case GTE:
                                        return (flightData.getId().compareTo(filterObject.getFilterValue()) >= 0);
                                    case LTE:
                                        return (flightData.getId().compareTo(filterObject.getFilterValue()) <= 0);
                                    case LIKE:
                                        return (flightData.getId().contains(filterObject.getFilterValue()));
                                }
                            case DEP:
                                switch (filterObject.getEquisionType()) {
                                    case GTE:
                                        return (flightData.getDeparture().compareTo(filterObject.getFilterValue()) >= 0);
                                    case LTE:
                                        return (flightData.getDeparture().compareTo(filterObject.getFilterValue()) <= 0);
                                    case LIKE:
                                        return (flightData.getDeparture().contains(filterObject.getFilterValue()));
                                }
                        }
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    public List<FlightData> getAllFlights() {
        return flightaggProvider.getAllFlights();
    }
}
