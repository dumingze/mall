package com.dmz.zrw.service;

import com.dmz.zrw.dao.AdminDao;
import com.dmz.zrw.dao.AdminDaoImpl;
import com.dmz.zrw.model.Admin;
import com.dmz.zrw.model.bo.AdminAddBo;
import com.dmz.zrw.model.bo.AdminLoginBo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminServiceImpl implements AdminService {

    AdminDao adminDao=new AdminDaoImpl();
    @Override
    public Integer login(AdminLoginBo adminLoginBo) {

        int code = adminDao.login(adminLoginBo);
        return code;

    }

    @Override
    public List<Admin> showAllAdmins() {

        return adminDao.showAllAdmins();
    }

    @Override
    public boolean delectAdmin(Integer id) {
        return adminDao.delectAdmin(id);
    }

    @Override
    public boolean addAdmin(AdminAddBo adminAddBo) {
       return adminDao.addAdmin(adminAddBo);
    }

    @Override
    public Admin showAdmin(Integer id) {
        return  adminDao.showAdmins(id);
    }

    @Override
    public boolean updeteAdminss(Admin admin) {
        return  adminDao.updeteAdminss(admin);
    }
}
