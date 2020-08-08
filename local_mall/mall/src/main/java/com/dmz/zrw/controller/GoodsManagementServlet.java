package com.dmz.zrw.controller;

import com.dmz.zrw.model.Goods;
import com.dmz.zrw.model.GoodsType;
import com.dmz.zrw.model.Result;
import com.dmz.zrw.model.bo.AddGoodsBO;
import com.dmz.zrw.model.bo.AddTypeBo;
import com.dmz.zrw.service.GoodServiceImpl;
import com.dmz.zrw.service.GoodsService;
import com.dmz.zrw.utils.FileUploadUtils;
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

@WebServlet("/api/admin/goods/*")
public class GoodsManagementServlet extends HttpServlet {

    GoodsService goodsService=new GoodServiceImpl();
    Gson gson=new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String replace = requestURI.replace("/api/admin/goods/", "");
        System.out.println(replace);


        if ("addType".equals(replace)){
            String stringgson= MyStreamUtils.getInputStreamToString(request);
            AddTypeBo addTypeBo=gson.fromJson(stringgson, AddTypeBo.class);
            if (addTypeBo.getName()==null){
                response.getWriter().print(gson.toJson(Result.error("您添加的为空")));
                return;
            }

          boolean isAddType=  addType(addTypeBo);
            if (isAddType){
                response.getWriter().print(gson.toJson(Result.ok("添加成功")));
            }
            else {
                response.getWriter().print(gson.toJson(Result.error("重复添加")));
            }

        }

        if("imgUpload".equals(replace)){
            Map<String, Object> stringObjectMap = FileUploadUtils.parseRequest(request);
            System.out.println(stringObjectMap);
            String addressFront = (String) getServletContext().getAttribute("addressFront");
            String s=addressFront+"/";

            response.getWriter().println(gson.toJson(Result.ok(stringObjectMap.get("file"))));
        }
        if ("addGoods".equals(replace)){
            boolean isAddGoods=addGoods(request,response);
         if (isAddGoods){
             response.getWriter().print(gson.toJson(Result.ok()));
             return;
         }
         else {
             response.getWriter().print(gson.toJson(Result.error("添加失败")));
             return;
         }

        }

    }

    private boolean addGoods(HttpServletRequest request, HttpServletResponse response) {
        String stringgson=null;
        try {
            stringgson = MyStreamUtils.getInputStreamToString(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        AddGoodsBO addGoodsBO = gson.fromJson(stringgson, AddGoodsBO.class);
             return goodsService.addGoods(addGoodsBO);

    }

    private boolean addType(AddTypeBo addTypeBo) {

        return goodsService.addType(addTypeBo);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        String replace = requestURI.replace("/api/admin/goods/", "");
        System.out.println(replace);
        if ("getType".equals(replace)){
          List<GoodsType> goodsTypeList= getGoodsType();
          if (goodsTypeList!=null){
              response.getWriter().print(gson.toJson(Result.ok(goodsTypeList)));
              System.out.println(gson.toJson(Result.ok(goodsTypeList)));
              return;
          }
          else {
              response.getWriter().print(gson.toJson(Result.error("暂无查询")));

              return;
          }

        }
        else if ("getGoodsByType".equals(replace)){

          List<Goods> goodsList = showGoodsList(request,response);

          if (goodsList!=null){
              response.getWriter().print(gson.toJson(Result.ok(goodsList)));
              return;
          }
          else {
              response.getWriter().print(gson.toJson(Result.error("该类别没有商品")));
              return;
          }
        }
        else if("deleteGoods".equals(replace)){
            boolean isdeleteGoods=deleteGoods(request,response);
            if (isdeleteGoods){
                response.getWriter().print(gson.toJson(Result.ok()));
                return;
            }
            else {
                response.getWriter().print(gson.toJson(Result.error("删除失败")));
                return;
            }
        }

    }

    private boolean deleteGoods(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.parseInt(request.getParameter("id"));

        boolean isDeleteGoods=goodsService.deleteGoods(id);
        return  isDeleteGoods;

    }

    private List<Goods> showGoodsList(HttpServletRequest request, HttpServletResponse response) {

        String typeIdstr = request.getParameter("typeId");
        Integer typeId=Integer.parseInt(typeIdstr);
        return  goodsService.showGoodsList(typeId);
    }

    private List<GoodsType> getGoodsType() {

        return goodsService.getGoodsType();
    }
}
