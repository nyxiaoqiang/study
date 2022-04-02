package com.study.swagger.boot07swagger.controller;

import com.study.swagger.boot07swagger.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("我的接口")
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello swagger";
    }

    @ApiOperation("获取玩家信息接口说明")
    @PostMapping("/getUser")
    public User getUser(){
        return new User("李雪强","123456");
    }
}
