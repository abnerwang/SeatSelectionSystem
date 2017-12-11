package com.qiyexuxu.web.controller;

import com.google.gson.Gson;
import com.qiyexuxu.common.InfoMessage;
import com.qiyexuxu.common.RespCode;
import com.qiyexuxu.domain.Classroom;
import com.qiyexuxu.domain.DataResp;
import com.qiyexuxu.service.ClassroomService;
import com.qiyexuxu.service.impl.ClassroomServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddClassroomServlet")
public class AddClassroomServlet extends BaseServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String classroomID = request.getParameter("classroomID");
        int countOfSeats = Integer.parseInt(request.getParameter("countOfSeats"));

        Classroom classroom = new Classroom();
        classroom.setClassroomID(classroomID);
        classroom.setCountOfSeats(countOfSeats);

        // 调用 service 层的服务来新建教室
        ClassroomService classroomService = new ClassroomServiceImpl();
        try {
            boolean isSuccess = classroomService.addClassroom(classroom);
            if (isSuccess) {
                if (isAjaxRequest(request)) {
                    DataResp resp = new DataResp(RespCode.ADD_CLASSROOM_SUCCESS);
                    response.getOutputStream().write(new Gson().toJson(resp).getBytes());
                    return;
                }
                request.setAttribute("message", InfoMessage.ADD_CLASSROOM_SUCCESS);
                request.getRequestDispatcher("/WEB-INF/jsp/addClassroom.jsp").forward(request, response);
            }
        } catch (Exception e) {
            if (isAjaxRequest(request)) {
                DataResp resp = new DataResp(RespCode.ADD_CLASSROOM_FAIL);
                response.getOutputStream().write(new Gson().toJson(resp).getBytes());
                return;
            }
            request.setAttribute("message", InfoMessage.INNER_ERROR);
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        }
    }
}
