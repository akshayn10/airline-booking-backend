package com.isa.airlinebookingbackend.service.implementation.auth;

import com.isa.airlinebookingbackend.dto.ApiResponse;
import com.isa.airlinebookingbackend.dto.auth.request.ContactDetailsRequestDTO;
import com.isa.airlinebookingbackend.dto.auth.request.UpdateUserDetailsRequestDTO;
import com.isa.airlinebookingbackend.dto.auth.response.UserResponseDto;
import com.isa.airlinebookingbackend.dto.user.response.PastBookingResponseDTO;
import com.isa.airlinebookingbackend.dto.user.response.UpcomingTripResponseDTO;
import com.isa.airlinebookingbackend.entity.Booking;
import com.isa.airlinebookingbackend.entity.Passenger;
import com.isa.airlinebookingbackend.entity.auth.User;
import com.isa.airlinebookingbackend.exception.auth.UserNotFoundException;
import com.isa.airlinebookingbackend.repository.BookingRepo;
import com.isa.airlinebookingbackend.repository.UserRepository;
import com.isa.airlinebookingbackend.service.auth.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.isa.airlinebookingbackend.constant.Constants.USER_NOT_FOUND;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BookingRepo bookingRepo;

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
    public ApiResponse<UserResponseDto> updateUserDetails(String email, UpdateUserDetailsRequestDTO requestDTO) {
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

    @Override
    public ApiResponse<List<PastBookingResponseDTO>> getPastBookings(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND + email));
        List<Booking> allBookingsByUser = bookingRepo.findAllByUser(user);
        List<PastBookingResponseDTO> pastBookingsResponse = new ArrayList<>();
        for (Booking booking : allBookingsByUser) {
            if (booking.getFlight().getDepartureTime().isBefore(OffsetDateTime.now()) || booking.isCancelled()) {
                String flightDetailsString = getFlightDetailsString(booking);

                StringBuilder passengerNamesListString = new StringBuilder();
                for (Passenger passenger : booking.getPassengers()) {
                    passengerNamesListString.append(passenger.getFirstName()).append(" ").append(passenger.getLastName());
                    passengerNamesListString.append(", ");
                }
                var pastBookingResponseDTO = PastBookingResponseDTO.builder()
                        .bookingId(booking.getBookingId())
                        .flightDetails(flightDetailsString)
                        .bookingDateTime(booking.getBookingDate())
                        .passengers(passengerNamesListString.toString())
                        .totalCost(booking.getTotalCost())
                        .isCancelled(booking.isCancelled()).build();
                pastBookingsResponse.add(pastBookingResponseDTO);
            }
        }
        return ApiResponse.success(pastBookingsResponse);

    }


    @Override
    public ApiResponse<List<UpcomingTripResponseDTO>> getUpcomingTrips(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND + email));
        List<Booking> allBookingsByUser = bookingRepo.findAllByUser(user);
        List<UpcomingTripResponseDTO> upcomingTripResponseDTOS = new ArrayList<>();
        for (Booking booking : allBookingsByUser) {
            if (booking.getFlight().getDepartureTime().isAfter(OffsetDateTime.now()) && !booking.isCancelled()) {
                String flightDetailsString = getFlightDetailsString(booking);
                StringBuilder passengerNamesListString = new StringBuilder();
                for (Passenger passenger : booking.getPassengers()) {
                    passengerNamesListString.append(passenger.getFirstName()).append(" ").append(passenger.getLastName());
                    passengerNamesListString.append(", ");
                }
                var upcomingTripDto = UpcomingTripResponseDTO.builder()
                        .bookingId(booking.getBookingId())
                        .flightDetails(flightDetailsString)
                        .bookingDateTime(booking.getBookingDate())
                        .passengers(passengerNamesListString.toString())
                        .departureTime(booking.getFlight().getDepartureTime())
                        .arrivalTime(booking.getFlight().getArrivalTime())
                        .totalCost(booking.getTotalCost()).build();

                upcomingTripResponseDTOS.add(upcomingTripDto);
            }
        }
        return ApiResponse.success(upcomingTripResponseDTOS);
    }

    private String getFlightDetailsString(Booking booking) {

        return booking.getFlight().getFleet().getModel() + " - " + " From " + booking.getFlight().getArrivalLocation().getCode() + " - " + " To " + booking.getFlight().getDepartureLocation().getCode();
    }

}
