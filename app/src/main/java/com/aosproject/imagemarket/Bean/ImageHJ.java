package com.aosproject.imagemarket.Bean;

public class ImageHJ {

    int code;
    String filepath;
    String title;
    String detail;
    String fileformat;
    int category;
    String tag;
    int price;
    String location;
    String user_email;
    String myname;

    public ImageHJ(String myname) {
        this.myname = myname;
    }

    public ImageHJ(int code) {
        this.code = code;
    }

    public ImageHJ(int code, String filepath, String title, int price) {
        this.code = code;
        this.filepath = filepath;
        this.title = title;
        this.price = price;
    }

    public ImageHJ(int code, String filepath, String title, String detail, String fileformat, int category, String tag, int price, String location) {
        this.code = code;
        this.filepath = filepath;
        this.title = title;
        this.detail = detail;
        this.fileformat = fileformat;
        this.category = category;
        this.tag = tag;
        this.price = price;
        this.location = location;
    }

    public ImageHJ(String filepath, String title, String detail, String fileformat, int category, String tag, int price, String location) {
        this.filepath = filepath;
        this.title = title;
        this.detail = detail;
        this.fileformat = fileformat;
        this.category = category;
        this.tag = tag;
        this.price = price;
        this.location = location;
    }

    public ImageHJ(String filepath, String title, String detail, String fileformat, int category, String tag, int price, String location, String user_email) {
        this.filepath = filepath;
        this.title = title;
        this.detail = detail;
        this.fileformat = fileformat;
        this.category = category;
        this.tag = tag;
        this.price = price;
        this.location = location;
        this.user_email = user_email;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getFileformat() {
        return fileformat;
    }

    public void setFileformat(String fileformat) {
        this.fileformat = fileformat;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getMyname() {
        return myname;
    }

    public void setMyname(String myname) {
        this.myname = myname;
    }
}
