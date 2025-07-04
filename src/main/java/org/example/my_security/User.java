package org.example.my_security;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String name;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String gitHub;

    @Column(unique = true)
    private String linkedIn;


    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(length = 1000)
    private String about;


    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_technologies", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "technologies")
    private Set<String> technology;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role.name()));
    }
}
