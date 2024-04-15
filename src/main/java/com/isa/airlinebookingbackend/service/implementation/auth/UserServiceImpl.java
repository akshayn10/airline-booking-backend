package com.isa.airlinebookingbackend.service.implementation.auth;

import com.isa.airlinebookingbackend.dto.ApiResponse;
import com.isa.airlinebookingbackend.dto.auth.request.ContactDetailsRequestDTO;
import com.isa.airlinebookingbackend.dto.auth.request.UpdateUserDetailsRequestDTO;
import com.isa.airlinebookingbackend.dto.auth.response.UserResponseDto;
import com.isa.airlinebookingbackend.entity.auth.User;
import com.isa.airlinebookingbackend.exception.auth.UserNotFoundException;
import com.isa.airlinebookingbackend.repository.UserRepository;
import com.isa.airlinebookingbackend.service.auth.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.isa.airlinebookingbackend.constant.Constants.USER_NOT_FOUND;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public ApiResponse<String> addContactDetails(ContactDetailsRequestDTO requestDTO) {
        User user = userRepository.findByEmail(requestDTO.getEmail()).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND + requestDTO.getEmail()));
        user.setAddressLine(requestDTO.getAddressLine());
        user.setCity(requestDTO.getCity());
        user.setState(requestDTO.getState());
        user.setCountry(requestDTO.getCountry());
        user.setPhoneNumberPrefix(requestDTO.getPhoneNumberPrefix());
        user.setPhoneNumber(requestDTO.getPhoneNumber());
        user.setZipCode(requestDTO.getZipCode());
        userRepository.save(user);
        return ApiResponse.success("User updated for " + user.getEmail());
    }

    @Override
    public ApiResponse<UserResponseDto> getUserDetailsByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND + email));
        var userResponseDto = UserResponseDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .addressLine(user.getAddressLine())
                .city(user.getCity())
                .state(user.getState())
                .country(user.getCountry())
                .phoneNumberPrefix(user.getPhoneNumberPrefix())
                .phoneNumber(user.getPhoneNumber())
                .zipCode(user.getZipCode())
                .role(user.getRole())
                .username(user.getUsername())
                .build();
        return ApiResponse.success(userResponseDto);
    }

    @Override
    public ApiResponse<UserResponseDto> updateUserDetails(String email,UpdateUserDetailsRequestDTO requestDTO) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND + email));
        user.setFirstName(requestDTO.getFirstName());
        user.setLastName(requestDTO.getLastName());
        user.setAddressLine(requestDTO.getAddressLine());
        user.setCity(requestDTO.getCity());
        user.setState(requestDTO.getState());
        user.setCountry(requestDTO.getCountry());
        user.setPhoneNumberPrefix(requestDTO.getPhoneNumberPrefix());
        user.setPhoneNumber(requestDTO.getPhoneNumber());
        user.setZipCode(requestDTO.getZipCode());
        user.setUsername(user.getUsername());
        userRepository.save(user);

        var userResponseDto = UserResponseDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .addressLine(user.getAddressLine())
                .city(user.getCity())
                .state(user.getState())
                .country(user.getCountry())
                .phoneNumberPrefix(user.getPhoneNumberPrefix())
                .phoneNumber(user.getPhoneNumber())
                .zipCode(user.getZipCode())
                .role(user.getRole())
                .username(user.getUsername())
                .build();
        return ApiResponse.success(userResponseDto);
    }
}
