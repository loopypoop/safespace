package kz.iitu.gateway.service;

import kz.iitu.gateway.entity.Role;
import kz.iitu.gateway.entity.User;
import kz.iitu.gateway.repository.RoleRepository;
import kz.iitu.gateway.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findById(2L).get());
        user.setRoles(roles);
        return userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User: " + username + " not found!");
        }
//        return user;

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getAuthorities().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        });

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);

    }
}
