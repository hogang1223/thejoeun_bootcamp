package com.aosproject.imagemarket.Bean;

public class ImgListBean {

    int imageCode;
    String filepath;
    String title;
    String price;
    String sellCount;

    public ImgListBean(int imageCode, String filepath, String title, String price, String sellCount) {
        this.imageCode = imageCode;
        this.filepath = filepath;
        this.title = title;
        this.price = price;
        this.sellCount = sellCount;
    }

    public int getImageCode() {
        return imageCode;
    }

    public void setImageCode(int imageCode) {
        this.imageCode = imageCode;
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

    public String getSellCount() {
        return sellCount;
    }

    public void setSellCount(String sellCount) {
        this.sellCount = sellCount;
    }
}
