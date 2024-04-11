package com.isa.airlinebookingbackend.payload;

import java.time.OffsetDateTime;

public record FlightPayload(Long id, Long departureLocation, Long arrivalLocation,
                            OffsetDateTime departureTime, OffsetDateTime arrivalTime,
                            Long fleetId, Float economyFare, Float premiumFare, Float businessFare,
                            Integer remainingEconomySeats, Integer remainingPremiumSeats,
                            Integer remainingBusinessSeats, boolean flightHasBookings) {
}
