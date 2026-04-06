package com.mahmoud.bidboard.controller;

import com.mahmoud.bidboard.dto.CreateUserRequest;
import com.mahmoud.bidboard.dto.UserResponse;
import com.mahmoud.bidboard.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public UserResponse createUser(@Valid @RequestBody CreateUserRequest request){
        return userService.createUser(request);
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id){
    return userService.getUserById(id);
    }
}
