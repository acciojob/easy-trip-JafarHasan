package com.driver.Services;

import com.driver.Repository.FlightRepository;
import com.driver.model.City;
import com.driver.model.Flight;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class FlightService {
    FlightRepository flightRepository=new FlightRepository();

    public String addFlight(Flight flight){

        //Return a "SUCCESS" message string after adding a flight.
        return flightRepository.addFlight(flight);
    }
    public String bookATicket(Integer flightId,Integer passengerId){

        //If the numberOfPassengers who have booked the flight is greater than : maxCapacity, in that case :
        //return a String "FAILURE"
        //Also if the passenger has already booked a flight then also return "FAILURE".
        //else if you are able to book a ticket then return "SUCCESS"

        return flightRepository.bookATicket(flightId,passengerId);
    }

    public String cancelATicket(Integer flightId,Integer passengerId){

        //If the passenger has not booked a ticket for that flight or the flightId is invalid or in any other failure case
        // then return a "FAILURE" message
        // Otherwise return a "SUCCESS" message
        // and also cancel the ticket that passenger had booked earlier on the given flightId

        return flightRepository.cancelATicket(flightId,passengerId);
    }

    public int countOfBookingsDoneByPassengerAllCombined(Integer passengerId){

        //Tell the count of flight bookings done by a passenger: This will tell the total count of flight bookings done by a passenger :
        return flightRepository.countOfBookingsDoneByPassengerAllCombined(passengerId);

    }

    public double getShortestDurationOfPossibleBetweenTwoCities(City fromCity, City toCity){

        //Find the duration by finding the shortest flight that connects these 2 cities directly
        //If there is no direct flight between 2 cities return -1.
        List<Flight> flightList=flightRepository.getAllFlightList();

        double minimumDuration=Integer.MAX_VALUE;
        for(Flight flight:flightList){
            if(flight.getFromCity().equals(fromCity) && flight.getToCity().equals(toCity)){
                if(flight.getDuration()<minimumDuration){
                    minimumDuration=flight.getDuration();
                }
            }
        }
        if(minimumDuration==Integer.MAX_VALUE) return -1;
        return minimumDuration;
    }

    public int calculateFlightFare(Integer flightId){

        //Calculation of flight prices is a function of number of people who have booked the flight already.
        //Price for any flight will be : 3000 + noOfPeopleWhoHaveAlreadyBooked*50
        //Suppose if 2 people have booked the flight already : the price of flight for the third person will be 3000 + 2*50 = 3100
        //This will not include the current person who is trying to book, he might also be just checking price
        int noOfTicketsBook=flightRepository.noOfTicketsBookForFlightId(flightId);
        int price=3000+noOfTicketsBook*50;
        return price;

    }

    public int calculateRevenueOfAFlight(Integer flightId){

        //Calculate the total revenue that a flight could have
        //That is of all the passengers that have booked a flight till now and then calculate the revenue
        //Revenue will also decrease if some passenger cancels the flight

        int price=calculateFlightFare(flightId);
        int canceledTickets=flightRepository.getCanceledTickets(flightId);
        int canceledPrice=canceledTickets*50;
        return price-canceledPrice;
    }

}
