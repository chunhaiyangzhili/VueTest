package com.example.vuetest.service;

import com.example.vuetest.dao.UserDao;
import com.example.vuetest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description
 *
 * @author zwzhang5
 * @description
 * @date Created on 2021/3/29 11:41
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;


    @Override
    public List<User> getUserList(int startPage,int pageSize) {
        return userDao.getUserList(startPage,pageSize);
    }

    @Override
    public int getUserCount() {
        return userDao.getUserCount();
    }

    @Override
    public boolean deleteUserById(int id) {
        boolean flag = false;
        try {
            System.out.println("开始啦-------");
            userDao.deleteUserById(id);
            flag = true;
        }catch (Exception e){
            System.out.println("结束啦-------");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public User selectUserById(int id) {
        User user = null;
        try {
            user = userDao.selectUserById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean updateUserById(User user) {
        boolean flag = false;
        try {
            userDao.updateUserById(user);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean insertUser(User user) {
        boolean flag = false;
        try {
            userDao.insertUser(user);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
}
