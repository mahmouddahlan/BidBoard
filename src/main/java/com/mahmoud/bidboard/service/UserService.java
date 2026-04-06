package com.mahmoud.bidboard.service;

import com.mahmoud.bidboard.dto.CreateUserRequest;
import com.mahmoud.bidboard.dto.UserResponse;
import com.mahmoud.bidboard.entity.User;
import com.mahmoud.bidboard.exception.ResourceNotFoundException;
import com.mahmoud.bidboard.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(CreateUserRequest request) {
        User user = User.builder()
                .username(request.username())
                .email(request.email())
                .build();

        User saved = userRepository.save(user);
        return mapToResponse(saved);
    }

    public User getUserEntityById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public UserResponse getUserById(Long id) {
        return mapToResponse(getUserEntityById(id));
    }

    private UserResponse mapToResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }
}