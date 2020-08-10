package com.dmz.zrw.model;

public class CurSpec {
    Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CurSpec(Integer id) {
        this.id = id;
    }

    public CurSpec() {
    }

    @Override
    public String toString() {
        return "CurSpec{" +
                "id=" + id +
                '}';
    }
}
