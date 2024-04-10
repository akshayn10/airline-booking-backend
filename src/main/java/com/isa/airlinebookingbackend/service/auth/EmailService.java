package com.isa.airlinebookingbackend.service.auth;

import jakarta.mail.MessagingException;

public interface EmailService {
    public void sendEmail(String to, String subject, String body) throws MessagingException;
}
