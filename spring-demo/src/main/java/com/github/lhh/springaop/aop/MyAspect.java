package com.github.lhh.springaop.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

    @Pointcut("execution(* com.github.lhh.springaop.service.*.*(..)))")
    private void pointcut() {

    }

    @Before("com.github.lhh.springaop.aop.MyAspect.pointcut()")
    public void before() {
        System.out.println("pointcut before");
    }

    @After("com.github.lhh.springaop.aop.MyAspect.pointcut()")
    public void after() {
        System.out.println("pointcut after");
    }


}
