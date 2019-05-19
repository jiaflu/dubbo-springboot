package com.ljf.bootuserserviceprovider.service.impl;

import com.ljf.bootuserserviceprovider.service.UserService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import java.util.List;

//@Service
@Component
public class UserServiceImpl implements UserService {

    @Override
    public List<Integer> getUserAddressList(String userId) {
        // TODO Auto-generated method stub
        return null;
    }

}
