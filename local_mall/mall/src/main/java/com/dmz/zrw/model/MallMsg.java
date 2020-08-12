package com.dmz.zrw.model;

import java.sql.Date;

public class MallMsg {
    Integer id;
    Integer userId;
    String username;
    Integer goodId;
    String goodsname;
    Integer state;
    String questcontent;
    String replycontent;
    Date createtime;
    Date replytime;

    public String getReplycontent() {
        return replycontent;
    }

    public void setReplycontent(String replycontent) {
        this.replycontent = replycontent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getQuestcontent() {
        return questcontent;
    }

    public void setQuestcontent(String questcontent) {
        this.questcontent = questcontent;
    }



    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getReplytime() {
        return replytime;
    }

    public void setReplytime(Date replytime) {
        this.replytime = replytime;
    }

    @Override
    public String toString() {
        return "MallMsg{" +
                "id=" + id +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", goodId=" + goodId +
                ", goodsname='" + goodsname + '\'' +
                ", state=" + state +
                ", questcontent='" + questcontent + '\'' +
                ", replycontent='" + replycontent + '\'' +
                ", createtime=" + createtime +
                ", replytime=" + replytime +
                '}';
    }
}
