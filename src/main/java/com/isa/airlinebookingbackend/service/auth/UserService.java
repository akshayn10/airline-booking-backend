package com.isa.airlinebookingbackend.service.auth;

import com.isa.airlinebookingbackend.dto.ApiResponse;
import com.isa.airlinebookingbackend.dto.auth.request.ContactDetailsRequestDTO;
import com.isa.airlinebookingbackend.dto.auth.request.UpdateUserDetailsRequestDTO;
import com.isa.airlinebookingbackend.dto.auth.response.UserResponseDto;

public interface UserService {
    ApiResponse<String> addContactDetails(ContactDetailsRequestDTO requestDTO);
    ApiResponse<UserResponseDto> getUserDetailsByEmail(String email);
    ApiResponse<UserResponseDto> updateUserDetails(String email,UpdateUserDetailsRequestDTO requestDTO);


}
