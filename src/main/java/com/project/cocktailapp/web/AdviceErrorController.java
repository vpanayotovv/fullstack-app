package com.project.cocktailapp.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceErrorController {

    @ExceptionHandler(RuntimeException.class)
    public String errorHandle() {
        return "error";
    }
}
