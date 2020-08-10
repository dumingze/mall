package com.dmz.zrw.controller.admin;

import com.dmz.zrw.model.Result;
import com.dmz.zrw.model.bo.ChangeOrderBo;
import com.dmz.zrw.model.bo.OrdersByPageBo;
import com.dmz.zrw.model.vo.InspectOrderVo;
import com.dmz.zrw.model.vo.OrdersByPageVo;
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
import java.util.Map;

@WebServlet("/api/admin/order/*")
public class OrdersServlet extends HttpServlet {

    Gson gson=new Gson();
    OrderSurvice orderSurvice=new OrderSurviceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        String replace = requestURI.replace("/api/admin/order/", "");
        if ("ordersByPage".equals(replace)){

            Map<String, Object> resulyMap = ordersByPage(request, response);
            System.out.println("展示订单"+resulyMap);
            response.getWriter().print(gson.toJson(Result.ok(resulyMap)));



            return;
        }
        else  if ("changeOrder".equals(replace)){
            String string = MyStreamUtils.getInputStreamToString(request);
            ChangeOrderBo changeOrderBo = gson.fromJson(string, ChangeOrderBo.class);
            boolean isChangeOrder =changeOrder(changeOrderBo);
            if (isChangeOrder){
                response.getWriter().print(gson.toJson(Result.ok()));
                return;
            }
            else {
                response.getWriter().print(gson.toJson(Result.error("删除失败")));
                return;
            }
        }

    }
    private boolean changeOrder(ChangeOrderBo changeOrderBo) {
        return   orderSurvice.changeOrder(changeOrderBo);
    }

    private Map<String, Object> ordersByPage(HttpServletRequest request, HttpServletResponse response) {

        String stringGson=null;
        try {
           stringGson= MyStreamUtils.getInputStreamToString(request);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(stringGson);
        OrdersByPageBo ordersByPageBo = gson.fromJson(stringGson, OrdersByPageBo.class);
        System.out.println(ordersByPageBo);



        Map<String, Object> mapResult=orderSurvice.ordersByPage(ordersByPageBo);
        return mapResult;


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        String replace = requestURI.replace("/api/admin/order/", "");
        if ("deleteOrder".equals(replace)){
          boolean isDeleteOrder = deleteOrder(request,response);
          if (isDeleteOrder){
              response.getWriter().print(gson.toJson(Result.ok()));
              return;
          }
          else {
              response.getWriter().print(gson.toJson(Result.error("删除失败")));
          }
        }
        else//先展示order
            if("order".equals(replace)){
            Integer id  = Integer.parseInt(request.getParameter("id"));


            System.out.println("显示的id"+id);
         InspectOrderVo inspectOrderVo=inspectOrder(id);

         response.getWriter().print(gson.toJson(Result.ok(inspectOrderVo)));
         return;

        }

    }



    private InspectOrderVo inspectOrder(Integer id) {
       return orderSurvice.inspectOrder(id);
    }

    private boolean deleteOrder(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.parseInt(request.getParameter("id"));
       return orderSurvice.deleteOrder(id);
    }
}
