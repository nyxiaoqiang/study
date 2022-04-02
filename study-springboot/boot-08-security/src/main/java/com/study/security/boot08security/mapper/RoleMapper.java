package com.study.security.boot08security.mapper;

import com.study.security.boot08security.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoleMapper {
    List<Role> getRolesByUserId(Integer userId);
}
