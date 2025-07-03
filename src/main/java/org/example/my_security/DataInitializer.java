package org.example.my_security;

import lombok.AllArgsConstructor;
import org.example.my_security.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if(!userRepository.existsByUsername("admin")) {
            User user = new User();

            user.setName("Абдулатип Атаев");
            user.setEmail("https://mail.google.com/mail/u/0/#inbox");
            user.setGitHub("https://github.com/AbdulatipA");
            user.setLinkedIn("https://www.linkedin.com/in/my-security");
            user.setUsername("admin");
            user.setAbout("Тут должна быть краткая информация...");
            user.setTechnology(Set.of("Java", "Css", "Html"));
            user.setPassword(passwordEncoder.encode("11111"));
            user.setRole(Role.ROLE_ADMIN);
            userRepository.save(user);
        }
    }
}
