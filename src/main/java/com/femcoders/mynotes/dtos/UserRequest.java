package com.femcoders.mynotes.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotBlank(message = "Username cannot be blank!") @Size(max = 50, message = "Username cannot be longer than 50 characters!")
        String username,
        @NotBlank(message = "Email cannot be blank!") @Email(message = "Email should be valid!")
        String email,
        @NotBlank(message = "Password cannot be blank!") @Size(min = 8, message = "Password should be at least 8 characters long!")
        String password) {
}
