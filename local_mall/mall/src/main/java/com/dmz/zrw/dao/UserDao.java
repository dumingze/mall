package com.dmz.zrw.dao;

import com.dmz.zrw.model.User;

import com.dmz.zrw.model.bo.LoginMallUserBo;
import com.dmz.zrw.model.bo.MallUpdatePwdBo;
import com.dmz.zrw.model.bo.SignInMallUserBo;
import com.dmz.zrw.model.bo.UpdateUserDataBo;
import com.dmz.zrw.model.vo.SigninUserVo;

import java.util.List;

public interface UserDao {
    List<User> showUsers();

    boolean deleteUser(int id);

    List<User> searchUser(String word);

    SigninUserVo signup(SignInMallUserBo signInMallUserBo);

    User login(LoginMallUserBo loginMallUserBo);

    User data(String nickname);

    boolean checkOldPwd(MallUpdatePwdBo mallUpdatePwdBo);

    boolean updateUser(MallUpdatePwdBo mallUpdatePwdBo);

    boolean updateUserData(UpdateUserDataBo updateUserDataBo);
}
