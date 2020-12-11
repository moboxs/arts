package com.github.lhh.springaop;

import com.github.lhh.springaop.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

@EnableAspectJAutoProxy
@ComponentScan("com.github.lhh.*")
public class SpringApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.refresh();
        UserService userService = (UserService) annotationConfigApplicationContext.getBean("userService");

        Resource resource = new ClassPathResource("");
        try {
            InputStream inputStream = resource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }


        userService.getUser(1);
    }
}
