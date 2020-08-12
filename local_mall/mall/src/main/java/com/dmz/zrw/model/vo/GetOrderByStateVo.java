package com.dmz.zrw.model.vo;

import java.sql.Date;

public class GetOrderByStateVo {
    Integer id;
    Integer state;
    Integer goodsNum;
    Double amount;
    Integer goodsDetailId;
    Date createtime;
    boolean hasComment;
    GetOrderByStateVogoodsVo goods=new GetOrderByStateVogoodsVo();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getGoodsDetailId() {
        return goodsDetailId;
    }

    public void setGoodsDetailId(Integer goodsDetailId) {
        this.goodsDetailId = goodsDetailId;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public boolean isHasComment() {
        return hasComment;
    }

    public void setHasComment(boolean hasComment) {
        this.hasComment = hasComment;
    }

    public GetOrderByStateVogoodsVo getGoods() {
        return goods;
    }

    public void setGoods(GetOrderByStateVogoodsVo goods) {
        this.goods = goods;
    }

    public GetOrderByStateVo(Integer id, Integer state, Integer goodsNum, Double amount, Integer goodsDetailId, Date createtime, boolean hasComment, GetOrderByStateVogoodsVo goods) {
        this.id = id;
        this.state = state;
        this.goodsNum = goodsNum;
        this.amount = amount;
        this.goodsDetailId = goodsDetailId;
        this.createtime = createtime;
        this.hasComment = hasComment;
        this.goods = goods;
    }

    public GetOrderByStateVo() {
    }

    @Override
    public String toString() {
        return "GetOrderByStateVo{" +
                "id=" + id +
                ", state=" + state +
                ", goodsNum=" + goodsNum +
                ", amount=" + amount +
                ", goodsDetailId=" + goodsDetailId +
                ", createtime=" + createtime +
                ", hasComment=" + hasComment +
                ", goods=" + goods +
                '}';
    }
}
