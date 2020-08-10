package com.dmz.zrw.model.bo;

import java.util.Objects;

public class SpecBo {
    Integer id;
    String specName;
     String unitPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public SpecBo(Integer id, String specName, String unitPrice) {
        this.id = id;
        this.specName = specName;
        this.unitPrice = unitPrice;
    }

    public SpecBo() {
    }

    @Override
    public String toString() {
        return "SpecBo{" +
                "id=" + id +
                ", specName='" + specName + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecBo specBo = (SpecBo) o;
        return Objects.equals(id, specBo.id) &&
                Objects.equals(specName, specBo.specName) &&
                Objects.equals(unitPrice, specBo.unitPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, specName, unitPrice);
    }
}
