package com.roomify.roomifybackend.services.implementation;

import com.roomify.roomifybackend.persistence.entity.UserEntity;
import com.roomify.roomifybackend.persistence.repository.UserRepository;
import com.roomify.roomifybackend.presentation.dto.response.ProfileResponse;
import com.roomify.roomifybackend.services.exception.NoExistException;
import com.roomify.roomifybackend.services.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSerVice implements IUserService {

    private final UserRepository userRepository;

    @Override
    public ProfileResponse getProfile(String email) {
        UserEntity userFound = userRepository.findByEmail(email).orElse(null);
        if (userFound == null) {
            throw new NoExistException("El usuario no existe");
        }
        return new ProfileResponse(
                userFound.getId(),
                userFound.getUsername(),
                userFound.getLastName(),
                userFound.getEmail(),
                userFound.getPhoneNumber(),
                userFound.getBirthday(),
                userFound.getRoles()
        );
    }


}
