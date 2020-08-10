package com.dmz.zrw.dao;

import com.dmz.zrw.model.Goods;
import com.dmz.zrw.model.GoodsType;
import com.dmz.zrw.model.Spec;
import com.dmz.zrw.model.bo.*;
import com.dmz.zrw.model.vo.GetGoodsInfoVo;
import com.dmz.zrw.model.vo.GoodsVo;
import com.dmz.zrw.model.vo.SpecVo;
import com.dmz.zrw.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public class GoodsDapImpl implements GoodsDao {
    @Override
    public List<GoodsType> getType() {
        QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());
        List<GoodsType> list=null;
        try {
            list=queryRunner.query("select * from type",new BeanListHandler<GoodsType>(GoodsType.class));
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("------------------------------");
        }

        return list;
    }

    @Override
    public boolean addType(AddTypeBo addTypeBo)  {
        QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());
        Integer integer=null;
        List<AddTypeBo> addTypeBoList=null;

        try {
            addTypeBoList= queryRunner.query("select name from type",new BeanListHandler<AddTypeBo>(AddTypeBo.class));
            System.out.println("insert into type values (null,?)"+addTypeBoList);
            System.out.println("AddTypeBo"+addTypeBo);
            if (addTypeBoList.contains(addTypeBo)){
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(addTypeBo.getName());
        try {
           /*integer= queryRunner.update("insert into type values(null,？)" ,addTypeBo.getName());*/
           integer= queryRunner.update("insert into type values (null,?)",addTypeBo.getName());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(integer);
        }
        return integer==1?true:false;

    }

    @Override
    public boolean addToFormGoods(Goods goods) {
        QueryRunner queryRunner =new QueryRunner(DruidUtils.getDataSource());
        Integer update=null;
        try {
            update= queryRunner.update("insert into goods values (null,?,?,?,?,?,?)",
                     goods.getName(),
                     goods.getImg(),
                     goods.getTypeId(),
                     goods.getDesc(),
                     goods.getPrice(),
                     goods.getStockNum()

                     );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  update==1?true:false;
    }

    @Override
    public int getLastId() {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        //BigInteger   count  Long
        BigInteger query = null;
        try {
            query = (BigInteger) runner.query("select last_insert_id()", new ScalarHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query.intValue();
    }

    @Override
    public boolean addSpecs(List<AddSpecBO> specBOList, int goodsId) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Integer insert=null;
        for (AddSpecBO addSpecBO : specBOList) {
            try {
               insert= runner.update("insert into spec values (null,?,?,?,?)",
                        addSpecBO.getSpecName(),
                        addSpecBO.getStockNum(),
                        addSpecBO.getUnitPrice(),
                        goodsId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return insert>0?true:false;
    }

    @Override
    public List<Goods> showGoodsList(Integer typeId) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<Goods> goodsList=null;
        try {
            goodsList= runner.query("select id, img,name, price,typeid,stockNum from goods  where typeId = ? ",new BeanListHandler<Goods>(Goods.class),typeId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return goodsList;
    }

    @Override
    public List<Goods> showAllGoodsList() {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<Goods> goodsList=null;
        try {
            goodsList= runner.query("select id, img,name, price,typeid,stockNum from goods  ",new BeanListHandler<Goods>(Goods.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return goodsList;
    }

    @Override
    public boolean deleteFromGoods(Integer id) {

     QueryRunner  runner=new QueryRunner(DruidUtils.getDataSource());
     Integer isdeleteGoods = null;
        try {
            isdeleteGoods=runner.update("delete  from goods where id = ? ",id);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return  isdeleteGoods>0?true:false;

    }

    @Override
    public boolean deleteFromSpec(Integer id) {
     QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());
     Integer idDeleteFromSpec=null;
        try {
            idDeleteFromSpec=queryRunner.update("delete  from spec where goodId = ? ",id);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return idDeleteFromSpec>0?true:false;
    }

    @Override
    public GetGoodsInfoVo getGoodsInfoVo(Integer id) {
        QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());
        GetGoodsInfoVo goodsInfoVo=new GetGoodsInfoVo();
        List<SpecVo> specvoList=null;
       GoodsVo goodvo=null;

        try {
            specvoList=queryRunner.query("select id ,name as specName,stockNum,price as unitPrice from spec where goodId = ? ",new BeanListHandler<SpecVo>(SpecVo.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            goodvo=queryRunner.query("select id,img,name,'desc',typeId,price as unitPrice from goods where id = ?  " ,new BeanHandler<GoodsVo>(GoodsVo.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        goodsInfoVo.setSpecs(specvoList);
        goodsInfoVo.setGoods(goodvo);


        System.out.println(goodsInfoVo);
        return goodsInfoVo;
    }

    @Override
    public boolean updateGoodsInGoods(Goods goods) {
        QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());
        Integer isUpdateGoodsInGoods=null;
//update  admin set  email=? ,pwd=? ,nickname=? where id=?
        try {
            isUpdateGoodsInGoods=    queryRunner.update("update goods set name = ? ,img = ? ,typeId = ? ,`desc` = ? , price = ? ,stockNum = ?  where id = ? ",
                    goods.getName(),goods.getImg(),goods.getTypeId(),goods.getDesc(),goods.getPrice(),goods.getStockNum(),goods.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUpdateGoodsInGoods==1?true:false;
    }

    @Override
    public boolean updateGoodsInSpec(List<UpdateGoodsSpecBo> specBOList, int goodid) {
        QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());
        Integer integerDelete=null;
        try {
            integerDelete=queryRunner.update("delete from spec where goodId = ? ",goodid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (integerDelete==null){
            return false;
        }

        Integer reInsertInSpec=null;
        for (UpdateGoodsSpecBo updateGoodsSpecBo : specBOList) {
            try {
                reInsertInSpec= queryRunner.update("insert into spec values (null,?,?,?,?)",
                        updateGoodsSpecBo.getSpecName(),
                        updateGoodsSpecBo.getStockNum(),
                        updateGoodsSpecBo.getUnitPrice(),
                        goodid);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (reInsertInSpec == null){
            return false;
        }

        return true;
    }

    @Override
    public boolean addSpec(UpdateNomalSpecBo updateNomalSpecBo) {
        QueryRunner queryRunner=new QueryRunner(DruidUtils.getDataSource());
        Integer isAddSpec =null;
        try {
            isAddSpec=queryRunner.update("insert into spec values (null,?,?,?,?)",
                    updateNomalSpecBo.getSpecName(),updateNomalSpecBo.getStockNum(),
                    updateNomalSpecBo.getUnitPrice(),updateNomalSpecBo.getGoodsId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (isAddSpec==1){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean DeleteSpec(DeleteSpecBo deleteSpecBo) {
        QueryRunner runner=new QueryRunner(DruidUtils.getDataSource());
        System.out.println(deleteSpecBo);
        Integer isDeleteSpec=null;
         Integer goodId=Integer.parseInt(deleteSpecBo.getGoodsId());
        try {
            isDeleteSpec=runner.update("delete from spec where goodId = ? and name = ? ",goodId,deleteSpecBo.getSpecName());
            //isDeleteSpec=runner.update("delete from spec where   name = ? ",deleteSpecBo.getSpecName());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isDeleteSpec==1?true:false;
    }


}
