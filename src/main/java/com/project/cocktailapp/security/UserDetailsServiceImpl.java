package com.project.cocktailapp.security;

import com.project.cocktailapp.model.UserEntity;
import com.project.cocktailapp.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository
                .findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Invalid Username!"));
        return mapToUserDetails(userEntity);
    }

    private UserDetails mapToUserDetails(UserEntity userEntity) {

        List<SimpleGrantedAuthority> roles = userEntity.getRoles()
                .stream()
                .map(userRole -> new SimpleGrantedAuthority("ROLE_" + userRole.getRole().name()))
                .collect(Collectors.toList());

        return new User(
            userEntity.getUsername(),userEntity.getPassword(),roles
        );
    }
}
