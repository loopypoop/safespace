package kz.iitu.gateway.service;

import kz.iitu.gateway.entity.ChangePasswordRequest;
import kz.iitu.gateway.entity.RegisterUserRequest;
import kz.iitu.gateway.entity.User;
import kz.iitu.gateway.entity.UserDetail;
import kz.iitu.gateway.repository.UserDetailRepository;
import kz.iitu.gateway.repository.UserRepository;
import kz.iitu.gateway.security.CustomEncoder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UserServiceImpl {

    private final UserRepository userRepository;
    private final CustomEncoder passwordEncoder;
    private final UserDetailRepository userDetailRepository;


    public Mono<String> changePassword(ChangePasswordRequest changePasswordRequest) {
        return this.userRepository.findById(changePasswordRequest.getUserId()).map(user -> {
            if (!user.getPassword().equals(passwordEncoder.encode(changePasswordRequest.getCurrentPassword()))) {
                return "Wrong current password.";
            }
            if (!changePasswordRequest.getConfirmationPassword().equals(changePasswordRequest.getNewPassword())) {
                return "Confirmation password doesn't match to your new password.";
            }

            user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
            this.userRepository.save(user).subscribe();
            return "Password changed successfully.";
        });
    }

    public Mono<String> createUser(RegisterUserRequest registerUserRequest) {
        User user = User.builder()
                .username(registerUserRequest.getUsername())
                .password(passwordEncoder.encode(registerUserRequest.getPassword()))
                .role(registerUserRequest.getRole())
                .build();


        return this.userRepository.save(user).map(u -> {
            UserDetail userDetail = UserDetail.builder()
                    .departmentId(registerUserRequest.getDepartmentId())
                    .userId(u.getId())
                    .build();
            this.userDetailRepository.save(userDetail).subscribe();
            return "User created successfully!";
        }).onErrorReturn(
                "User already exists."
        );

    }
}
