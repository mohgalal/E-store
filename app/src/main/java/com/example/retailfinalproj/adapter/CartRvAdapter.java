package com.example.retailfinalproj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retailfinalproj.ProductModel;
import com.example.retailfinalproj.R;

import java.util.List;

public class CartRvAdapter extends RecyclerView.Adapter<CartRvAdapter.CartViewHolder>  {

   private List<ProductModel> productModelList;
    private Context context;
    private OnProClickListener onProClickListener;
    private OnIncClickeListener onIncClickeListener;
    private OnDecClickeListener onDecClickeListener;

    public CartRvAdapter(List<ProductModel> productModelList, Context context, OnProClickListener onProClickListener, OnIncClickeListener onIncClickeListener, OnDecClickeListener onDecClickeListener) {
        this.productModelList = productModelList;
        this.context = context;
        this.onProClickListener = onProClickListener;
        this.onIncClickeListener = onIncClickeListener;
        this.onDecClickeListener = onDecClickeListener;
    }




    public interface OnIncClickeListener{

        void onIncClick(View view , int position);
    }

    public interface OnDecClickeListener{

        void onDecClick(View view , int position);
    }

    public interface OnProClickListener{

        void onProClicked(View view , int position);

    }


    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.cart_rv_design,parent,false);

        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartViewHolder holder, final int position) {

        ProductModel productModel= productModelList.get(position);
        holder.title.setText(productModel.getTitle());
        holder.details.setText(productModel.getDetails());
        holder.price.setText(productModel.getPriceFinalText());
        Glide.with(context).load(productModel.getImage()).into(holder.cartIv);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onProClickListener.onProClicked(v , holder.getAdapterPosition());
            }
        });
        holder.incre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onIncClickeListener.onIncClick(v,position);
            }
        });
        holder.decer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDecClickeListener.onDecClick(v,position);
            }
        });

        holder.quantity.setText(productModel.getQuantity()+"");
    }

    @Override
    public int getItemCount() {
        return productModelList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {

        ImageView cartIv;
        TextView title,details,price,quantity;
        ImageButton incre , decer;



        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            cartIv= itemView.findViewById(R.id.cart_iv);
            title= itemView.findViewById(R.id.cart_title_tv);
            details= itemView.findViewById(R.id.cart_details_tv);
            price= itemView.findViewById(R.id.cart_price_tv);
            incre= itemView.findViewById(R.id.inc_ib);
            decer= itemView.findViewById(R.id.dec_ib);
            quantity= itemView.findViewById(R.id.quanitiy_tv);

        }
    }
}
