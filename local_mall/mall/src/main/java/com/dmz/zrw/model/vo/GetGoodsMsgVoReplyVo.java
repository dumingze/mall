package com.dmz.zrw.model.vo;

import java.sql.Date;

public class GetGoodsMsgVoReplyVo {
    String content;
    Date time;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "GetGoodsMsgVoReplyVo{" +
                "content='" + content + '\'' +
                ", time=" + time +
                '}';
    }

    public GetGoodsMsgVoReplyVo(String content, Date time) {
        this.content = content;
        this.time = time;
    }

    public GetGoodsMsgVoReplyVo() {
    }
}
