package com.dmz.zrw.service;

import com.dmz.zrw.model.bo.AddOrderFromMall;
import com.dmz.zrw.model.bo.ChangeOrderBo;
import com.dmz.zrw.model.bo.CommitcommentBo;
import com.dmz.zrw.model.bo.OrdersByPageBo;
import com.dmz.zrw.model.vo.GetOrderByStateVo;
import com.dmz.zrw.model.vo.InspectOrderVo;

import java.util.List;
import java.util.Map;

public interface OrderSurvice {
    Map<String, Object> ordersByPage(OrdersByPageBo ordersByPageBo);

    boolean deleteOrder(Integer id);
    InspectOrderVo inspectOrder(Integer id);

    boolean changeOrder(ChangeOrderBo changeOrderBo);

    boolean addOrder(AddOrderFromMall addOrderFromMall);

    List<GetOrderByStateVo> getOrderByState(Integer state, String nickname);

    boolean pay(Integer id);

    boolean confirmReceive(Integer id);

    boolean sendComment(CommitcommentBo commitcommentBo);
}
