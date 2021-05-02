package com.example.retailfinalproj.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface WebServices {

    @GET("products")
    Call<ProductsResponse> getProductResponse();

    @GET("categories")
    Call<CategoryResponse> getCategoryResponse();

    @POST("register")
    Call<RegisterResponse> registerNewUser(@Body RegisterRequset registerRequset);

    @POST("login")
    Call<RegisterResponse> loginUser(@Body RegisterRequset registerRequset);
}
