package com.TaskManagement.TaskManagement.Services;

import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.TaskManagement.TaskManagement.DTOs.UserWriteDTO;
import com.TaskManagement.TaskManagement.Models.AppUser;
import com.TaskManagement.TaskManagement.Models.Role;
import com.TaskManagement.TaskManagement.Repositories.RoleRepository;
import com.TaskManagement.TaskManagement.Repositories.UserRepository;
import com.TaskManagement.TaskManagement.Security.JwtService;

@Service
public class UserService {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;

    public UserService(AuthenticationManager authenticationManager, UserRepository userRepository,
            RoleRepository roleRepository, PasswordEncoder passwordEncoder,
            JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public ResponseEntity<String> register(UserWriteDTO UserWriteDTO) {
        if (userRepository.existsByUsername(UserWriteDTO.username())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        AppUser user = new AppUser();
        user.setUsername(UserWriteDTO.username());
        user.setPassword(passwordEncoder.encode((UserWriteDTO.password())));

        Role roles = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registered success!", HttpStatus.CREATED);
    }

    public ResponseEntity<String> login(UserWriteDTO userWriteDTO) {
        AppUser authenticatedUser = authenticate(userWriteDTO);

        String token = jwtService.generateToken(authenticatedUser);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    public AppUser authenticate(UserWriteDTO input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.username(),
                        input.password()));

        return userRepository.findByUsername(input.username())
                .orElseThrow();
    }
}
