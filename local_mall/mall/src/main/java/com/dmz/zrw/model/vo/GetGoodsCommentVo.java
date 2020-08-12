package com.dmz.zrw.model.vo;

import java.sql.Date;

public class GetGoodsCommentVo {
     Double score;
     Integer id;
     String specName;
     String comment;
     Date time;
     Integer userId;
     GetGoodsCommentUserVo user=new GetGoodsCommentUserVo();

    public GetGoodsCommentUserVo getUser() {
        return user;
    }

    public void setUser(GetGoodsCommentUserVo user) {
        this.user = user;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public GetGoodsCommentVo(Double score, Integer id, String specName, String comment, Date time, Integer userId) {
        this.score = score;
        this.id = id;
        this.specName = specName;
        this.comment = comment;
        this.time = time;
        this.userId = userId;
    }

    public GetGoodsCommentVo() {
    }

    @Override
    public String toString() {
        return "GetGoodsCommentVo{" +
                "score=" + score +
                ", id=" + id +
                ", specName='" + specName + '\'' +
                ", comment='" + comment + '\'' +
                ", time=" + time +
                ", userId=" + userId +
                '}';
    }
}
