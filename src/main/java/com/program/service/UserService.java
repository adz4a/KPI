package com.program.service;

import com.program.exception.UserException;
import com.program.model.User;
import com.program.payload.request.AssignRequest;
import com.program.payload.request.RegisterRequest;
import com.program.payload.request.UpdateUserRequest;

import java.util.List;

public interface UserService {

    User createUser(User user) throws UserException;

    User changeRoleToAdmin(User user) throws UserException;

    List<User> findAll() throws UserException;

    List<User> findUsersExceptTeachers() throws UserException;

    List<User> getUsersByRoleTeacher() throws UserException;

    User getUserByEmail(String email) throws UserException;

    User isUserEmailPresent(String email) throws UserException;

    Boolean existsByEmail(String email) throws UserException;

    User getUserById(Long userId) throws UserException;

    void updateUser(Long id, UpdateUserRequest updateUserRequest) throws UserException;

    void registerUser(RegisterRequest registerRequest) throws UserException;

    void deleteUser(Long id) throws UserException;


}
