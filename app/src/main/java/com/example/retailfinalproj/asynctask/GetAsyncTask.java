package com.example.retailfinalproj.asynctask;

import android.os.AsyncTask;

import com.example.retailfinalproj.ProductModel;
import com.example.retailfinalproj.room.ProductDao;

import java.util.List;

public class GetAsyncTask extends AsyncTask<Void,Void, List<ProductModel>> {

    private ProductDao productDao;

    public GetAsyncTask(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    protected List<ProductModel> doInBackground(Void... voids) {
        return productDao.getAllProducts();
    }
}
