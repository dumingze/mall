package com.dmz.zrw.dao;

import com.dmz.zrw.model.Goods;
import com.dmz.zrw.model.GoodsType;
import com.dmz.zrw.model.bo.AddSpecBO;
import com.dmz.zrw.model.bo.AddTypeBo;

import java.util.List;

public interface GoodsDao {
    List<GoodsType> getType();

    boolean addType(AddTypeBo addTypeBo);

    boolean addToFormGoods(Goods goods);

    int getLastId();

    boolean addSpecs(List<AddSpecBO> specBOList, int goodsId);

    List<Goods> showGoodsList(Integer typeId);

    boolean deleteFromGoods(Integer id);

    boolean deleteFromSpec(Integer id);

}
