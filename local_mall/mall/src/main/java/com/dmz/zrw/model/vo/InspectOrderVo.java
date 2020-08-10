package com.dmz.zrw.model.vo;

import com.dmz.zrw.model.CurSpec;
import com.dmz.zrw.model.CurState;
import com.dmz.zrw.model.Spec;
import com.dmz.zrw.model.States;
import com.dmz.zrw.model.bo.SpecBo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InspectOrderVo {

    Integer id;
    Double amount;
    Integer num;
    Integer goodsDetailId;
    Integer state;
    String goods;
    List<SpecBo> spec=new ArrayList<>();
    List<States> states=new ArrayList<>();
    CurState curState=new CurState();
    CurSpec curSpec=new CurSpec();

    {

        states.add(new States(0,"未付款"));
        states.add(new States(1,"未发货"));
        states.add(new States(2,"已发货"));
        states.add(new States(3,"已完成订单"));


    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getGoodsDetailId() {
        return goodsDetailId;
    }

    public void setGoodsDetailId(Integer goodsDetailId) {
        this.goodsDetailId = goodsDetailId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }


    public List<States> getStates() {
        return states;
    }

    public void setStates(List<States> states) {
        this.states = states;
    }

    public CurState getCurState() {
        return curState;
    }

    public void setCurState(CurState curState) {
        this.curState = curState;
    }

    public CurSpec getCurSpec() {
        return curSpec;
    }

    public void setCurSpec(CurSpec curSpec) {
        this.curSpec = curSpec;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InspectOrderVo that = (InspectOrderVo) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(num, that.num) &&
                Objects.equals(goodsDetailId, that.goodsDetailId) &&
                Objects.equals(state, that.state) &&
                Objects.equals(goods, that.goods) &&
                Objects.equals(spec, that.spec) &&
                Objects.equals(states, that.states) &&
                Objects.equals(curState, that.curState) &&
                Objects.equals(curSpec, that.curSpec);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, num, goodsDetailId, state, goods, spec, states, curState, curSpec);
    }

    public List<SpecBo> getSpec() {
        return spec;
    }

    public void setSpec(List<SpecBo> spec) {
        this.spec = spec;
    }

    @Override
    public String toString() {
        return "InspectOrderVo{" +
                "id=" + id +
                ", amount=" + amount +
                ", num=" + num +
                ", goodsDetailId=" + goodsDetailId +
                ", state=" + state +
                ", goods='" + goods + '\'' +
                ", specList=" + spec +
                ", states=" + states +
                ", curState=" + curState +
                ", curSpec=" + curSpec +
                '}';
    }
}
