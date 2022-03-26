package com.study.smn.service;


import com.study.smn.model.Blog;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BlogService {

    @Transactional(readOnly = true)
    public List<Blog> getBlogs();

    @Transactional(readOnly = true)
    public List<Blog> getBlogs2();

    @Transactional
    public int deleteById(String id);
}
