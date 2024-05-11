package com.driver.Repository;

import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class FlightRepository {
    HashMap<Integer, Flight> flightDB;
    HashMap<Integer, List<Integer>> flightPassengerDB;
    HashMap<Integer,Integer> flightCancelDB;

    public FlightRepository(){
        flightDB=new HashMap<>();
        flightPassengerDB=new HashMap<>();
        flightCancelDB=new HashMap<>();

    }
    public String addFlight(Flight flight){

        //Return a "SUCCESS" message string after adding a flight.
        int key=flight.getFlightId();
        flightDB.put(key,flight);
        return "SUCCESS";
    }

    public String bookATicket(Integer flightId,Integer passengerId){

        //If the numberOfPassengers who have booked the flight is greater than : maxCapacity, in that case :
        //return a String "FAILURE"
        //Also if the passenger has already booked a flight then also return "FAILURE".
        //else if you are able to book a ticket then return "SUCCESS"

        //flight already added
        if(flightPassengerDB.containsKey(flightId)) {
            Flight flight=flightDB.get(flightId);

            List<Integer> passengerList = flightPassengerDB.get(flightId);
            if(passengerList.contains(passengerId)){
                return "FAILURE";
            }
            else if(passengerList.size()>=flight.getMaxCapacity()){ ////all passenger booked
                return "FAILURE";
            }
            else{
                passengerList.add(passengerId);
                flightPassengerDB.put(flightId,passengerList);
            }
            int noOfPassenger = passengerList.size();
        }
        //flight is not added yet now flight will be added with a new passengerList
        else{
            List<Integer> newPassengerList=new ArrayList<>();
            newPassengerList.add(passengerId);
            flightPassengerDB.put(flightId,newPassengerList);
        }
        return "SUCCESS";
    }
    public String cancelATicket(Integer flightId,Integer passengerId){

        //If the passenger has not booked a ticket for that flight or the flightId is invalid or in any other failure case
        // then return a "FAILURE" message
        // Otherwise return a "SUCCESS" message
        // and also cancel the ticket that passenger had booked earlier on the given flightId
        //flight id is valid or invalid

        List<Integer> pId=flightPassengerDB.get(flightId);
        if(!pId.contains(passengerId)) return "FAILURE";
        List<Integer> newList=new ArrayList<>();
        int n=pId.size();

        for(int i=0;i<n;i++){
            if(pId.get(i)==passengerId){
                newList.add(i);
            }
        }

        for(int i=0;i<newList.size();i++){
            int idx=newList.get(i);
            pId.remove(idx);
        }

        flightCancelDB.put(flightId,flightCancelDB.getOrDefault(0,1)+1);
        return "SUCCESS";
    }

    public int countOfBookingsDoneByPassengerAllCombined(Integer passengerId){

        //Tell the count of flight bookings done by a passenger: This will tell the total count of flight bookings done by a passenger :
        int cnt=0;
        for(int flightID:flightPassengerDB.keySet()){
            List<Integer> passengerList=flightPassengerDB.get(flightID);
            for(int pId:passengerList){
                if(pId==passengerId){
                    cnt++;
                }
            }
        }
        return cnt;
    }

    //for getShortestDurationOfPossibleBetweenTwoCities() method we need all flight list
    public List<Flight> getAllFlightList(){
        List<Flight> flightList= new ArrayList<>();
        for(Flight flight:flightDB.values()){
            flightList.add(flight);

        }
        return flightList;
    }
    public int noOfTicketsBookForFlightId(int flightId){
        if(flightPassengerDB.containsKey(flightId)){
            return flightPassengerDB.get(flightId).size();
        }
        return 0;
    }
    public int getCanceledTickets(Integer flightId){
        if(flightCancelDB.containsKey(flightId)){
            return flightCancelDB.get(flightId);
        }
        return 1;
    }

}
