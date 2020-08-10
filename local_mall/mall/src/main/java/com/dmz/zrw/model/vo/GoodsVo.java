package com.dmz.zrw.model.vo;

public class GoodsVo {
    Integer id;
    String name;
    Integer typeId;
    String img;
    String desc;

    Double unitPrice;


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

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "GoodsVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", typeId='" + typeId + '\'' +
                ", img='" + img + '\'' +
                ", desc='" + desc + '\'' +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
