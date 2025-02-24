package com.example.boxingarena.controller_app;

import com.example.boxingarena.bean.UserBean;
import com.example.boxingarena.dao.UserDao;
import com.example.boxingarena.entity.User;

import java.sql.SQLException;

public class UserControllerApp {



    //region get

    public  UserBean login(UserBean userBean) throws SQLException {
        UserDao userDao = new UserDao();
        User user = userDao.login(userBean.getUsername(), userBean.getPassword());
        userBean.setRole(user.getRoleEntity());
        userBean.setUsername(user.getUsernameEntity());
        userBean.setPassword(user.getPasswordEntity());
        userBean.setId(user.getIdEntity());
        return userBean;
    }

    public  Boolean signing(UserBean userBean) throws SQLException {
        UserDao userDao = new UserDao();
        userDao.signing(userBean.getUsername(), userBean.getPassword(), userBean.getRole());
        return true;
    }


}
