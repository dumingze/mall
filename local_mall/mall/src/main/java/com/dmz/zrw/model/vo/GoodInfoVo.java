package com.dmz.zrw.model.vo;

import java.util.ArrayList;
import java.util.List;

public class GoodInfoVo {

    String img;
    String name;
    String desc;
    Integer typeId;
    List<SpecVo> specs =new ArrayList<>();
    Double unitPrice;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public List<SpecVo> getSpecs() {
        return specs;
    }

    public void setSpecs(List<SpecVo> specs) {
        this.specs = specs;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public GoodInfoVo(String img, String name, String desc, Integer typeId, List<SpecVo> specs, Double unitPrice) {
        this.img = img;
        this.name = name;
        this.desc = desc;
        this.typeId = typeId;
        this.specs = specs;
        this.unitPrice = unitPrice;
    }

    public GoodInfoVo() {
    }
}
