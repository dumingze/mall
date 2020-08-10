package com.dmz.zrw.dao;

import com.dmz.zrw.model.User;
import com.dmz.zrw.model.bo.LoginMallUserBo;
import com.dmz.zrw.model.vo.LoginUserVo;
import com.dmz.zrw.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {
   // QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());
    @Override
    public List<User> showUsers() {
        QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());
        List<User> userList=null;
        try {
           userList= queryRunner.query("SELECT * FROM  user;",new BeanListHandler<User>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public boolean deleteUser(int id) {
        QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());
        Integer integer=null;
      // queryRunner.
        try {
          integer=  queryRunner.update("delete  from user where id=?",id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return integer!=0 ?true:false;

    }

    @Override
    public List<User> searchUser(String word)   {
        QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());
        List<User> users=null;
        String sql="%"+word+"%";
        try {
          users=  queryRunner.query("select * from user where nickname like ?",new BeanListHandler<User>(User.class),sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public LoginUserVo signup(LoginMallUserBo loginMallUserBo) {
        LoginUserVo loginUserVo=new LoginUserVo();
        QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());
        Integer sign=null;
        try {
            sign=queryRunner.update("insert into user values (null,?,?,?,?,?,?)",
                    loginMallUserBo.getEmail(),loginMallUserBo.getNickname(),
                    loginMallUserBo.getPwd(),loginMallUserBo.getRecipient(),
                    loginMallUserBo.getAddress(),loginMallUserBo.getPhone());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (sign==1){
            loginUserVo.setName(loginMallUserBo.getNickname());
            loginUserVo.setToken(loginMallUserBo.getNickname());

        }
        return loginUserVo;

    }

}
