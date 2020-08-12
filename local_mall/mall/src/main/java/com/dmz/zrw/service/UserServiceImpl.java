package com.dmz.zrw.service;

import com.dmz.zrw.dao.UserDao;
import com.dmz.zrw.dao.UserDaoImpl;
import com.dmz.zrw.model.User;

import com.dmz.zrw.model.bo.LoginMallUserBo;
import com.dmz.zrw.model.bo.MallUpdatePwdBo;
import com.dmz.zrw.model.bo.SignInMallUserBo;
import com.dmz.zrw.model.bo.UpdateUserDataBo;
import com.dmz.zrw.model.vo.SigninUserVo;

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
    public SigninUserVo signup(SignInMallUserBo signInMallUserBo) {
        return userDao.signup(signInMallUserBo);
    }

    @Override
    public User login(LoginMallUserBo loginMallUserBo) {
        return userDao.login(loginMallUserBo);
    }

    @Override
    public User data(String nickname) {
        return userDao.data(nickname);
    }

    @Override
    public boolean updateMallUserPwd(MallUpdatePwdBo mallUpdatePwdBo) {
        System.out.println("mallUpdatePwdBo"+mallUpdatePwdBo);
       boolean checkOldPwd=userDao.checkOldPwd(mallUpdatePwdBo);
       boolean updateUser=userDao.updateUser(mallUpdatePwdBo);
       if (checkOldPwd&&updateUser){
           return  true;
       }
       else {
           return false;
       }
    }

    @Override
    public boolean updateUserData(UpdateUserDataBo updateUserDataBo) {
        return userDao.updateUserData(updateUserDataBo);
    }

}
