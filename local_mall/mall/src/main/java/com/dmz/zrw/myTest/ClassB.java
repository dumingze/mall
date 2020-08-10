package com.dmz.zrw.myTest;

import java.util.Objects;

public class ClassB {

    int age;

    String name;

    public ClassB(int age, String name) {

        this.age = age;
        this.name = name;
    }


    @Override
    public String toString() {
        return "ClassB{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassB classB = (ClassB) o;
        return age == classB.age &&
                Objects.equals(name, classB.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name);
    }
}
