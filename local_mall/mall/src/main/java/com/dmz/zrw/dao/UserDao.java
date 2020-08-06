package com.dmz.zrw.dao;

import com.dmz.zrw.model.User;

import java.util.List;

public interface UserDao {
    List<User> showUsers();

    boolean deleteUser(int id);

    List<User> searchUser(String word);

}
