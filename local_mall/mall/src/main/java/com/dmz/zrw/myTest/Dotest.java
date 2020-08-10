package com.dmz.zrw.myTest;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class Dotest {


    public static void main(String[] args) {
        ClsaaA clsaaA=new ClsaaA(1,new ClassB(1,"dmz"));
        Gson gson=new Gson();
        String s = gson.toJson(clsaaA);
        System.out.println("聚合方式"+s);


        Map<String ,Object> map=new HashMap();
        map.put("total",1);
        map.put("classB",new ClassB(1,"dmz"));
        String s1 = gson.toJson(map);
        System.out.println("map"+s1);

    }
}
