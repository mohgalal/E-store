package com.example.retailfinalproj.fragments;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.retailfinalproj.ProductModel;
import com.example.retailfinalproj.adapter.ProductRvAdapter;
import com.example.retailfinalproj.asynctask.InsertAsyncTask;
import com.example.retailfinalproj.retrofit.ProductsResponse;
import com.example.retailfinalproj.R;
import com.example.retailfinalproj.retrofit.RetrofitFactoty;
import com.example.retailfinalproj.retrofit.WebServices;
import com.example.retailfinalproj.room.ProductsDatabase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    RecyclerView productRv;
    List<ProductModel> productModelList = new ArrayList<>();
    ProductRvAdapter adapter;

    WebServices webServices;
    ProgressDialog dialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        productRv = view.findViewById(R.id.product_rv);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dialog = new ProgressDialog(requireContext());
        dialog.setProgressStyle(ProgressDialog.BUTTON2);
        dialog.show();

        setUpRecyclerView();
        getAllProdust();


    }



    private void getAllProdust() {

        webServices = RetrofitFactoty.getRetrofit().create(WebServices.class);

        Call<ProductsResponse> getResponse= webServices.getProductResponse();

        getResponse.enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {

                dialog.dismiss();
                productModelList.clear();
                productModelList.addAll(response.body().getProductModelList());
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {
                dialog.dismiss();
                Log.d("abd",t.toString());
                Toast.makeText(requireContext(), "Error!", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void setUpRecyclerView() {

        adapter = new ProductRvAdapter(productModelList, getContext(), new ProductRvAdapter.OnProductClickListener() {
            @Override
            public void onProductClick(View view, int position) {

                ProductModel clickProduct = productModelList.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("abc", clickProduct);

                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_productDetailsFragment, bundle);


            }
        }, new ProductRvAdapter.OnAddProductClick() {
            @Override
            public void onAddClick(View view, int postion) {

                Toast.makeText(requireContext(), "added to Cart", Toast.LENGTH_SHORT).show();
                ProductModel productModel = productModelList.get(postion);
                 new InsertAsyncTask(ProductsDatabase.getDatabase(getContext()).getProductDao()).execute(productModel);

//                 Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_cartFragment);

            }
        });
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        productRv.setLayoutManager(layoutManager);
        productRv.addItemDecoration(new GridSpacingItemDecoration(2,dpToPx(14),true));
        productRv.setItemAnimator(new DefaultItemAnimator());
        productRv.setAdapter(adapter);


    }
    public static class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}