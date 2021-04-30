package com.example.vuetest.service;

import com.example.vuetest.model.User;

import java.util.List;

/**
 * Description
 *
 * @author zwzhang5
 * @description
 * @date Created on 2021/3/29 11:39
 */
public interface UserService {
    List<User> getUserList(int startPage,int pageSize);
    int getUserCount();
    boolean deleteUserById(int id);
    User selectUserById(int id);
    boolean updateUserById(User user);
    boolean insertUser(User user);
}
