package com.roomify.roomifybackend.services.implementation;

import com.roomify.roomifybackend.persistence.entity.*;
import com.roomify.roomifybackend.persistence.repository.BookingRepository;
import com.roomify.roomifybackend.persistence.repository.RoleRepository;
import com.roomify.roomifybackend.persistence.repository.UserRepository;
import com.roomify.roomifybackend.presentation.dto.request.AuthRegisterUserRequest;
import com.roomify.roomifybackend.presentation.dto.response.DeleteResponse;
import com.roomify.roomifybackend.presentation.dto.response.ProfileResponse;
import com.roomify.roomifybackend.presentation.dto.response.SaveResponse;
import com.roomify.roomifybackend.presentation.dto.response.UserResponse;
import com.roomify.roomifybackend.presentation.mappers.UserMapper;
import com.roomify.roomifybackend.services.exception.NoExistException;
import com.roomify.roomifybackend.services.interfaces.IUserService;
import com.roomify.roomifybackend.specification.SearchUserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserSerVice implements IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BookingRepository bookingRepository;
    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

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
                userFound.getRole()
        );
    }

    @Override
    public PageResult<UserResponse> getUsers(Integer page, Integer size, boolean orderAsc, FiltersUsers filtersUsers) {
        Pageable paging = PageRequest.of(page, size);
        System.out.println(filtersUsers.email());
        System.out.println(filtersUsers.role());
        SearchUserSpecification specification = new SearchUserSpecification(filtersUsers.email(), filtersUsers.role());
        Page<UserEntity> usersPage = userRepository.findAll(specification, paging);
        List<UserResponse> usersList = userMapper.toResponseList(usersPage.getContent());
        return new PageResult<>(
                usersList,
                usersPage.getNumber(),
                usersPage.getSize(),
                usersPage.getTotalPages(),
                usersPage.getTotalElements()
        );
    }

    @Override
    public UserResponse getUser(Long id) {
        UserEntity userFound = userRepository.findById(id).orElse(null);
        if (userFound == null) {
            throw new NoExistException("El usuario no existe.");
        }
        return userMapper.toResponse(userFound);
    }

    @Override
    public SaveResponse updateUser(Long id, AuthRegisterUserRequest registerUserRequest) {
        UserEntity userFound = userRepository.findById(id).orElse(null);
        RoleEntity roleFound = roleRepository.findRoleEnum(registerUserRequest.roleRequest().role()).orElse(null);
        if (userFound == null) {
            throw new NoExistException("El usuario no existe.");
        }
        if (roleFound == null) {
            throw new NoExistException("El rol no existe.");
        }
        userFound.setUsername(registerUserRequest.username());
        userFound.setLastName(registerUserRequest.lastName());
        userFound.setPhoneNumber(registerUserRequest.phoneNumber());
        userFound.setEmail(registerUserRequest.email());
        if (registerUserRequest.password() != null && !registerUserRequest.password().isEmpty()) {
            userFound.setPassword(passwordEncoder.encode(registerUserRequest.password()));
        }

        userFound.setRole(roleFound);

        userRepository.save(userFound);
        return new SaveResponse("Datos de usuario actualizados", LocalDate.now());
    }

    @Override
    public DeleteResponse deteleUser(Long id) {
        UserEntity userFound = userRepository.findById(id).orElse(null);
        boolean bookingFound = bookingRepository.existsActiveBookingsByUserId(id);
        if (userFound == null) {
            throw new NoExistException("El usuario no existe.");
        }

        if (bookingFound) {
            throw new NoExistException("No se puede eliminar, este usuario tiene reservas activas");
        }
        userRepository.deleteById(id);
        return new DeleteResponse("Usuario elimnado", LocalDate.now());
    }


}
