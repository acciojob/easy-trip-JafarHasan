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

        List<Flight> flightList=flightRepository.getAllFlightList();
        City ansCity=null;
        for(Flight flight:flightList){
            if(flight.getFlightId()==flightId){
                ansCity=flight.getFromCity();
            }
        }
        List<Airport> airportList=airportRepository.getAllAirportList();
        for(Airport airport:airportList){
            if(ansCity == airport.getCity()){
                return airport.getAirportName();
            }
        }
        return null;
    }
}