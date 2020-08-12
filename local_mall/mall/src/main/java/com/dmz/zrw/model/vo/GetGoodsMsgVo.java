package com.dmz.zrw.model.vo;

import java.sql.Date;

public class GetGoodsMsgVo {
    Integer id;
    String content;
    String asker;
    Date time;
    GetGoodsMsgVoReplyVo reply=new GetGoodsMsgVoReplyVo();

    public GetGoodsMsgVo(Integer id, String content, String asker, Date time, GetGoodsMsgVoReplyVo reply) {
        this.id = id;
        this.content = content;
        this.asker = asker;
        this.time = time;
        this.reply = reply;
    }

    public GetGoodsMsgVoReplyVo getReply() {
        return reply;
    }

    public void setReply(GetGoodsMsgVoReplyVo reply) {
        this.reply = reply;
    }

    public GetGoodsMsgVo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAsker() {
        return asker;
    }

    public void setAsker(String asker) {
        this.asker = asker;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }



    @Override
    public String toString() {
        return "GetGoodsMsgVo{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", asker='" + asker + '\'' +
                ", time=" + time +
                ", getGoodsMsgVoReplyVo=" + reply +
                '}';
    }
}
