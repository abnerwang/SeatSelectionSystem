package com.qiyexuxu.web.controller;

import com.qiyexuxu.common.InfoMessage;
import com.qiyexuxu.exception.SeatOccupiedException;
import com.qiyexuxu.exception.SeatSelectedException;
import com.qiyexuxu.service.SeatService;
import com.qiyexuxu.service.impl.SeatServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SeatSelectionServlet")
public class SeatSelectionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String studentID = request.getParameter("studentID");
        String classroomID = request.getParameter("classroomID");
        int seatRow = Integer.parseInt(request.getParameter("seatRow"));
        int seatColumn = Integer.parseInt(request.getParameter("seatColumn"));

        // 调用 service 层的服务进行选座
        SeatService seatService = new SeatServiceImpl();
        try {
            boolean isSuccess = seatService.select(studentID, classroomID, seatRow, seatColumn);
            if (isSuccess) {
                request.setAttribute("message", InfoMessage.SELECT_SEAT_SUCCESS);
                request.getRequestDispatcher("/message.jsp").forward(request, response);
            }
        } catch (SeatOccupiedException e) {
            request.setAttribute("message", InfoMessage.SELECT_SEAT_FAIL_OCCUPY);
            request.getRequestDispatcher("/WEB-INF/jsp/seatSelection.jsp").forward(request, response);
        } catch (SeatSelectedException e) {
            request.setAttribute("message", InfoMessage.SELECT_SEAT_FAIL_RELEASE);
            request.getRequestDispatcher("/WEB-INF/jsp/seatSelection.jsp").forward(request, response);
        }
    }
}
