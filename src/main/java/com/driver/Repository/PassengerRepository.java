package com.driver.Repository;

import com.driver.model.Passenger;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;

@Repository
public class PassengerRepository {
    HashMap<Integer, Passenger> passengerDB;
    public PassengerRepository(){
        passengerDB=new HashMap<>();
    }

    public String addPassenger(Passenger passenger){

        //Add a passenger to the database
        //And return a "SUCCESS" message if the passenger has been added successfully.
        int id=passenger.getPassengerId();
        passengerDB.put(id,passenger);
        return "SUCCESS";
    }
}
