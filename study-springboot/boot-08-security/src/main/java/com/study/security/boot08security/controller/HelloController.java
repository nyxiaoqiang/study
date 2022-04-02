package com.study.security.boot08security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.plugin.liveconnect.SecurityContextHelper;

@RestController
public class HelloController{
    @GetMapping("/hello")
    public String hello(){
        return "Hello,it's safe";
    }

    @GetMapping("admin/hello")
    public String adminHello(){
        return "Hello,admin is safe";
    }

    @GetMapping("user/hello")
    public String userHello(){
        return "Hello,user is safe";
    }

    @GetMapping("/isLogin")
    public Object getUserInfo(){
        if(!"anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getName())){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            System.out.println(authentication);
            // [Principal=User(id=5, userName=懒羊羊, passWord=$2a$10$RMuFXGQ5AtH4wOvkUqyvuecpqUSeoxZYqilXzbz50dceRsga.WYiq,
            // roles=[Role(id=1, name=ROLE_vip0)]), Credentials=[PROTECTE
            return "以及登录";
        }else {
            return "请先登录";
        }
    }
}
