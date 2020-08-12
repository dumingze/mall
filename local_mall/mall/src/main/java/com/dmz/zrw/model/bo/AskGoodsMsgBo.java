package com.dmz.zrw.model.bo;

public class AskGoodsMsgBo {
   Integer goodsId;
   String msg;// nickname 提问的内容
   String token;//user nickname

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "AskGoodsMsgBo{" +
                "goodsId=" + goodsId +
                ", msg='" + msg + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
