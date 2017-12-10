package com.qiyexuxu.utils;


import com.qiyexuxu.dao.impl.UserDaoImpl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 数据库的连接和关闭操作
 */
public class DBUtils {
    private static Properties dbConfig = new Properties();
    private static String drive = null;
    private static String username = null;
    private static String password = null;
    private static String url = null;

    static {
        InputStream inStream = UserDaoImpl.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            dbConfig.load(inStream);
            drive = dbConfig.getProperty("drive");   // 得到数据库的驱动
            username = dbConfig.getProperty("username");   // 得到数据库的用户名
            password = dbConfig.getProperty("password");   // 得到数据库的密码
            url = dbConfig.getProperty("url");   // 访问数据库的 url

            // 加载数据库的驱动
            Class.forName(drive);
        } catch (IOException | ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * 建立并返回数据库的连接
     *
     * @return 返回数据库的连接
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new ExceptionInInitializerError(e);
        }
        return conn;
    }

    /**
     * 关闭数据库的连接
     *
     * @param conn 数据库的连接
     * @param ps   数据库查询语句
     * @param rs   数据库查询结果
     */
    public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭与数据库的连接
     *
     * @param conn 数据库的连接
     * @param ps   数据库查询语句
     */
    public static void close(Connection conn, PreparedStatement ps) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

