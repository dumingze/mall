package com.dmz.zrw.controller.mall;

import com.dmz.zrw.dao.OrderDao;
import com.dmz.zrw.dao.OrderDaoImpl;
import com.dmz.zrw.model.Goods;
import com.dmz.zrw.model.Orders;
import com.dmz.zrw.model.Result;
import com.dmz.zrw.model.bo.AskGoodsMsgBo;
import com.dmz.zrw.model.vo.*;
import com.dmz.zrw.service.GoodServiceImpl;
import com.dmz.zrw.service.GoodsService;
import com.dmz.zrw.utils.MyStreamUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/api/mall/goods/*")
public class GoodsServlet extends HttpServlet {

    Gson gson=new Gson();

    GoodsService goodsService=new GoodServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String replace = requestURI.replace("/api/mall/goods/", "");
        if ("askGoodsMsg".equals(replace)){

            //将提问内容添加到mallmsg
          boolean isAskGoodsMsg = askGoodsMsg(request,response);

          if (isAskGoodsMsg){
              response.getWriter().print(gson.toJson(Result.ok()));
          }
          else {
              response.getWriter().print(gson.toJson(Result.error("提问失败")));
          }

        }

    }

    private boolean askGoodsMsg(HttpServletRequest request, HttpServletResponse response) {
        String string =null;
        try {
            string=MyStreamUtils.getInputStreamToString(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        AskGoodsMsgBo askGoodsMsgBo = gson.fromJson(string, AskGoodsMsgBo.class);
        System.out.println(askGoodsMsgBo);
        boolean isAskGoodsMsg=goodsService.askGoodsMsgBo(askGoodsMsgBo);
        return isAskGoodsMsg;

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
 /*       System.out.println("goodsInfoVo "+goodsInfoVo);
        System.out.println("getSpecs "+goodsInfoVo.getSpecs());*/
        GoodsVo gvo=goodsInfoVo.getGoods();
        GoodInfoVo goodInfoVo=new GoodInfoVo(gvo.getImg(),gvo.getName(),gvo.getDesc(),gvo.getTypeId(),goodsInfoVo.getSpecs(),gvo.getUnitPrice());

        response.getWriter().print(gson.toJson(Result.ok(goodInfoVo)));
        }
        else if ("getGoodsMsg".equals(replace)){
         List<GetGoodsMsgVo> getGoodsMsgVoList=  getGoodsMsg(request,response);
         if (getGoodsMsgVoList!=null){
             response.getWriter().print(gson.toJson(Result.ok(getGoodsMsgVoList)));
             return;
         }
         else {
             response.getWriter().print(gson.toJson(Result.error("展示失败")));
             return;
         }
        }
        else if ("getGoodsComment".equals(replace)){

            Map<String,Object> map=new HashMap<>();
            List<GetGoodsCommentVo> commentList=null;
            commentList= getGoodsComment(request,response);
            Double rate=getGoodsCommentRate(request);
            map.put("commentList",commentList);
            map.put("rate",rate);
            System.out.println(gson.toJson(Result.ok(map)));

            response.getWriter().print(gson.toJson(Result.ok(map)));

            return;

        }

        else if ("searchGoods".equals(replace)){

            List<SearchGoodsVo> searchGoodsVoList=searchGoods(request,response);
            if (searchGoodsVoList!=null){
                response.getWriter().print(gson.toJson(Result.ok(searchGoodsVoList)));
                return;
            }
            else {
                response.getWriter().print(gson.toJson(Result.error("无查询数据")));
                return;
            }
        }
    }

    private List<SearchGoodsVo> searchGoods(HttpServletRequest request, HttpServletResponse response) {
        String keyword = request.getParameter("keyword");
        return  goodsService.searchGoods(keyword);
    }

    private Double getGoodsCommentRate(HttpServletRequest request) {
        Integer goodsId = Integer.parseInt(request.getParameter("goodsId"));

        return goodsService.getGoodsCommentRate(goodsId);
    }

    private List<GetGoodsCommentVo> getGoodsComment(HttpServletRequest request, HttpServletResponse response) {
        Integer goodsId = Integer.parseInt(request.getParameter("goodsId"));

        return goodsService.getGoodsComment(goodsId);
    }

    private List<GetGoodsMsgVo> getGoodsMsg(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        return goodsService.getGoodsMsg(id);
    }

    private GetGoodsInfoVo getGoodsInfo(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        GetGoodsInfoVo goodsInfo = goodsService.getGoodsInfo(id);

        goodsInfo.getGoods().setId(null);
        return goodsInfo;
    }

    private List<Goods> getGoodsByType(HttpServletRequest request, HttpServletResponse response) {


        Integer typeId = Integer.parseInt( request.getParameter("typeId"));

        List<Goods> goods=null;
        if (typeId!=(-1)){
             goods = goodsService.showGoodsList(typeId);

        }
        else {
            goods=goodsService.showAllGoodsList();
        }
        return goods;
    }
}
