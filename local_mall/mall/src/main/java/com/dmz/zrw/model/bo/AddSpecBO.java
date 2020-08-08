package com.dmz.zrw.model.bo;

/**
 * 用于后台管理系统添加商品规格
 */
public class AddSpecBO {

    private String specName;

    private String stockNum;

    private String unitPrice;

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getStockNum() {
        return stockNum;
    }

    public void setStockNum(String stockNum) {
        this.stockNum = stockNum;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "AddSpecBO{" +
                "specName='" + specName + '\'' +
                ", stockNum='" + stockNum + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                '}';
    }
}
