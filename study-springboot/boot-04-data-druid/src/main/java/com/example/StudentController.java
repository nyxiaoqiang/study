package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController()
public class StudentController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/list")
    public List<Map<String, Object>> userList(){
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from tbl_middle_student");
        return maps;
    }

    @RequestMapping("/add")
    public String addUser(){
        int update = jdbcTemplate.update("insert into tbl_middle_student(name, age, grade, gender, height, classid) values ('lxq',12,3,'male','175',1)");
        return "insert "+ update;
    }
}
