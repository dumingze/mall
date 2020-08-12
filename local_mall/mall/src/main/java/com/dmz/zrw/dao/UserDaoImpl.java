package com.dmz.zrw.dao;

import com.dmz.zrw.model.User;

import com.dmz.zrw.model.bo.LoginMallUserBo;
import com.dmz.zrw.model.bo.MallUpdatePwdBo;
import com.dmz.zrw.model.bo.SignInMallUserBo;
import com.dmz.zrw.model.bo.UpdateUserDataBo;
import com.dmz.zrw.model.vo.SigninUserVo;
import com.dmz.zrw.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {

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
    public SigninUserVo signup(SignInMallUserBo signInMallUserBo) {
        SigninUserVo signinUserVo =null;
        QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());
        Integer sign=null;
        try {
            sign=queryRunner.update("insert into user values (null,?,?,?,?,?,?)",
                    signInMallUserBo.getEmail(),signInMallUserBo.getNickname(),
                    signInMallUserBo.getPwd(),signInMallUserBo.getRecipient(),
                    signInMallUserBo.getAddress(),signInMallUserBo.getPhone());
        } catch (SQLException e) {
            e.printStackTrace();

        }
        finally {
            if (sign==1){
                signinUserVo=new SigninUserVo();
                signinUserVo.setName(signInMallUserBo.getNickname());
                signinUserVo.setToken(signInMallUserBo.getNickname());

            }
            //注册不成功的话会返回null
            return signinUserVo;
        }


    }

    @Override
    public User login(LoginMallUserBo loginMallUserBo) {
        QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());
        User user=null;
        try {
            user=queryRunner.query("select * from user where email = ? and pwd = ?",new BeanHandler<User>(User.class),loginMallUserBo.getEmail(),loginMallUserBo.getPwd());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(user);
        return user;
    }

    @Override
    public User data(String nickname) {
        QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());
        User user=null;
        try {
            user=queryRunner.query("select * from user where nickname = ? ",new BeanHandler<User>(User.class),nickname);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(user);
        return user;

    }

    @Override
    public boolean checkOldPwd(MallUpdatePwdBo mallUpdatePwdBo) {
        QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());

        User user=null;
        try {
            user=queryRunner.query("select * from user where id = ? ",new BeanHandler<User>(User.class),mallUpdatePwdBo.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(user.getPwd());
        System.out.println(mallUpdatePwdBo.getOldPwd());
        if (user.getPwd().equals(mallUpdatePwdBo.getOldPwd())){
            System.out.println("ture");
            return true;
        }
        return false;
    }

    @Override
    public boolean updateUser(MallUpdatePwdBo mallUpdatePwdBo) {
        QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());
        Integer isUpdate=null;
        try {
            isUpdate=queryRunner.update("update user set pwd = ? where id =  ? ",mallUpdatePwdBo.getNewPwd(),mallUpdatePwdBo.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isUpdate==1?true:false;
    }

    @Override
    public boolean updateUserData(UpdateUserDataBo updateUserDataBo) {
        System.out.println(updateUserDataBo);
        QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());
        Integer isUpdateUserData=null;
        try {
            isUpdateUserData=queryRunner.update("update user set address = ? ,nickname = ?,phone = ?,recipient = ? where id = ? ",updateUserDataBo.getAddress(),
                    updateUserDataBo.getNickname(),updateUserDataBo.getPhone(),updateUserDataBo.getRecipient(),updateUserDataBo.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  isUpdateUserData==1?true:false;

    }


}
