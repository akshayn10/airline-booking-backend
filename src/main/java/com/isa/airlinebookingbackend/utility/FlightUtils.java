package com.isa.airlinebookingbackend.utility;

import com.isa.airlinebookingbackend.entity.Fleet;
import com.isa.airlinebookingbackend.entity.Flight;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class FlightUtils {
    public static boolean isFlightHasBookings(Flight flight) {
        Fleet fleet = flight.getFleet();

        return !flight.getRemainingEconomySeats().equals(fleet.getTotalEconomySeats()) ||
                !flight.getRemainingPremiumSeats().equals(fleet.getTotalPremiumSeats()) ||
                !flight.getRemainingBusinessSeats().equals(fleet.getTotalBusinessSeats());
    }
}
