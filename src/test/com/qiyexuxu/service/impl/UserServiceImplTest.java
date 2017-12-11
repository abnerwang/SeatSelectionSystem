package test.com.qiyexuxu.service.impl;

import com.qiyexuxu.domain.User;
import com.qiyexuxu.service.UserService;
import com.qiyexuxu.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * UserServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Dec 7, 2017</pre>
 */
public class UserServiceImplTest {

    private User user = new User();
    private UserService userService = null;

    @Before
    public void before() throws Exception {
        user.setStudentID("E17301177");
        user.setUsername("王小平");
        user.setPassword("Wxp@0910");

        userService = new UserServiceImpl();
    }

    @After
    public void after() throws Exception {
        user = null;
        userService = null;
    }

    /**
     * Method: register(User user)
     */
    @Test
    public void testRegister() throws Exception {
        if (userService.register(user)) {
            System.out.println("用户注册成功！");
        } else {
            System.out.println("用户注册失败！");
        }
    }

    /**
     * Method: login(String studentID, String password)
     */
    @Test
    public void testLogin() throws Exception {
        User userCopy = userService.login(user.getStudentID(), user.getPassword());
        if (userCopy != null) {
            System.out.println("用户登录成功！");
        } else {
            System.out.println("用户名或密码错误！");
        }
    }


} 
