package com.dmz.zrw.model.bo;

public class AddOrderFromMall {
   Double amount;
   Integer goodsDetailId;
   Integer num;
   Integer state;
   String token;

    public AddOrderFromMall(Double amount, Integer goodsDetailId, Integer num, Integer state, String token) {
        this.amount = amount;
        this.goodsDetailId = goodsDetailId;
        this.num = num;
        this.state = state;
        this.token = token;
    }

    public AddOrderFromMall() {
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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "AddOrderFromMall{" +
                "amount=" + amount +
                ", goodsDetailId=" + goodsDetailId +
                ", num=" + num +
                ", state=" + state +
                ", token='" + token + '\'' +
                '}';
    }
}
