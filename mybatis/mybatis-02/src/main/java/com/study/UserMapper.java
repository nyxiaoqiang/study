package com.study;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public interface UserMapper {

    @Select("select  * from user")
    @Results(id = "userMap",value={
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "name",property = "userName"),
            @Result(column = "pwd",property = "password")
    })
    public List<User> getUserList();

    @Select("select * from user")
    public List<Map<String,Object>> getUserListMap();

    @ResultMap(value = {"userMap"})
    @Select("select * from user where name like '%${name}%'")
    public List<User> selectByName(@Param("name") String name);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert(value = {"insert into user (id, name, pwd, time) values (#{id}, #{userName}, #{password}, #{time})"})
    void addUser(User user);

    @Update("update user set name = #{userName}, pwd = #{password} where id = #{id}")
    int updateUser(User user);

    @Delete("delete from user where id = #{id]}")
    int deleteUser(int id);
}
