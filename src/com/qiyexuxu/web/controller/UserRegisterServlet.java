package com.qiyexuxu.web.controller;

import com.qiyexuxu.common.InfoMessage;
import com.qiyexuxu.domain.User;
import com.qiyexuxu.exception.UserExistException;
import com.qiyexuxu.service.UserService;
import com.qiyexuxu.service.impl.UserServiceImpl;
import com.qiyexuxu.utils.WebUtils;
import com.qiyexuxu.web.formbean.RegisterForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 将用户请求封装为相应的 JavaBean 并返回
        RegisterForm registerForm = WebUtils.request2Bean(request, RegisterForm.class);

        // 对用户提交信息进行合法性校验，若校验失败，则回显失败信息
        if (!registerForm.isValid()) {
            request.setAttribute("registerForm", registerForm);
            request.getRequestDispatcher("/WEB-INF/jsp/userRegister.jsp").forward(request, response);
            return;
        }

        // 将表单中的信息封装到 User 对象中
        User user = new User();
        user.setStudentID(registerForm.getStudentID());
        user.setUsername(registerForm.getUsername());
        user.setPassword(registerForm.getPassword());

        // 校验成功，则调用 service 层处理注册请求
        UserService service = new UserServiceImpl();
        try {
            boolean isSuccess = service.register(user);
            if (isSuccess) {
                request.setAttribute("message", InfoMessage.REGISTER_SUCCESS);
                request.getRequestDispatcher("/message.jsp").forward(request, response);
            } else {
                request.setAttribute("message", InfoMessage.REGISTER_FAIL);
                request.getRequestDispatcher("/message.jsp").forward(request, response);
                return;
            }
        } catch (UserExistException e) {
            request.setAttribute("message", InfoMessage.REGISTERED);
            request.getRequestDispatcher("/WEB-INF/jsp/userRegister.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("message", InfoMessage.INNER_ERROR);
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        }

    }
}
