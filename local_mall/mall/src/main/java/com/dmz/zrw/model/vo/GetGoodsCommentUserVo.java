package com.dmz.zrw.model.vo;

public class GetGoodsCommentUserVo {
    String nickname;

    public GetGoodsCommentUserVo(String nickname) {
        this.nickname = nickname;
    }

    public GetGoodsCommentUserVo() {
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
