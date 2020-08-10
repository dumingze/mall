package com.dmz.zrw.dao;

import com.dmz.zrw.model.Goods;
import com.dmz.zrw.model.GoodsType;
import com.dmz.zrw.model.bo.*;
import com.dmz.zrw.model.vo.GetGoodsInfoVo;

import java.util.List;

public interface GoodsDao {
    List<GoodsType> getType();

    boolean addType(AddTypeBo addTypeBo);

    boolean addToFormGoods(Goods goods);

    int getLastId();

    boolean addSpecs(List<AddSpecBO> specBOList, int goodsId);

    List<Goods> showGoodsList(Integer typeId);
    List<Goods> showAllGoodsList();

    boolean deleteFromGoods(Integer id);

    boolean deleteFromSpec(Integer id);

    GetGoodsInfoVo getGoodsInfoVo(Integer id);

    boolean updateGoodsInGoods(Goods goods);


    boolean updateGoodsInSpec(List<UpdateGoodsSpecBo> specBOList, int parseInt);

    boolean addSpec(UpdateNomalSpecBo updateNomalSpecBo);

    boolean DeleteSpec(DeleteSpecBo deleteSpecBo);


}
