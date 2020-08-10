package com.dmz.zrw.controller.admin;

import com.dmz.zrw.model.Result;
import com.dmz.zrw.model.User;
import com.dmz.zrw.model.bo.MulticonditionalQueryBo;
import com.dmz.zrw.service.AdminService;
import com.dmz.zrw.service.AdminServiceImpl;
import com.dmz.zrw.service.UserService;
import com.dmz.zrw.service.UserServiceImpl;
import com.dmz.zrw.utils.MyStreamUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/admin/user/*")
public class UserManagementServlet extends HttpServlet {
    private Gson gson = new Gson();

    private UserService userService=new UserServiceImpl();



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String replace = requestURI.replace("/api/admin/user/", "");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doget");
        String requestURI = request.getRequestURI();
        String replace = requestURI.replace("/api/admin/user/", "");
        if("allUser".equals(replace)){
           List<User>  userList =showUsers();
           if (userList.size()>0){
             response.getWriter().print(gson.toJson(Result.ok(userList)));
             return;
           }
           else {
               response.getWriter().print(gson.toJson(Result.error("用户为空")));
               return;
           }
        }
        else if ("deleteUser".equals(replace)){
          boolean isDeleteUser = deleteUser(request,response);

          if (isDeleteUser){
              response.getWriter().print(gson.toJson(Result.ok("删除成功")));
              return;
          }
          else {
              response.getWriter().print(gson.toJson(Result.error("删除失败")));
              return;
          }

        }

        else if ("searchUser".equals(replace)){

            String word = request.getParameter("word");
            if ("".equals(word)){
                try {
                    response.getWriter().print(gson.toJson(Result.error("请输入有效信息")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return ;
            }
            List<User> users=searchUser(request,response,word);
            System.out.println(users.size());
            System.out.println(users);
            if (users==null){
                response.getWriter().print(gson.toJson(Result.error("查询的数据为空")));
                return;
            }
            else {
                response.getWriter().print(gson.toJson(Result.ok(users)));

                return;
            }
        }
    }

    private List<User> searchUser(HttpServletRequest request, HttpServletResponse response,String word) {

      return  userService.searchUser(word);
    }

    private boolean deleteUser(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");

        return userService.deleteUser(Integer.parseInt(id));
    }

    private List<User> showUsers() {

       return userService.showUsers();

    }
}
