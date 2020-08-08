package com.dmz.zrw.service;

import com.dmz.zrw.model.Goods;
import com.dmz.zrw.model.GoodsType;
import com.dmz.zrw.model.bo.AddGoodsBO;
import com.dmz.zrw.model.bo.AddTypeBo;

import java.util.List;

public interface GoodsService {
    List<GoodsType> getGoodsType();

    boolean addType(AddTypeBo addTypeBo);

    boolean addGoods(AddGoodsBO addGoodsBO);

    List<Goods> showGoodsList(Integer typeId);

    boolean deleteGoods(Integer id);
}
