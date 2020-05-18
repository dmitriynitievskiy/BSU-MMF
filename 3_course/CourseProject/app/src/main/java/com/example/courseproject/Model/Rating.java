package com.example.courseproject.Model;

public class Rating {
    private String userPhone;
    private String placeId;
    private String rateValue;
    private String comment;

    public Rating(){}

    public Rating(String userPhone, String placeId, String rateValue, String comment) {
        this.userPhone = userPhone;
        this.placeId = placeId;
        this.rateValue = rateValue;
        this.comment = comment;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getRateValue() {
        return rateValue;
    }

    public void setRateValue(String rateValue) {
        this.rateValue = rateValue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
