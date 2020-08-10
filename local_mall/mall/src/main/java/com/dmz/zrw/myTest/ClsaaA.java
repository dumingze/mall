package com.dmz.zrw.myTest;

import java.util.Objects;

public class ClsaaA {


    int total;
    ClassB classB;

    public ClsaaA(int total, ClassB classB) {
        this.total = total;
        this.classB = classB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClsaaA clsaaA = (ClsaaA) o;
        return total == clsaaA.total &&
                classB.equals(clsaaA.classB);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, classB);
    }
}
