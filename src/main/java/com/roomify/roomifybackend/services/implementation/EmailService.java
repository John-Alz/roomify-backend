package com.roomify.roomifybackend.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class EmailService {

    private final JavaMailSender mailSender;

    public void sendPasswordResetEmail(String to, String resetLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Restablecimiento de contraseña - Roomify");
        message.setText("Hola,\n\nPara restablecer tu contraseña, haz clic en el siguiente enlace:\n\n"
                + resetLink + "\n\nEste enlace expirará en 15 minutos.\n\nRoomify.");
        message.setFrom("jairoanngelll@gmail.com"); // Debe coincidir con spring.mail.username

        mailSender.send(message);
    }

}
