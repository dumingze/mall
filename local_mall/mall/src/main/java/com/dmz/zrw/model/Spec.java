package com.dmz.zrw.model;

public class Spec {
    Integer id;
    String name;
    Integer stockNum;
    Double price;
    Integer goodId;

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
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

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Spec{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stockNum=" + stockNum +
                ", price=" + price +
                ", goodId=" + goodId +
                '}';
    }

    public Spec(Integer id, String name, Integer stockNum, Double price) {
        this.id = id;
        this.name = name;
        this.stockNum = stockNum;
        this.price = price;
    }

    public Spec(Integer id, String name, Integer stockNum, Double price, Integer goodId) {
        this.id = id;
        this.name = name;
        this.stockNum = stockNum;
        this.price = price;
        this.goodId = goodId;
    }

    public Spec() {
    }
}
