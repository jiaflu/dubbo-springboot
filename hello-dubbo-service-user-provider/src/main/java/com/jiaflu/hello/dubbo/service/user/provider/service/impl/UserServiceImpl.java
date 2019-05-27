package com.jiaflu.hello.dubbo.service.user.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jiaflu.hellodubboserviceuserapi.service.UserService;



@Service(version = "${user.service.version}")
public class UserServiceImpl implements UserService {
    @Override
    public String sayHi() {
        return "hello Dubbo.";
    }
}
