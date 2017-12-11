package com.qiyexuxu.web.controller;

import com.google.gson.Gson;
import com.qiyexuxu.common.InfoMessage;
import com.qiyexuxu.domain.DataResp;
import com.qiyexuxu.common.RespCode;
import com.qiyexuxu.service.SeatService;
import com.qiyexuxu.service.impl.SeatServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ReleaseSeatServlet")
public class ReleaseSeatServlet extends BaseServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        setContentType(request, response);

        String classroomID = request.getParameter("classroomID");
        int seatRow = Integer.parseInt(request.getParameter("seatRow"));
        int seatColumn = Integer.parseInt(request.getParameter("seatColumn"));

        // 调用 service 层的服务处理释放座位请求
        SeatService service = new SeatServiceImpl();
        boolean isSuccess = service.release(classroomID, seatRow, seatColumn);
        if (isSuccess) {
            if (isAjaxRequest(request)) {
                DataResp resp = new DataResp(RespCode.RELEASE_SEAT_SUCCESS);
                response.getOutputStream().write(new Gson().toJson(resp).getBytes("utf-8"));
                return;
            }
            request.setAttribute("message", InfoMessage.RELEASE_SEAT_SUCCESS);
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        } else {
            if (isAjaxRequest(request)) {
                DataResp resp = new DataResp(RespCode.RELEASE_SEAT_FAIL);
                response.getOutputStream().write(new Gson().toJson(resp).getBytes("utf-8"));
                return;
            }
            request.setAttribute("message", InfoMessage.RELEASE_SEAT_FAIL);
            request.getRequestDispatcher("message.jsp").forward(request, response);
        }
    }
}
