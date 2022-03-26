package com.study.smn.mapper;

import com.study.smn.model.Blog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BlogMapper {

    @Select("select * from blog")
    List<Blog> getAll();

    @Delete("delete from blog where id = #{id}")
    int deleteById(String id);
}
