package com.example.courseproject.Model;

public class Favorites {
    private String PlaceId,PlaceName,PlaceMenuId,PlaceImage,PlaceDescription,UserPhone;

    public Favorites() {
    }

    public Favorites(String placeId, String placeName, String placeMenuId, String placeImage, String placeDescription, String userPhone) {
        PlaceId = placeId;
        PlaceName = placeName;
        PlaceMenuId = placeMenuId;
        PlaceImage = placeImage;
        PlaceDescription = placeDescription;
        UserPhone = userPhone;
    }

    public String getPlaceId() {
        return PlaceId;
    }

    public void setPlaceId(String placeId) {
        PlaceId = placeId;
    }

    public String getPlaceName() {
        return PlaceName;
    }

    public void setPlaceName(String placeName) {
        PlaceName = placeName;
    }

    public String getPlaceMenuId() {
        return PlaceMenuId;
    }

    public void setPlaceMenuId(String placeMenuId) {
        PlaceMenuId = placeMenuId;
    }

    public String getPlaceImage() {
        return PlaceImage;
    }

    public void setPlaceImage(String placeImage) {
        PlaceImage = placeImage;
    }

    public String getPlaceDescription() {
        return PlaceDescription;
    }

    public void setPlaceDescription(String placeDescription) {
        PlaceDescription = placeDescription;
    }

    public String getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(String userPhone) {
        UserPhone = userPhone;
    }
}
