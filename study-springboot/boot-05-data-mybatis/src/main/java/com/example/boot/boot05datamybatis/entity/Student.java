package com.example.boot.boot05datamybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private Integer id;
    private String name;
    private int age;
    private int grade;
    private String gender;
    private int height;
    private int classid;
}
