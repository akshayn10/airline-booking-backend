package com.isa.airlinebookingbackend.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpcomingTripResponseDTO {
    private Long bookingId;
    private String flightDetails;
    private Instant bookingDateTime;
    private OffsetDateTime departureTime;
    private OffsetDateTime arrivalTime;
    private String passengers;
    private int[] seatNumbers;
    private float totalCost;
}
