package com.dmz.zrw.model.bo;

import java.util.Objects;

public class OrdersByPageBo {

    //订单id
   String id;

   String address;

   Integer currentPage;

   //商品名称
   String  goods;

   String moneyLimit1;
   String moneyLimit2;

   //用户昵称
   String name;


   Integer pagesize;

   //-1全部，0：未付款 1：未发货  2：已发货  3：已到货
   Integer state;


   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public Integer getCurrentPage() {
      return currentPage;
   }

   public void setCurrentPage(Integer currentPage) {
      this.currentPage = currentPage;
   }

   public String getGoods() {
      return goods;
   }

   public void setGoods(String goods) {
      this.goods = goods;
   }

   public String getMoneyLimit1() {
      return moneyLimit1;
   }

   public void setMoneyLimit1(String moneyLimit1) {
      this.moneyLimit1 = moneyLimit1;
   }

   public String getMoneyLimit2() {
      return moneyLimit2;
   }

   public void setMoneyLimit2(String moneyLimit2) {
      this.moneyLimit2 = moneyLimit2;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Integer getPagesize() {
      return pagesize;
   }

   public void setPagesize(Integer pagesize) {
      this.pagesize = pagesize;
   }

   public Integer getState() {
      return state;
   }

   public void setState(Integer state) {
      this.state = state;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      OrdersByPageBo that = (OrdersByPageBo) o;
      return Objects.equals(id, that.id) &&
              Objects.equals(address, that.address) &&
              Objects.equals(currentPage, that.currentPage) &&
              Objects.equals(goods, that.goods) &&
              Objects.equals(moneyLimit1, that.moneyLimit1) &&
              Objects.equals(moneyLimit2, that.moneyLimit2) &&
              Objects.equals(name, that.name) &&
              Objects.equals(pagesize, that.pagesize) &&
              Objects.equals(state, that.state);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, address, currentPage, goods, moneyLimit1, moneyLimit2, name, pagesize, state);
   }

}
