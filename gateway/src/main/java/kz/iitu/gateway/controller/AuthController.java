package kz.iitu.gateway.controller;

import kz.iitu.gateway.entity.ChangePasswordRequest;
import kz.iitu.gateway.entity.LoginRequest;
import kz.iitu.gateway.entity.RegisterUserRequest;
import kz.iitu.gateway.entity.User;
import kz.iitu.gateway.repository.UserRepository;
import kz.iitu.gateway.security.CustomEncoder;
import kz.iitu.gateway.service.UserDetailsServiceImpl;
import kz.iitu.gateway.service.UserServiceImpl;
import kz.iitu.gateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class AuthController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private CustomEncoder passwordEncoder;

    @Autowired
    private UserRepository repository;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Mono<ResponseEntity<?>> login(@RequestBody LoginRequest loginRequest) {
        return userDetailsService.loadUserByUsername(loginRequest.getUsername()).map(user -> {
            if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                return ResponseEntity.ok(jwtUtil.generateToken(user));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }).defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PostMapping("/signup")
    public Mono<User> createPerson(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("User");
        return this.repository.save(user);
    }

    @PostMapping("/create-user")
    public ResponseEntity<Mono<String>> createUser(@RequestBody RegisterUserRequest registerUserRequest) {
        return ResponseEntity.ok(this.userService.createUser(registerUserRequest));
    }

    @PostMapping("/change-password")
    public Mono<String> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        return this.userService.changePassword(changePasswordRequest);
    }

//    @GetMapping("/check")
//    public ResponseEntity<Mono<User>> hello() {
//        return ResponseEntity.ok(this.repository.getByUsername("adil"));
//    }
}
