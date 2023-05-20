package com.aosproject.imagemarket.Bean;

public class SellListBean {

    String dealDate;
    String buyCode;
    String filepath;
    String title;
    String price;

    public SellListBean(String dealDate, String buyCode, String filepath, String title, String price) {
        this.dealDate = dealDate;
        this.buyCode = buyCode;
        this.filepath = filepath;
        this.title = title;
        this.price = price;
    }

    public String getDealDate() {
        return dealDate;
    }

    public void setDealDate(String dealDate) {
        this.dealDate = dealDate;
    }

    public String getBuyCode() {
        return buyCode;
    }

    public void setBuyCode(String buyCode) {
        this.buyCode = buyCode;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
