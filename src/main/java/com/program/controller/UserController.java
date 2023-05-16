package com.program.controller;

import com.program.exception.UserException;
import com.program.model.User;
import com.program.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User newUser) throws UserException {

        User user = userService.getUserByEmail(newUser.getEmail());
        if (user != null) {
            return new ResponseEntity<>("User already exists.", HttpStatus.BAD_REQUEST);
        }
        userService.createUser(newUser);
        return new ResponseEntity<>("Created user successfully, please login.", HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() throws UserException {
        List<User> users =	userService.findAll();
        return new ResponseEntity<List<User>>(users,HttpStatus.OK);
    }


    @GetMapping("/user/getById/{Id}")
    public ResponseEntity<User> getCategoryById(@PathVariable("Id") Long id ) throws UserException {
        User user1 = userService.getUserById(id);
        return new ResponseEntity<User>(user1,HttpStatus.OK);
    }

    @PostMapping("/user/update/{id}")
    public ResponseEntity<User> updateUserById( @PathVariable Long id, @RequestBody User user) throws UserException{
        User user1=	userService.updateUser(id,user);
        return new ResponseEntity<User>(user1,HttpStatus.OK);
    }



}
