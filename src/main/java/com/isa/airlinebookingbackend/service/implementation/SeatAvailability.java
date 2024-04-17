package com.isa.airlinebookingbackend.service.implementation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeatAvailability {
    private Long flightId;
    private int economySeats;
    private int businessSeats;
    private int premiumSeats;
}
