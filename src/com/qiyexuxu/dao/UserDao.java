package com.qiyexuxu.dao;

import com.qiyexuxu.domain.User;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

/**
 * 为 service 层操作数据库提供的三个接口
 * addUser: 向数据库中添加用户，实现注册
 * findUser(String studentID): 注册之前检测用户是否已经注册了
 * findUser(String studentID, String password): 向数据库中查询用户，处理用户登录
 */
public interface UserDao {
    boolean addUser(User user) throws IllegalAccessException, IntrospectionException, InvocationTargetException;

    boolean findUser(String studentID);

    User findUser(String studentID, String password);
}
