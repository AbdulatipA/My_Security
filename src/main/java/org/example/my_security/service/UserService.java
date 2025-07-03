package org.example.my_security.service;

import lombok.AllArgsConstructor;
import org.example.my_security.Role;
import org.example.my_security.User;
import org.example.my_security.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User create(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Такой пользователь уже есть");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_ADMIN);
        return userRepository.save(user);
    }

    public User update(User user) {
       User userSaved = userRepository.findById(user.getId())
               .orElseThrow(() -> new RuntimeException("Такой пользователь не найден"));

        userSaved.setEmail(user.getEmail());
        userSaved.setName(user.getName());

        return userRepository.save(userSaved);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getAuthorities())
                .build();
    }
}
