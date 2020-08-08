package com.dmz.zrw.service;

import com.dmz.zrw.dao.GoodsDao;
import com.dmz.zrw.dao.GoodsDapImpl;
import com.dmz.zrw.model.Goods;
import com.dmz.zrw.model.GoodsType;
import com.dmz.zrw.model.bo.AddGoodsBO;
import com.dmz.zrw.model.bo.AddSpecBO;
import com.dmz.zrw.model.bo.AddTypeBo;

import java.util.List;

public class GoodServiceImpl implements GoodsService {

    GoodsDao goodsDao=new GoodsDapImpl();
    @Override
    public List<GoodsType> getGoodsType() {
        return goodsDao.getType() ;
    }

    @Override
    public boolean addType(AddTypeBo addTypeBo) {
        return  goodsDao.addType(addTypeBo);
    }

    @Override
    public boolean addGoods(AddGoodsBO addGoodsBO) {
        List<AddSpecBO> specBOList=addGoodsBO.getSpecList();
        System.out.println("specBOList"+specBOList);
        Integer stockNum=0;

        Double price=Double.MAX_VALUE;

        for (AddSpecBO addSpecBO:specBOList) {
            if (Integer.parseInt(addSpecBO.getStockNum())>stockNum){
                stockNum=Integer.parseInt(addSpecBO.getStockNum());
            }
            if (price>Double.parseDouble(addSpecBO.getUnitPrice())){
                price=Double.parseDouble(addSpecBO.getUnitPrice());
            }
        }
     /*   for (AddSpecBO addSpecBO : specBOList) {
            if(price > Double.parseDouble(addSpecBO.getUnitPrice())){
                price = Double.parseDouble(addSpecBO.getUnitPrice());
            }
            if(stockNum < Integer.parseInt(addSpecBO.getStockNum())){
                stockNum = Integer.parseInt(addSpecBO.getStockNum());
            }
        }*/
        String preaddress="http://localhost:8084/";
        String imageUlr=preaddress+addGoodsBO.getImg();
        Goods goods=new Goods(addGoodsBO.getName(),addGoodsBO.getTypeId(),imageUlr,addGoodsBO.getDesc(),stockNum,price);

        boolean isaddToFormGoods=goodsDao.addToFormGoods(goods);

        int goodsId = goodsDao.getLastId();

        boolean isaddSpecs=goodsDao.addSpecs(specBOList, goodsId);


        if (isaddToFormGoods&&isaddSpecs){
            return true;
        }
        else {
            return false;
        }


    }

    @Override
    public List<Goods> showGoodsList(Integer typeId) {
        return goodsDao.showGoodsList(typeId);
    }

    @Override
    public boolean deleteGoods(Integer id) {
        boolean deleteFromGoods=goodsDao.deleteFromGoods(id);

       boolean deleteFromSpec = goodsDao.deleteFromSpec(id);

       if (deleteFromGoods&deleteFromSpec){

           return true;

       }
       else {
           return false;
       }
    }
}
