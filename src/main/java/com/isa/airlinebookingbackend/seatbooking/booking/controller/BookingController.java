package com.isa.airlinebookingbackend.seatbooking.booking.controller;

import com.isa.airlinebookingbackend.seatbooking.booking.model.Booking;
import com.isa.airlinebookingbackend.seatbooking.booking.service.BookingService;
import com.isa.airlinebookingbackend.seatbooking.passenger.model.Passenger;
import com.isa.airlinebookingbackend.seatbooking.passenger.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/booking/v1")
@RequiredArgsConstructor
@Validated
public class BookingController {

    @Autowired
    private BookingService bookingService;
    @Autowired
    private PassengerService passengerService;

    @PostMapping("/book")
    public String book(@RequestBody List<Booking> booking) {
        System.out.println("Accessed");
        bookingService.addBooking(booking);
        return "bookingService.addBooking(booking)";
    }

    @GetMapping("/getBooking")
    public List<Booking> getBooking() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/getPassengers/{id}")
    public List<Passenger> getPassengers(@PathVariable long id) {
        return passengerService.getAllPassengersByBookingId(id);
    }
}
