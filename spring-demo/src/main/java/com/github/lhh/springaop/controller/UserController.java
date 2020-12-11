package com.github.lhh.springaop.controller;

import com.github.lhh.springaop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/login/{id}")
    public String login(@PathVariable int id) {
        String user = userService.getUser(id);
        return user;
    }
}
