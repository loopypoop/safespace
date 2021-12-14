package kz.iitu.gateway.controller;

import kz.iitu.gateway.entity.User;
import kz.iitu.gateway.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserDetailsServiceImpl userService;

    @PostMapping
    public User create(@RequestBody User user) {
        return this.userService.create(user);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return this.userService.getUser(id);
    }
}
