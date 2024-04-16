package com.isa.airlinebookingbackend.controller;

import com.isa.airlinebookingbackend.dto.ApiResponse;
import com.isa.airlinebookingbackend.dto.auth.request.ContactDetailsRequestDTO;
import com.isa.airlinebookingbackend.dto.auth.request.UpdateUserDetailsRequestDTO;
import com.isa.airlinebookingbackend.dto.auth.response.UserResponseDto;
import com.isa.airlinebookingbackend.service.auth.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.isa.airlinebookingbackend.constant.Constants.ADMIN_AND_USER_ACCESS;
import static com.isa.airlinebookingbackend.constant.Constants.USER_ACCESS;

@RestController
@Slf4j
@RequestMapping("user/")
@PreAuthorize(ADMIN_AND_USER_ACCESS)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("contact-details")
    public ResponseEntity<ApiResponse<String>> addContactDetails(@RequestBody ContactDetailsRequestDTO requestDTO) {
        log.info("Contact Details addition request: {}", requestDTO.toString());

        return ResponseEntity.ok(userService.addContactDetails(requestDTO));
    }
    @PutMapping("{email}")
    public ResponseEntity<ApiResponse<UserResponseDto>> updateUserDetails(@RequestBody UpdateUserDetailsRequestDTO requestDTO, @PathVariable String email) {
        log.info("Update user Details request: {}", requestDTO.toString());

        return ResponseEntity.ok(userService.updateUserDetails(email,requestDTO));
    }

    @GetMapping("by-email/{email}")
    public ResponseEntity<ApiResponse<UserResponseDto>> getUserDetailsByEmail(@PathVariable String email) {
        log.info("Get by email request: {}", email);
        return ResponseEntity.ok(userService.getUserDetailsByEmail(email));
    }
}
