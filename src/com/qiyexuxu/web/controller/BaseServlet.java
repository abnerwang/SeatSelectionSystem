package com.qiyexuxu.web.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseServlet extends HttpServlet {
    public boolean isAjaxRequest(HttpServletRequest request) {

        // jquery header 中有ajax的标识

        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));

    }

    public void setContentType (HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        if (isAjaxRequest(request)) {
            response.setContentType("application/json;charset=UTF-8");
        } else {
            response.setContentType("text/html;charset=UTF-8");
        }
    }
}
