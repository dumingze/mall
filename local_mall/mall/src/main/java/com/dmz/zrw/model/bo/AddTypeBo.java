package com.dmz.zrw.model.bo;

import java.util.Objects;

public class AddTypeBo {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AddTypeBo{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddTypeBo addTypeBo = (AddTypeBo) o;
        return name.equals(addTypeBo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
