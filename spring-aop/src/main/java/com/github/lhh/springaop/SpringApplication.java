package com.github.lhh.springaop;

import com.github.lhh.springaop.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@ComponentScan("com.github.lhh.*")
public class SpringApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.refresh();
        UserService userService = (UserService) annotationConfigApplicationContext.getBean("userService");

        userService.getUser(1);
    }
}
