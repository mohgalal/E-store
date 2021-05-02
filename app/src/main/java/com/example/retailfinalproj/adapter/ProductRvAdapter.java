package com.example.retailfinalproj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retailfinalproj.ProductModel;
import com.example.retailfinalproj.R;

import java.util.List;

public class ProductRvAdapter extends RecyclerView.Adapter<ProductRvAdapter.ProductViewHolder> {


    List<ProductModel> productModelList;
    Context context;
    OnProductClickListener onProductClickListener;
    OnAddProductClick onAddProductClick;


    public interface OnAddProductClick{

        void onAddClick(View view , int postion);

    }

    public interface OnProductClickListener{
        void onProductClick(View view, int position);
    }

    public ProductRvAdapter(List<ProductModel> productModelList, Context context, OnProductClickListener onProductClickListener, OnAddProductClick onAddProductClick) {
        this.productModelList = productModelList;
        this.context = context;
        this.onProductClickListener = onProductClickListener;
        this.onAddProductClick = onAddProductClick;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.product_rv_design,parent,false);


        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductViewHolder holder, final int position) {

        ProductModel productModel = productModelList.get(position);
        holder.title.setText(productModel.getTitle());
        holder.details.setText(productModel.getDetails());
        holder.price.setText(productModel.getPriceFinalText());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onProductClickListener.onProductClick(v, holder.getAdapterPosition());
            }
        });
        // hena ema ba3oz a access 3la image
        Glide.with(context).load(productModel.getImage()).into(holder.image);

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddProductClick.onAddClick(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productModelList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title,details,price;
        ImageButton add;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.product_iv);
            title = itemView.findViewById(R.id.title_tv);
            details = itemView.findViewById(R.id.details_tv);
            price = itemView.findViewById(R.id.price_tv);
            add = itemView.findViewById(R.id.add_ib);

        }
    }
}
