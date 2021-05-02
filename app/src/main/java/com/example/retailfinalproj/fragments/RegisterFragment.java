package com.example.retailfinalproj.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retailfinalproj.R;
import com.example.retailfinalproj.retrofit.RegisterRequset;
import com.example.retailfinalproj.retrofit.RegisterResponse;
import com.example.retailfinalproj.retrofit.RetrofitFactoty;
import com.example.retailfinalproj.retrofit.WebServices;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterFragment extends Fragment {

    TextInputEditText nameTv;
    TextInputEditText emailTv;
    TextInputEditText passwordTv;
    MaterialButton registerBtn;
    TextView goToLoginTv;

    WebServices webServices;
    ProgressDialog dialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_register, container, false);
        nameTv = view.findViewById(R.id.name_et);
        emailTv = view.findViewById(R.id.emil_et);
        passwordTv = view.findViewById(R.id.pass_et);
        registerBtn = view.findViewById(R.id.signup_btn);
        goToLoginTv = view.findViewById(R.id.go_to_login_tv);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dialog = new ProgressDialog(requireContext());
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

       setUpOnClickListener();

    }

    private void setUpOnClickListener() {
        goToLoginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(v).navigate(R.id.action_registerFragment_to_loginFragment);
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.show();

                String name = nameTv.getText().toString();
                String email = emailTv.getText().toString();
                String password = passwordTv.getText().toString();

                webServices = RetrofitFactoty.getRetrofit().create(WebServices.class);

                final Call<RegisterResponse> register = webServices.registerNewUser(new RegisterRequset(name,email,password));

                register.enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                        dialog.dismiss();
                        Toast.makeText(requireContext(), "done", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {
                        dialog.dismiss();
                        Toast.makeText(requireContext() , "error", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
}