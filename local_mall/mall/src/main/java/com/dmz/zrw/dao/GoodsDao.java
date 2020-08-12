package com.dmz.zrw.dao;

import com.dmz.zrw.model.Comment;
import com.dmz.zrw.model.Goods;
import com.dmz.zrw.model.GoodsType;
import com.dmz.zrw.model.MallMsg;
import com.dmz.zrw.model.bo.*;
import com.dmz.zrw.model.vo.*;

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


    List<MsgStateBo> getListStateAndTime(Integer goodsId, String nickname);

    boolean askGoodsMsgBo(MallMsg mallMsg);

    List<NoReplyMsgVo> noReplyMsg();

    Boolean reply(ReplyBo replyBo);

    List<RepliedMsgVo> repliedMsg();

    List<GetGoodsMsgVo> getGoodsMsg(Integer id);

    List<Comment> getCommentListByGoodsId(Integer goodsId);

    Double getGoodsCommentRate(Integer goodsId);

    List<SearchGoodsVo> searchGoods(String keyword);
}
