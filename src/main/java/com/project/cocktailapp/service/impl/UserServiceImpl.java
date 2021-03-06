package com.project.cocktailapp.service.impl;

import com.project.cocktailapp.exception.EntityNotFoundException;
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

            UserEntity bartender = new UserEntity();
            RoleEntity bartenderRole = roleRepository.findById((long) 2).get();
            bartender.setUsername("gosho");
            bartender.setFirstName("goshokata");
            bartender.setLastName("goshev");
            bartender.setPassword(passwordEncoder.encode("1234"));
            bartender.setEmail("goshko@gmail.com");
            bartender.setRegisterDate(LocalDateTime.now());
            bartender.setImgUrl("https:gosho-pick.com");
            bartender.setGender(Gender.MALE);
            bartender.setRoles(List.of(bartenderRole));
            userRepository.save(bartender);
            LOGGER.info("Bartender Added");
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

    @Override
    public UserEntity getUserByUsername(String username) {
       return userRepository.findByUsername(username).orElseThrow(()-> new IllegalArgumentException("no such user"));
    }

    @Override
    public List<String> getAllUsers() {
        return userRepository.finAllByUsername();
    }

    @Override
    public void changeRole(String username, RoleName roleName) {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("no such user"));
        RoleEntity roleEntity = roleRepository.findByRole(roleName).orElseThrow(() -> new EntityNotFoundException("no such role"));
        if (!userEntity.getRoles().contains(roleEntity)){
            userEntity.getRoles().add(roleEntity);
            userRepository.saveAndFlush(userEntity);
        }
    }
}
