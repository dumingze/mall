package com.dmz.zrw.model.bo;

public class RealLoginBo {
    String email;

    String pwd;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    //response :{"code":0,"data":{"code":0,"name":"dumingze","token":"dumingze"}}

}
