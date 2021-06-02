package com.aoslec.customadapterview;

public class Weather {
    private String day;
    private int icon;
    private String comment;

    public Weather(String day, int icon, String comment) {
        this.day = day;
        this.icon = icon;
        this.comment = comment;
    }

    public String getDay() {
        return day;
    }

    public int getIcon() {
        return icon;
    }

    public String getComment() {
        return comment;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
