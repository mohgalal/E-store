package com.example.retailfinalproj.retrofit;

import com.example.retailfinalproj.CategoriesModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryResponse {

    @SerializedName("categories")
    private List<CategoriesModel> categoriesModelsList;

    public List<CategoriesModel> getCategoriesModelsList() {
        return categoriesModelsList;
    }

    public void setCategoriesModelsList(List<CategoriesModel> categoriesModelsList) {
        this.categoriesModelsList = categoriesModelsList;
    }
}
