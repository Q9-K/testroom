package com.buaa.mapper;


import com.buaa.pojo.User;

import java.util.List;

public interface UserMapper {
    List<User> findAll();
    User findUserByID(String ID);
    User findUserByName(String name);
    //这里如果直接采用多参数传参会有bug，可能是mybatis的bug
    User findUserByUserIDAndPassword(User user);
    User findUserByUserID(String userID);
    //同上
    void addUser(User user);
}