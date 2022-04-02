package com.study.security.boot08security.mapper;

import com.study.security.boot08security.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    /**
     * 通过用户名获取用户信息
     *
     * @param username 用户名
     * @return User 用户信息
     */
    List<User> getUserByUsername(String username);
}