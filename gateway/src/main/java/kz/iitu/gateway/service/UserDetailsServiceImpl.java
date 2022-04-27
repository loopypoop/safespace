package kz.iitu.gateway.service;

import kz.iitu.gateway.entity.User;
import kz.iitu.gateway.repository.UserRepository;
import kz.iitu.gateway.security.CustomEncoder;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl {

    private final UserRepository userRepository;

    public Mono<User> loadUserByUsername(String username) throws UsernameNotFoundException {
        Mono<User> user = userRepository.getByUsernameAndIsActive(username, true).map(v -> v);
        return user.switchIfEmpty(Mono.empty());
    }

}
