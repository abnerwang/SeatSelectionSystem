package com.qiyexuxu.web.controller;

import com.google.gson.Gson;
import com.qiyexuxu.common.InfoMessage;
import com.qiyexuxu.domain.DataResp;
import com.qiyexuxu.common.RespCode;
import com.qiyexuxu.domain.User;
import com.qiyexuxu.exception.UserExistException;
import com.qiyexuxu.service.UserService;
import com.qiyexuxu.service.impl.UserServiceImpl;
import com.qiyexuxu.utils.WebUtils;
import com.qiyexuxu.web.formbean.RegisterForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserRegisterServlet")
public class UserRegisterServlet extends BaseServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setContentType(request, response);

        // 将用户请求封装为相应的 JavaBean 并返回
        RegisterForm registerForm = WebUtils.request2Bean(request, RegisterForm.class);

        // 对用户提交信息进行合法性校验，若校验失败，则回显失败信息
        if (!registerForm.isValid()) {
            if (isAjaxRequest(request)) {
                DataResp resp = new DataResp(RespCode.REGISTER_FAIL, registerForm.getErrorMap());
                response.getOutputStream().write(new Gson().toJson(resp).getBytes("utf-8"));
                return;
            }
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
                if (isAjaxRequest(request)) {
                    DataResp<User> resp = new DataResp<>(RespCode.REGISTER_SUCCESS, user);
                    response.getOutputStream().write(new Gson().toJson(resp).getBytes("utf-8"));
                    return;
                }
                request.setAttribute("message", InfoMessage.REGISTER_SUCCESS);
                request.getRequestDispatcher("/message.jsp").forward(request, response);
            } else {
                if (isAjaxRequest(request)) {
                    DataResp resp = new DataResp(RespCode.LOGIN_FAIL);
                    response.getOutputStream().write(new Gson().toJson(resp).getBytes("utf-8"));
                    return;
                }
                request.setAttribute("message", InfoMessage.REGISTER_FAIL);
                request.getRequestDispatcher("/message.jsp").forward(request, response);
                return;
            }
        } catch (UserExistException e) {
            if (isAjaxRequest(request)) {
                DataResp resp = new DataResp(RespCode.REGISTERED);
                response.getOutputStream().write(new Gson().toJson(resp).getBytes("utf-8"));
                return;
            }
            request.setAttribute("message", InfoMessage.REGISTERED);
            request.getRequestDispatcher("/WEB-INF/jsp/userRegister.jsp").forward(request, response);
        } catch (Exception e) {
            if (isAjaxRequest(request)) {
                DataResp resp = new DataResp(RespCode.EXCEPTION);
                response.getOutputStream().write(new Gson().toJson(resp).getBytes("utf-8"));
                return;
            }
            request.setAttribute("message", InfoMessage.INNER_ERROR);
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        }

    }
}
