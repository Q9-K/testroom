package com.buaa.pojo;

import com.buaa.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID=7981560250804078637l;
    private int id;
    private String userID;
    private String name;
    private String password;
    private String picture;
    private Status status;


    public User(int id, String name, String password,String userID) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.userID=userID;
    }

    public User(String name,String password,String userID){
        this.name=name;
        this.password=password;
        this.userID=userID;
    }

    public User(String userID,String password){
        this.userID=userID;
        this.password=password;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }




    public boolean isUserIDExisted() throws  IOException{
        InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        try{
            User result = userMapper.findUserByUserID(this.userID);
            this.name= result.name;
            return true;
        }catch (Exception e){
            return false;
        }finally {
            sqlSession.close();
        }
    }

    public boolean isUserIDAndPasswordExisted() throws IOException{
        InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        try {
            User result = userMapper.findUserByUserIDAndPassword(new User(userID,password));
            this.name=result.name;
            return true;
        }catch (Exception e){
            return false;
        }finally {
            sqlSession.close();
        }
    }

    public void register() throws IOException{
        InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        try{
            userMapper.addUser(this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
