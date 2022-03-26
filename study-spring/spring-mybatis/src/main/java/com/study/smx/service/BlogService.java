package com.study.smx.service;


import com.study.smx.model.Blog;

import java.util.List;

public interface BlogService {
    public List<Blog> getBlogs();
    public List<Blog> getBlogs2();
    public int deleteById(String id);
}
