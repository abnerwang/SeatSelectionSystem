package com.qiyexuxu.dao;

import com.qiyexuxu.domain.Seat;
import com.qiyexuxu.exception.SeatOccupiedException;
import com.qiyexuxu.exception.SeatSelectedException;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

/**
 * 为 service 层提供两个接口
 * selectSeat: 提供选座功能  选座成功返回 true  否则返回 false
 * releaseSeat: 提供释放座位功能  释放成功返回 true  否则返回 false
 * 管理员加座、删座功能未做实现
 */
public interface SeatDao {
    // 为 ClassroomDaoImpl 提供的初始化加座接口
    boolean addSeat(Seat seat) throws IllegalAccessException, IntrospectionException,
            InvocationTargetException;

    boolean selectSeat(String studentID, String classroomID, int seatRow, int seatColumn)
            throws SeatOccupiedException, SeatSelectedException;

    boolean releaseSeat(String classroomID, int seatRow, int seatColumn);
}
