package com.project.cocktailapp.service.impl;

import com.project.cocktailapp.model.entity.RoleEntity;
import com.project.cocktailapp.model.entity.UserEntity;
import com.project.cocktailapp.model.entity.enums.Gender;
import com.project.cocktailapp.model.entity.enums.RoleName;
import com.project.cocktailapp.model.service.UserServiceModel;
import com.project.cocktailapp.repository.RoleRepository;
import com.project.cocktailapp.repository.UserRepository;
import com.project.cocktailapp.security.UserDetailsServiceImpl;
import com.project.cocktailapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final Logger LOGGER;
    private final ModelMapper modelmapper;
    private final UserDetailsServiceImpl userDetailsService;
    private final Logger logger;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, Logger logger, ModelMapper modelmapper, UserDetailsServiceImpl userDetailsService, Logger logger1) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        LOGGER = logger;
        this.modelmapper = modelmapper;
        this.userDetailsService = userDetailsService;
        this.logger = logger1;
    }

    @Override
    public void importAdmin() {
        if (userRepository.count() == 0) {
            UserEntity admin = new UserEntity();
            RoleEntity adminRole = roleRepository.findById((long) 1).get();
            admin.setUsername("pesho");
            admin.setFirstName("Petur");
            admin.setLastName("Panayotov");
            admin.setPassword(passwordEncoder.encode("1234"));
            admin.setEmail("peshkata@gmail.com");
            admin.setRegisterDate(LocalDateTime.now());
            admin.setImgUrl("https:pesho-pick.com");
            admin.setGender(Gender.MALE);
            admin.setRoles(List.of(adminRole));
            userRepository.save(admin);
            LOGGER.info("Admin Added");
        }
    }

    @Override
    public boolean userNameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public void register(UserServiceModel userServiceModel) {
        UserEntity userEntity = modelmapper.map(userServiceModel,UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(userServiceModel.getPassword()));

        RoleEntity roleEntity = roleRepository.findByRole(RoleName.USER).orElseThrow(() -> new IllegalArgumentException("no such role"));
        userEntity.setRoles(List.of(roleEntity));
        userEntity.setRegisterDate(LocalDateTime.now());

        userEntity = userRepository.save(userEntity);
        logger.info(String.format("User %s Registered",userEntity.getUsername()));

        UserDetails principal = userDetailsService.loadUserByUsername(userEntity.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                userEntity.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
