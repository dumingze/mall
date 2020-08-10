package com.dmz.zrw.model.bo;

public class DeleteSpecBo {
    String goodsId;
    String specName;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    @Override
    public String toString() {
        return "DeleteSpecBo{" +
                "goodsId='" + goodsId + '\'' +
                ", specName='" + specName + '\'' +
                '}';
    }
}
