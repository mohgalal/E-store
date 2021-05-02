package com.example.retailfinalproj.room;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.retailfinalproj.ProductModel;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void Insertproduct(ProductModel productModel);

    @Query("SELECT * FROM products")
    List<ProductModel> getAllProducts();

    @Query("DELETE FROM products")
    void deleteAllProducts();

    @Update
    void updateProduct(ProductModel productModel);
}
