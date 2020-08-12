package com.dmz.zrw.model.bo;

public class MallUpdatePwdBo {
    String id;
    String confirmPwd;
    String newPwd;
    String oldPwd;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConfirmPwd() {
        return confirmPwd;
    }

    public void setConfirmPwd(String confirmPwd) {
        this.confirmPwd = confirmPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    @Override
    public String toString() {
        return "MallUpdatePwdBo{" +
                "id='" + id + '\'' +
                ", confirmPwd='" + confirmPwd + '\'' +
                ", newPwd='" + newPwd + '\'' +
                ", oldPwd='" + oldPwd + '\'' +
                '}';
    }
}
