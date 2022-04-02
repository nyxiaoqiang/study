package com.study.security.boot08security.service;

import com.study.security.boot08security.entity.User;
import com.study.security.boot08security.mapper.RoleMapper;
import com.study.security.boot08security.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userDetailService")
public class UserServiceImpl implements  UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<User> users = userMapper.getUserByUsername(s);

        if (null == users || users.size() ==0) {
            throw new UsernameNotFoundException("该用户不存在！");
        }else{
            users.get(0).setRoles(roleMapper.getRolesByUserId(users.get(0).getId()));
            System.out.println("***********************"+users.get(0).getAuthorities());
            return users.get(0);
        }
    }
}


