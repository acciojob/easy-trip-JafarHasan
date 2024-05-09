package com.driver.Services;

import com.driver.Repository.AirportRepository;
import com.driver.Repository.FlightRepository;
import com.driver.Repository.PassengerRepository;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PassengerService {
    PassengerRepository passengerRepository = new PassengerRepository();
    AirportRepository airportRepository=new AirportRepository();
    FlightRepository flightRepository=new FlightRepository();

    public String addPassenger(Passenger passenger) {

        //Add a passenger to the database
        //And return a "SUCCESS" message if the passenger has been added successfully.

        return passengerRepository.addPassenger(passenger);

    }
    public int getNumberOfPeopleOn(Date date,String airportName){

        //Calculate the total number of people who have flights on that day on a particular airport
        //This includes both the people who have come for a flight and who have landed on an airport after their flight

//        List<Airport> airportList=airportRepository.getAllAirportList();
//        for (Airport airport : airportList) {
//            if(airport.getAirportName().equals(airportName)){
//                City city=airport.getCity();
//            }
//        }

        List<Flight> flightList=flightRepository.getAllFlightList();
        if(flightList.isEmpty()) return 0;

        List<Flight> list=new ArrayList<>();
        for(Flight flight:flightList){
            if(flight.getFlightDate().equals(date)){
                list.add(flight);
            }
        }
        int cnt=0;
        for(Flight flight:list){
            cnt+=flightRepository.noOfTicketsBookForFlightId(flight.getFlightId());
        }
        return cnt;
    }


}
