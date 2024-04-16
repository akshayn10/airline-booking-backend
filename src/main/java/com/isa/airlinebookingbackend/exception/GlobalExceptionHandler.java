package com.isa.airlinebookingbackend.exception;

import com.isa.airlinebookingbackend.dto.ApiResponse;
import com.isa.airlinebookingbackend.exception.auth.*;
import com.isa.airlinebookingbackend.exception.booking.BookingNotFoundException;
import com.isa.airlinebookingbackend.exception.booking.FlightNotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<HttpStatusCode> handleNoSuchElementException(NoSuchElementException exception) {
        exception.printStackTrace();
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<HttpStatusCode> handleIllegalArgumentException(IllegalArgumentException exception) {
        exception.printStackTrace();
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Security Exceptions
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationException(MethodArgumentNotValidException ex) {
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        Map<String,String> map = HashMap.newHashMap(errors.size());
        errors.forEach(error -> {
            String key = ((FieldError) error).getField();
            String val = error.getDefaultMessage();
            map.put(key, val);
        });
        ApiResponse<Map<String, String>> response = new ApiResponse<>(false, "Provided arguments are invalid, see data for details.", map);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({UsernameNotFoundException.class, BadCredentialsException.class, UserNotFoundException.class,UserAlreadyExistWithEmailException.class, AuthenticationCredentialsNotFoundException.class})
    public ResponseEntity<ApiResponse<String>> handleUnexpectedException(Exception ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ApiResponse.error(ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ApiResponse<String>> handleUnexpectedException(ExpiredJwtException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ApiResponse.error("JWT Access Token Expired"), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<ApiResponse<String>> handleUnexpectedException(MalformedJwtException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ApiResponse.error("JWT Token Malformed"), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ApiResponse<String>> handleUnexpectedException(DisabledException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ApiResponse.error(ex.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<ApiResponse<String>> handleUnexpectedException(LockedException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ApiResponse.error(ex.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(OTPExpiredException.class)
    public ResponseEntity<ApiResponse<String>> handleUnexpectedException(OTPExpiredException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ApiResponse.error(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OTPMismatchException.class)
    public ResponseEntity<ApiResponse<String>> handleUnexpectedException(OTPMismatchException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ApiResponse.error(ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(OTPNotFoundForUserException.class)
    public ResponseEntity<ApiResponse<String>> handleUnexpectedException(OTPNotFoundForUserException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ApiResponse.error(ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({BookingNotFoundException.class, FlightNotFoundException.class})
    public ResponseEntity<ApiResponse<String>> handleUnexpectedException(BookingNotFoundException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ApiResponse.error(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OTPNotVerifiedException.class)
    public ResponseEntity<ApiResponse<String>> handleUnexpectedException(OTPNotVerifiedException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ApiResponse.error(ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(PasswordMisMatchException.class)
    public ResponseEntity<ApiResponse<String>> handleUnexpectedException(PasswordMisMatchException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ApiResponse.error(ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(RefreshTokenExpiredException.class)
    public ResponseEntity<ApiResponse<String>> handleUnexpectedException(RefreshTokenExpiredException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ApiResponse.error(ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(RefreshTokenNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleUnexpectedException(RefreshTokenNotFoundException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ApiResponse.error(ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<ApiResponse<String>> handleUnexpectedException(MessagingException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ApiResponse.error(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
