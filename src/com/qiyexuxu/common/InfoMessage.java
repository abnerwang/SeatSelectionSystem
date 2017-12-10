package com.qiyexuxu.common;

public class InfoMessage {
    // 数据库中的表
    public static String STUDENT_TABLE_NAME = "student";
    public static String CLASSROOM_TABLE_NAME = "classroom";
    public static String SEAT_TABLE_NAME = "seat";

    // 用户表单的学号错误信息
    public static String ERROR_STUDENT_ID_NULL = "学号不能为空!";
    public static String ERROR_STUDENT_ID_LENGTH = "学号长度不应超过 10 位!";

    // 用户表单的密码错误信息
    public static String ERROR_PASSWORD_NULL = "密码不能为空！";
    public static String ERROR_PASSWORD_LENGTH = "密码长度必须为 6 到 18 位！";
    public static String ERROR_PASSWORD_FORMAT = "密码必须是 6 到 18 位数字和字母的组合!";
    public static String ERROR_PASSWORD_MATCH = "两次输入的密码不匹配！";

    // 用户注册成功、失败信息
    public static String REGISTER_SUCCESS = "用户注册成功！";
    public static String REGISTER_FAIL = "用户注册失败！";
    public static String REGISTERED = "该用户已经注册了！";

    // 新建教室成功信息
    public static String ADD_CLASSROOM_SUCCESS = "创建教室成功！";

    // 服务器出现内部错误
    public static String INNER_ERROR = "服务器出现内部错误！";

    // 用户登录成功、失败信息
    public static String LOGIN_SUCCESS = "用户登录成功！";
    public static String LOGIN_FAIL = "用户名或密码错误！";

    // 选座成功、失败消息
    public static String SELECT_SEAT_SUCCESS = "选座成功！";
    public static String SELECT_SEAT_FAIL_OCCUPY = "该座位已被占用！";
    public static String SELECT_SEAT_FAIL_RELEASE = "必须释放已选座位才能继续选座！";

    // 释放座位成功、失败消息
    public static String RELEASE_SEAT_SUCCESS = "座位释放成功！";
    public static String RELEASE_SEAT_FAIL = "座位释放失败！";
}
