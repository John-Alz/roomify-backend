package com.roomify.roomifybackend.services.implementation;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.roomify.roomifybackend.persistence.entity.UserEntity;
import com.roomify.roomifybackend.persistence.repository.UserRepository;
import com.roomify.roomifybackend.services.exception.NoExistException;
import com.roomify.roomifybackend.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PasswordResetService {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    private static final String RESET_LINK_BASE_URL = "http://localhost:5173/auth/new-password?token=";

    public void sendResetLink(String email) {
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new NoExistException("No existe ningún usuario con ese correo.");
        }

        String token = jwtUtils.createResetToken(email);
        String resetLink = RESET_LINK_BASE_URL + token;

        emailService.sendPasswordResetEmail(email, resetLink);
    }

    // 2. Valida el token y actualiza la contraseña
    public void resetPassword(String token, String newPassword) {
        try {
            DecodedJWT decodedJWT = jwtUtils.verifyToken(token);
            String email = jwtUtils.extractEmail(decodedJWT);

            UserEntity user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));

            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);

        } catch (JWTVerificationException e) {
            throw new IllegalArgumentException("El token no es válido o ha expirado.");
        }
    }

}
