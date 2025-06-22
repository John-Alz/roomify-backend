package com.roomify.roomifybackend.presentation.controller;

import com.roomify.roomifybackend.persistence.entity.FiltersUsers;
import com.roomify.roomifybackend.persistence.entity.PageResult;
import com.roomify.roomifybackend.presentation.dto.request.AuthRegisterUserRequest;
import com.roomify.roomifybackend.presentation.dto.response.DeleteResponse;
import com.roomify.roomifybackend.presentation.dto.response.SaveResponse;
import com.roomify.roomifybackend.presentation.dto.response.UserResponse;
import com.roomify.roomifybackend.services.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @GetMapping
    public ResponseEntity<PageResult<UserResponse>> getUsers(
            Integer page,
            Integer size,
            boolean orderAsc,
            @ModelAttribute FiltersUsers filtersUsers) {
        PageResult<UserResponse> responsePageResultUsers = userService.getUsers(page, size, orderAsc, filtersUsers);
        return ResponseEntity.ok(responsePageResultUsers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        UserResponse userResponse = userService.getUser(id);
        return ResponseEntity.ok(userResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaveResponse> putUser(@PathVariable Long id, @RequestBody AuthRegisterUserRequest registerUserRequest) {
        return ResponseEntity.ok(userService.updateUser(id, registerUserRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponse> deleteUser(@PathVariable Long id) {
        DeleteResponse deleteResponse = userService.deteleUser(id);
        return ResponseEntity.ok(deleteResponse);
    }

}

