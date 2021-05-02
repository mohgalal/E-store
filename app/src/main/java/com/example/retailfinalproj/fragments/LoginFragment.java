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

public class LoginFragment extends Fragment {

   private TextInputEditText emailEt,passwordEt;
   private MaterialButton loginBtn;
   private TextView goToSignUpTv;

   private WebServices webServices;
   private ProgressDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_login, container, false);
        emailEt = v.findViewById(R.id.eamilo_et);
        passwordEt = v.findViewById(R.id.passo_et);
        loginBtn = v.findViewById(R.id.login_btn);
        goToSignUpTv = v.findViewById(R.id.go_to_login_tv);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dialog = new ProgressDialog(requireContext());
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        setUpClickListeners();
    }

    private void setUpClickListeners() {
        goToSignUpTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_registerFragment);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();

                String email = emailEt.getText().toString();
                String password = passwordEt.getText().toString();

                webServices = RetrofitFactoty.getRetrofit().create(WebServices.class);

                Call<RegisterResponse> login = webServices.loginUser(new RegisterRequset(email,password));

                login.enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                        dialog.dismiss();
                        Toast.makeText(requireContext(), "done", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {
                        dialog.dismiss();
                        Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
    }
}