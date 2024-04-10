package com.isa.airlinebookingbackend.service.implementation.auth;

import com.isa.airlinebookingbackend.entity.auth.OTP;
import com.isa.airlinebookingbackend.entity.auth.User;
import com.isa.airlinebookingbackend.exception.auth.OTPExpiredException;
import com.isa.airlinebookingbackend.exception.auth.OTPNotFoundForUserException;
import com.isa.airlinebookingbackend.repository.OTPRepository;
import com.isa.airlinebookingbackend.security.JwtConfig;
import com.isa.airlinebookingbackend.service.auth.OTPService;
import com.isa.airlinebookingbackend.utility.OTPGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class OTPServiceImpl implements OTPService {
    private final OTPRepository otpRepository;
    private final JwtConfig jwtConfig;

    @Override
    public String createOTP(User user) {
        OTP otp = otpRepository.findByUser(user).orElse(null);
        if (otp != null) {
            otp.setOtp(getOTP());
            otp.setExpiryDate(getExpiryDate());
        } else {
            otp = OTP.builder()
                    .user(user)
                    .otp(getOTP())
                    .expiryDate(getExpiryDate())
                    .build();
        }
        otpRepository.save(otp);
        return otp.getOtp();
    }

    @Override
    public OTP findOTPByUser(User user) {
        return otpRepository.findByUser(user).orElseThrow(()-> new OTPNotFoundForUserException("OTP not found for user "+user.getEmail()));
    }

    @Override
    public void verifyExpiration(OTP otp) {
        if (otp.getExpiryDate().compareTo(Instant.now()) < 0) {
            otpRepository.delete(otp);
            throw new OTPExpiredException(otp.getOtp() + " OTP token is expired");
        }

    }

    @Override
    public void verifyOTP(OTP otp) {
        otp.setVerified(true);
        otpRepository.save(otp);
    }

    @Override
    public void deleteByUser(User user) {
        otpRepository.deleteByUser(user);
    }

    private Instant getExpiryDate() {
        return Instant.now().plusMillis(jwtConfig.getAccessExpiration());
    }
    private String getOTP() {
        return OTPGenerator.generateOTP();
    }
}
