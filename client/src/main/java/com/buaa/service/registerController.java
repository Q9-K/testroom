package com.buaa.service;

import com.buaa.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class registerController {
    private static final Logger logger = LoggerFactory.getLogger(registerController.class);
    private String name;
    private String password;

    //需要实现下面方法
    private String getName() {
        /*需要从文本框获取字段*/
    }

    private String getPassword() {
        /*需要从文本框获取字段*/
    }

    public void register() throws IOException {
        User user = new User(name,password);
        if(!user.isUserNameExisted()){
            logger.info("waiting for you for a long time, {}",name);
            /*待实现的操作*/
        }
    }

}
