package com.roomify.roomifybackend.presentation.controller;

import com.roomify.roomifybackend.presentation.dto.request.AuthLoginRequest;
import com.roomify.roomifybackend.presentation.dto.request.AuthRegisterUserRequest;
import com.roomify.roomifybackend.presentation.dto.response.AuthResponse;
import com.roomify.roomifybackend.presentation.dto.response.ProfileResponse;
import com.roomify.roomifybackend.services.implementation.UserDetailServiceImpl;
import com.roomify.roomifybackend.services.implementation.UserSerVice;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailServiceImpl userDetailService;
    @Autowired
    private UserSerVice userSerVice;

    @PostMapping("/log-in")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest userRequest) {
        AuthResponse authResponse = this.userDetailService.loginUser(userRequest);
        ResponseCookie jwtCookie = ResponseCookie.from("jwtToken", authResponse.jwt())
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(3600)
                .sameSite("Strict")
                .build();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(authResponse);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid AuthRegisterUserRequest registerUserRequest) {
        return new ResponseEntity<>(this.userDetailService.registerUser(registerUserRequest), HttpStatus.CREATED);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        ResponseCookie jwtCookie = ResponseCookie.from("jwtToken", "")
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(0)
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, jwtCookie.toString());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileResponse> profile(Authentication authentication) {
        String email = authentication.getPrincipal().toString();
        System.out.println("email found: " + email);
        ProfileResponse profileResponse = userSerVice.getProfile(email);
        return ResponseEntity.ok(profileResponse);
    }

}
