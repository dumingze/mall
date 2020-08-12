package com.dmz.zrw.service;

import com.dmz.zrw.model.User;

import com.dmz.zrw.model.bo.LoginMallUserBo;
import com.dmz.zrw.model.bo.MallUpdatePwdBo;
import com.dmz.zrw.model.bo.SignInMallUserBo;
import com.dmz.zrw.model.bo.UpdateUserDataBo;
import com.dmz.zrw.model.vo.SigninUserVo;

import java.util.List;

public interface UserService {

    List<User> showUsers();

    boolean deleteUser(int id);

    List<User> searchUser(String word);

    SigninUserVo signup(SignInMallUserBo signInMallUserBo);

    User  login(LoginMallUserBo loginMallUserBo);

    User data(String nickname);

    boolean updateMallUserPwd(MallUpdatePwdBo mallUpdatePwdBo);

    boolean updateUserData(UpdateUserDataBo updateUserDataBo);
}
