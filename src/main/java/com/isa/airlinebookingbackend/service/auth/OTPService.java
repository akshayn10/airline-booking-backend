package com.isa.airlinebookingbackend.service.auth;


import com.isa.airlinebookingbackend.entity.auth.OTP;
import com.isa.airlinebookingbackend.entity.auth.User;

public interface OTPService {
    String createOTP(User user);

    OTP findOTPByUser(User user);

    void verifyExpiration(OTP otp);
    void verifyOTP(OTP otp);
    void deleteByUser(User user);
}
