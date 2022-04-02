package com.study.security.boot08security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vip")
public class VipController {

    @RequestMapping("vip0/hello")
    public String vip0hello(){
        return "hello vip0";
    }

    @RequestMapping("vip1/hello")
    public String vip1hello(){
        return "hello vip1";
    }
}
