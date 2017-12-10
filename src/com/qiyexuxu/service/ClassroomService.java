package com.qiyexuxu.service;

import com.qiyexuxu.domain.Classroom;
import com.qiyexuxu.domain.Seat;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * 向 Web 层提供 service 服务
 * addClassroom  添加教室（管理员功能，未做实现）
 * returnOccupied 返回指定教室中所有被占用的座位
 * sumOfSeats  返回教室中总的座位数
 */
public interface ClassroomService {
    boolean addClassroom(Classroom classroom) throws IllegalAccessException,
            IntrospectionException, InvocationTargetException;

    ArrayList<Seat> returnOccupied(String classroomID) throws IllegalAccessException,
            InvocationTargetException, InstantiationException;

    int sumOfSeats(String classroomID);
}
