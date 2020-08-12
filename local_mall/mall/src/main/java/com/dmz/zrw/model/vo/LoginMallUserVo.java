package com.dmz.zrw.model.vo;

public class LoginMallUserVo {
    String name;
    String token;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginMallUserVo(String name, String token) {
        this.name = name;
        this.token = token;
    }

    public LoginMallUserVo() {
    }

    @Override
    public String toString() {
        return "LoginMallUserVo{" +
                "name='" + name + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
