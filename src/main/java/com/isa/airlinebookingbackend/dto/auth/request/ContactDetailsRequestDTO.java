package com.isa.airlinebookingbackend.dto.auth.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class ContactDetailsRequestDTO {
    @NotNull(message = "Email Cannot be null")
    @NotEmpty(message = "Email is required")
    private String email;
    @NotNull(message = "First name Cannot be null")
    @NotEmpty(message = "First name is required")
    private String firstName;
    @NotNull(message = "Last name Cannot be null")
    @NotEmpty(message = "First name is required")
    private String lastName;
    @NotNull(message = "Username Cannot be null")
    @NotEmpty(message = "Username is required")
    private String userName;
    @NotNull(message = "Address line cannot be null")
    @NotEmpty(message = "Address line is required")
    private String addressLine;
    @NotNull(message = "City cannot be null")
    @NotEmpty(message = "City is required")
    private String city;
    @NotNull(message = "State cannot be null")
    @NotEmpty(message = "State is required")
    private String state;
    @NotNull(message = "Country cannot be null")
    @NotEmpty(message = "Country is required")
    private String country;
    @NotNull(message = "Zip code cannot be null")
    @NotEmpty(message = "Zip code is required")
    private String zipCode;
    @NotNull(message = "Phone number prefix cannot be null")
    @NotEmpty(message = "Phone number prefix is required")
    private String phoneNumberPrefix;
    @NotNull(message = "Phone number cannot be null")
    @NotEmpty(message = "Phone number is required")
    private String phoneNumber;
}
