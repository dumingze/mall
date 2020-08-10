package com.dmz.zrw.model;

public class CurState {
   Integer id;

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public CurState(Integer id) {
      this.id = id;
   }

   public CurState() {
   }

   @Override
   public String toString() {
      return "CurState{" +
              "id=" + id +
              '}';
   }
}

