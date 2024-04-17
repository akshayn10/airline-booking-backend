package com.isa.airlinebookingbackend.dto.booking.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@RequiredArgsConstructor
@ToString
public class ConfirmBookingRequestDTO {
    private int[] seatNumbers;
}
