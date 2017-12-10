package com.qiyexuxu.web.controller;

import com.qiyexuxu.common.InfoMessage;
import com.qiyexuxu.domain.Seat;
import com.qiyexuxu.service.ClassroomService;
import com.qiyexuxu.service.impl.ClassroomServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ClassroomSelectionServlet")
public class ClassroomSelectionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 从用户请求中拿到教室号
        String classroomID = request.getParameter("classroomID");

        // 利用 service 提供的服务返回教室信息
        ClassroomService service = new ClassroomServiceImpl();
        try {
            // 得到该教室中被占用的座位组成的数组
            ArrayList<Seat> occupiedSeats = service.returnOccupied(classroomID);
            Seat[] occupiedArray = new Seat[occupiedSeats.size()];
            occupiedArray = occupiedSeats.toArray(occupiedArray);

            // 得到该教室中的座位总数
            int sumOfSeats = service.sumOfSeats(classroomID);

            // 得到该教室中的剩余座位数
            int restOfSeats = sumOfSeats - occupiedSeats.size();

            request.setAttribute("occupiedArray", occupiedArray);
            request.setAttribute("sumOfSeats", sumOfSeats);
            request.setAttribute("restOfSeats", restOfSeats);
            request.getRequestDispatcher("/classroomMessage.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("message", InfoMessage.INNER_ERROR);
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        }
    }
}
