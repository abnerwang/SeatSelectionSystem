package test.com.qiyexuxu.dao.impl;

import com.qiyexuxu.dao.UserDao;
import com.qiyexuxu.dao.impl.UserDaoImpl;
import com.qiyexuxu.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * UserDaoImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Dec 7, 2017</pre>
 */
public class UserDaoImplTest {

    private User user = new User();
    private UserDao dao;

    @Before
    public void before() throws Exception {
        user.setStudentID("E17301177");
        user.setUsername("王小平");
        user.setPassword("Wxp@0910");

        dao = new UserDaoImpl();
    }

    @After
    public void after() throws Exception {
        user = null;
    }

    /**
     * Method: addUser(User user)
     */
    @Test
    public void testAddUser() throws Exception {
        if (dao.addUser(user)) {
            System.out.println("用户注册成功！");
        }
    }

    /**
     * Method: findUser(String studentID)
     */
    @Test
    public void testFindUserStudentID() throws Exception {
        if (dao.findUser(user.getStudentID())) {
            System.out.println("用户已经注册过了！");
        }
    }

    /**
     * Method: findUser(String studentID, String password)
     */
    @Test
    public void testFindUserForStudentIDPassword() throws Exception {
        User userTest = null;
        userTest = dao.findUser(user.getStudentID(), user.getPassword());
        if (userTest != null) {
            System.out.println("用户登录成功！");
        } else {
            System.out.println("用户登录失败！");
        }
    }


} 
