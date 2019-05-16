package com.example.connie.brandee.models;

public class Questions {
    String id;
    String name;
    String logo;
    String level;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }



    public Questions(String id, String name, String logo, String level) {

        this.id = id;
        this.name = name;
        this.logo = logo;
        this.level = level;
    }
}