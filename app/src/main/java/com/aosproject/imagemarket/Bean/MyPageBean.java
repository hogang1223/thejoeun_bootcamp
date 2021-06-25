package com.aosproject.imagemarket.Bean;

public class MyPageBean {

    String myname;
    String email;
    String password;
    String phone;
    String account_bank;
    String account_name;
    String account_number;

    public MyPageBean(String myname, String email, String password, String phone, String account_bank, String account_name, String account_number) {
        this.myname = myname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.account_bank = account_bank;
        this.account_name = account_name;
        this.account_number = account_number;
    }

    public String getMyname() {
        return myname;
    }

    public void setMyname(String myname) {
        this.myname = myname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAccount_bank() {
        return account_bank;
    }

    public void setAccount_bank(String account_bank) {
        this.account_bank = account_bank;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }
}
