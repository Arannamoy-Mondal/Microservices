package com.microservice.user;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    public UserRepo userRepo;

    @GetMapping("")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<User> users = userRepo.findAll();
            if (users.size() < 1) {
                var user1 = User.builder()
                        .userName("user1")
                        .password("password")
                        .userEmail("user@user.com")
                        .build();
                userRepo.save(user1);
                System.out.println(user1);
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    userRepo.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user,
            BindingResult bindingResult) {

        try {
            userRepo.save(user);
            return ResponseEntity.status(HttpStatus.OK).body(userRepo.findAll());
        } catch (Exception e) {
            if (bindingResult.hasErrors()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(bindingResult.getFieldError().getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
