package com.dmz.zrw.filter;

import com.dmz.zrw.model.Result;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/api/mall/*")
public class MallFilter implements Filter {

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request= (HttpServletRequest) req;
        HttpServletResponse response= (HttpServletResponse) resp;
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //请求图片的时候不会经过这个路径
        response.setContentType("text/html;charset=utf-8");


        //跨域所必须的
        response.setHeader("Access-Control-Allow-Origin","http://localhost:8085");
        response.setHeader("Access-Control-Allow-Methods","POST,GET,OPTIONS,PUT,DELETE");
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,Authorization,Content-Type");
        response.setHeader("Access-Control-Allow-Credentials","true");

        if(!request.getMethod().equalsIgnoreCase("OPTIONS")){
            //不对options请求做任何判断，因为options请求不会携带cookie
            if(auth(request)){
                //需要验证权限
                String username = (String) request.getSession().getAttribute("username");
                if(username == null){
                    response.getWriter().println(new Gson().toJson(Result.error("需要登录才可以进行下一步操作")));
                    return;
                }
            }
        }
        chain.doFilter(req, resp);
    }
    private boolean auth(HttpServletRequest request) {
        if("/api/mall/order/getOrderByState".equalsIgnoreCase(request.getRequestURI()) ||"/api/mall/order/addOrder".equalsIgnoreCase(request.getRequestURI())||"/api/mall/goods/askGoodsMsg".equalsIgnoreCase(request.getRequestURI())|| "/api/mall/user/data".equalsIgnoreCase(request.getRequestURI())){
            return true;
        }
        return false;
    }

    public void init(FilterConfig config) throws ServletException {

    }
}
