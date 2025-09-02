package com.femcoders.mynotes.dtos;

import com.femcoders.mynotes.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponse toDto(User user) {
        if (user == null) {
            return null;
        }
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail());
    }

    public User toEntity(UserRequest request) {
        if (request == null) {
            return null;
        }
        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        return user;
    }

    public void updateEntityFromDto(UserRequest request, User user) {
        if (request == null || user == null) {
            return;
        }
        user.setUsername(request.username());
        user.setEmail(request.email());
    }
}
