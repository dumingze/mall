package com.dmz.zrw.service;

import com.dmz.zrw.model.User;
import com.dmz.zrw.model.bo.LoginMallUserBo;
import com.dmz.zrw.model.vo.LoginUserVo;

import java.util.List;

public interface UserService {

    List<User> showUsers();

    boolean deleteUser(int id);

    List<User> searchUser(String word);

    LoginUserVo signup(LoginMallUserBo loginMallUserBo);
}
