package com.femcoders.mynotes.controllers;

import com.femcoders.mynotes.dtos.JwtResponse;
import com.femcoders.mynotes.dtos.UserRequest;
import com.femcoders.mynotes.dtos.UserResponse;
import com.femcoders.mynotes.security.UserDetail;
import com.femcoders.mynotes.security.jwt.JwtService;
import com.femcoders.mynotes.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(UserService userService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody @Valid UserRequest userRequest) {
        UserRequest userRequestWithRolByDefault = new UserRequest(userRequest.username(), userRequest.email(), userRequest.password());

        UserResponse userResponse = userService.addUser(userRequestWithRolByDefault);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody UserRequest userRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.username(), userRequest.password()));

        UserDetail userDetail = (UserDetail) authentication.getPrincipal();

        String token = jwtService.generateToken(userDetail);

        JwtResponse jwtResponse = new JwtResponse(token);
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }
}
