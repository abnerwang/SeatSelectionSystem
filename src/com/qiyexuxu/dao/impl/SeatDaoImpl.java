package com.qiyexuxu.dao.impl;

import com.qiyexuxu.common.InfoMessage;
import com.qiyexuxu.domain.Seat;
import com.qiyexuxu.exception.SeatOccupiedException;
import com.qiyexuxu.exception.SeatSelectedException;
import com.qiyexuxu.utils.DaoUtils;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import static com.qiyexuxu.utils.DBUtils.close;
import static com.qiyexuxu.utils.DBUtils.getConnection;
import static com.qiyexuxu.utils.DaoUtils.bean2Map;
import static com.qiyexuxu.utils.DaoUtils.insert2DBByMap;

public class SeatDaoImpl implements com.qiyexuxu.dao.SeatDao {
    /**
     * 将座位信息写入到数据库中
     *
     * @param seat 要写入数据库的座位
     * @return 返回写入成功或失败信息  true: 写入成功   false: 写入失败
     * @throws IllegalAccessException
     * @throws IntrospectionException
     * @throws InvocationTargetException
     */
    @Override
    public boolean addSeat(Seat seat) throws IllegalAccessException, IntrospectionException,
            InvocationTargetException {
        Map<String, Object> seatInfoMap = bean2Map(seat);
        boolean isSuccess = insert2DBByMap(seatInfoMap, InfoMessage.SEAT_TABLE_NAME);

        return isSuccess;
    }

    /**
     * 在新建教室的时候批量添加座位（为添加座位时的性能考虑）
     *
     * @param seats 需要添加的座位组成的数组
     * @return 批量添加是否成功   true: 添加成功过  false： 添加失败
     */
    @Override
    public boolean addSeats(ArrayList<Seat> seats) {
        boolean isSuccess = true;

        Connection conn = getConnection();
        PreparedStatement preparedStatement = null;

        for (Seat seat : seats) {
            String classroomID = seat.getClassroomID();
            int seatRow = seat.getSeatRow();
            int seatColumn = seat.getSeatColumn();
            String sql = "INSERT INTO seat(classroomID, seatRow, seatColumn, studentID, occupationFlag) VALUES " +
                    "('" + classroomID + "', " + seatRow + ", " + seatColumn + ", " + null + ", '" + "0')";
            try {
                preparedStatement = conn.prepareStatement(sql);
                int effectedRows = preparedStatement.executeUpdate();
                if (effectedRows < 1) {
                    isSuccess = false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        close(conn, preparedStatement);

        return isSuccess;
    }

    /**
     * 在数据库中处理选座
     *
     * @param studentID   选择该座位学生的学号
     * @param classroomID 被选择的座位所在的教室号
     * @param seatRow     该座位所在的行
     * @param seatColumn  该座位所在的列
     * @return 选座是否成功   true: 成功  false：失败
     */
    @Override
    public Seat selectSeat(String studentID, String classroomID, int seatRow, int seatColumn)
            throws SeatOccupiedException, SeatSelectedException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Seat seat = null;

        // 判断该学生是否已经选过座了，若已经选了，则抛出异常
        Connection conn = getConnection();
        String ifSelected = "SELECT * FROM " + InfoMessage.SEAT_TABLE_NAME + " WHERE studentID = '" +
                studentID + "' AND occupationFlag = '1'";
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            preparedStatement = conn.prepareStatement(ifSelected);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                throw new SeatSelectedException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        // 判断座位是否已被占用
        String ifOcuSql = "SELECT * FROM " + InfoMessage.SEAT_TABLE_NAME + " WHERE classroomID = '"
                + classroomID + "' AND seatRow = " + seatRow + " AND seatColumn = " + seatColumn +
                " AND occupationFlag = '0'";
        try {
            preparedStatement = conn.prepareStatement(ifOcuSql);
            rs = preparedStatement.executeQuery();
            if (!rs.next()) {
                throw new SeatOccupiedException();   // 向 service 层抛出座位已被占用异常
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 选择对应教室的对应座位
        // 然后把选择这个座位的学生学号和这个座位的占用状态填进去
        String selectSql = "UPDATE " + InfoMessage.SEAT_TABLE_NAME + " SET occupationFlag = " + "'1', studentID = '" +
                studentID + "' WHERE classroomID = '" + classroomID + "' AND seatRow = " + seatRow +
                " AND seatColumn = " + seatColumn;
        String querySql = "SELECT * FROM " + InfoMessage.SEAT_TABLE_NAME + " WHERE studentID = '" + studentID +
                "' AND occupationFlag = '1'";
        try {
            preparedStatement = conn.prepareStatement(selectSql);
            preparedStatement.executeUpdate();
            preparedStatement = conn.prepareStatement(querySql);
            rs = preparedStatement.executeQuery();
            seat = DaoUtils.autoBean(Seat.class, rs).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, preparedStatement, rs);
        }

        return seat;
    }

    /**
     * 在数据库中释放座位
     *
     * @param classroomID 释放的座位所在的教室号
     * @param seatRow     释放的座位所在的行
     * @param seatColumn  释放的座位所在的列
     * @return 座位释放成功与否  true: 释放成功  false: 释放失败
     */
    @Override
    public boolean releaseSeat(String classroomID, int seatRow, int seatColumn) {
        boolean isSuccess = true;

        // 操作数据库
        String sql = "UPDATE " + InfoMessage.SEAT_TABLE_NAME + " SET occupationFlag = " + "'0', studentID = '" +
                null + "' WHERE classroomID = '" + classroomID + "' AND seatRow = " + seatRow +
                " AND seatColumn = " + seatColumn;
        Connection conn = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            int effectedRows = preparedStatement.executeUpdate();
            if (effectedRows < 1)
                isSuccess = false;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, preparedStatement);
        }

        return isSuccess;
    }
}
