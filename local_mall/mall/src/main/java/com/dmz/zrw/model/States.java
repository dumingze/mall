package com.dmz.zrw.model;

public class States {
    Integer id;
    String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public States(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public States() {
    }

    @Override
    public String toString() {
        return "States{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
