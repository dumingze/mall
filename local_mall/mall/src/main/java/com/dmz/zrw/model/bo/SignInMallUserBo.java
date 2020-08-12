package com.dmz.zrw.model.bo;

public class SignInMallUserBo {



        String address;

        String email;
        String nickname;

        String phone;

        String pwd;

        String recipient;


        public SignInMallUserBo() {
        }

        public SignInMallUserBo(String address, String email, String nickname, String phone, String pwd, String recipient) {
            this.address = address;
            this.email = email;
            this.nickname = nickname;
            this.phone = phone;
            this.pwd = pwd;
            this.recipient = recipient;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getRecipient() {
            return recipient;
        }

        public void setRecipient(String recipient) {
            this.recipient = recipient;
        }

        @Override
        public String toString() {
            return "SignInMallUserBo{" +
                    "address='" + address + '\'' +
                    ", email='" + email + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", phone='" + phone + '\'' +
                    ", pwd='" + pwd + '\'' +
                    ", recipient='" + recipient + '\'' +
                    '}';
        }
    }


