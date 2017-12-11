package com.qiyexuxu.dao;

import com.qiyexuxu.domain.Classroom;
import com.qiyexuxu.domain.Seat;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * 向 service 层提供三个服务
 * addClassroom: 增加教室（管理员功能，未做实现）
 * occupySeats： 返回教室中所有被占用的座位
 * countOfSeats： 返回教室中的座位总数
 */
public interface ClassroomDao {
    boolean addClassroom(Classroom classroom) throws IllegalAccessException,
            IntrospectionException, InvocationTargetException;

    ArrayList<Seat> occupySeats(String classroomID) throws InstantiationException,
            IllegalAccessException, InvocationTargetException;

    int countOfSeats(String classroomID);
}
