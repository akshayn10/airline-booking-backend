package com.isa.airlinebookingbackend.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PastBookingResponseDTO {
    private Long bookingId;
    private String flightDetails;
    private Instant bookingDateTime;
    private String passengers;
    private float totalCost;
    private boolean isCancelled;
    private String status;
}
