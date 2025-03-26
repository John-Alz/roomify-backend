package com.roomify.roomifybackend.services.implementation;

import com.roomify.roomifybackend.persistence.repository.UserRepository;
import com.roomify.roomifybackend.presentation.dto.response.ProfileResponse;
import com.roomify.roomifybackend.services.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSerVice implements IUserService {

    private final UserRepository userRepository;

    @Override
    public ProfileResponse getProfile(String email) {
        return userRepository.findByEmail(email)
                .map(user -> new ProfileResponse(user.getId(), user.getUsername(), user.getEmail(), user.getRoles()))
                .orElseThrow(() -> new RuntimeException("User not found"));
    }


}
