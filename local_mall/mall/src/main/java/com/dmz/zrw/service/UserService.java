package com.dmz.zrw.service;

import com.dmz.zrw.model.User;

import java.util.List;

public interface UserService {

    List<User> showUsers();

    boolean deleteUser(int id);

    List<User> searchUser(String word);
}
