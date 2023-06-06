package com.program.controller;

import com.program.exception.CategoryException;
import com.program.exception.EventException;
import com.program.exception.StatusException;
import com.program.exception.UserException;
import com.program.model.User;
import com.program.payload.request.UpdateUserRequest;
import com.program.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAllUsers() throws UserException {
        List<User> users =	userService.findAll();
        return new ResponseEntity<List<User>>(users,HttpStatus.OK);
    }

    @GetMapping("/users/except")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getUsersExceptTeachers() throws UserException {
        List<User> users =	userService.findUsersExceptTeachers();
        return new ResponseEntity<List<User>>(users,HttpStatus.OK);
    }

    @GetMapping("/user/getById/{Id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getUserById(@PathVariable("Id") Long id ) {
        try {
            User user1 = userService.getUserById(id);
            return new ResponseEntity<User>(user1,HttpStatus.OK);
        }catch (UserException ex){
            String errorMessage = "Error: " + ex.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity GetUpdateUserById( @PathVariable Long id){
        try {
            User user = userService.getUserById(id);
            return new ResponseEntity<>(user,HttpStatus.OK);
        }catch (UserException ex){
            String errorMessage = "Error: " + ex.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PutMapping("/user/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity updateUserById( @PathVariable Long id, @RequestBody UpdateUserRequest updateUserRequest){
        try {
           	userService.updateUser(id,updateUserRequest);
            return new ResponseEntity<>("The user updated successfully!",HttpStatus.OK);
        }catch (UserException ex){
            String errorMessage = "Error: " + ex.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/user/delete/{Id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> deleteUserById(@PathVariable ("Id") Long id){
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>("User with this id deleted", HttpStatus.OK);
        }catch (UserException ex) {
            String errorMessage = "Error setting: " + ex.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
