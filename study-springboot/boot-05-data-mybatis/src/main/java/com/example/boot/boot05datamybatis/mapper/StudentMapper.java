package com.example.boot.boot05datamybatis.mapper;

import com.example.boot.boot05datamybatis.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper {
    List<Student> getStudentList();
}
