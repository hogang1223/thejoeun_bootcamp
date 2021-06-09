package com.aoslec.recyclerview_networkjson_student;

public class Student {

    String code;
    String name;
    String dept;
    String phone;
    String img;

    public Student(String code, String name, String dept, String phone, String img) {
        this.code = code;
        this.name = name;
        this.dept = dept;
        this.phone = phone;
        this.img = img;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}