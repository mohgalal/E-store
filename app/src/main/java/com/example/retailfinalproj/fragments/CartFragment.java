package com.example.retailfinalproj.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.retailfinalproj.ProductModel;
import com.example.retailfinalproj.R;
import com.example.retailfinalproj.adapter.CartRvAdapter;
import com.example.retailfinalproj.asynctask.DeleteAsyncTask;
import com.example.retailfinalproj.asynctask.GetAsyncTask;
import com.example.retailfinalproj.asynctask.UpdateAsyncTask;
import com.example.retailfinalproj.room.ProductsDatabase;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class CartFragment extends Fragment {

    private RecyclerView cartRv;
    private List<ProductModel> productModelList = new ArrayList<>();
    private CartRvAdapter adapter;

    MaterialButton clearBtn,checkoutBtn;

   AlertDialog.Builder dialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        cartRv = view.findViewById(R.id.cart_rv);
        clearBtn = view.findViewById(R.id.clear_btn);
        checkoutBtn = view.findViewById(R.id.checkout_btn);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getAllProductsFromDB();
        setUpRecyclerView();
        setUpOnClickListeners();
    }

    private void setUpOnClickListeners() {

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog= new AlertDialog.Builder(requireContext());
                dialog.setTitle("Delete All Products");
                dialog.setMessage("Do you want to delete all your products");
                dialog.setIcon(R.drawable.ic_delete);
                dialog.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new DeleteAsyncTask(ProductsDatabase.getDatabase(requireContext()).getProductDao()).execute();
                        productModelList.clear();
                        adapter.notifyDataSetChanged();
                    }
                });
                dialog.setNeutralButton("CANCEL",null);
                dialog.show();



            }
        });

        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_cartFragment_to_registerFragment);
            }
        });
    }

    private void getAllProductsFromDB() {

        try {
            productModelList.addAll(new GetAsyncTask(ProductsDatabase.getDatabase(requireContext()).getProductDao()).execute().get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void setUpRecyclerView() {

        adapter = new CartRvAdapter(productModelList, requireContext(), new CartRvAdapter.OnProClickListener() {
            @Override
            public void onProClicked(View view, int position) {
                ProductModel productModel = productModelList.get(position);

                Bundle bundle = new Bundle();

                bundle.putSerializable("abc", productModel);

                Navigation.findNavController(view).navigate(R.id.action_cartFragment_to_productDetailsFragment, bundle);
            }
        }, new CartRvAdapter.OnIncClickeListener() {
            @Override
            public void onIncClick(View view, int position) {

                ProductModel productModel = productModelList.get(position);

                productModel.setQuantity(productModel.getQuantity()+1);

                new UpdateAsyncTask(ProductsDatabase.getDatabase(requireContext()).getProductDao()).execute(productModel);

                adapter.notifyDataSetChanged();


            }
        }, new CartRvAdapter.OnDecClickeListener() {
            @Override
            public void onDecClick(View view, int position) {
                ProductModel productModel = productModelList.get(position);
                if (productModel.getQuantity() >1) {

                    productModel.setQuantity(productModel.getQuantity() - 1);

                    new UpdateAsyncTask(ProductsDatabase.getDatabase(requireContext()).getProductDao()).execute(productModel);

                    adapter.notifyDataSetChanged();
                }
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false);
        cartRv.setLayoutManager(layoutManager);
        cartRv.addItemDecoration(new DividerItemDecoration(requireContext(),LinearLayoutManager.VERTICAL));
        cartRv.setItemAnimator(new DefaultItemAnimator());
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(cartRv);
        cartRv.setAdapter(adapter);
    }
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            productModelList.remove(viewHolder.getAdapterPosition());
            adapter.notifyDataSetChanged();

        }
    };
}