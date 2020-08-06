package com.dmz.zrw.model.bo;

public class AdminLoginBo {
    private  String email;
    private String pwd;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String psd) {
        this.pwd = psd;
    }

    @Override
    public String toString() {
        return "AdminLoginBo{" +
                "email='" + email + '\'' +
                ", psd='" + pwd + '\'' +
                '}';
    }
}
