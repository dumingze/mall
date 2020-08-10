package com.dmz.zrw.controller.mall;

import com.dmz.zrw.model.Result;
import com.dmz.zrw.model.bo.LoginMallUserBo;
import com.dmz.zrw.model.bo.RealLoginBo;
import com.dmz.zrw.model.vo.LoginUserVo;
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

            LoginUserVo loginUserVo=signup(request,response);
            response.getWriter().print(gson.toJson(Result.ok(loginUserVo)));
            return;

        }
        else if ("login".equals(replace)){
           boolean isLogin=login(request,response);

        }
    }

    private boolean login(HttpServletRequest request, HttpServletResponse response) {
        String string=null;
        try {
            string= MyStreamUtils.getInputStreamToString(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        RealLoginBo realLoginBo = gson.fromJson(string, RealLoginBo.class);
        userService.login(realLoginBo);
        //-------------------------------------------------------------------------------------------------
        //注册还没测试呢

    }

    private LoginUserVo signup(HttpServletRequest request, HttpServletResponse response) {
        String string=null;
        try {
            string      = MyStreamUtils.getInputStreamToString(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

        LoginMallUserBo loginMallUserBo=gson.fromJson(string,LoginMallUserBo.class);
      return   userService.signup(loginMallUserBo);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
