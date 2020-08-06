package com.dmz.zrw.service;

import com.dmz.zrw.model.Admin;
import com.dmz.zrw.model.bo.AdminAddBo;
import com.dmz.zrw.model.bo.AdminLoginBo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface AdminService {
    public Integer login(AdminLoginBo adminLoginBo);

    public List<Admin> showAllAdmins();

   public boolean delectAdmin(Integer id);

    boolean addAdmin(AdminAddBo adminAddBo);

    Admin showAdmin(Integer id);

    boolean updeteAdminss(Admin admin);
}
