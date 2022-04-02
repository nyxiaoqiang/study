package com.example.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("/toTest")
    public String toTestPage(){
        // classpath:/templates/test/html
        return "test";
    }
}
