package com.roomify.roomifybackend.presentation.controller;

import com.roomify.roomifybackend.presentation.dto.AuthLoginRequest;
import com.roomify.roomifybackend.presentation.dto.AuthRegisterUserRequest;
import com.roomify.roomifybackend.presentation.dto.AuthResponse;
import com.roomify.roomifybackend.presentation.dto.ProfileResponse;
import com.roomify.roomifybackend.services.implementation.UserDetailServiceImpl;
import com.roomify.roomifybackend.services.implementation.UserSerVice;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailServiceImpl userDetailService;
    @Autowired
    private UserSerVice userSerVice;

    @PostMapping("/log-in")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest userRequest) {
        return new ResponseEntity<>(this.userDetailService.loginUser(userRequest), HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid AuthRegisterUserRequest registerUserRequest) {
        return new ResponseEntity<>(this.userDetailService.registerUser(registerUserRequest), HttpStatus.CREATED);
    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileResponse> profile(Authentication authentication) {
        String email = authentication.getPrincipal().toString();
        ProfileResponse profileResponse = userSerVice.getProfile(email);
        return ResponseEntity.ok(profileResponse);
    }

}
