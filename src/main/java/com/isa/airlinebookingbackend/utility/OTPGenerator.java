package com.isa.airlinebookingbackend.utility;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class OTPGenerator {
    private static final int OTP_LENGTH = 6;
    private static final Random random = new Random();

    public static String generateOTP() {
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(random.nextInt(10)); // Generates a random digit (0-9) and appends it to the OTP
        }
        return otp.toString();
    }
}
