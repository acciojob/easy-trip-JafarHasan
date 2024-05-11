package com.driver.Services;

import com.driver.Repository.AirportRepository;
import com.driver.Repository.FlightRepository;
import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirportService {

     public AirportRepository airportRepository=new AirportRepository();
     public FlightRepository flightRepository=new FlightRepository();

    public String addAirport(Airport airport){
        return airportRepository.addAirport(airport);
    }

    public String getLargestAirportName(){
        return airportRepository.getLargestAirportName();
    }

    public String getAirportNameFromFlightId(Integer flightId){

        //We need to get the starting airportName from where the flight will be taking off (Hint think of City variable if that can be of some use)
        //return null incase the flightId is invalid or you are not able to find the airportName

        List<Flight> flightslist = new ArrayList<>();
        flightslist =flightRepository.getAllFlightList();

        City anscity = null;
        for(Flight flight : flightslist){
            if(flight.getFlightId() == flightId){
                anscity = flight.getFromCity();
            }
        }
        List<Airport> airportslist = new ArrayList<>();
        airportslist =airportRepository.getAllAirportList();

        for(Airport airport : airportslist){
            if(airport.getCity().equals(anscity)){
                return airport.getAirportName();
            }
        }
        return null;
    }
}
