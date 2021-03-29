package com.project.cocktailapp.aop;

import com.project.cocktailapp.service.LogDetailsService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    private final LogDetailsService logDetailsService;

    public LogAspect(LogDetailsService logDetailsService) {
        this.logDetailsService = logDetailsService;
    }

    @Pointcut("execution(* com.project.cocktailapp.web.CocktailController.details(..))")
    public void trackDetails(){};

    @After("trackDetails()")
    public void afterAdvice(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        String action = joinPoint.getSignature().getName();
        logDetailsService.createLog((Long) args[0], action);
    };

}
