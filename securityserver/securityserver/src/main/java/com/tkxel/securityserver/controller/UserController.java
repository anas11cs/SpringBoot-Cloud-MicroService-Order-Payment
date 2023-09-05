package com.tkxel.securityserver.controller;

import com.tkxel.securityserver.entity.User;
import com.tkxel.securityserver.service.JwtGenerator;
import com.tkxel.securityserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.AccountNotFoundException;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtGenerator jwtGenerator;
    @PostMapping("/register")
    public ResponseEntity<?> postUser(@RequestBody User user){
        try{
            userService.saveUser(user);
            return new ResponseEntity<>("User Created or Updated", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
            if(user.getUsername() == null || user.getPassword() == null) {
                throw new AccountNotFoundException("UserName or Password is Empty");
            }
            User userData = userService.getUserByNameAndPassword(user.getUsername(), user.getPassword());
            if(userData == null){
                throw new AccountNotFoundException("UserName or Password is Invalid");
            }
            return new ResponseEntity<>(jwtGenerator.generateToken(user), HttpStatus.OK);
        } catch (AccountNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
