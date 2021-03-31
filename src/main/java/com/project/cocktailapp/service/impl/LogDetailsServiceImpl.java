package com.project.cocktailapp.service.impl;

import com.project.cocktailapp.model.entity.CocktailEntity;
import com.project.cocktailapp.model.entity.LogDetailEntity;
import com.project.cocktailapp.model.entity.UserEntity;
import com.project.cocktailapp.repository.LogDetailsRepository;
import com.project.cocktailapp.service.CocktailService;
import com.project.cocktailapp.service.LogDetailsService;
import com.project.cocktailapp.service.UserService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LogDetailsServiceImpl implements LogDetailsService {

    private final LogDetailsRepository logDetailsRepository;
    private final UserService userService;
    private final CocktailService cocktailService;

    public LogDetailsServiceImpl(LogDetailsRepository logDetailsRepository, UserService userService, CocktailService cocktailService) {
        this.logDetailsRepository = logDetailsRepository;
        this.userService = userService;
        this.cocktailService = cocktailService;
    }

    @Override
    public void createLog(Long id, String action) {
        CocktailEntity cocktailEntity = cocktailService.getById(id);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity = userService.getUserByUsername(authentication.getName());

        LogDetailEntity logDetailEntity = new LogDetailEntity();
        logDetailEntity.setUserEntity(userEntity);
        logDetailEntity.setAction(action);
        logDetailEntity.setCocktailEntity(cocktailEntity);
        logDetailEntity.setOnTime(LocalDateTime.now());

        logDetailsRepository.save(logDetailEntity);
    }

    @Scheduled(cron = "${scheduling.work-cron}")
    public void cleanLogDetailsTable(){
        logDetailsRepository.deleteAll();
    }
}
