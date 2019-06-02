package com.example.afishaminsk.Model;

public class Place {
    public int id;
    public String name, adress, work, description;

    public Place(int id, String name, String adress, String work, String description) {
        this.id = id;
        this.name = name;
        this.adress = adress;
        this.work = work;
        this.description = description;
    }

    public Place() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
