package com.example.retailfinalproj;

import com.google.gson.annotations.SerializedName;

public class CategoriesModel {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("avatar")
    private String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
