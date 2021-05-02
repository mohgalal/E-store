package com.example.retailfinalproj.retrofit;

import com.example.retailfinalproj.ProductModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductsResponse {

    @SerializedName("products")
  private List<ProductModel> productModelList;

    public List<ProductModel> getProductModelList() {
        return productModelList;
    }

    public void setProductModelList(List<ProductModel> productModelList) {
        this.productModelList = productModelList;
    }
}
