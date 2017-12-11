package com.qiyexuxu.service;

import com.qiyexuxu.domain.User;
import com.qiyexuxu.exception.UserExistException;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

/**
 * 用户注册和登录接口
 * register(User user)： 用户注册的接口
 * login(String studentID, String password)： 用户登录接口
 */
public interface UserService {
    boolean register(User user) throws UserExistException, IllegalAccessException,
            IntrospectionException, InvocationTargetException;

    User login(String studentID, String password);
}
