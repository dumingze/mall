package com.dmz.zrw.dao;

import com.dmz.zrw.model.*;
import com.dmz.zrw.model.bo.ChangeOrderBo;
import com.dmz.zrw.model.bo.OrdersByPageBo;
import com.dmz.zrw.model.vo.GetOrderByStateVo;
import com.dmz.zrw.model.vo.InspectOrderVo;
import com.dmz.zrw.model.vo.OrdersByPageVo;

import java.util.List;

public interface OrderDao {
    int getOrderTotal(OrdersByPageBo ordersByPageBo);

    List<OrdersByPageVo> getOrdersByPageVoList(OrdersByPageBo ordersByPageBo);

    boolean deleteOrder(Integer id);

    InspectOrderVo inspectOrder(Integer id);

    boolean changeOrder(ChangeOrderBo changeOrderBo);

    User searchUserInfomation(String nickname);

    Spec searchSpecInformatin(Integer specId);

    Goods searchgoodInformatin(Integer goodId);

    boolean addOrder(Orders orders);

    List<GetOrderByStateVo> getOrderByState(Integer state, String nickname);

    boolean pay(Integer id);

    boolean confirmReceive(Integer id);

    Orders getOrderByOrderId(Integer orderId);

    boolean insertIntoComment(Comment comment);

    boolean changeHasCommentInOrders(Integer orderId);
}
