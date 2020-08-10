package com.dmz.zrw.model;

import java.sql.Date;
import java.util.Objects;

public class Orders {

    private Integer id;

    private Integer userId;

    private String nickname;

    private String name;

    private String address;

    private String phone;

    private String goods;

    private Integer goodsId;

    //spec name
    private String spec;

    //spec id
    private Integer goodsDetailId;

    private Double price;

    //商品数量
    private Integer goodsNum;

    private Double amount;

    private Integer stateId;

    private Date createtime;

    private Date updatetime;

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public Integer getGoodsDetailId() {
        return goodsDetailId;
    }

    public void setGoodsDetailId(Integer goodsDetailId) {
        this.goodsDetailId = goodsDetailId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", userId=" + userId +
                ", nickname='" + nickname + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", goods='" + goods + '\'' +
                ", goodsId=" + goodsId +
                ", spec='" + spec + '\'' +
                ", goodsDetailId=" + goodsDetailId +
                ", price=" + price +
                ", goodsNum=" + goodsNum +
                ", amount=" + amount +
                ", stateId=" + stateId +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                '}';
    }

    public Orders() {
    }

    public Orders(Integer id, Integer userId, String nickname, String name, String address, String phone, String goods, Integer goodsId, String spec, Integer goodsDetailId, Double price, Integer goodsNum, Double amount, Integer stateId, Date createtime, Date updatetime) {
        this.id = id;
        this.userId = userId;
        this.nickname = nickname;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.goods = goods;
        this.goodsId = goodsId;
        this.spec = spec;
        this.goodsDetailId = goodsDetailId;
        this.price = price;
        this.goodsNum = goodsNum;
        this.amount = amount;
        this.stateId = stateId;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return Objects.equals(id, orders.id) &&
                Objects.equals(userId, orders.userId) &&
                Objects.equals(nickname, orders.nickname) &&
                Objects.equals(name, orders.name) &&
                Objects.equals(address, orders.address) &&
                Objects.equals(phone, orders.phone) &&
                Objects.equals(goods, orders.goods) &&
                Objects.equals(goodsId, orders.goodsId) &&
                Objects.equals(spec, orders.spec) &&
                Objects.equals(goodsDetailId, orders.goodsDetailId) &&
                Objects.equals(price, orders.price) &&
                Objects.equals(goodsNum, orders.goodsNum) &&
                Objects.equals(amount, orders.amount) &&
                Objects.equals(stateId, orders.stateId) &&
                Objects.equals(createtime, orders.createtime) &&
                Objects.equals(updatetime, orders.updatetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, nickname, name, address, phone, goods, goodsId, spec, goodsDetailId, price, goodsNum, amount, stateId, createtime, updatetime);
    }
}
