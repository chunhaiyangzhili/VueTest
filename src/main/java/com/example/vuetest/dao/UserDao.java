package com.example.vuetest.dao;

import com.example.vuetest.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description
 *
 * @author zwzhang5
 * @description
 * @date Created on 2021/3/29 11:36
 */
@Component
@Mapper
public interface UserDao {
    List<User> getUserList(int startPage,int pageSize);
    int getUserCount();
    boolean deleteUserById(int id);
    User selectUserById(int id);
    boolean updateUserById(User user);
    boolean insertUser(User user);
}
