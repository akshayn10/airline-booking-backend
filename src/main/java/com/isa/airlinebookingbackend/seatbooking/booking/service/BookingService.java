package com.isa.airlinebookingbackend.seatbooking.booking.service;

import com.isa.airlinebookingbackend.seatbooking.booking.model.Booking;
import com.isa.airlinebookingbackend.seatbooking.booking.repo.BookingRepo;
import com.isa.airlinebookingbackend.seatbooking.passenger.model.Passenger;
import com.isa.airlinebookingbackend.seatbooking.passenger.repo.PassengerRepo;
import com.isa.airlinebookingbackend.seatbooking.passenger.service.PassengerService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class BookingService {
    @Autowired
    private BookingRepo bookingRepo;
    @Autowired
    private PassengerRepo passengerRepo;
    @Autowired
    private PassengerService passengerService;

    public void addBooking(List<Booking> bookings) {
        for(Booking booking : bookings) {
            Booking savedBooking = bookingRepo.save(booking);
            passengerService.addPassengers(booking.getPassengers(),savedBooking);
//            for(Passenger passenger : booking.getPassengers()) {
//                passenger.setBooking(savedBooking);
//                passengerRepo.save(passenger);
//            }
        }
    }

    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

//    public List<Passenger> getPassengersByBookingId(long id) {
//        return  passengerRepo.findAllByBookingId(id);
//    }
}
