package com.study.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestRestController {
    @RequestMapping("json2")
    public User json2(){
        User user = new User();
        user.setId(2);
        user.setAge(20);
        user.setName("李雪强2");
        return user;
    }

    @RequestMapping("json3")
    public Map<String, Object> json3(){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("id",3);
        map.put("age",20);
        map.put("name","李雪强3");
        return map;
    }

    @RequestMapping("json4")
    public String json4(){
        return "\"id\":3,\"name\":\"李雪强3\",\"age\":20}";
    }
}
