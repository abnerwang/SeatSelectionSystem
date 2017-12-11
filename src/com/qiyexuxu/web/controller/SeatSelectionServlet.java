package com.qiyexuxu.web.controller;

import com.google.gson.Gson;
import com.qiyexuxu.common.InfoMessage;
import com.qiyexuxu.common.RespCode;
import com.qiyexuxu.domain.DataResp;
import com.qiyexuxu.domain.Seat;
import com.qiyexuxu.exception.SeatOccupiedException;
import com.qiyexuxu.exception.SeatSelectedException;
import com.qiyexuxu.service.SeatService;
import com.qiyexuxu.service.impl.SeatServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SeatSelectionServlet")
public class SeatSelectionServlet extends BaseServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setContentType(request, response);

        String studentID = request.getParameter("studentID");
        String classroomID = request.getParameter("classroomID");
        int seatRow = Integer.parseInt(request.getParameter("seatRow"));
        int seatColumn = Integer.parseInt(request.getParameter("seatColumn"));

        // 调用 service 层的服务进行选座
        SeatService seatService = new SeatServiceImpl();
        try {
            Seat seat = seatService.select(studentID, classroomID, seatRow, seatColumn);
            if (seat != null) {
                if (isAjaxRequest(request)) {
                    DataResp resp = new DataResp(RespCode.SELECT_SEAT_SUCCESS, seat);
                    response.getOutputStream().write(new Gson().toJson(resp).getBytes("utf-8"));
                    return;
                }
                request.setAttribute("message", InfoMessage.SELECT_SEAT_SUCCESS);
                request.getRequestDispatcher("/message.jsp").forward(request, response);
                return;
            } else {
                DataResp resp = new DataResp(RespCode.RELEASE_SEAT_FAIL);
                response.getOutputStream().write(new Gson().toJson(resp).getBytes("utf-8"));
            }
        } catch (SeatOccupiedException e) {
            if (isAjaxRequest(request)) {
                DataResp resp = new DataResp(RespCode.SELECT_SEAT_FAIL_OCCUPY);
                response.getOutputStream().write(new Gson().toJson(resp).getBytes("utf-8"));
                return;
            }
            request.setAttribute("message", InfoMessage.SELECT_SEAT_FAIL_OCCUPY);
            request.getRequestDispatcher("/WEB-INF/jsp/seatSelection.jsp").forward(request, response);
        } catch (SeatSelectedException e) {
            if (isAjaxRequest(request)) {
                DataResp resp = new DataResp(RespCode.SELECT_SEAT_FAIL_RELEASE);
                response.getOutputStream().write(new Gson().toJson(resp).getBytes("utf-8"));
                return;
            }
            request.setAttribute("message", InfoMessage.SELECT_SEAT_FAIL_RELEASE);
            request.getRequestDispatcher("/WEB-INF/jsp/seatSelection.jsp").forward(request, response);
        } catch (Exception e) {
            DataResp resp = new DataResp(RespCode.EXCEPTION);
            response.getOutputStream().write(new Gson().toJson(resp).getBytes("utf-8"));
        }
    }
}
