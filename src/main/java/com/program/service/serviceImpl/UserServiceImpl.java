package com.program.service.serviceImpl;

import com.program.exception.CategoryException;
import com.program.exception.UserException;
import com.program.model.Category;
import com.program.model.Role;
import com.program.model.User;
import com.program.repository.RoleRepository;
import com.program.repository.UserRepository;
import com.program.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final String ADMIN="ADMIN";
    private static final String USER="USER";

    private UserRepository userRepository;
    private RoleRepository roleRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public User changeRoleToAdmin(User user) throws UserException {
        Role adminRole = roleRepository.findByRole(ADMIN);
        user.setRoles(new ArrayList<>(Collections.singletonList(adminRole)));
        return userRepository.save(user);
    }


    @Override
    public List<User> findAll() throws UserException {
        return userRepository.findAll();
    }

    @Override
    public List<User> getUsersByRoleTeacher() throws UserException {
        List<User> users = userRepository.findAll();
        users.removeIf(user -> user.isAdmin() && user.isObserver());
        return users;
    }


    @Override
    public User createUser(User user) throws UserException {
        user.setPassword(user.getPassword());
        Role userRole = roleRepository.findByRole(USER);
        //add degree_____________________________________________________________
        user.setRoles(new ArrayList<>(Collections.singletonList(userRole)));
        return userRepository.save(user);
    }
    @Override
    public User getUserByEmail(String email) throws UserException {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean isUserEmailPresent(String email) throws UserException {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public User getUserById(Integer id) throws UserException {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User updateUser(Integer id, User user) throws UserException {

        Optional<User> opt = userRepository.findById(id);

        if (opt.isPresent())
        {
            User user1 = userRepository.save(user);
            return user1;
        }else {
            throw new UserException("Category with given id is not present........");
        }
    }

    @Override
    public void deleteUser(Integer id) throws UserException {
        User user = userRepository.getOne(id);
//        user.getTasksOwned().forEach(task -> task.setOwner(null));
        userRepository.delete(user);
    }

//    @Override
//    public User getUserEvents(Integer id) throws UserException {
//
//        Optional<User> opt = userRepository.findById(id);
//
////        if (opt.stream().anyMatch(user -> ))
//    }
}
