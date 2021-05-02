package com.example.retailfinalproj.room;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

import com.example.retailfinalproj.ProductModel;

@Database(entities = {ProductModel.class}, version = 1, exportSchema = false)
public abstract class ProductsDatabase extends androidx.room.RoomDatabase {

    public abstract ProductDao getProductDao();

    //singlton

    private static ProductsDatabase database;

    public static ProductsDatabase getDatabase(Context context) {


        if (database == null){
            database = Room.databaseBuilder(context,ProductsDatabase.class,"products_database").build();
        }

        return database;
    }

}
