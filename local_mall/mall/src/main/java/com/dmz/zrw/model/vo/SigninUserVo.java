package com.dmz.zrw.model.vo;

//命名错了，这个是注册用的
public class SigninUserVo {
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

    public SigninUserVo(String name, String token) {
        this.name = name;
        this.token = token;
    }

    public SigninUserVo() {
    }
}
