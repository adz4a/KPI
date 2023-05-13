package com.program.controller;

import com.program.exception.UserException;
import com.program.helper.TokenHelper;
import com.program.model.User;
import com.program.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {

    @Autowired
    public UserService userService;

    @Autowired
    private TokenHelper tokenHelper;


    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam("email") String email, @RequestParam("password") String password) throws UserException{
        if (email == null || password == null) {
            return new ResponseEntity<>("No credentials specified", HttpStatus.BAD_REQUEST);
        }
        User user = userService.getUserByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            int token = tokenHelper.generateTokenForUser(user);
            return new ResponseEntity<>("Authorization successful. Here's your token: " + token, HttpStatus.OK);
        }

        return new ResponseEntity<>("Bad credentials", HttpStatus.BAD_REQUEST);


    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader int token) {

        User user = tokenHelper.getUserByToken(token);
        if (user != null) {
            String msgUser = "Bye, " + user.getEmail() + "!";
            tokenHelper.deleteTokenForUser(token);
            return new ResponseEntity<>(msgUser, HttpStatus.OK);
        }

        return new ResponseEntity<>("Token expired", HttpStatus.UNAUTHORIZED);

    }




}
