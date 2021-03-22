package com.project.cocktailapp.service;

import com.project.cocktailapp.model.entity.RoleEntity;
import com.project.cocktailapp.model.entity.UserEntity;
import com.project.cocktailapp.model.entity.enums.Gender;
import com.project.cocktailapp.repository.RoleRepository;
import com.project.cocktailapp.repository.UserRepository;
import org.slf4j.Logger;
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

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, Logger logger) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        LOGGER = logger;
    }

    @Override
    public void importAdmin() {
        if (userRepository.count() == 0) {
            UserEntity admin = new UserEntity();
            RoleEntity adminRole = roleRepository.findById((long) 1).get();
            admin.setUsername("pesho");
            admin.setFirstName("Petur");
            admin.setLastName("Panayotov");
            admin.setPassword(this.passwordEncoder.encode("1234"));
            admin.setEmail("peshkata@gmail.com");
            admin.setRegisterDate(LocalDateTime.now());
            admin.setImgUrl("https:pesho-pick.com");
            admin.setGender(Gender.MALE);
            admin.setRoles(List.of(adminRole));
            userRepository.save(admin);
            LOGGER.info("Admin Added");
        }
    }
}
