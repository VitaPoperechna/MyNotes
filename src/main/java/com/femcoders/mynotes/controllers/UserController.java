package com.femcoders.mynotes.controllers;

import com.femcoders.mynotes.dtos.UserRequest;
import com.femcoders.mynotes.dtos.UserResponse;
import com.femcoders.mynotes.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    public final UserService UserService;

    public UserController(UserService UserService) {
        this.UserService = UserService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return new ResponseEntity<>(UserService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<UserResponse> getUserByUsername(@PathVariable String name) {
        return new ResponseEntity<>(UserService.getUserByUsername(name), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<UserResponse> registerUser (@Valid @RequestBody UserRequest userRequest) {
        UserResponse newUser = UserService.registerUser(userRequest);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
}
