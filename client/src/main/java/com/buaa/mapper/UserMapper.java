package com.buaa.mapper;


import com.buaa.pojo.User;

import java.util.List;

public interface UserMapper {
    List<User> findAll();
    User findUserByID(String ID);
    User findUserByName(String name);
    User findUserByNameAndPassword(String name, String password);
    void addUser(String name, String password);
}