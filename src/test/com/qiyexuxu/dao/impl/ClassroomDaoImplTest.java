package test.com.qiyexuxu.dao.impl;

import com.qiyexuxu.common.InfoMessage;
import com.qiyexuxu.dao.ClassroomDao;
import com.qiyexuxu.dao.impl.ClassroomDaoImpl;
import com.qiyexuxu.domain.Classroom;
import com.qiyexuxu.domain.Seat;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static com.qiyexuxu.utils.DBUtils.close;
import static com.qiyexuxu.utils.DBUtils.getConnection;

/**
 * ClassroomDaoImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Dec 9, 2017</pre>
 */
public class ClassroomDaoImplTest {

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
//TODO: Test goes here...
        Classroom classroom = new Classroom();
        classroom.setClassroomID("102");
        classroom.setCountOfSeats(5);
        ClassroomDao classroomDao = new ClassroomDaoImpl();
        boolean isSuccess = classroomDao.addClassroom(classroom);
        if (isSuccess) {
            System.out.println("创建教室成功!");
        } else {
            System.out.println("创建教室失败！");
        }
    }

    /**
     * Method: occupySeats(String classroomID)
     */
    @Test
    public void testOccupySeats() throws Exception {
//TODO: Test goes here...
        ClassroomDao classroomDao = new ClassroomDaoImpl();
        ArrayList<Seat> seats = new ArrayList<>();
        seats = classroomDao.occupySeats("101");
        int size = seats.size();
        Seat[] seats1 = new Seat[size];
        seats1 = seats.toArray(seats1);
        for (int i = 0; i < size; i++) {
            System.out.println(seats1[i].getStudentID());
        }
    }

    /**
     * Method: countOfSeats(String classroomID)
     */
    @Test
    public void testCountOfSeats1() throws Exception {
        ClassroomDao classroomDao = new ClassroomDaoImpl();
        int count = classroomDao.countOfSeats("101");
        System.out.println(count);
    }

    @Test
    public void testCountOfSeats2() throws Exception {
        Connection conn = getConnection();
        String sql = "SELECT COUNT(*) AS NUM FROM " + InfoMessage.SEAT_TABLE_NAME + " WHERE classroomID = '101'";
        PreparedStatement ps = null;
        ResultSet rs = null;
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
        close(conn, ps, rs);
    }

} 
