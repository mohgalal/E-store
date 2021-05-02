package com.example.retailfinalproj.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.retailfinalproj.ProductModel;
import com.example.retailfinalproj.R;
import com.example.retailfinalproj.asynctask.InsertAsyncTask;
import com.example.retailfinalproj.asynctask.UpdateAsyncTask;
import com.example.retailfinalproj.room.ProductsDatabase;
import com.google.android.material.button.MaterialButton;

public class ProductDetailsFragment extends Fragment {

    private ImageView productImage;
    private TextView titleProductTv,detailsProductTv,priceProductTv,quantityProductTv,descriptionProductTv;
    private MaterialButton addToCartBtn;
    private ImageButton incBtn,decBtn;

    ProductModel productModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_product_details, container, false);

        productImage = view.findViewById(R.id.image_prod_iv);
        titleProductTv = view.findViewById(R.id.title_details_tv);
        detailsProductTv = view.findViewById(R.id.detail_details_tv);
        priceProductTv = view.findViewById(R.id.price_details_tv);
        quantityProductTv = view.findViewById(R.id.quanitiy_tv);
        descriptionProductTv = view.findViewById(R.id.description_details_tv);
        addToCartBtn = view.findViewById(R.id.button_mb);
        incBtn = view.findViewById(R.id.inc_ib);
        decBtn = view.findViewById(R.id.dec_ib);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getClickedProductFromHomeFragment();
        setUpClickListener();

    }

    private void setUpClickListener(){

        incBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                productModel.setQuantity(productModel.getQuantity()+1);

                quantityProductTv.setText(productModel.getQuantity()+"");

                new UpdateAsyncTask(ProductsDatabase.getDatabase(requireContext()).getProductDao()).execute(productModel);

            }
        });

        decBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (productModel.getQuantity() >1){

                productModel.setQuantity(productModel.getQuantity()-1);

                quantityProductTv.setText(productModel.getQuantity()+"");

                new UpdateAsyncTask(ProductsDatabase.getDatabase(requireContext()).getProductDao()).execute(productModel);
            }
            }
        });

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new InsertAsyncTask(ProductsDatabase.getDatabase(requireContext()).getProductDao()).execute(productModel);
                Navigation.findNavController(v).navigate(R.id.action_productDetailsFragment_to_cartFragment);
            }
        });

    }

    private void getClickedProductFromHomeFragment() {

        Bundle args = getArguments();
        if (args != null){
            productModel = (ProductModel) args.getSerializable("abc");

            Glide.with(requireContext()).load(productModel.getImage()).into(productImage);
            titleProductTv.setText(productModel.getTitle());
            detailsProductTv.setText(productModel.getDetails());
            priceProductTv.setText(productModel.getPriceFinalText());
            descriptionProductTv.setText(productModel.getDescription());
            quantityProductTv.setText(productModel.getQuantity()+"");


        }

}}