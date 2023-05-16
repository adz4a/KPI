package com.program.controller;

import com.program.detail.UserDetailsImpl;
import com.program.exception.UserException;
import com.program.helper.TokenHelper;
import com.program.helper.jwt.JwtUtils;
import com.program.model.User;
import com.program.payload.response.JwtResponse;
import com.program.payload.request.LoginRequest;
import com.program.repository.RoleRepository;
import com.program.repository.UserRepository;
import com.program.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    public UserService userService;

    @Autowired
    private TokenHelper tokenHelper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;


    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam("email") String email, @RequestParam("password") String password) throws UserException{
        if (email == null || password == null) {
            return new ResponseEntity<>("No credentials specified", HttpStatus.BAD_REQUEST);
        }
        User user = userService.getUserByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            int token = tokenHelper.generateTokenForUser(user);
            System.out.println(token);
            return new ResponseEntity<>("Authorization successful. Here's your token: " + token, HttpStatus.OK);
        }

        return new ResponseEntity<>("Bad credentials", HttpStatus.BAD_REQUEST);


    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getEmail(),
                roles));

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
