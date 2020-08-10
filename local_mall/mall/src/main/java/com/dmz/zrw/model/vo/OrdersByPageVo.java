package com.dmz.zrw.model.vo;

public class OrdersByPageVo {
    private Integer id;

    private Integer userId;

    private Integer goodsDetailId;

    private String goods;

    private Integer goodsNum;

    private Double amount;

    private Integer stateId;

    private String state;

    private String spec;

    private OrderUserVO user = new OrderUserVO();

    //只需要提供四个相应的set方法即可
    //dbutils如何封装数据  数据库每列的属性值以及对应的值
    //根据这个值，去拼接出setXXX方法，然后到你对应的bean中去寻找该方法
    //调用该方法，完成赋值
    public void setNickname(String nickname) {
        user.setNickname(nickname);
    }
    public void setName(String name) {
        user.setName(name);
    }
    public void setAddress(String address) {
        user.setAddress(address);
    }
    public void setPhone(String phone) {
        user.setPhone(phone);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodsDetailId() {
        return goodsDetailId;
    }

    public void setGoodsDetailId(Integer goodsDetailId) {
        this.goodsDetailId = goodsDetailId;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getStateId() {
        return stateId;
    }
    /**
     * 当dbutils反射封装stateId的时候，肯定会调用setStateId方法
     * 能不能在这个方法里面做一些事情呢？
     * 0 未付款
     * 1 未发货
     * 2 已发货
     * 3 已到货
     * @param stateId
     */
    public void setStateId(Integer stateId) {
        this.stateId = stateId;
        if(stateId == 0){
            setState("未付款");
        }else if(stateId == 1){
            setState("未发货");
        }else if(stateId == 2){
            setState("已发货");
        }else if(stateId == 3){
            setState("已到货");
        }
    }

    public String getState() {
        return state;
    }


    public void setState(String state) {
        this.state = state;
    }

    public OrderUserVO getUser() {
        return user;
    }

    public void setUser(OrderUserVO user) {
        this.user = user;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }
}
