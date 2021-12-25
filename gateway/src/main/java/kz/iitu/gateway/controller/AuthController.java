package kz.iitu.gateway.controller;

import kz.iitu.gateway.entity.LoginRequest;
import kz.iitu.gateway.entity.User;
import kz.iitu.gateway.repository.UserRepository;
import kz.iitu.gateway.security.CustomEncoder;
import kz.iitu.gateway.service.UserDetailsServiceImpl;
import kz.iitu.gateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class AuthController {

    @Autowired
    private UserDetailsServiceImpl userService;

    @Autowired
    private CustomEncoder passwordEncoder;

    @Autowired
    private UserRepository repository;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Mono<ResponseEntity<?>> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("test");
        return userService.loadUserByUsername(loginRequest.getUsername()).map(user -> {
            System.out.println(passwordEncoder.encode(loginRequest.getPassword()));
            System.out.println(user.getPassword());
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

    @GetMapping("/check")
    public ResponseEntity<Mono<User>> hello() {
        return ResponseEntity.ok(this.repository.getByUsername("adil"));
    }
}
