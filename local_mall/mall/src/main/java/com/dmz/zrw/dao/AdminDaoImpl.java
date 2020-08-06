package com.dmz.zrw.dao;


import com.dmz.zrw.model.Admin;
import com.dmz.zrw.model.bo.AdminAddBo;
import com.dmz.zrw.model.bo.AdminLoginBo;
import com.dmz.zrw.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class AdminDaoImpl implements AdminDao {

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
        Integer update=null ;
        try {
            update= runner.update("insert into admin (email,pwd,nickname)values(?,?,?)", adminAddBo.getEmail(), adminAddBo.getPwd(), adminAddBo.getNickname());
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


}
