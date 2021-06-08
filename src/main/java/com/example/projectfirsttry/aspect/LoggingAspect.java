package com.example.projectfirsttry.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Slf4j
@Aspect
public class LoggingAspect {
    @Pointcut("bean(*Service*)")
    private void beanPointCut(){}

    @Pointcut("within(com.example.projectfirsttry.services..*)")
    private void withinPointCut(){}

    @Pointcut("args(model)")
    private void argsPointCut(Object model){}
    @Pointcut("within(org.springframework.data.jpa.repository)")
    private void withinPointCut2(){}


    @Around("beanPointCut() && withinPointCut()  && argsPointCut(model)")
    public Object serviceLog(ProceedingJoinPoint proceedingJoinPoint, Object model) throws Throwable {
        log.info("Executing business service method with model {}", model.toString());
        Object retValue = proceedingJoinPoint.proceed();
        log.info("Executed successfully with return value {}", retValue);
        return retValue;
    }

}
