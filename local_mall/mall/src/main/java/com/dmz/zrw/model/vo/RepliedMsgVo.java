package com.dmz.zrw.model.vo;

import java.sql.Date;

public class RepliedMsgVo {
    Integer id;
    Integer userId;
    Integer goodsId;
    String content;
    Integer state;
    Date createtime;
    String replyContent;
    RepliedMsgVoGoods goods=new RepliedMsgVoGoods();
    RepliedMsgVouser  user=new RepliedMsgVouser();

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public RepliedMsgVoGoods getGoods() {
        return goods;
    }

    public void setGoods(RepliedMsgVoGoods goods) {
        this.goods = goods;
    }

    public RepliedMsgVouser getUser() {
        return user;
    }

    public void setUser(RepliedMsgVouser user) {
        this.user = user;
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


}
