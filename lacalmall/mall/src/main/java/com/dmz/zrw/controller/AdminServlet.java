package com.dmz.zrw.controller;

import com.dmz.zrw.model.Admin;
import com.dmz.zrw.model.bo.AdminAddBo;
import com.dmz.zrw.model.bo.AdminLoginBo;
import com.dmz.zrw.model.vo.AdminLoginVO;
import com.dmz.zrw.model.Result;
import com.dmz.zrw.service.AdminService;
import com.dmz.zrw.service.AdminServiceImpl;
import com.dmz.zrw.utils.MyStreamUtils;
import com.google.gson.Gson;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

@WebServlet("/api/admin/admin/*")
public class AdminServlet extends HttpServlet {
    private Gson gson = new Gson();
    private AdminService adminService = new AdminServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //response.getWriter().println("dopost");
//*的话，用request.getServletPath())会匹配不到匹配
        String requestURI = request.getRequestURI();
        String replace = requestURI.replace("/api/admin/admin/", "");
        if ("login".equals(replace)){
            login(request,response);
        }
        else if ("addAdminss".equals(replace)){

            boolean isAdded = addAdminss(request, response);
            if (isAdded){
                response.getWriter().print(gson.toJson(Result.ok("添加成功")));
            }
            else {
                response.getWriter().print(gson.toJson(Result.error("添加失败")));
            }

        }
        else if ("updateAdminss".equals(replace)){
           boolean isupdateAdminss= updateAdminss(request,response);

           if (isupdateAdminss){
               response.getWriter().print(gson.toJson(Result.ok()));
           }
           else {
               response.getWriter().print(gson.toJson(Result.error("修改失败")));
           }
        }


    }

    private boolean updateAdminss(HttpServletRequest request, HttpServletResponse response) {
        String gsonSting= null;
        try {
            gsonSting = MyStreamUtils.getInputStreamToString(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Admin admin = gson.fromJson(gsonSting, Admin.class);

      return  adminService.updeteAdminss(admin);

    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String gsonSting= null;
        try {
            gsonSting = MyStreamUtils.getInputStreamToString(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        AdminLoginBo adminLoginBo = gson.fromJson(gsonSting, AdminLoginBo.class);
            if (StringUtils.isEmpty(adminLoginBo.getEmail())||StringUtils.isEmpty(adminLoginBo.getPwd())){

                response.getWriter().println(gson.toJson(Result.error("参数不能为空")));
                return;
            }

            Integer code = adminService.login(adminLoginBo);
            System.out.println(code);
            if (code==200){
                try {
                    response.getWriter().print(gson.toJson(Result.ok(new AdminLoginVO(adminLoginBo.getEmail(),adminLoginBo.getEmail()))));
                    System.out.println(gson.toJson(Result.ok(new AdminLoginVO(adminLoginBo.getEmail(),adminLoginBo.getEmail()))));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }
            else {
                response.getWriter().println(gson.toJson(Result.error("确认用户名和密码")));
                System.out.println(gson.toJson(Result.error("确认用户名和密码")));
            }





    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        String replace = requestURI.replace("/api/admin/admin/", "");
        System.out.println(replace);
        if ("allAdmins".equals(replace)){
            //对所有admin用户的信息展示
            List<Admin> admins = showAllAdmins();
            if (admins.size()>=1){
                Result ok = Result.ok(admins);
                response.getWriter().print(gson.toJson(ok));
            }
            else {
                Result error = Result.error("无用户选择");
                response.getWriter().print(gson.toJson(error));
            }
        }

       else if ("deleteAdmins".equals(replace)){
            Integer id = Integer.parseInt(request.getParameter("id"));
            boolean isDelect = delectAdmin(id);

            if (isDelect){
                response.getWriter().print(gson.toJson(Result.ok("删除成功")));
            }
            else {
                response.getWriter().print(gson.toJson(Result.error("删除失败")));
            }
        }
       else if ("getAdminsInfo".equals(replace)){
            Integer id = Integer.parseInt(request.getParameter("id"));
          Admin admin=showAdmin(id);

          if (admin!=null){
              response.getWriter().print(gson.toJson(Result.ok(admin)));
          }
          else {
              response.getWriter().print(gson.toJson(Result.error("查询失败")));
          }
        }


    }

    private Admin showAdmin(Integer id) {
         return   adminService.showAdmin(id);
    }

    private boolean addAdminss(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String gsonSting= null;
        try {
            gsonSting = MyStreamUtils.getInputStreamToString(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        AdminAddBo adminAddBo = gson.fromJson(gsonSting, AdminAddBo.class);
        System.out.println(adminAddBo);
        return adminService.addAdmin(adminAddBo);

    }

    private boolean delectAdmin(Integer id) {
        boolean isDelect = adminService.delectAdmin(id);
        return isDelect;

    }

    //对所有admin用户的信息展示
    private List<Admin> showAllAdmins() {
       return adminService.showAllAdmins();
    }
}
