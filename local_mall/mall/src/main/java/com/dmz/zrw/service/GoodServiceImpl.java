package com.dmz.zrw.service;

import com.dmz.zrw.dao.GoodsDao;
import com.dmz.zrw.dao.GoodsDapImpl;
import com.dmz.zrw.dao.OrderDao;
import com.dmz.zrw.dao.OrderDaoImpl;
import com.dmz.zrw.model.*;
import com.dmz.zrw.model.bo.*;
import com.dmz.zrw.model.vo.*;

import java.util.LinkedList;
import java.util.List;

public class GoodServiceImpl implements GoodsService {

    GoodsDao goodsDao=new GoodsDapImpl();
    OrderDao orderDao=new OrderDaoImpl();
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
        Goods goods=new Goods(addGoodsBO.getName(),Integer.parseInt(addGoodsBO.getTypeId()),imageUlr,addGoodsBO.getDesc(),stockNum,price);

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
    public List<Goods> showAllGoodsList() {
        return goodsDao.showAllGoodsList();
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

    @Override
    public GetGoodsInfoVo getGoodsInfo(Integer id) {
        return goodsDao.getGoodsInfoVo(id);
    }

    @Override
    public boolean updateGoods(UpdateGoodsBo updateGoodsBo) {
        List<UpdateGoodsSpecBo> specBOList=updateGoodsBo.getSpecBos();

        Integer stockNum=0;

        Double price=Double.MAX_VALUE;

        for (UpdateGoodsSpecBo specBO:specBOList) {
            if (specBO.getStockNum()>stockNum){
                stockNum=specBO.getStockNum();
            }
            if (price>specBO.getUnitPrice()){
                price=specBO.getUnitPrice();
            }

        }

        String preaddress="http://localhost:8084/";
        String imageUlr=preaddress+updateGoodsBo.getImg();
        Goods goods=new Goods(Integer.parseInt(updateGoodsBo.getId()),updateGoodsBo.getName(),updateGoodsBo.getTypeId(),imageUlr,updateGoodsBo.getDesc(),stockNum,price);

        boolean isUpdateGoodsInGoods = goodsDao.updateGoodsInGoods(goods);

        boolean isUpdateGoodsInSpec = goodsDao.updateGoodsInSpec(specBOList,Integer.parseInt(updateGoodsBo.getId()));

        if (isUpdateGoodsInGoods&&isUpdateGoodsInSpec){
            return true;
        }
        else {
            return false;
        }

    }

    @Override
    public boolean addSpec(UpdateNomalSpecBo updateNomalSpecBo) {
        return goodsDao.addSpec(updateNomalSpecBo);
    }

    @Override
    public boolean DeleteSpec(DeleteSpecBo deleteSpecBo) {
        return goodsDao.DeleteSpec(deleteSpecBo);
    }

    @Override
    public boolean askGoodsMsgBo(AskGoodsMsgBo askGoodsMsgBo) {
        //复用orderDao 里面的方法
        String nickname =askGoodsMsgBo.getToken();
        Integer goodsId = askGoodsMsgBo.getGoodsId();
        String msg = askGoodsMsgBo.getMsg();
        User user = orderDao.searchUserInfomation(nickname);
        Goods goods = orderDao.searchgoodInformatin(goodsId);


      /*  Integer id;
        Integer userId;
        String username;
        Integer goodId;
        String goodsname;
        Integer state;// 1表示未回复，0表示回复了
        String questcontent;

        Date createtime;
        Date replytime;*/

    /*  List<MsgStateBo> msgStateBoList=goodsDao.getListStateAndTime(goodsId,nickname);
        System.out.println(msgStateBoList);*/

        MallMsg mallMsg=new MallMsg();
        mallMsg.setUserId(user.getId());
        mallMsg.setUsername(user.getNickname());
        mallMsg.setGoodId(goods.getId());
        mallMsg.setGoodsname(goods.getName());

        mallMsg.setQuestcontent(msg);
      boolean isAskGoodsMsgBo = goodsDao.askGoodsMsgBo(mallMsg);
        return  isAskGoodsMsgBo;
    }

    @Override
    public List<NoReplyMsgVo> noReplyMsg() {
        return goodsDao.noReplyMsg();
    }

    @Override
    public Boolean reply(ReplyBo replyBo) {
        return goodsDao.reply(replyBo);
    }

    @Override
    public List<RepliedMsgVo> repliedMsg() {
        return goodsDao.repliedMsg();
    }

    @Override
    public List<GetGoodsMsgVo> getGoodsMsg(Integer id) {
        return goodsDao.getGoodsMsg(id);
    }

    @Override
    public List<GetGoodsCommentVo> getGoodsComment(Integer goodsId) {
        List<Comment> commentList =goodsDao.getCommentListByGoodsId(goodsId);
        System.out.println(commentList);
        List<GetGoodsCommentVo> commentVoList=new LinkedList<>();
        for (Comment comment:commentList) {
            GetGoodsCommentVo commentVo =new GetGoodsCommentVo();
            GetGoodsCommentUserVo userVo=new GetGoodsCommentUserVo(comment.getNickname());
            commentVo.setUser(userVo);
            commentVo.setScore(comment.getScore());
            commentVo.setId(comment.getId());
            commentVo.setSpecName(comment.getSpecName());
            commentVo.setComment(comment.getContent());
            commentVo.setTime(comment.getTime());
            commentVo.setUserId(comment.getUserId());
            commentVoList.add(commentVo);

        }
        return commentVoList;
    }

    @Override
    public Double getGoodsCommentRate(Integer goodsId) {
        return goodsDao.getGoodsCommentRate(goodsId);
    }

    @Override
    public List<SearchGoodsVo> searchGoods(String keyword) {
        return goodsDao.searchGoods(keyword);
    }
}
