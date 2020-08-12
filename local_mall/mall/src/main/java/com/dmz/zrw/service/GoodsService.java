package com.dmz.zrw.service;

import com.dmz.zrw.model.Goods;
import com.dmz.zrw.model.GoodsType;
import com.dmz.zrw.model.bo.*;
import com.dmz.zrw.model.vo.*;

import java.util.List;

public interface GoodsService {
    List<GoodsType> getGoodsType();

    boolean addType(AddTypeBo addTypeBo);

    boolean addGoods(AddGoodsBO addGoodsBO);

    List<Goods> showGoodsList(Integer typeId);
    List<Goods> showAllGoodsList();

    boolean deleteGoods(Integer id);

    GetGoodsInfoVo getGoodsInfo(Integer id);

    boolean updateGoods(UpdateGoodsBo updateGoodsBo);

    boolean addSpec(UpdateNomalSpecBo updateNomalSpecBo);

    boolean DeleteSpec(DeleteSpecBo deleteSpecBo);


    boolean askGoodsMsgBo(AskGoodsMsgBo askGoodsMsgBo);

    List<NoReplyMsgVo> noReplyMsg();

    Boolean reply(ReplyBo replyBo);

    List<RepliedMsgVo> repliedMsg();

    List<GetGoodsMsgVo> getGoodsMsg(Integer id);

    List<GetGoodsCommentVo> getGoodsComment(Integer goodsId);

    Double getGoodsCommentRate(Integer goodsId);

    List<SearchGoodsVo> searchGoods(String keyword);
}
