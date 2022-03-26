package com.study.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    @ResponseBody
    //produces 可以指定响应体的返回类型和编码。
    //@RequestMapping(value = "/json1",produces = "application/json;charset=utf-8")
    @RequestMapping(value = "/json1")
    public String json1(){
        User user = new User();
        user.setAge(10);
        user.setName("李雪强");
        user.setId(000001);
        return JSONObject.toJSONString(user);
    }
}

@Data
class User{
    private int id;
    private String name;
    private int age;
}