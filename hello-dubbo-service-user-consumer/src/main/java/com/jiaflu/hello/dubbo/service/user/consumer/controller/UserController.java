package com.jiaflu.hello.dubbo.service.user.consumer.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.jiaflu.hellodubboserviceuserapi.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Reference(version = "${user.service.version}")
    private UserService userService;

    @RequestMapping(value = "hi")
    public String sayHi() {
        return userService.sayHi();
    }
}
