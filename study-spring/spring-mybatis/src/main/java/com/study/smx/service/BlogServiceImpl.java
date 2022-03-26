package com.study.smx.service;

import com.study.smx.mapper.BlogMapper;
import com.study.smx.model.Blog;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public class BlogServiceImpl implements BlogService{

    private BlogMapper mapper;

    private SqlSessionTemplate sessionTemplate;

    //通过setter方法注入
    public void setMapper(BlogMapper mapper) {
        this.mapper = mapper;
    }

    public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
        this.sessionTemplate = sessionTemplate;
    }

    public List<Blog> getBlogs() {
        return mapper.getAll();
    }

    public List<Blog> getBlogs2() {
        BlogMapper mapper = sessionTemplate.getMapper(BlogMapper.class);
        return mapper.getAll();
    }

    public int deleteById(String id) {
        return mapper.deleteById(id);
    }
}
