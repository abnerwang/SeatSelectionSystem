package com.qiyexuxu.service.impl;

import com.qiyexuxu.dao.SeatDao;
import com.qiyexuxu.dao.impl.SeatDaoImpl;
import com.qiyexuxu.exception.SeatOccupiedException;
import com.qiyexuxu.exception.SeatSelectedException;

/**
 * 向 Web 层提供选座和释放座位功能
 */
public class SeatServiceImpl implements com.qiyexuxu.service.SeatService {
    private SeatDao seatDao = new SeatDaoImpl();

    /**
     * 向 Web 层提供选座功能
     *
     * @param studentID   选择该座位的学生学号
     * @param classroomID 该座位所在的教室号
     * @param seatRow     该座位所在的行
     * @param seatColumn  该座位所在的列
     * @return 选座是否成功   true 选座成功  false 选座失败
     * @throws SeatOccupiedException
     */
    @Override
    public boolean select(String studentID, String classroomID, int seatRow, int seatColumn)
            throws SeatOccupiedException, SeatSelectedException {
        boolean isSuccess = seatDao.selectSeat(studentID, classroomID, seatRow, seatColumn);
        return isSuccess;
    }


    /**
     * 向 Web 层提供释放座位功能
     *
     * @param classroomID 该座位所在的教室号
     * @param seatRow     该座位所在的行
     * @param seatColumn  该座位所在的列
     * @return 座位释放是否成功   true 释放成功过  false 释放失败
     */
    @Override
    public boolean release(String classroomID, int seatRow, int seatColumn) {
        boolean isSuccess = seatDao.releaseSeat(classroomID, seatRow, seatColumn);
        return isSuccess;
    }
}
