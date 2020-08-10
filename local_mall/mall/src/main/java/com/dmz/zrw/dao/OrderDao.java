package com.dmz.zrw.dao;

import com.dmz.zrw.model.bo.ChangeOrderBo;
import com.dmz.zrw.model.bo.OrdersByPageBo;
import com.dmz.zrw.model.vo.InspectOrderVo;
import com.dmz.zrw.model.vo.OrdersByPageVo;

import java.util.List;

public interface OrderDao {
    int getOrderTotal(OrdersByPageBo ordersByPageBo);

    List<OrdersByPageVo> getOrdersByPageVoList(OrdersByPageBo ordersByPageBo);

    boolean deleteOrder(Integer id);

    InspectOrderVo inspectOrder(Integer id);

    boolean changeOrder(ChangeOrderBo changeOrderBo);
}
