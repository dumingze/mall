package com.dmz.zrw.controller.mall;

import com.dmz.zrw.model.GoodsType;
import com.dmz.zrw.model.Result;
import com.dmz.zrw.service.GoodServiceImpl;
import com.dmz.zrw.service.GoodsService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/mall/index/*")
public class IndexServlet extends HttpServlet {
    Gson gson=new Gson();
    GoodsService goodsService=new GoodServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String replace = requestURI.replace("/api/mall/index/", "");
        if ("getType".equals(replace)){
            List<GoodsType> type = getType();
            response.getWriter().print(gson.toJson(Result.ok(type)));
        }
    }

    private  List<GoodsType> getType() {
        List<GoodsType> goodsType = goodsService.getGoodsType();
        System.out.println(gson.toJson(Result.ok(goodsType)));
        return goodsType;
    }
}
