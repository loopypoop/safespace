package kz.iitu.business.controller;

import kz.iitu.business.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final IUserService userService;

    @GetMapping("/deactivate/{userId}")
    public Mono<String> deactivateUser(@PathVariable Long userId) {
        return this.userService.deactivateUser(userId);
    }
}
