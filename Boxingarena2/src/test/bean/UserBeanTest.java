package test.bean;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

    class UserBeanTest {
    @Test
    void wrongField() {
        assertThrows(Exception.class, () -> {
            UserBean userBean = new UserBean();
            userBean.checkField("","password");
        });
    }

    @Test
    void rightField() {
        UserBean userBean = new UserBean();
        assertDoesNotThrow(() -> userBean.checkField("username", "password"));
    }
}
