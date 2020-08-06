package com.dmz.zrw.dao;


import com.dmz.zrw.model.Admin;
import com.dmz.zrw.model.bo.AdminAddBo;
import com.dmz.zrw.model.bo.AdminLoginBo;
import com.dmz.zrw.model.bo.MulticonditionalQueryBo;
import com.dmz.zrw.model.bo.UpdatePwdBo;
import com.dmz.zrw.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl implements AdminDao {
    public static final Integer updatepwdSuccess = 1;
    public static final Integer updatepOldpwdWrong=2;
    public static final Integer updatepSqlpwdWrong =3;

    @Override
    public int login(AdminLoginBo loginBO) {
        //JDBC
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Long query = null;
        try {
            query = (Long) runner.query("select count(id) from admin where email = ? and pwd = ?",
                    new ScalarHandler(), loginBO.getEmail(), loginBO.getPwd());
        } catch (SQLException e) {
            e.printStackTrace();
            return 500;
        }
        return query != 0 ? 200:404;
    }

    @Override
    public List<Admin> showAllAdmins() {
        QueryRunner runner=new QueryRunner(DruidUtils.getDataSource());
        List<Admin> admins=null;
        try {
            admins=runner.query("select * from admin",new BeanListHandler<Admin>(Admin.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;

    }

    @Override
    public boolean delectAdmin(Integer id) {
        QueryRunner runner=new QueryRunner(DruidUtils.getDataSource());
        Integer update=null;
        try {
             update = runner.update("delete  from admin where id=?", id);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        if (update>=1){
            return true;
        }
        else {return false;}
    }

    @Override
    public boolean addAdmin(AdminAddBo adminAddBo) {
        QueryRunner runner=new QueryRunner(DruidUtils.getDataSource());
        Long query=null ;
        //用户名要唯一，不然会插入失败

        try {
            query = (Long) runner.query("select count(id) from admin where email= ? ", new ScalarHandler(), adminAddBo.getEmail());
            System.out.println(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (query>0){
            return false;
        }


        Integer update=null;
        try {

            update=  runner.update("insert into admin (email,pwd,nickname)values(?,?,?)", adminAddBo.getEmail(), adminAddBo.getPwd(), adminAddBo.getNickname());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (update>=1){
            return true;
        }
        else {return false;}
    }

    @Override
    public Admin showAdmins(Integer id) {
        QueryRunner runner=new QueryRunner(DruidUtils.getDataSource());
        Admin admin=null;
        try {
            admin = runner.query("select * from admin where id=?", new BeanHandler<Admin>(Admin.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

    @Override
    public boolean updeteAdminss(Admin admin) {
        QueryRunner runner=new QueryRunner(DruidUtils.getDataSource());
        Integer update=null ;

        try {
            update = runner.update("update  admin set  email=? ,pwd=? ,nickname=? where id=?", admin.getEmail(), admin.getPwd(), admin.getNickname(),admin.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (update>0){
            return true;
        }
        else {
            return false;
        }


    }

    @Override
    public List<Admin> multiconditionalQuery( MulticonditionalQueryBo multiconditionalQueryBo) {
        QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());
        List<Admin> adminlistRsult=null;

        try {
            List<Object> list = new ArrayList<>();
            String sql="select * from admin where 1=1 ";
            if (!StringUtils.isEmpty(multiconditionalQueryBo.getEmail())){
                sql=sql+" and email like ?";
                list.add("%"+multiconditionalQueryBo.getEmail()+"%");
            }
            if (!StringUtils.isEmpty(multiconditionalQueryBo.getNickname())){
                sql=sql+"and nickname like ?";
                list.add("%"+multiconditionalQueryBo.getNickname()+"%");
            }
            System.out.println(sql);
            adminlistRsult=queryRunner.query(sql,new BeanListHandler<Admin>(Admin.class),list.toArray());
        }
        catch (SQLException e){
            e.printStackTrace();

        }

        return adminlistRsult;
    }

    @Override
    public Integer changePwd(UpdatePwdBo updatePwdBo) {
        QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());
        Admin adminquery=null;
        try {
             adminquery = queryRunner.query("select * from admin where email=?", new BeanHandler<Admin>(Admin.class), updatePwdBo.getAdminToken());
            System.out.println("adminquery"+adminquery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!adminquery.getPwd().equals(updatePwdBo.getOldPwd())){
            return updatepOldpwdWrong;
        }


        Integer queryResult=null;
        try {
            queryResult=queryRunner.update("update  admin set  pwd=? where email=? ",updatePwdBo.getNewPwd(),updatePwdBo.getAdminToken());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (queryResult>0){
            return updatepwdSuccess;
        }
        else {
            return updatepSqlpwdWrong;
        }


    }


}
