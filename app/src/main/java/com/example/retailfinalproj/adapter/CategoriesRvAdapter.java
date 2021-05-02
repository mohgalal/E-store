package com.example.retailfinalproj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retailfinalproj.CategoriesModel;
import com.example.retailfinalproj.R;

import java.util.List;

public class CategoriesRvAdapter extends RecyclerView.Adapter<CategoriesRvAdapter.CategoriesViewHolder> {

    List<CategoriesModel> categoriesModelList;
    Context context;

    public CategoriesRvAdapter(List<CategoriesModel> categoriesModelList, Context context) {
        this.categoriesModelList = categoriesModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.category_rv_design,parent,false);

        return new CategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {

        CategoriesModel categoriesModel = categoriesModelList.get(position);
        Glide.with(context).load(categoriesModel.getImage()).into(holder.categoryIv);
        holder.categoryTV.setText(categoriesModel.getName());
    }

    @Override
    public int getItemCount() {
        return categoriesModelList.size();
    }

    class CategoriesViewHolder extends RecyclerView.ViewHolder{

        ImageView categoryIv;
        TextView categoryTV;

        public CategoriesViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryIv =  itemView.findViewById(R.id.category_iv);
            categoryTV =  itemView.findViewById(R.id.category_title_tv);

        }
    }
}
