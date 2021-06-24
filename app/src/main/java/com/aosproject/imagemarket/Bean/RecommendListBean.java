package com.aosproject.imagemarket.Bean;

public class RecommendListBean {

    int imgCode;
    String filepath;
    String myname;
    String title;
    String price;
    int recommend;

    public RecommendListBean(int imgCode, String filepath, String myname, String title, String price, int recommend) {
        this.imgCode = imgCode;
        this.filepath = filepath;
        this.myname = myname;
        this.title = title;
        this.price = price;
        this.recommend = recommend;
    }

    public int getImageCode() {
        return imgCode;
    }

    public void setImageCode(int imgCode) {
        this.imgCode = imgCode;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getMyname() {
        return myname;
    }

    public void setMyname(String myname) {
        this.myname = myname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getRecommend() {
        return recommend;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }
}
