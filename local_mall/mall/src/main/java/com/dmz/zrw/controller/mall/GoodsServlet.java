package com.dmz.zrw.controller.mall;

import com.dmz.zrw.model.Goods;
import com.dmz.zrw.model.Result;
import com.dmz.zrw.model.vo.GetGoodsInfoVo;
import com.dmz.zrw.model.vo.GoodInfoVo;
import com.dmz.zrw.model.vo.GoodsVo;
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

@WebServlet("/api/mall/goods/*")
public class GoodsServlet extends HttpServlet {

    Gson gson=new Gson();
    GoodsService goodsService=new GoodServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String replace = requestURI.replace("/api/mall/goods/", "");
        if ("getGoodsByType".equals(replace)){
        List<Goods> goods= getGoodsByType(request,response);
        response.getWriter().print(gson.toJson(Result.ok(goods)));
        }
        else if("getGoodsInfo".equals(replace)){
        GetGoodsInfoVo goodsInfoVo=getGoodsInfo(request,response);
            GoodsVo gvo=goodsInfoVo.getGoods();
        GoodInfoVo goodInfoVo=new GoodInfoVo(gvo.getImg(),gvo.getName(),gvo.getDesc(),gvo.getTypeId(),goodsInfoVo.getSpecs(),gvo.getUnitPrice());

        response.getWriter().print(gson.toJson(Result.ok(goodInfoVo)));
        }
    }

    private GetGoodsInfoVo getGoodsInfo(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        GetGoodsInfoVo goodsInfo = goodsService.getGoodsInfo(id);

        goodsInfo.getGoods().setId(null);
        return goodsInfo;
    }

    private List<Goods> getGoodsByType(HttpServletRequest request, HttpServletResponse response) {

       // System.out.println("typeId"+request.getParameter("typeId"));
        Integer typeId = Integer.parseInt( request.getParameter("typeId"));
        // Integer typeId = Integer.parseInt(request.getParameter("typeId"));
        List<Goods> goods=null;
        if (typeId!=(-1)){
             goods = goodsService.showGoodsList(typeId);
         //   System.out.println(goods);
        }
        else {
            goods=goodsService.showAllGoodsList();
        }
        return goods;
    }
}
