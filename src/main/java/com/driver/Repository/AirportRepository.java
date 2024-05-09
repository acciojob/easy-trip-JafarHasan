package com.driver.Repository;

import com.driver.model.Airport;
import com.driver.model.Flight;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class AirportRepository {
    HashMap<String , Airport> airportDB;

    public AirportRepository(){
        airportDB=new HashMap<>();
    }

    public String addAirport(Airport airport){
        String name=airport.getAirportName();
        airportDB.put(name,airport);
        return "SUCCESS";
    }

    public String getLargestAirportName(){
        int max=Integer.MIN_VALUE;
        String name="";
        for(String key:airportDB.keySet()){

            Airport airport=airportDB.get(key);
            int terminal=airport.getNoOfTerminals();
            if (terminal > max|| (terminal == max && (name == null || airport.getAirportName().compareTo(name) < 0))) {
                max=terminal;
                name=airport.getAirportName();
            }
        }
        return name;
    }

    public Airport getAirportByName(String airportName){
        for(Airport airport:airportDB.values()){
            if(airportName.equals(airport.getAirportName())){
                return airport;
            }
        }
        return null;

    }
    public List<Airport> getAllAirportList(){
        List<Airport> airportList=new ArrayList<>();
        for(Airport airport:airportDB.values()){
            airportList.add(airport);
        }
        return airportList;
    }
}
