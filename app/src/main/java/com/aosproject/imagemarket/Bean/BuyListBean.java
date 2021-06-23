package com.aosproject.imagemarket.Bean;

public class BuyListBean {

    private int dealNo;
    private String dealDate;
    private String buyCode;
    private String filepath;
    private String myname;
    private String title;
    private String price;
    private int downloadCount;
    private int recommend;

    public BuyListBean(int dealNo, String dealDate, String buyCode, String filepath, String myname, String title, String price, int downloadCount, int recommend) {
        this.dealNo = dealNo;
        this.dealDate = dealDate;
        this.buyCode = buyCode;
        this.filepath = filepath;
        this.myname = myname;
        this.title = title;
        this.price = price;
        this.downloadCount = downloadCount;
        this.recommend = recommend;
    }

    public int getDealNo() {
        return dealNo;
    }

    public void setDealNo(int dealNo) {
        this.dealNo = dealNo;
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

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    public int getRecommend() {
        return recommend;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }
}
