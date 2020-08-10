package com.dmz.zrw.service;

import com.dmz.zrw.model.bo.ChangeOrderBo;
import com.dmz.zrw.model.bo.OrdersByPageBo;
import com.dmz.zrw.model.vo.InspectOrderVo;

import java.util.Map;

public interface OrderSurvice {
    Map<String, Object> ordersByPage(OrdersByPageBo ordersByPageBo);

    boolean deleteOrder(Integer id);
    InspectOrderVo inspectOrder(Integer id);

    boolean changeOrder(ChangeOrderBo changeOrderBo);
}
