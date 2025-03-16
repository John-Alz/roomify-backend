package com.roomify.roomifybackend.services.interfaces;

import com.roomify.roomifybackend.presentation.dto.ProfileResponse;

public interface IUserService {

    ProfileResponse getProfile(String email);

}
