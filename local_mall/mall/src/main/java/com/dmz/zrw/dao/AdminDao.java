package com.dmz.zrw.dao;


import com.dmz.zrw.model.Admin;
import com.dmz.zrw.model.bo.AdminAddBo;
import com.dmz.zrw.model.bo.AdminLoginBo;

import java.util.List;

public interface AdminDao {
    int login(AdminLoginBo loginBO);

    List<Admin> showAllAdmins();

    boolean delectAdmin(Integer id);

    boolean addAdmin(AdminAddBo adminAddBo);

    Admin showAdmins(Integer id);

    boolean updeteAdminss(Admin admin);

    //List<Admin> allAdmins();
}
