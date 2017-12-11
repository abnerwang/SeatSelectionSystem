package test.com.qiyexuxu.utils;

import com.qiyexuxu.common.InfoMessage;
import com.qiyexuxu.domain.Seat;
import com.qiyexuxu.domain.User;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import static com.qiyexuxu.utils.DBUtils.getConnection;
import static com.qiyexuxu.utils.DaoUtils.autoBean;
import static com.qiyexuxu.utils.DaoUtils.bean2Map;
import static com.qiyexuxu.utils.DaoUtils.insert2DBByMap;
import static org.apache.commons.beanutils.BeanUtils.*;

/**
 * DaoUtils Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Dec 7, 2017</pre>
 */
public class DaoUtilsTest {

    User user = new User();

    @Before
    public void before() throws Exception {
        user.setStudentID("E17301177");
        user.setUsername("王小平");
        user.setPassword("Wxp@0910");
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: bean2Map(T bean)
     */
    @Test
    public void testBean2Map() throws Exception {
        Map<String, Object> beanMap = bean2Map(user);
        Iterator<Map.Entry<String, Object>> iterator = beanMap.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            System.out.print(entry.getKey() + ": ");
            System.out.println(entry.getValue());
        }
    }

    /**
     * Method: insert2DBByMap(Map<String, Object> map, String tableName)
     */
    @Test
    public void testInsert2DBByMap() throws Exception {
        Map<String, Object> beanMap = bean2Map(user);
        insert2DBByMap(beanMap, InfoMessage.STUDENT_TABLE_NAME);

        Connection conn = getConnection();
        String studentID = user.getStudentID();
        String sql = "SELECT studentID FROM " + InfoMessage.STUDENT_TABLE_NAME + " WHERE studentID = '"
                + studentID + "'";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            System.out.println("注册成功！");
        } else {
            System.out.println("注册失败！");
        }
    }

    /**
     * Method: autoBean(Class<T> tClass, ResultSet resultSet)
     */
    @Test
    public void testAutoBean1() throws Exception {
        Connection conn = getConnection();
        String studentID = user.getStudentID();
        String sql = "SELECT * FROM " + InfoMessage.STUDENT_TABLE_NAME + " WHERE studentID = '"
                + studentID + "'";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();

        ArrayList<User> users = autoBean(User.class, rs);
        User userCopy = users.get(0);

        System.out.println("StudentID: " + userCopy.getStudentID() + " username: " + userCopy.getUsername() +
                " password: " + userCopy.getPassword());
    }

    /**
     * Method: autoBean(Class<T> tClass, ResultSet resultSet)
     */
    @Test
    public void testAutoBean2() throws SQLException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ArrayList<Seat> seats = new ArrayList<>();

        Connection conn = getConnection();
        String sql = "SELECT * FROM " + InfoMessage.SEAT_TABLE_NAME;
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();

        seats = autoBean(Seat.class, rs);

        for (Seat seat : seats) {
            System.out.println(seat.getStudentID());
        }
    }
}
