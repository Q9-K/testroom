package com.buaa.service;

import com.buaa.mapper.UserMapper;
import com.buaa.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class loginController {
    private static final Logger logger = LoggerFactory.getLogger(loginController.class);
    private String name;
    private String password;

    //需要实现下面方法
    private String getName() {
        /*需要从文本框获取字段*/
    }

    private String getPassword() {
        /*需要从文本框获取字段*/
    }

    public void login() throws IOException{
        User user = new User(name,password);
        if(user.isUserNameAndPasswordExisted()){
            logger.info("welcome, {}",name);
            /*待实现的操作*/
        }
    }
}
