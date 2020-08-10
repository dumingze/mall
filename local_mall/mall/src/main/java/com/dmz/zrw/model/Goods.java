package com.dmz.zrw.model;

public class Goods {
    Integer id;
    String name;
    Integer typeId;
    String img;
    String desc;
    Integer stockNum;
    Double price;

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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public Goods(String name, Integer typeId, String img, String desc, Integer stockNum, Double price) {
        this.name = name;
        this.typeId = typeId;
        this.img = img;
        this.desc = desc;
        this.stockNum = stockNum;
        this.price = price;
    }

    public Goods(Integer id, String name, Integer typeId, String img, String desc, Integer stockNum, Double price) {
        this.id = id;
        this.name = name;
        this.typeId = typeId;
        this.img = img;
        this.desc = desc;
        this.stockNum = stockNum;
        this.price = price;
    }

    public Goods() {

    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", typeId='" + typeId + '\'' +
                ", img='" + img + '\'' +
                ", desc='" + desc + '\'' +
                ", stockNum=" + stockNum +
                ", price=" + price +
                '}';
    }
}
