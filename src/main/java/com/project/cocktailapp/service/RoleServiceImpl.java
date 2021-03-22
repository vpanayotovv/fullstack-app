package com.project.cocktailapp.service;

import com.project.cocktailapp.model.RoleEntity;
import com.project.cocktailapp.model.enums.RoleName;
import com.project.cocktailapp.repository.RoleRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final Logger LOGGER;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, Logger logger) {
        this.roleRepository = roleRepository;
        LOGGER = logger;
    }

    @Override
    public void importRoles() {
        if (this.roleRepository.count() == 0){
            saveRole(RoleName.ADMIN, "Added Role Admin");

            saveRole(RoleName.BARTENDER, "Added Role Bartender");

            saveRole(RoleName.USER, "Added Role User");
        }
    }

    private void saveRole(RoleName admin2, String message) {
        RoleEntity role = new RoleEntity();
        role.setRole(admin2);
        roleRepository.save(role);
        LOGGER.info(message);
    }
}
