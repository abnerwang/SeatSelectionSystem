package com.qiyexuxu.dao.impl;

import com.qiyexuxu.common.InfoMessage;
import com.qiyexuxu.dao.SeatDao;
import com.qiyexuxu.domain.Classroom;
import com.qiyexuxu.domain.Seat;

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
import static com.qiyexuxu.utils.DaoUtils.*;

public class ClassroomDaoImpl implements com.qiyexuxu.dao.ClassroomDao {
    /**
     * 往数据库中写入新建的教室 （管理员功能，未实现）
     *
     * @param classroom 需要新建的教室对象
     * @return 返回教室是否创建成功   true: 创建成功   false: 创建失败
     */
    @Override
    public boolean addClassroom(Classroom classroom) throws IllegalAccessException,
            IntrospectionException, InvocationTargetException {
        boolean isSuccess = true;

        // 首先将封装教室信息的 JavaBean 转换成封装教室信息的 Map
        Map<java.lang.String, Object> crInfoMap = bean2Map(classroom);

        // 将封装教室信息的 Map 存入到数据库中并返回是否存入成功
        isSuccess = insert2DBByMap(crInfoMap, InfoMessage.CLASSROOM_TABLE_NAME);

        // 新建存放教室的数组
        ArrayList<Seat> seats = new ArrayList<>();

        // 往教室中批量添加座位
        SeatDao seatDao = new SeatDaoImpl();
        for (int i = 1; i <= classroom.getCountOfSeats(); i++) {
            for (int j = 1; j <= classroom.getCountOfSeats(); j++) {
                Seat seat = new Seat();
                seat.setClassroomID(classroom.getClassroomID());
                seat.setSeatRow(i);
                seat.setSeatColumn(j);

                // 开始时没有任何学生占用座位
                seat.setStudentID(null);
                seat.setOccupationFlag('0');

                seats.add(seat);
            }
        }

        isSuccess = seatDao.addSeats(seats);

        return isSuccess;
    }


    /**
     * 返回一个教室内所有被占用的座位
     *
     * @param classroomID 教室号
     * @return 返回所有被占用的座位组成的数组
     */
    @Override
    public ArrayList<Seat> occupySeats(String classroomID) throws InstantiationException,
            IllegalAccessException, InvocationTargetException {
        ArrayList<Seat> list = new ArrayList<>();

        // 操作数据库中的座位表进行查询
        String sql = "SELECT * FROM " + InfoMessage.SEAT_TABLE_NAME + " WHERE classroomID = '" + classroomID + "' AND "
                + " occupationFlag = '" + "1'";
        Connection conn = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            list = autoBean(Seat.class, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, preparedStatement, rs);
        }
        return list;
    }

    /**
     * 向数据库中查询指定教室中的座位总数
     *
     * @param classroomID 指定教室的教室号
     * @return 座位总数
     */
    @Override
    public int countOfSeats(String classroomID) {
        int num = 0;

        Connection conn = getConnection();
        String sql = "SELECT COUNT(*) AS NUM FROM " + InfoMessage.SEAT_TABLE_NAME + " WHERE classroomID = '"
                + classroomID + "'";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                num = rs.getInt("NUM");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, rs);
        }

        return num;
    }

}
