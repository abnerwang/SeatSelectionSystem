package test.com.qiyexuxu.service.impl;

import com.qiyexuxu.dao.ClassroomDao;
import com.qiyexuxu.dao.impl.ClassroomDaoImpl;
import com.qiyexuxu.domain.Classroom;
import com.qiyexuxu.domain.Seat;
import com.qiyexuxu.service.ClassroomService;
import com.qiyexuxu.service.impl.ClassroomServiceImpl;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.ArrayList;

/**
 * ClassroomServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Dec 9, 2017</pre>
 */
public class ClassroomServiceImplTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: addClassroom(Classroom classroom)
     */
    @Test
    public void testAddClassroom() throws Exception {
        Classroom classroom = new Classroom();
        classroom.setClassroomID("102");
        classroom.setCountOfSeats(5);

        ClassroomService classroomService = new ClassroomServiceImpl();
        boolean isSuccess = classroomService.addClassroom(classroom);
        if (isSuccess) {
            System.out.println("教室创建成功！");
        } else {
            System.out.println("教室创建失败！");
        }
    }

    /**
     * Method: returnOccupied(String classroomID)
     */
    @Test
    public void testReturnOccupied() throws Exception {
        ArrayList<Seat> seats = new ArrayList<>();
        ClassroomDao classroomDao = new ClassroomDaoImpl();
        seats = classroomDao.occupySeats("101");
        for (Seat seat : seats) {
            System.out.println(seat.getStudentID());
        }
    }

    /**
     * Method: sumOfSeats(String classroomID)
     */
    @Test
    public void testSumOfSeats() throws Exception {
        ClassroomDao classroomDao = new ClassroomDaoImpl();
        int count = classroomDao.countOfSeats("101");
        System.out.println(count);
    }


} 
