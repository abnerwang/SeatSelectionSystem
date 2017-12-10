package com.qiyexuxu.web.controller;

import com.qiyexuxu.common.InfoMessage;
import com.qiyexuxu.domain.Classroom;
import com.qiyexuxu.service.ClassroomService;
import com.qiyexuxu.service.impl.ClassroomServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddClassroomServlet")
public class AddClassroomServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

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
                request.setAttribute("message", InfoMessage.ADD_CLASSROOM_SUCCESS);
                request.getRequestDispatcher("/WEB-INF/jsp/addClassroom.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("message", InfoMessage.INNER_ERROR);
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        }
    }
}
