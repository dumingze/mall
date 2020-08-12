package com.dmz.zrw.model.bo;

public class CommitcommentBo {
    String content;
    Integer goodsDetailId;
    Integer goodsId;
    Integer orderId;
    Double score;
    String token;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getGoodsDetailId() {
        return goodsDetailId;
    }

    public void setGoodsDetailId(Integer goodsDetailId) {
        this.goodsDetailId = goodsDetailId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "CommitcommentBo{" +
                "content='" + content + '\'' +
                ", goodsDetailId=" + goodsDetailId +
                ", goodsId=" + goodsId +
                ", orderId=" + orderId +
                ", score=" + score +
                ", token='" + token + '\'' +
                '}';
    }
}
