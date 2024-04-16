package com.isa.airlinebookingbackend.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access= AccessLevel.PRIVATE)
public class Constants {

    public static final String ROLE_PREFIX = "ROLE_";
    public static final String JWT_ISSUER = "ISA";

    // Define Access
    public static final String ADMIN_ACCESS = "hasRole('ADMIN')";
    public static final String USER_ACCESS = "hasRole('USER')";
    public static final String ADMIN_AND_USER_ACCESS  = "hasAnyRole('USER', 'ADMIN')";

    // Email Constants
    public static final String RESET_PASSWORD_SUBJECT = "Reset Password Verification code";
    public static final String CONFIRM_EMAIL_SUBJECT = "Email Confirmation Code";

    // Define constants for endpoint URLs
    public static final String LOGIN_URL = "login";
    public static final String REGISTER_URL = "register";
    public static final String CONTACT_DETAILS_URL = "contact-details";
    public static final String REFRESH_TOKEN_URL = "refresh-token/{refreshToken}";
    public static final String CHANGE_PASSWORD_URL = "change-password";
    public static final String FORGOT_PASSWORD_URL = "forgot-password";
    public static final String FORGOT_PASSWORD_CONFIRMATION_URL = "forgot-password-confirmation";
    public static final String RESET_PASSWORD_URL = "reset-password";
    public static final String EMAIL_CONFIRMATION_URL = "email-confirmation";

    // Exception Message Constants
    public static final String USER_NOT_FOUND = "User not found";
}
