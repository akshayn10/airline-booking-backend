package com.isa.airlinebookingbackend.payload;

public record FleetPayload(Long id, String code, String model,
                           Integer totalEconomySeats, Integer totalPremiumSeats, Integer totalBusinessSeats) {
}
