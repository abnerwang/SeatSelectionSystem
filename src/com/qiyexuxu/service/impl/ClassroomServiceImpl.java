package com.qiyexuxu.service.impl;

import com.qiyexuxu.dao.ClassroomDao;
import com.qiyexuxu.dao.impl.ClassroomDaoImpl;
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
public class ClassroomServiceImpl implements com.qiyexuxu.service.ClassroomService {
    private ClassroomDao classroomDao = new ClassroomDaoImpl();

    /**
     * 添加教室（管理员功能，未实现）
     *
     * @param classroom 需要添加的教室
     * @return 是否添加成功  true：添加成功  false：添加失败
     * @throws IllegalAccessException
     * @throws IntrospectionException
     * @throws InvocationTargetException
     */
    @Override
    public boolean addClassroom(Classroom classroom) throws IllegalAccessException,
            IntrospectionException, InvocationTargetException {
        boolean isSuccess = true;
        isSuccess = classroomDao.addClassroom(classroom);
        return isSuccess;
    }

    /**
     * 返回指定教室中所有被占用的座位组成的数组
     *
     * @param classroomID 指定教室的教室号
     * @return 被占用座位组成的数组
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    @Override
    public ArrayList<Seat> returnOccupied(String classroomID) throws IllegalAccessException,
            InvocationTargetException, InstantiationException {
        ArrayList<Seat> seatsOccupied = new ArrayList<>();
        classroomDao = new ClassroomDaoImpl();
        seatsOccupied = classroomDao.occupySeats(classroomID);
        return seatsOccupied;
    }

    /**
     * 返回指定教室中总的座位数
     *
     * @param classroomID 指定教室的教室号
     * @return 教室中总的座位数
     */
    @Override
    public int sumOfSeats(String classroomID) {
        int sum = classroomDao.countOfSeats(classroomID);
        return sum;
    }
}
