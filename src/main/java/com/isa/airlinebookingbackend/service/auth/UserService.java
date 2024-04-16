package com.isa.airlinebookingbackend.service.auth;

import com.isa.airlinebookingbackend.dto.ApiResponse;
import com.isa.airlinebookingbackend.dto.auth.request.ContactDetailsRequestDTO;
import com.isa.airlinebookingbackend.dto.auth.request.UpdateUserDetailsRequestDTO;
import com.isa.airlinebookingbackend.dto.auth.response.UserResponseDto;
import com.isa.airlinebookingbackend.dto.user.response.PastBookingResponseDTO;
import com.isa.airlinebookingbackend.dto.user.response.UpcomingTripResponseDTO;

import java.util.List;

public interface UserService {
    ApiResponse<String> addContactDetails(ContactDetailsRequestDTO requestDTO);
    ApiResponse<UserResponseDto> getUserDetailsByEmail(String email);
    ApiResponse<UserResponseDto> updateUserDetails(String email,UpdateUserDetailsRequestDTO requestDTO);

    ApiResponse<List<PastBookingResponseDTO>> getPastBookings(String email);
    ApiResponse<List<UpcomingTripResponseDTO>> getUpcomingTrips(String email);


}
