package ru.kafi.beautysalonapigateway.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {
    @Pointcut(value = "execution(* ru.kafi.beautysalonapigateway.controller..*.*(..))")
    public void allControllerMethods() {
    }
}
