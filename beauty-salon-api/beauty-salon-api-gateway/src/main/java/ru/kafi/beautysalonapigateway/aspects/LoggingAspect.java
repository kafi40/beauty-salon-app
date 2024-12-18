package ru.kafi.beautysalonapigateway.aspects;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Before("ru.kafi.beautysalonapigateway.aspects.Pointcuts.allControllerMethods()")
    public void beforeControllerMethods(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = methodSignature.getMethod().getName();
        String path = "";
        for (Object o : joinPoint.getArgs()) {
            if (o instanceof HttpServletRequest) {
                path = ((HttpServletRequest) o).getRequestURI();
            }
        }
        log.info("API gateway ({}): Try {} by path={}", className, methodName, path);
    }
}
