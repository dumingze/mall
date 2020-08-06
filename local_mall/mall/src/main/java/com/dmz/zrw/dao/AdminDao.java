package com.dmz.zrw.dao;


import com.dmz.zrw.model.Admin;
import com.dmz.zrw.model.User;
import com.dmz.zrw.model.bo.AdminAddBo;
import com.dmz.zrw.model.bo.AdminLoginBo;
import com.dmz.zrw.model.bo.MulticonditionalQueryBo;
import com.dmz.zrw.model.bo.UpdatePwdBo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface AdminDao {
    int login(AdminLoginBo loginBO);

    List<Admin> showAllAdmins();

    boolean delectAdmin(Integer id);

    boolean addAdmin(AdminAddBo adminAddBo);

    Admin showAdmins(Integer id);

    boolean updeteAdminss(Admin admin);

    List<Admin> multiconditionalQuery( MulticonditionalQueryBo multiconditionalQueryBo);

    Integer changePwd(UpdatePwdBo updatePwdBo);




    //List<Admin> allAdmins();
}
