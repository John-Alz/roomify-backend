package com.roomify.roomifybackend.services.interfaces;

import com.roomify.roomifybackend.persistence.entity.FiltersUsers;
import com.roomify.roomifybackend.persistence.entity.PageResult;
import com.roomify.roomifybackend.persistence.entity.UserEntity;
import com.roomify.roomifybackend.presentation.dto.request.AuthRegisterUserRequest;
import com.roomify.roomifybackend.presentation.dto.response.DeleteResponse;
import com.roomify.roomifybackend.presentation.dto.response.ProfileResponse;
import com.roomify.roomifybackend.presentation.dto.response.SaveResponse;
import com.roomify.roomifybackend.presentation.dto.response.UserResponse;

public interface IUserService {

    ProfileResponse getProfile(String email);

    PageResult<UserResponse> getUsers(Integer page, Integer size, boolean orderAsc, FiltersUsers filtersUsers);

    UserResponse getUser(Long id);

    SaveResponse updateUser(Long id, AuthRegisterUserRequest registerUserRequest);

    DeleteResponse deteleUser(Long id);
}
