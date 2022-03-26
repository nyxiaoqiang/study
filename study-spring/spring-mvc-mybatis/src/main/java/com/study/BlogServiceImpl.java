package com.study;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

public class BlogServiceImpl implements BlogService{

    public BlogMapper mapper;

    public SqlSessionTemplate sessionTemplate;

    public void setMapper(BlogMapper mapper) {
        this.mapper = mapper;
    }

    public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
        this.sessionTemplate = sessionTemplate;
    }

    public List<Blog> getAll() {
        return mapper.getAll();
    }
}
