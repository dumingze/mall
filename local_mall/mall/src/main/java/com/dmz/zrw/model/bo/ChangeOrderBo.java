package com.dmz.zrw.model.bo;

public class ChangeOrderBo {
    String id;
    Integer num;
    Integer spec;
    Integer state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getSpec() {
        return spec;
    }

    public void setSpec(Integer spec) {
        this.spec = spec;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "ChangeOrderBo{" +
                "id='" + id + '\'' +
                ", num=" + num +
                ", spec=" + spec +
                ", state=" + state +
                '}';
    }

    public ChangeOrderBo(String id, Integer num, Integer spec, Integer state) {
        this.id = id;
        this.num = num;
        this.spec = spec;
        this.state = state;
    }

    public ChangeOrderBo() {
    }
}
