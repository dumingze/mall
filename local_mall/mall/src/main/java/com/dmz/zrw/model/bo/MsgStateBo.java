package com.dmz.zrw.model.bo;

import java.sql.Date;

public class MsgStateBo {

    Integer stateId;
    Date createtime;

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "MsgStateBo{" +
                "stateId=" + stateId +
                ", createtime=" + createtime +
                '}';
    }
}
