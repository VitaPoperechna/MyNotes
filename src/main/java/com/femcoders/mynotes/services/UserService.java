package com.femcoders.mynotes.services;

import com.femcoders.mynotes.dtos.UserMapper;
import com.femcoders.mynotes.dtos.UserRequest;
import com.femcoders.mynotes.dtos.UserResponse;
import com.femcoders.mynotes.models.User;
import com.femcoders.mynotes.repositories.UserRepository;
import com.femcoders.mynotes.security.UserDetail;
import org.hibernate.ObjectNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public List<UserResponse> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::toDto).toList();
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(User.class.getSimpleName(), id));
        return UserMapper.toDto(user);
    }

    public UserResponse addUser(UserRequest userRequest){
        User newUser = UserMapper.toEntity(userRequest);
        newUser.setPassword(passwordEncoder.encode(userRequest.password()));
        User savedUser = userRepository.save(newUser);
        return UserMapper.toDto(savedUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new UserDetail(user))
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
