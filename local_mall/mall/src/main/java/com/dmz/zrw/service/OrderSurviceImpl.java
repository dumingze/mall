package com.dmz.zrw.service;

import com.dmz.zrw.dao.OrderDao;
import com.dmz.zrw.dao.OrderDaoImpl;
import com.dmz.zrw.model.bo.ChangeOrderBo;
import com.dmz.zrw.model.bo.OrdersByPageBo;
import com.dmz.zrw.model.vo.InspectOrderVo;
import com.dmz.zrw.model.vo.OrdersByPageVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderSurviceImpl implements OrderSurvice {

    OrderDao orderDao=new OrderDaoImpl();
    @Override
    public Map<String, Object> ordersByPage(OrdersByPageBo ordersByPageBo) {

        Map<String, Object> resultMap=new HashMap<>();
        int total=orderDao.getOrderTotal(ordersByPageBo);

        List<OrdersByPageVo> ordersByPageVoList=orderDao.getOrdersByPageVoList(ordersByPageBo);

        resultMap.put("total", total);
        resultMap.put("orders", ordersByPageVoList);
        return resultMap;
    }

    @Override
    public boolean deleteOrder(Integer id) {
        return orderDao.deleteOrder(id);
    }

    @Override
    public InspectOrderVo inspectOrder(Integer id) {


      InspectOrderVo inspectOrderVo=  orderDao.inspectOrder(id);
      return inspectOrderVo;

    }

    @Override
    public boolean changeOrder(ChangeOrderBo changeOrderBo) {
        return orderDao.changeOrder(changeOrderBo);
    }
}
