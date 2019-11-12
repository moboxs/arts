package com.github.lhh.springaop.service;

import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {

    public String getUser(int id) {
        return id == 1 ? "liuhonghao" : "";
    }
}
