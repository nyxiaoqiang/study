package com.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    @Autowired
    public BlogService blogService;

    @RequestMapping("/getAll")
    public ArrayList<Blog> getAll(){
        ArrayList<Blog> all = (ArrayList<Blog>) blogService.getAll();
        return all;
    }
}
