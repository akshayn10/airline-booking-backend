package com.isa.airlinebookingbackend.controller;

import com.isa.airlinebookingbackend.service.implementation.PassengerService;
import com.isa.airlinebookingbackend.entity.Passenger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passenger")
@RequiredArgsConstructor
@Validated
public class PassengerController {
    @Autowired
    private PassengerService passengerService;

    @GetMapping("/getAllPassengers")
    public List<Passenger> getAllPassengers() {
        System.out.println("Inside getAllPassengers");
        return passengerService.getAllPassengers();
    }

    @GetMapping("/getPassengerbyId/{id}")
    public Passenger getPassengerById(@PathVariable Long id) {
        return passengerService.getPassengerById(id);
    }

    @PostMapping("/insertPassengers")
    public List<Passenger> insertPassengers(@RequestBody List<Passenger> passengers){
        System.out.println("Inside postInsertPassengers");
        return passengerService.savePassengers(passengers);
    }

    @PutMapping("/updatePassenger/{id}")
    public Passenger updatePassenger(@PathVariable("id") long id,@RequestBody Passenger passenger){
        return passengerService.updatePassenger(passenger);
    }

    @DeleteMapping("/deletePassenger/{id}")
    public void deletePassenger(@PathVariable("id") long id){
        passengerService.deletePassenger(id);
    }
}