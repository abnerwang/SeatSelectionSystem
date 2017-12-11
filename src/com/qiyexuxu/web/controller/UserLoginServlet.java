package com.qiyexuxu.web.controller;

import com.google.gson.Gson;
import com.qiyexuxu.common.InfoMessage;
import com.qiyexuxu.domain.DataResp;
import com.qiyexuxu.common.RespCode;
import com.qiyexuxu.domain.User;
import com.qiyexuxu.service.UserService;
import com.qiyexuxu.service.impl.UserServiceImpl;
import com.qiyexuxu.utils.WebUtils;
import com.qiyexuxu.web.formbean.LoginForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserLoginServlet")
public class UserLoginServlet extends BaseServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setContentType(request, response);

        LoginForm loginForm = WebUtils.request2Bean(request, LoginForm.class);

        // 对登录表单进行校验，校验失败则回显消息（校验学号）
        if (!loginForm.isValid()) {
            if (isAjaxRequest(request)) {
                DataResp resp = new DataResp(RespCode.LOGIN_FAIL, loginForm.getErrorMap());
                response.getOutputStream().write(new Gson().toJson(resp).getBytes("utf-8"));
                return;
            }
            request.setAttribute("loginForm", loginForm);
            request.setAttribute("message", InfoMessage.LOGIN_FAIL);
            request.getRequestDispatcher("/WEB-INF/jsp/userLogin.jsp").forward(request, response);
            return;
        }

        // 校验成功则调用 service 层的服务进行登录
        UserService service = new UserServiceImpl();
        User user = service.login(loginForm.getStudentID(), loginForm.getPassword());
        if (user == null) {
            if (isAjaxRequest(request)) {
                DataResp resp = new DataResp(RespCode.LOGIN_FAIL);
                response.getOutputStream().write(new Gson().toJson(resp).getBytes("utf-8"));
                return;
            }
            request.setAttribute("message", InfoMessage.LOGIN_FAIL);
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        } else {
            if (isAjaxRequest(request)) {
                DataResp<User> resp = new DataResp(RespCode.LOGIN_SUCCESS, user);
                response.getOutputStream().write(new Gson().toJson(resp).getBytes("utf-8"));
                return;
            }
            request.setAttribute("message", InfoMessage.LOGIN_SUCCESS);
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        }
    }
}
