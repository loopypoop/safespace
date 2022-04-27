package kz.iitu.business.service.impl;

import kz.iitu.business.repository.UserRepository;
import kz.iitu.business.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    @Override
    public Mono<String> deactivateUser(Long userId) {
        return this.userRepository.findById(userId).map(u -> {
            u.setIsActive(false);

            this.userRepository.save(u).subscribe();
            return "User deleted successfully!";
        });
    }
}
