package com.dmz.zrw.model.bo;

import java.util.List;

public class UpdateGoodsBo {

   String id;
   String desc;

   String img;
   String name;
  //  specList: [{id: 1233, specName: "l", stockNum: 83, unitPrice: 500},…]
    Integer typeId;
    List<UpdateGoodsSpecBo> specList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public List<UpdateGoodsSpecBo> getSpecBos() {
        return specList;
    }

    public void setSpecBos(List<UpdateGoodsSpecBo> specBos) {
        this.specList = specBos;
    }

    public UpdateGoodsBo(String id, String desc, String img, String name, Integer typeId, List<UpdateGoodsSpecBo> specBos) {
        this.id = id;
        this.desc = desc;
        this.img = img;
        this.name = name;
        this.typeId = typeId;
        this.specList = specBos;
    }

    public UpdateGoodsBo() {
    }

    @Override
    public String toString() {
        return "UpdateGoodsBo{" +
                "id='" + id + '\'' +
                ", desc='" + desc + '\'' +
                ", img='" + img + '\'' +
                ", name='" + name + '\'' +
                ", typeId=" + typeId +
                ", specBos=" + specList +
                '}';
    }
}
