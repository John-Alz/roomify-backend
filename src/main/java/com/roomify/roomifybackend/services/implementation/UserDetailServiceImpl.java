package com.roomify.roomifybackend.services.implementation;

import com.roomify.roomifybackend.persistence.entity.RoleEnum;
import com.roomify.roomifybackend.presentation.dto.request.AuthLoginRequest;
import com.roomify.roomifybackend.presentation.dto.request.AuthRegisterUserRequest;
import com.roomify.roomifybackend.presentation.dto.response.AuthResponse;
import com.roomify.roomifybackend.persistence.entity.RoleEntity;
import com.roomify.roomifybackend.persistence.entity.UserEntity;
import com.roomify.roomifybackend.persistence.repository.RoleRepository;
import com.roomify.roomifybackend.persistence.repository.UserRepository;
import com.roomify.roomifybackend.services.exception.NoExistException;
import com.roomify.roomifybackend.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("El usuario con este email no existe"));

//        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        List<SimpleGrantedAuthority> authorityList = Collections.singletonList(new SimpleGrantedAuthority(userEntity.getRole().getRoleEnum().toString()));

//        userEntity.getRoles().forEach(role -> {
//            authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name())));
//        });

//        userEntity.getRoles().stream()
//                .flatMap(role -> role.getPermissionsList().stream())
//                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));

        return new User(
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.isEnabled(),
                userEntity.isAccountNoExpired(),
                userEntity.isCredentialNoExpired(),
                userEntity.isAccountNoLocked(),
                authorityList
        );
    }

    public AuthResponse loginUser(AuthLoginRequest authLoginRequest) {
        String email = authLoginRequest.email();
        String password = authLoginRequest.password();
        Authentication authentication = this.authenticate(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtUtils.createToken(authentication);
        AuthResponse authResponse = new AuthResponse(email, "User Loged Succesfull", accessToken, true);
        return authResponse;
    }

    public Authentication authenticate (String email, String password) {
        UserDetails userDetails = loadUserByUsername(email);


        if(userDetails == null) {
            throw new BadCredentialsException("Invalid email or password");
        }

        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(email, userDetails.getPassword(), userDetails.getAuthorities());

    }

    public AuthResponse registerUser(AuthRegisterUserRequest registerUserRequest) {
        UserEntity userFound = userRepository.findByEmail(registerUserRequest.email()).orElse(null);
        if(userFound != null) {
            throw new NoExistException("El correo ya esta asociado a otro usuario.");
        }

        String username = registerUserRequest.username();
        String lastName = registerUserRequest.lastName();
        String email = registerUserRequest.email();
        String phoneNumber = registerUserRequest.phoneNumber();
        LocalDate birthday = registerUserRequest.birthday();
        String password = registerUserRequest.password();
//        List<String> roleRequest = registerUserRequest.roleRequest().roles();
//        Set<RoleEntity> roleEntitySet = roleRepository.findRoleEntitiesByRoleEnumIn(roleRequest).stream().collect(Collectors.toSet());

        RoleEnum role = registerUserRequest.roleRequest().role();

        RoleEntity roleFound = roleRepository.findRoleEnum(role).orElse(null);

        if(roleFound == null) {
            throw new NoExistException("Role no existe");
        }
//        if(roleEntitySet.isEmpty()) {
//            throw new IllegalArgumentException("Invalid role");
//        }

        UserEntity userEntity = UserEntity.builder()
                .username(username)
                .lastName(lastName)
                .email(email)
                .phoneNumber(phoneNumber)
                .birthday(birthday)
                .password(passwordEncoder.encode(password))
                .role(roleFound)
                .isEnabled(true)
                .accountNoLocked(true)
                .accountNoExpired(true)
                .credentialNoExpired(true)
                .build();

        UserEntity userCreated = userRepository.save(userEntity);

        //Para crear token necesitamos una lista de los permisos que va a tener.
//        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
//        userCreated.getRoles().forEach(role -> {
//            authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name())));
//        });

        List<SimpleGrantedAuthority> authorityList = Collections.singletonList(new SimpleGrantedAuthority(userCreated.getRole().getRoleEnum().toString()));

        Authentication authentication = new UsernamePasswordAuthenticationToken(userCreated.getEmail(), userCreated.getPassword(), authorityList);
        String accessToken = jwtUtils.createToken(authentication);
        AuthResponse authResponse = new AuthResponse(userCreated.getEmail(), "User created successfully", accessToken, true);
        return authResponse;
    }

}
