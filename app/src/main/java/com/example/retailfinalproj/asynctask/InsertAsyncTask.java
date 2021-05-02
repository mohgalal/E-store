package com.example.retailfinalproj.asynctask;

import android.os.AsyncTask;

import com.example.retailfinalproj.ProductModel;
import com.example.retailfinalproj.room.ProductDao;

public class InsertAsyncTask extends AsyncTask<ProductModel, Void , Void> {

    private ProductDao productDao;

    public InsertAsyncTask(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    protected Void doInBackground(ProductModel... productModels) {
        productDao.Insertproduct(productModels[0]);
        return null;
    }
}
