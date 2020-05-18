package com.example.courseproject.Model;

public class Place {
    private String Name,Image,Description,MenuID;

    public Place() {  }

    public Place(String name, String image, String description, String menuID) {
        Name = name;
        Image = image;
        Description = description;
        MenuID = menuID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getMenuID() {
        return MenuID;
    }

    public void setMenuID(String menuID) {
        MenuID = menuID;
    }
}
