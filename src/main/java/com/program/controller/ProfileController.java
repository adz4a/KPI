package com.program.controller;

import antlr.Token;
import com.program.helper.TokenHelper;
import com.program.model.Teacher;
import com.program.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

    @Autowired
    public TokenHelper tokenHelper;


    @GetMapping("/profile")
    public HttpEntity<? extends Object> getUsers(@RequestHeader int token) {
        User user = tokenHelper.getUserByToken(token);
        if (user != null) {
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }
        Teacher teacher = tokenHelper.getTeacherByToken(token);
        if (teacher != null) {
            return new ResponseEntity<Teacher>(teacher, HttpStatus.OK);
        }
        return new ResponseEntity<>("Token expired", HttpStatus.UNAUTHORIZED);

    }

}
