package com.qiyexuxu.service.impl;

import com.qiyexuxu.dao.UserDao;
import com.qiyexuxu.dao.impl.UserDaoImpl;
import com.qiyexuxu.domain.User;
import com.qiyexuxu.exception.UserExistException;
import com.qiyexuxu.utils.Md5AndBase64AlgorithmUtil;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

public class UserServiceImpl implements com.qiyexuxu.service.UserService {
    /**
     * 向 Web 层提供用户注册服务
     *
     * @param user 要注册的用户信息
     * @return 是否注册成功  true: 注册成功  false: 注册失败
     * @throws UserExistException        用户已经注册了
     * @throws IllegalAccessException
     * @throws IntrospectionException
     * @throws InvocationTargetException
     */
    @Override
    public boolean register(User user) throws UserExistException, IllegalAccessException,
            IntrospectionException, InvocationTargetException {
        UserDao dao = new UserDaoImpl();

        // 首先判断用户是否已经注册
        String studentID = user.getStudentID();
        boolean isExist = dao.findUser(studentID);
        if (isExist) {
            throw new UserExistException();
        }
        // 对用户密码进行加密
        user.setPassword(Md5AndBase64AlgorithmUtil.genEncryptMessage(user.getPassword()));

        // 将注册结果返回
        return dao.addUser(user);
    }

    /**
     * 为 Web 层提供用户登录服务
     *
     * @param studentID 学号
     * @param password  密码
     * @return 登录成功则返回该用户，登录失败返回 null
     */
    @Override
    public User login(String studentID, String password) {
        User user = null;

        // 将用户提交的密码进行加密处理，方便和数据库中的密码配对
        String cryptographicPassword = Md5AndBase64AlgorithmUtil.genEncryptMessage(password);
        UserDao dao = new UserDaoImpl();
        user = dao.findUser(studentID, cryptographicPassword);
        return user;
    }
}
