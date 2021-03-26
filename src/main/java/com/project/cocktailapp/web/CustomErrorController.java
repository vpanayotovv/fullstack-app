package com.project.cocktailapp.web;
import com.project.cocktailapp.exception.EntityNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CustomErrorController{

    @ExceptionHandler(EntityNotFoundException.class)
    public ModelAndView handleEntityNotFoundException(EntityNotFoundException ex){
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("message",ex.getMessage());
        return mav;
    }
}
