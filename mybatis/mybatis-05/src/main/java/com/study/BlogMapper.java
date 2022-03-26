package com.study;

import java.util.List;
import java.util.Map;

public interface BlogMapper {
    boolean addBlog(Blog blog);
    List<Blog> selectByIf(Map<String,Object> params);
    List<Blog> selectByParams(Map<String,Object> params);
    List<Blog> testSql(Map<String,Object> params);
    List<Blog> queeryByIds(Map<String, List<String>> params);
}
