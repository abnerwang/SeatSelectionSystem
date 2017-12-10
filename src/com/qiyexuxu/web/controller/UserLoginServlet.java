package com.qiyexuxu.web.controller;

import com.qiyexuxu.common.InfoMessage;
import com.qiyexuxu.domain.User;
import com.qiyexuxu.service.UserService;
import com.qiyexuxu.service.impl.UserServiceImpl;
import com.qiyexuxu.utils.WebUtils;
import com.qiyexuxu.web.formbean.LoginForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        LoginForm loginForm = WebUtils.request2Bean(request, LoginForm.class);

        // 对登录表单进行校验，校验失败则回显消息（校验学号）
        if (!loginForm.isValid()) {
            request.setAttribute("loginForm", loginForm);
            request.getRequestDispatcher("/WEB-INF/jsp/userLogin.jsp").forward(request, response);
        }

        // 校验成功则调用 service 层的服务进行登录
        UserService service = new UserServiceImpl();
        User user = service.login(loginForm.getStudentID(), loginForm.getPassword());
        if (user == null) {
            request.setAttribute("message", InfoMessage.LOGIN_FAIL);
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        } else {
            request.setAttribute("message", InfoMessage.LOGIN_SUCCESS);
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        }
    }
}
