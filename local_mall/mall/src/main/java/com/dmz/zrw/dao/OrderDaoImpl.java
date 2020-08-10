package com.dmz.zrw.dao;

import com.dmz.zrw.model.CurSpec;
import com.dmz.zrw.model.CurState;
import com.dmz.zrw.model.Orders;
import com.dmz.zrw.model.bo.ChangeOrderBo;
import com.dmz.zrw.model.bo.OrdersByPageBo;
import com.dmz.zrw.model.bo.SpecBo;
import com.dmz.zrw.model.vo.InspectOrderVo;
import com.dmz.zrw.model.vo.OrdersByPageVo;
import com.dmz.zrw.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

}
