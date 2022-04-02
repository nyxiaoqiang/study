package com.example.boot.boot05datamybatis.controller;

import com.example.boot.boot05datamybatis.entity.Student;
import com.example.boot.boot05datamybatis.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentMapper mapper;

    @RequestMapping("/student/list")
    public List<Student> getList(){
        return mapper.getStudentList();
    }
}
