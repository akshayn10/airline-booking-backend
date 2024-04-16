package com.isa.airlinebookingbackend.dto.booking.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
@ToString
public class PassengerRequestDTO {

    private String firstName;
    private String lastName;
    private String passportNo;
    private String mealPreference;
    private LocalDate dateOfBirth;
    private String gender;

}
