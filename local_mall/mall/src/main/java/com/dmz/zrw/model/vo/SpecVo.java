package com.dmz.zrw.model.vo;

public class SpecVo {
    Integer id;
    String name;
    String specName;
    Integer stockNum;
    Double unitPrice;

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "SpecVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", specName='" + specName + '\'' +
                ", stockNum=" + stockNum +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
