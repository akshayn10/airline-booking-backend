package com.isa.airlinebookingbackend.controller;

import com.isa.airlinebookingbackend.dto.ApiResponse;
import com.isa.airlinebookingbackend.dto.booking.request.BookingRequestDTO;
import com.isa.airlinebookingbackend.dto.booking.request.ConfirmBookingRequestDTO;
import com.isa.airlinebookingbackend.entity.Booking;
import com.isa.airlinebookingbackend.entity.Flight;
import com.isa.airlinebookingbackend.service.implementation.BookingService;
import com.isa.airlinebookingbackend.entity.Passenger;
import com.isa.airlinebookingbackend.service.implementation.PassengerService;
import com.isa.airlinebookingbackend.service.implementation.SeatAvailability;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static com.isa.airlinebookingbackend.constant.Constants.ADMIN_ACCESS;
import static com.isa.airlinebookingbackend.constant.Constants.USER_ACCESS;

@RestController
@RequestMapping(path = "/booking")
@PreAuthorize(USER_ACCESS)
@RequiredArgsConstructor
public class BookingController {

    @Autowired
    private BookingService bookingService;
    @Autowired
    private PassengerService passengerService;

    @PostMapping()
    public ResponseEntity<Long> book(@RequestBody BookingRequestDTO requestDTO) {
        System.out.println(requestDTO.toString());
        return ResponseEntity.ok().body(bookingService.addBooking(requestDTO).getBookingId());
    }

    @GetMapping()
    public List<Booking> getBooking() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{bookingId}")
    public Booking getBooking(@PathVariable Long bookingId) {
        return bookingService.getBookingById(bookingId);
    }

    @GetMapping("/getPassengers/{id}")
    public List<Passenger> getPassengers(@PathVariable long id) {
        return passengerService.getAllPassengersByBookingId(id);
    }

    @GetMapping("/booked-seats/{flightId}")
    public int[] getBookedSeatsForFlight(@PathVariable long flightId) {
        return bookingService.getBookedSeats(flightId);
    }

    @GetMapping("/getByFlight/{id}")
    public Flight getFlight(@PathVariable long id) {
        return passengerService.getFlightbyId(id);
    }

    @PutMapping("/confirm-booking/{bookingId}")
    public ResponseEntity<Void> confirmBooking(@PathVariable long bookingId, @RequestBody ConfirmBookingRequestDTO requestDTO) {
        bookingService.confirmBooking(bookingId, requestDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/cancel-booking/{bookingId}")
    public ResponseEntity<ApiResponse<Booking>> cancelBooking(@PathVariable long bookingId) {
        return ResponseEntity.ok().body(bookingService.cancelBooking(bookingId));
    }

    @PreAuthorize(ADMIN_ACCESS)
    @GetMapping("/getBookedSeat")
    public ResponseEntity<List<SeatAvailability>> getSeatDetails(){
         List <SeatAvailability> seatAvailabilityMap = bookingService.getNoOfSeatBooked();
        return ResponseEntity.ok().body(seatAvailabilityMap);
    }
}
