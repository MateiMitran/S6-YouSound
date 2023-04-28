package com.backend.userservice.services;

import com.backend.userservice.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {


    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    public void sendVerificationEmail(User user) {
        String to = user.getEmail();
        String subject = "Email Verification";
        String verificationUrl = "http://localhost:3000/verify/" + user.getVerification_token();
        String text = "Please click the following link to verify your email address: " + verificationUrl;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        javaMailSender.send(message);
    }
}