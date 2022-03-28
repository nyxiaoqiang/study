package com.study.boot.boot01hello;

import com.example.boot02mystarterautoconfigure.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloMyStarter {

    @Autowired
    HelloService helloService;

    @RequestMapping("/test")
    public String testMyStarter(){
        return helloService.sayHello();
    }
}
