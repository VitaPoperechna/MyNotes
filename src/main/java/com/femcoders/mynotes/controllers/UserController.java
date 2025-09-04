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
    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserByUserId(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<UserResponse> addUser (@Valid @RequestBody UserRequest userRequest) {
        UserResponse newUser = userService.addUser(userRequest);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
}
