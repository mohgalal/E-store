package com.example.retailfinalproj.asynctask;

import android.os.AsyncTask;

import com.example.retailfinalproj.room.ProductDao;

public class DeleteAsyncTask extends AsyncTask<Void,Void,Void> {
    private ProductDao productDao;

    public DeleteAsyncTask(ProductDao productDao) {
        this.productDao = productDao;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        productDao.deleteAllProducts();
        return null;
    }
}
