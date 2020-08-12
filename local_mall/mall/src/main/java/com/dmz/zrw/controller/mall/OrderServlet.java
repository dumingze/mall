package com.dmz.zrw.controller.mall;

import com.dmz.zrw.model.Result;
import com.dmz.zrw.model.bo.AddOrderFromMall;
import com.dmz.zrw.model.bo.CommitcommentBo;
import com.dmz.zrw.model.vo.GetOrderByStateVo;
import com.dmz.zrw.service.OrderSurvice;
import com.dmz.zrw.service.OrderSurviceImpl;
import com.dmz.zrw.utils.MyStreamUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/mall/order/*")
public class OrderServlet extends HttpServlet {
    OrderSurvice orderSurvice=new OrderSurviceImpl();
    Gson gson=new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String replace = requestURI.replace("/api/mall/order/", "");
        if ("addOrder".equals(replace)){

        boolean isAddOrder =   addOrder(request,response);
        if (isAddOrder){
            response.getWriter().print(gson.toJson(Result.ok()));
        }
        else {
            response.getWriter().print(gson.toJson(Result.error("添加失败")));
        }

        }
        else if ("sendComment".equals(replace)){
            boolean isSendComment=sendComment(request,response);
            if (isSendComment){
                response.getWriter().print(gson.toJson(Result.ok()));
                return;
            }
            else {
                response.getWriter().print(gson.toJson(Result.error("添加评论失败")));
                return;
            }
        }

    }

    private boolean sendComment(HttpServletRequest request, HttpServletResponse response) {
        String string =null;
        try {
            string= MyStreamUtils.getInputStreamToString(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

        CommitcommentBo commitcommentBo = gson.fromJson(string, CommitcommentBo.class);

        return  orderSurvice.sendComment(commitcommentBo);

    }

    private boolean addOrder(HttpServletRequest request, HttpServletResponse response) {
        String string=null;
        try {
             string = MyStreamUtils.getInputStreamToString(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

        AddOrderFromMall addOrderFromMall = gson.fromJson(string, AddOrderFromMall.class);

       return orderSurvice.addOrder(addOrderFromMall);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        String replace = requestURI.replace("/api/mall/order/", "");
        if ("getOrderByState".equals(replace)){
         List<GetOrderByStateVo> getOrderByStateVoList= getOrderByState(request,response);
         if (getOrderByStateVoList!=null){
             response.getWriter().print(gson.toJson(Result.ok(getOrderByStateVoList)));
             return;
         }
         else {
             response.getWriter().print(gson.toJson(Result.error("展示错误")));
             return;
         }
        }
        else if ("pay".equals(replace)){
            Integer id=Integer.parseInt(request.getParameter("id"));

           boolean isPay =pay(id);
           if (isPay){
               response.getWriter().print(gson.toJson(Result.ok()));
               return;
           }
           else {
               response.getWriter().print(gson.toJson(Result.error("付款失败")));
               return;
           }
        }

       else if ("confirmReceive".equals(replace)){
            Integer id=Integer.parseInt(request.getParameter("id"));

            boolean isConfirmReceive =confirmReceive(id);
            if (isConfirmReceive){
                response.getWriter().print(gson.toJson(Result.ok()));
                return;
            }
            else {
                response.getWriter().print(gson.toJson(Result.error("确认收货失败")));
                return;
            }

        }
       else if ("deleteOrder".equals(replace)){
          boolean isDelete=  deleteOrder(request,response);
          if (isDelete){
              response.getWriter().print(gson.toJson(Result.ok()));
              return;
          }
          else {
              response.getWriter().print(gson.toJson(Result.error("删除购物车失败")));
          }
        }
    }

    private boolean deleteOrder(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        return  orderSurvice.deleteOrder(id);
    }

    private boolean confirmReceive(Integer id) {
        return orderSurvice.confirmReceive(id);
    }

    private boolean pay(Integer id) {
      return   orderSurvice.pay(id);
    }

    private List<GetOrderByStateVo> getOrderByState(HttpServletRequest request, HttpServletResponse response) {
        Integer state =Integer.parseInt( request.getParameter("state"));
        String nickname =request.getParameter("token");
        return  orderSurvice.getOrderByState(state,nickname);
    }
}
