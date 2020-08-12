package com.dmz.zrw.model.vo;

import java.sql.Date;

public class NoReplyMsgVo {

    Integer id;
    Integer userId;
    Integer goodsId;
    String content;
    Integer state;
    Date createtime;
    NoReplyMsgVoGoods goods =new NoReplyMsgVoGoods();
    NoReplyMsgVouser  user =new NoReplyMsgVouser();

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

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public NoReplyMsgVoGoods getGoods() {
        return goods;
    }

    public void setGoods(NoReplyMsgVoGoods goods) {
        this.goods = goods;
    }

    public NoReplyMsgVouser getUser() {
        return user;
    }

    public void setUser(NoReplyMsgVouser user) {
        this.user = user;
    }
}
