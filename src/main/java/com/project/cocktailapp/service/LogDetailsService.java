package com.project.cocktailapp.service;

import org.springframework.stereotype.Service;

@Service
public interface LogDetailsService {
    void createLog(Long id, String action);
}
