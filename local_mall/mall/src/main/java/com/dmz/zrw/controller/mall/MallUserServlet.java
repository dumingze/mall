package com.dmz.zrw.controller.mall;

import com.dmz.zrw.model.Result;


import com.dmz.zrw.model.User;
import com.dmz.zrw.model.bo.LoginMallUserBo;
import com.dmz.zrw.model.bo.MallUpdatePwdBo;
import com.dmz.zrw.model.bo.SignInMallUserBo;
import com.dmz.zrw.model.bo.UpdateUserDataBo;
import com.dmz.zrw.model.vo.LoginMallUserVo;
import com.dmz.zrw.model.vo.SigninUserVo;
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

@WebServlet("/api/mall/user/*")
public class MallUserServlet extends HttpServlet {
    UserService userService=new UserServiceImpl();
    Gson gson=new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String replace = requestURI.replace("/api/mall/user/", "");
        if ("signup".equals(replace)){

            SigninUserVo signinUserVo =signup(request,response);
            if (signinUserVo!=null){
            response.getWriter().print(gson.toJson(Result.ok(signinUserVo)));
            return;
            }
            else {
                response.getWriter().print(gson.toJson(Result.error("邮箱已经被使用")));
                return;
            }

        }
        else if ("login".equals(replace)){
            LoginMallUserVo loginMallUserVo=login(request,response);
           if (loginMallUserVo==null){
               response.getWriter().print(gson.toJson(Result.error("登录失败")));

           }
           else {
               //System.out.println(loginMallUserVo);
               request.getSession().setAttribute("username",loginMallUserVo.getName());
               response.getWriter().print(gson.toJson(Result.ok(loginMallUserVo)));

           }

        }
        else if ("updatePwd".equals(replace)){
        boolean isUpdatePwd   = updateMallUserPwd(request,response);
        if (isUpdatePwd){
            response.getWriter().print(gson.toJson(Result.ok()));
        }
        else {
            response.getWriter().print(gson.toJson(Result.error("修改密码失败")));
        }
        }
        else if ("updateUserData".equals(replace)){

            boolean isUpdateUserData=updateUserData(request,response);
            if (isUpdateUserData){
                response.getWriter().print(gson.toJson(Result.ok()));
            }
            else {
                response.getWriter().print(gson.toJson(Result.error("修改失败")));
            }

        }
    }

    private boolean updateUserData(HttpServletRequest request, HttpServletResponse response) {
        String string=null;
        try {
            string=MyStreamUtils.getInputStreamToString(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        UpdateUserDataBo updateUserDataBo = gson.fromJson(string, UpdateUserDataBo.class);
        return  userService.updateUserData(updateUserDataBo);
    }

    private boolean updateMallUserPwd(HttpServletRequest request, HttpServletResponse response) {
        String string =null;
        try {
            string= MyStreamUtils.getInputStreamToString(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MallUpdatePwdBo mallUpdatePwdBo = gson.fromJson(string, MallUpdatePwdBo.class);


        return  userService.updateMallUserPwd(mallUpdatePwdBo);
    }

    private LoginMallUserVo login(HttpServletRequest request, HttpServletResponse response) {
        LoginMallUserVo loginMallUserVo=null;
        String string=null;
        try {
            string= MyStreamUtils.getInputStreamToString(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        LoginMallUserBo loginMallUserBo = gson.fromJson(string, LoginMallUserBo.class);
        System.out.println(loginMallUserBo);
        User user = userService.login(loginMallUserBo);
        System.out.println(user);
        if (user==null){
            return loginMallUserVo;
        }
        else {
            loginMallUserVo=new LoginMallUserVo(user.getNickname(),user.getNickname());
            return loginMallUserVo;
        }


    }

    private SigninUserVo signup(HttpServletRequest request, HttpServletResponse response) {
        String string=null;
        try {
            string      = MyStreamUtils.getInputStreamToString(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SignInMallUserBo signInMallUserBo=gson.fromJson(string,SignInMallUserBo.class);
      return   userService.signup(signInMallUserBo);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        String replace = requestURI.replace("/api/mall/user/", "");
        if ("data".equals(replace)){
            String nickname = request.getParameter("token");
            User users=data(nickname);
            if (users!=null){
                response.getWriter().print(gson.toJson(Result.ok(users)));
                return;
            }
            else{
                response.getWriter().print(gson.toJson(Result.error("加载用户信息失败")));
                return;
            }
        }

    }

    private User data(String nickname) {
        return userService.data(nickname);
    }
}
