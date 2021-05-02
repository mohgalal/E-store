package com.example.retailfinalproj.fragments;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.retailfinalproj.CategoriesModel;
import com.example.retailfinalproj.R;
import com.example.retailfinalproj.adapter.CartRvAdapter;
import com.example.retailfinalproj.adapter.CategoriesRvAdapter;
import com.example.retailfinalproj.retrofit.CategoryResponse;
import com.example.retailfinalproj.retrofit.RetrofitFactoty;
import com.example.retailfinalproj.retrofit.WebServices;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesFragment extends Fragment {

    private RecyclerView categoryRv;
    private CategoriesRvAdapter adapter;
    private List<CategoriesModel> categoriesModelList = new ArrayList<>();

    WebServices webServices;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        categoryRv = view.findViewById(R.id.category_rv);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        getAllCategories();
        setUpRecyclerView();

    }

    private void getAllCategories() {

        webServices = RetrofitFactoty.getRetrofit().create(WebServices.class);

        Call<CategoryResponse> responseCall = webServices.getCategoryResponse();

        responseCall.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                Toast.makeText(requireContext(), "success", Toast.LENGTH_SHORT).show();

                categoriesModelList.clear();
                categoriesModelList.addAll(response.body().getCategoriesModelsList());
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                Toast.makeText(requireContext(), "Erorr", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void setUpRecyclerView() {

        adapter = new CategoriesRvAdapter(categoriesModelList,requireContext());

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        categoryRv.setLayoutManager(layoutManager);
        categoryRv.addItemDecoration(new HomeFragment.GridSpacingItemDecoration(2,dpToPx(14),true));
        categoryRv.setItemAnimator(new DefaultItemAnimator());
        categoryRv.setAdapter(adapter);

    }
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

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