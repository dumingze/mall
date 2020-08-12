package com.dmz.zrw.service;

import com.dmz.zrw.dao.OrderDao;
import com.dmz.zrw.dao.OrderDaoImpl;
import com.dmz.zrw.model.*;
import com.dmz.zrw.model.bo.AddOrderFromMall;
import com.dmz.zrw.model.bo.ChangeOrderBo;
import com.dmz.zrw.model.bo.CommitcommentBo;
import com.dmz.zrw.model.bo.OrdersByPageBo;
import com.dmz.zrw.model.vo.GetOrderByStateVo;
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

    @Override
    public boolean addOrder(AddOrderFromMall addOrderFromMall) {
        String nickname=addOrderFromMall.getToken();
        System.out.println(addOrderFromMall);
        User user=orderDao.searchUserInfomation(nickname);
        System.out.println(user);
        Integer specId=addOrderFromMall.getGoodsDetailId();

        Spec spec=orderDao.searchSpecInformatin(specId);
        System.out.println(spec);
        Integer goodId=spec.getGoodId();
        //看看spec bean 中的id是哪个id
        Goods goods=orderDao.searchgoodInformatin(goodId);
        System.out.println(goods);

        Orders orders=new Orders();
        orders.setUserId(user.getId());
        orders.setNickname(user.getNickname());
        orders.setName(user.getEmail());
        orders.setAddress(user.getAddress());
        orders.setPhone(user.getPhone());
        orders.setGoods(goods.getName());
        orders.setGoodsId(goods.getId());
        orders.setSpec(spec.getName());
        orders.setGoodsDetailId(spec.getId());
        orders.setPrice(spec.getPrice());
        orders.setGoodsNum(addOrderFromMall.getNum());
        orders.setAmount(addOrderFromMall.getAmount());
        orders.setStateId(addOrderFromMall.getState());
        System.out.println(goods);

      boolean isAddOrder = orderDao.addOrder(orders);
        return isAddOrder;






    }

    @Override
    public List<GetOrderByStateVo> getOrderByState(Integer state, String nickname) {
        return orderDao.getOrderByState(state,nickname);
    }

    @Override
    public boolean pay(Integer id) {
        return orderDao.pay(id);
    }

    @Override
    public boolean confirmReceive(Integer id) {
        return orderDao.confirmReceive(id);
    }

    @Override
    public boolean sendComment(CommitcommentBo commitcommentBo) {
        Integer orderId = commitcommentBo.getOrderId();
        Orders orders=orderDao.getOrderByOrderId(orderId);

        //创建comment对象
        Comment comment=new Comment();
        comment.setGoodsId(orders.getGoodsId());
        comment.setOrderId(orders.getId());
        comment.setGoodsDetailId(orders.getGoodsDetailId());
        comment.setSpecName(orders.getSpec());
        comment.setNickname(orders.getNickname());
        comment.setContent(commitcommentBo.getContent());
        comment.setScore(commitcommentBo.getScore());
        comment.setUserId(orders.getUserId());


        boolean isInsertIntoComment=orderDao.insertIntoComment(comment);
        //将order中的hascomment 置为false

        boolean isChangeHasCommentInOrders=orderDao.changeHasCommentInOrders(orderId);

        if (isInsertIntoComment&&isChangeHasCommentInOrders){
            return true;
        }

        return false;
    }
}
