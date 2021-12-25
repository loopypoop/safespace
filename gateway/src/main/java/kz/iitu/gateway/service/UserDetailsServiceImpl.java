package kz.iitu.gateway.service;

import kz.iitu.gateway.entity.User;
import kz.iitu.gateway.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl {

    private final UserRepository userRepository;

    public Mono<User> loadUserByUsername(String username) throws UsernameNotFoundException {
        Mono<User> user = userRepository.getByUsername(username).map(v -> {
            System.out.println(v.getUsername());
            return v;
        });
        return user.switchIfEmpty(Mono.empty());
    }

//    public Mono<User> addUpdateUser(User user) {
//        System.out.println(user.getUsername());
//        System.out.println(user.getId());
//        return ;
//    }
}
