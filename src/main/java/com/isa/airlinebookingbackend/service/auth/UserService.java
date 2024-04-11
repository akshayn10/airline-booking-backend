package com.isa.airlinebookingbackend.service.auth;

import com.isa.airlinebookingbackend.dto.ApiResponse;
import com.isa.airlinebookingbackend.dto.auth.request.ContactDetailsRequestDTO;

public interface UserService {
    ApiResponse<String> addContactDetails(ContactDetailsRequestDTO requestDTO);


}
