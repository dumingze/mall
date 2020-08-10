package com.dmz.zrw.model.bo;

public class UpdateGoodsSpecBo {
    Integer id;
    String specName;
    Integer stockNum;
    Double unitPrice;

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

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public UpdateGoodsSpecBo(Integer id, String specName, Integer stockNum, Double unitPrice) {
        this.id = id;
        this.specName = specName;
        this.stockNum = stockNum;
        this.unitPrice = unitPrice;
    }

    public UpdateGoodsSpecBo() {
    }

    @Override
    public String toString() {
        return "UpdateGoodsSpecBo{" +
                "id=" + id +
                ", specName='" + specName + '\'' +
                ", stockNum=" + stockNum +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
