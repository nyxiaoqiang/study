package com.study.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mycontroller")
public class AnnoController {
    @RequestMapping("/hello")
    public String hello(Model model){
        model.addAttribute("msg","hello springmvc anno");
        return "hello";
    }
}
