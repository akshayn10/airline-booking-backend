package com.isa.airlinebookingbackend.dto.booking.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@RequiredArgsConstructor
@ToString
public class BookingRequestDTO {
    private int noOfSeatBooked;
    private String seatTypeBooked;
    private Long flightId;
    private String userEmail;
    private List<PassengerRequestDTO> passengers;
    private int[] seatNumbers;
}
