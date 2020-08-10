package com.dmz.zrw.model.vo;

import com.dmz.zrw.model.Goods;
import com.dmz.zrw.model.Spec;

import java.util.ArrayList;
import java.util.List;

public class GetGoodsInfoVo {
    List<SpecVo>specs =new ArrayList<>();
    GoodsVo goods=new GoodsVo();


    public GetGoodsInfoVo(List<SpecVo> specs, GoodsVo goods) {
        this.specs = specs;
        this.goods = goods;
    }

    public GetGoodsInfoVo() {
    }

    public List<SpecVo> getSpecs() {
        return specs;
    }

    public void setSpecs(List<SpecVo> specs) {
        this.specs = specs;
    }

    public GoodsVo getGoods() {
        return goods;
    }

    public void setGoods(GoodsVo goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "GetGoodsInfoVo{" +
                "specs=" + specs +
                ", goods=" + goods +
                '}';
    }
}
