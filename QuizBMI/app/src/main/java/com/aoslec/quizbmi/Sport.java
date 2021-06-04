package com.aoslec.quizbmi;

public class Sport {
    private int icon;
    private String sport;
    private String calorie;

    public Sport(int icon, String sport, String calorie) {
        this.icon = icon;
        this.sport = sport;
        this.calorie = calorie;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getCalorie() {
        return calorie;
    }

    public void setCalorie(String calorie) {
        this.calorie = calorie;
    }
}
