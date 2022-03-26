package com.study;

import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public interface UserDao {
    //查询所有用户
    public List<User> getUserList();

    public List<Map<String,Object>> getUserListMap();

    public List<Map<String,Object>> selectByName(String name);

    //根据id 查找用户
    public User getUserById(int id);

    //新增用户
    void addUser(User user);
    void addUserMap(Map<String,Object> user);

    //更新用户
    int updateUser(User user);

    //删除用户
    int deleteUser(int id);
}
