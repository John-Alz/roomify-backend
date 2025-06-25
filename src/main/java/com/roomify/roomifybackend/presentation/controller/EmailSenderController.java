package com.roomify.roomifybackend.presentation.controller;

import com.roomify.roomifybackend.services.implementation.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/email")
@RequiredArgsConstructor
public class EmailSenderController {

    private final EmailService emailService;

    @GetMapping
    public String testEmail(@RequestParam String email) {
        String dummyToken = "123456";
        String link = "http://localhost:5173/reset-password?token=" + dummyToken;
        emailService.sendPasswordResetEmail(email, link);
        return "Correo enviado a " + email;
    }

}
