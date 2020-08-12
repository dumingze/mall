package com.dmz.zrw.dao;

import com.dmz.zrw.model.*;
import com.dmz.zrw.model.bo.ChangeOrderBo;
import com.dmz.zrw.model.bo.OrdersByPageBo;
import com.dmz.zrw.model.bo.SpecBo;
import com.dmz.zrw.model.vo.*;
import com.dmz.zrw.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class OrderDaoImpl implements OrderDao {
    @Override
    public int getOrderTotal(OrdersByPageBo ordersByPageBo) {
        // select count(id) from orders where 1=1 and xxxxx
        Map map = getDynamicSqlAndParams(ordersByPageBo);
        String sql = (String) map.get("sql");
        List params = (List) map.get("params");
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        //查询count值，jdbc返回的就是long类型
        Long query = null;
        try {
            query = (Long) runner.query("select count(id) from orders " + sql, new ScalarHandler(),
                    params.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query.intValue();
    }


    private Map getDynamicSqlAndParams(OrdersByPageBo pageOrdersBO) {
        String baseSql = " where 1 = 1 ";
        List params = new ArrayList();
        if(!StringUtils.isEmpty(pageOrdersBO.getAddress())){
            baseSql += " and address like ? ";
            params.add("%" + pageOrdersBO.getAddress() + "%");
        }
        if(!StringUtils.isEmpty(pageOrdersBO.getGoods())){
            baseSql += " and goods like ? ";
            params.add("%" + pageOrdersBO.getGoods() + "%");
        }
        if(!StringUtils.isEmpty(pageOrdersBO.getMoneyLimit1())){
            baseSql += " and amount <= ? ";
            params.add(pageOrdersBO.getMoneyLimit1());
        }
        if(!StringUtils.isEmpty(pageOrdersBO.getMoneyLimit2())){
            baseSql += " and amount >= ? ";
            params.add(pageOrdersBO.getMoneyLimit2());
        }
        if(!StringUtils.isEmpty(pageOrdersBO.getId())){
            baseSql += " and id = ? ";
            params.add(pageOrdersBO.getId());
        }
        if(!StringUtils.isEmpty(pageOrdersBO.getName())){
            baseSql += " and nickname like ? ";
            params.add("%" + pageOrdersBO.getName() + "%");
        }
        if(pageOrdersBO.getState() != -1){
            baseSql += " and stateId = ? ";
            params.add(pageOrdersBO.getState());
        }
        HashMap<Object, Object> map = new HashMap<>();
        map.put("sql", baseSql);
        map.put("params", params);
        return map;
    }

    @Override
    public List<OrdersByPageVo> getOrdersByPageVoList(OrdersByPageBo pageOrdersBO) {
        Map map = getDynamicSqlAndParams(pageOrdersBO);
        String suffix = (String) map.get("sql");
        List params = (List) map.get("params");
        //注意 获取具体数据需要分页，但是查询总条目不需要limit
        String sql = "select * from orders " + suffix + " limit ? offset ?";


        params.add(pageOrdersBO.getPagesize());
        params.add((pageOrdersBO.getCurrentPage() - 1) * pageOrdersBO.getPagesize());
        System.out.println("从哪里开始"+(pageOrdersBO.getCurrentPage() - 1) * pageOrdersBO.getPagesize());
        System.out.println("getPagesize"+pageOrdersBO.getPagesize());
        System.out.println("Toarray"+params);
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<OrdersByPageVo> ordersVOS = null;
        try {
            ordersVOS = runner.query(sql, new BeanListHandler<OrdersByPageVo>(OrdersByPageVo.class), params.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordersVOS;
    }

    @Override
    public boolean deleteOrder(Integer id) {
        QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());
        Integer delete=null;

        try {
            delete=queryRunner.update("delete from orders where id = ? ",id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return delete==1?true:false;
    }

    @Override
    public InspectOrderVo inspectOrder(Integer id) {
        QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());
        InspectOrderVo inspectOrderVo=new InspectOrderVo();
        Orders orders=null;

        System.out.println("!!!!!!"+inspectOrderVo);
        try {
            //inspectOrderVo=queryRunner.query("select * from orders where id = ? ",new BeanHandler<InspectOrderVo>(InspectOrderVo.class),id);
            //System.out.println(inspectOrderVo);
            //InspectOrderVo{id=1, amount=1, num=null, goodsDetailId=5, state=null, goods='123', specList=[], states=[States{id=0, name='未付款'}, States{id=1, name='未发货'}, States{id=2, name='已发货'}, States{id=3, name='已完成订单'}],
            // curState=com.dmz.zrw.model.CurState@1f809fa5, curSpec=com.dmz.zrw.model.CurSpec@5ca76be9}
            orders=queryRunner.query("select * from orders where id = ? ",new BeanHandler<Orders>(Orders.class),id);
            System.out.println(orders);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        inspectOrderVo.setId(orders.getId());
        inspectOrderVo.setAmount(orders.getAmount());
        inspectOrderVo.setNum(orders.getGoodsNum());
        inspectOrderVo.setGoodsDetailId(orders.getGoodsDetailId());
        inspectOrderVo.setState(orders.getStateId());
        inspectOrderVo.setGoods(orders.getGoods());

//specList=[],
        List<SpecBo> specBoList=null;
        try {
          specBoList = queryRunner.query("select  id,name as specName,price as unitPrice from  spec where goodId = ? ",new BeanListHandler<SpecBo>(SpecBo.class),orders.getGoodsId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        inspectOrderVo.setSpec(specBoList);

        //System.out.println(inspectOrderVo);
        inspectOrderVo.setCurSpec(new CurSpec(orders.getGoodsDetailId()));
        inspectOrderVo.setCurState(new CurState(orders.getStateId()));
        System.out.println(inspectOrderVo);
        return inspectOrderVo;
    }

    @Override
    public boolean changeOrder(ChangeOrderBo changeOrderBo) {


        QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());
        Integer changeOrderResult=null;

        try {
            changeOrderResult=queryRunner.update("update  orders set  stateId= ? where id = ? ",changeOrderBo.getState(),changeOrderBo.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return changeOrderResult==1?true:false;
    }

    @Override
    public User searchUserInfomation(String nickname) {
        QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());
        User user=null;
        try {
            user= queryRunner.query("select * from user where nickname = ? ", new BeanHandler<User>(User.class), nickname);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public Spec searchSpecInformatin(Integer specId)  {
        QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());
        Spec spec=null;
        try {
            spec=queryRunner.query("select * from spec where id = ? ",new BeanHandler<Spec>(Spec.class),specId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return spec;
    }

    @Override
    public Goods searchgoodInformatin(Integer goodId) {
        QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());
        Goods goods=null;

        try {
            goods=queryRunner.query("select * from goods where id = ? ",new BeanHandler<Goods>(Goods.class),goodId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goods;
    }

    @Override
    public boolean addOrder(Orders orders) {

        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());
        Integer isAddOrder=null;
        try {
            isAddOrder=queryRunner.update("insert into orders values (null, ?,? ,? ,? ,? ,?  , ?, ?, ?,? ,?, ?, ?,? ,null ,false )",
                orders.getUserId(),orders.getNickname(),orders.getName(),orders.getAddress(),orders.getPhone(),
                    orders.getGoods(),orders.getGoodsId(),orders.getSpec(),orders.getGoodsDetailId(),
                    orders.getPrice(),orders.getGoodsNum(),orders.getAmount(),orders.getStateId(),simpleDateFormat.format(new Date(System.currentTimeMillis())));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(isAddOrder);
        return isAddOrder==1?true:false;
    }

    @Override
    public List<GetOrderByStateVo> getOrderByState(Integer state, String nickname) {
        QueryRunner runner=new QueryRunner(DruidUtils.getDataSource());
        List<Orders> ordersList=null;
        List<GetOrderByStateVo> getOrderByStateVoList=new LinkedList<>();
        //System.out.println(state);
        if (state!=-1) {
            try {
                ordersList = runner.query("select * from orders where stateId =  ? and nickname = ? ",new BeanListHandler<Orders>(Orders.class),state,nickname);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                ordersList =runner.query("select * from orders where nickname = ? ",new BeanListHandler<Orders>(Orders.class),nickname);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //0->flase
       // System.out.println(ordersList);

        for (Orders orders:ordersList) {
            Integer goodId=orders.getGoodsId();
            System.out.println("goodId"+goodId);
            GetOrderByStateVo getOrderByStateVo =new GetOrderByStateVo();
            GetOrderByStateVogoodsVo goods=new GetOrderByStateVogoodsVo();
            ImageAdddressVo imgAddress=null;
            try {
                 imgAddress = runner.query("select img from goods where id = ? ", new BeanHandler<ImageAdddressVo>(ImageAdddressVo.class), goodId);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            getOrderByStateVo.setId(orders.getId());
            getOrderByStateVo.setState(orders.getStateId());
            getOrderByStateVo.setGoodsNum(orders.getGoodsNum());
            getOrderByStateVo.setAmount(orders.getAmount());
            getOrderByStateVo.setGoodsDetailId(orders.getGoodsDetailId());
            getOrderByStateVo.setCreatetime(orders.getCreatetime());
            getOrderByStateVo.setHasComment(orders.getHasComment());

            goods.setId(orders.getGoodsId());
            goods.setImg(imgAddress.getImg());
            goods.setName(orders.getGoods());
            goods.setGoodsDetailId(orders.getGoodsDetailId());
            goods.setSpec(orders.getSpec());
            goods.setUnitPrice(orders.getPrice());

            getOrderByStateVo.setGoods(goods);

            getOrderByStateVoList.add(getOrderByStateVo);

        }
        return getOrderByStateVoList;
    }

    @Override
    public boolean pay(Integer id)  {
        QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());
       Integer isPay=null;
        try {
            isPay=queryRunner.update("update orders set stateId = 1 where id = ? ",id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isPay==1?true:false;
    }

    @Override
    public boolean confirmReceive(Integer id) {
        QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());
        Integer isConfirmReceive=null;
        try {
            isConfirmReceive=queryRunner.update("update orders set stateId = 3 where id = ? ",id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isConfirmReceive==1?true:false;

    }

    @Override
    public Orders getOrderByOrderId(Integer orderId) {
        QueryRunner queryRunner= new QueryRunner(DruidUtils.getDataSource());
        Orders order=null;
        try {
            order=queryRunner.query("select * from orders where id = ? ",new BeanHandler<Orders>(Orders.class),orderId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  order;
    }

    @Override
    public boolean insertIntoComment(Comment comment) {
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());
        Integer isInsertIntoComment=null;
        try {
            isInsertIntoComment=queryRunner.update("insert into comment values (null,?,?,?,?,?,?,?,?,? ) ",
                    comment.getOrderId(),comment.getGoodsId(),comment.getGoodsDetailId(),comment.getSpecName(),
                    comment.getNickname(),comment.getContent(),comment.getScore(),comment.getUserId(),
                    simpleDateFormat.format(new Date(System.currentTimeMillis())));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isInsertIntoComment==1?true:false;
    }

    @Override
    public boolean changeHasCommentInOrders(Integer orderId) {
        QueryRunner queryRunner= new QueryRunner(DruidUtils.getDataSource());
        Integer isChangeHasCommentInOrders=null;
        try {
            isChangeHasCommentInOrders=queryRunner.update("update orders set hasComment = 1 where id = ?  ",orderId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  isChangeHasCommentInOrders==1?true:false;
    }

}
