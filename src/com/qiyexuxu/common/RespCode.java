package com.qiyexuxu.common;

import com.qiyexuxu.domain.RetMsg;

public class RespCode {

    // 用户表单的学号错误信息
//    public final static RetMsg ERROR_STUDENT_ID_NULL = new RetMsg("1001", "学号不能为空!");
//    public final static RetMsg ERROR_STUDENT_ID_LENGTH = new RetMsg("1001", "学号长度不应超过 10 位!");

    // 用户表单的密码错误信息
//    public final static RetMsg ERROR_PASSWORD_NULL = new RetMsg("1001", "密码不能为空！");
//    public final static RetMsg ERROR_PASSWORD_LENGTH = new RetMsg("1001", "密码长度必须为 6 到 18 位！");
//    public final static RetMsg ERROR_PASSWORD_FORMAT = new RetMsg("1001", "密码必须是 6 到 18 位数字和字母的组合!");
//    public final static RetMsg ERROR_PASSWORD_MATCH = new RetMsg("1001", "两次输入的密码不匹配！");

    // 用户注册成功、失败信息
    public final static RetMsg REGISTER_SUCCESS = new RetMsg("0000", "用户注册成功！");
    public final static RetMsg REGISTER_FAIL = new RetMsg("9990", "用户注册失败！");
    public final static RetMsg REGISTERED = new RetMsg("9990", "该用户已经注册了！");

    // 新建教室成功信息
    public final static RetMsg ADD_CLASSROOM_SUCCESS = new RetMsg("0000", "创建教室成功！");
    public final static RetMsg ADD_CLASSROOM_FAIL = new RetMsg("9990", "创建教室失败！");

    // 选择教室信息成功
    public final static RetMsg SELECT_CLASSROOM_SUCCESS = new RetMsg("0000", "教室信息回显成功！");

    // 服务器出现内部错误
    public final static RetMsg EXCEPTION = new RetMsg("9999", "系统异常");

    // 用户登录成功、失败信息
    public final static RetMsg LOGIN_SUCCESS = new RetMsg("0000", "用户登录成功！");
    public final static RetMsg LOGIN_FAIL = new RetMsg("9990", "学号或密码错误！");

    // 选座成功、失败消息
    public final static RetMsg SELECT_SEAT_SUCCESS = new RetMsg("0000", "选座成功！");
    public final static RetMsg SELECT_SEAT_FAIL_OCCUPY = new RetMsg("9990", "该座位已被占用！");
    public final static RetMsg SELECT_SEAT_FAIL_RELEASE = new RetMsg("9990", "必须释放已选座位才能继续选座！");

    // 释放座位成功、失败消息
    public final static RetMsg RELEASE_SEAT_SUCCESS = new RetMsg("0000", "座位释放成功！");
    public final static RetMsg RELEASE_SEAT_FAIL = new RetMsg("9990", "座位释放失败！");

}
