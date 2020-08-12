package com.dmz.zrw.model;

import java.sql.Date;

public class Comment {
    Integer id;
    Integer orderId;
    Integer goodsId;
    Integer goodsDetailId;
    Integer userId;
    String specName;
    String nickname;
    String content;
    Double score;
    Date time;

    public Comment(Integer id, Integer orderId, Integer goodsId, Integer goodsDetailId, Integer userId, String specName, String nickname, String content, Double score, Date time) {
        this.id = id;
        this.orderId = orderId;
        this.goodsId = goodsId;
        this.goodsDetailId = goodsDetailId;
        this.userId = userId;
        this.specName = specName;
        this.nickname = nickname;
        this.content = content;
        this.score = score;
        this.time = time;
    }

    public Comment() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getGoodsDetailId() {
        return goodsDetailId;
    }

    public void setGoodsDetailId(Integer goodsDetailId) {
        this.goodsDetailId = goodsDetailId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", goodsId=" + goodsId +
                ", goodsDetailId=" + goodsDetailId +
                ", userId=" + userId +
                ", specName='" + specName + '\'' +
                ", nickname='" + nickname + '\'' +
                ", content='" + content + '\'' +
                ", score=" + score +
                ", time=" + time +
                '}';
    }
}
