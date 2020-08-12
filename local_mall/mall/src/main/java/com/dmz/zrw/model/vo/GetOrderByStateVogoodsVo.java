package com.dmz.zrw.model.vo;

public class GetOrderByStateVogoodsVo {
    Integer id;
    String img;
    String name;
    Integer goodsDetailId;
    String spec;
    Double unitPrice;

    public GetOrderByStateVogoodsVo(Integer id, String img, String name, Integer goodsDetailId, String spec, Double unitPrice) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.goodsDetailId = goodsDetailId;
        this.spec = spec;
        this.unitPrice = unitPrice;
    }

    public GetOrderByStateVogoodsVo() {
    }

    @Override
    public String toString() {
        return "GetOrderByStateVogoodsVo{" +
                "id=" + id +
                ", img='" + img + '\'' +
                ", name='" + name + '\'' +
                ", goodsDetailId=" + goodsDetailId +
                ", spec='" + spec + '\'' +
                ", unitPrice=" + unitPrice +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGoodsDetailId() {
        return goodsDetailId;
    }

    public void setGoodsDetailId(Integer goodsDetailId) {
        this.goodsDetailId = goodsDetailId;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
