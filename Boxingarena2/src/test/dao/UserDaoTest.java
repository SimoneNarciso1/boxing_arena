package test.dao;

import com.example.kartgp.entity.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

    class UserDaoTest {
    @Test
    void loginWrongUser() {
        assertThrows(Exception.class, () -> {
            String username = "testUsernam";
            String password = "testPassword";
            UserDao userDao = new UserDao();
            userDao.login(username, password);
        });
    }

    @Test
    void loginRightUser() {
        try {
            String username = "testUsername";
            String password = "testPassword";
            UserDao userDao = new UserDao();
            User user = userDao.login(username,password);
            assertNotNull(user);
            assertEquals(username, user.getUsernameEntity(), "username should match");
        } catch (Exception e) {
            fail("Exception not expected", e);
        }
    }
}
