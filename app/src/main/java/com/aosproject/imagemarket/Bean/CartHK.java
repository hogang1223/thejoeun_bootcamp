package com.aosproject.imagemarket.Bean;

import java.io.Serializable;

public class CartHK implements Serializable {
    boolean isSelected = false;
    int imageCode;
    int cartNo;
    String sellName;
    String sellEmail;
    String imageTitle;
    String imageFilepath;
    int imagePrice;
    int cartStatus;


    public CartHK(int imageCode, String sellName, String sellEmail, String imageTitle, String imageFilepath, int imagePrice) {
        this.imageCode = imageCode;
        this.sellName = sellName;
        this.sellEmail = sellEmail;
        this.imageTitle = imageTitle;
        this.imageFilepath = imageFilepath;
        this.imagePrice = imagePrice;
    }

    public CartHK(int imageCode, int cartNo, String sellName, String sellEmail, String imageTitle, String imageFilepath, int imagePrice, int cartStatus) {
        this.imageCode = imageCode;
        this.cartNo = cartNo;
        this.sellName = sellName;
        this.sellEmail = sellEmail;
        this.imageTitle = imageTitle;
        this.imageFilepath = imageFilepath;
        this.imagePrice = imagePrice;
        this.cartStatus = cartStatus;
        this.isSelected = isSelected;
    }

    public int getImageCode() {
        return imageCode;
    }

    public void setImageCode(int imageCode) {
        this.imageCode = imageCode;
    }

    public int getCartNo() {
        return cartNo;
    }

    public void setCartNo(int cartNo) {
        this.cartNo = cartNo;
    }

    public String getSellName() {
        return sellName;
    }

    public void setSellName(String sellName) {
        this.sellName = sellName;
    }

    public String getSellEmail() {
        return sellEmail;
    }

    public void setSellEmail(String sellEmail) {
        this.sellEmail = sellEmail;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    public String getImageFilepath() {
        return imageFilepath;
    }

    public void setImageFilepath(String imageFilepath) {
        this.imageFilepath = imageFilepath;
    }

    public int getCartStatus() {
        return cartStatus;
    }

    public void setCartStatus(int cartStatus) {
        this.cartStatus = cartStatus;
    }

    public int getImagePrice() {
        return imagePrice;
    }

    public void setImagePrice(int imagePrice) {
        this.imagePrice = imagePrice;
    }

    public boolean isSelected() {
        return isSelected;
    }
    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
