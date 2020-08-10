package com.dmz.zrw.service;

import com.dmz.zrw.dao.UserDao;
import com.dmz.zrw.dao.UserDaoImpl;
import com.dmz.zrw.model.User;
import com.dmz.zrw.model.bo.LoginMallUserBo;
import com.dmz.zrw.model.vo.LoginUserVo;

import java.util.List;

public class UserServiceImpl implements UserService {

    UserDao userDao=new UserDaoImpl();



    @Override
    public List<User> showUsers() {
        return  userDao.showUsers();
    }

    @Override
    public boolean deleteUser(int id) {
        return userDao.deleteUser(id);
    }

    @Override
    public List<User> searchUser(String word) {
        return userDao.searchUser(word);
    }

    @Override
    public LoginUserVo signup(LoginMallUserBo loginMallUserBo) {
        return userDao.signup(loginMallUserBo);
    }

}
