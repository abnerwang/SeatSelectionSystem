package com.qiyexuxu.dao.impl;

import com.qiyexuxu.common.InfoMessage;
import com.qiyexuxu.domain.User;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import static com.qiyexuxu.utils.DBUtils.close;
import static com.qiyexuxu.utils.DBUtils.getConnection;
import static com.qiyexuxu.utils.DaoUtils.*;


public class UserDaoImpl implements com.qiyexuxu.dao.UserDao {

    /**
     * 处理用户注册的方法
     *
     * @param user 需要注册的用户信息
     * @return 是否成功注册  false: 注册失败  true: 注册成功
     * @throws IllegalAccessException
     * @throws IntrospectionException
     * @throws InvocationTargetException
     */
    @Override
    public boolean addUser(User user) throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        // 将封装用户信息的 JavaBean 变成封装用户信息的 Map
        Map<String, Object> userInfoMap = bean2Map(user);

        // 将封装用户信息的 userInfoMap 存储到数据库的表中并返回是否存入成功
        boolean isSuccess = insert2DBByMap(userInfoMap, InfoMessage.STUDENT_TABLE_NAME);

        // 返回注册状态
        return isSuccess;

    }


    /**
     * 注册前需要检测用户是否已经注册
     *
     * @param studentID 检测用户是否注册依据 studentID 属性
     * @return 是否已经注册 true: 已经注册    false: 尚未注册
     */
    @Override
    public boolean findUser(String studentID) {
        // 建立数据库连接
        Connection conn = getConnection();
        // 建立查询用的 SQL 语句
        String sql = "SELECT * FROM " + InfoMessage.STUDENT_TABLE_NAME + " WHERE studentID = '" +
                studentID + "'";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return true;   // 用户已经注册过了
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {     // 关闭数据库连接
            close(conn, preparedStatement, resultSet);
        }

        return false;   // 用户尚未注册
    }

    /**
     * 处理用户登录的方法
     *
     * @param studentID 用户登录用的学号
     * @param password  用户登录用的密码
     * @return 返回登录成功的用户，若登录失败则返回 null
     */
    @Override
    public User findUser(String studentID, String password) {
        User user = null;

        // 建立数据库连接
        Connection conn = getConnection();
        // 建立查询用的 SQL 语句
        String sql = "SELECT * FROM " + InfoMessage.STUDENT_TABLE_NAME + " WHERE studentID = '" + studentID +
                "' AND password = '" + password + "'";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            // 若登录成功，则将数据库中查询到的数据封装成用户信息的 JavaBean
            ArrayList<User> users = autoBean(User.class, resultSet);
            user = users.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            close(conn, preparedStatement, resultSet);
        }

        return user;
    }
}
