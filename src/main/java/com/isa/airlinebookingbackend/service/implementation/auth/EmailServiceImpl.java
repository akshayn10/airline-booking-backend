package com.isa.airlinebookingbackend.service.implementation.auth;

import com.isa.airlinebookingbackend.service.auth.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static jakarta.mail.Message.RecipientType.TO;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendEmail(String to, String subject, String body) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        message.setFrom(new InternetAddress("sender@example.com"));
        message.setRecipients(TO, to);
        message.setSubject(subject);
        String htmlContent = "<h1 style=\"text-align: center; color: #333;\">Air Line Booking</h1>\n" +
                "<h3 style=\"text-align: center; color: #555;\">Please Find Your Verification Code Below</h3>\n" +
                "<div style=\"max-width: 600px; margin: 20px auto; padding: 20px; background-color: #fff; border-radius: 10px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\">\n" +
                "  <h2 style=\"text-align: center; color: #007bff;\">"+body+"</h2> " +
                "</div>";
        message.setContent(htmlContent, "text/html; charset=utf-8");
        mailSender.send(message);
    }
}