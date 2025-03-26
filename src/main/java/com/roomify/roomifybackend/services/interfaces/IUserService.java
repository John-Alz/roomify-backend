package com.roomify.roomifybackend.services.interfaces;

import com.roomify.roomifybackend.presentation.dto.response.ProfileResponse;

public interface IUserService {

    ProfileResponse getProfile(String email);

}
