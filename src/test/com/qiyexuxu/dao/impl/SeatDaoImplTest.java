package test.com.qiyexuxu.dao.impl;

import com.qiyexuxu.dao.SeatDao;
import com.qiyexuxu.dao.impl.SeatDaoImpl;
import com.qiyexuxu.domain.Seat;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * SeatDaoImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Dec 9, 2017</pre>
 */
public class SeatDaoImplTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: addSeat(Seat seat)
     */
    @Test
    public void testAddSeat() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: selectSeat(String studentID, String classroomID, int seatRow, int seatColumn)
     */
    @Test
    public void testSelectSeat() throws Exception {
//TODO: Test goes here...
        SeatDao seatDao = new SeatDaoImpl();
        Seat seat = seatDao.selectSeat("E17301177", "101", 1, 2);
        if (seat != null) {
            System.out.println("选座成功！");
        } else {
            System.out.println("选座失败！");
        }
    }

    /**
     * Method: releaseSeat(String classroomID, int seatRow, int seatColumn)
     */
    @Test
    public void testReleaseSeat() throws Exception {
        SeatDao seatDao = new SeatDaoImpl();
        boolean isSuccess = seatDao.releaseSeat("101", 1, 2);
        if (isSuccess) {
            System.out.println("座位释放成功！");
        } else {
            System.out.println("座位释放失败！");
        }
    }


} 
