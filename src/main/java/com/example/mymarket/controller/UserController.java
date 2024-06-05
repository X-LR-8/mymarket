package com.example.mymarket.controller;

import com.example.mymarket.dto.UserDto;
import com.example.mymarket.service.MarketService;
import com.example.mymarket.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getUserbyEmail(@RequestParam("email") String email) {
        try {
            val body=userService.getEmail(email);
            return ResponseEntity.status(HttpStatus.OK).body(body);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PutMapping
    @ResponseBody
    public ResponseEntity<?> checkUser(@RequestParam("email") String email,@RequestParam("password") String password) {
        try {
            Boolean answer=userService.checkUser(email,password);
            return ResponseEntity.status(HttpStatus.OK).body(answer);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PostMapping
    @ResponseBody
    public ResponseEntity<?> addUser(@RequestParam("username") String username,@RequestParam("email") String email,@RequestParam("password") String password,@RequestParam("instantbirthday") String instantbirthday) {
        try {
            val user=userService.addUser(UserDto.builder()
                    .username(username)
                    .email(email)
                    .password(password)
                    .instantbirthday(instantbirthday)
                    .build());
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
