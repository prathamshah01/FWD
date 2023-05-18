package com.example.fwd;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.fwd.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.zip.Inflater;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private String email,password;
    private FirebaseAuth mAuth;

    CheckBox checkbox;
    ProgressDialog progressDialog;

    CheckInternet internet = new CheckInternet();


//    boolean isClickable = true;


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        internet.InternetConnectivityChecker(this);
        internet.start();

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email= binding.edEmail.getText().toString().trim();
                password = binding.edPassword.getText().toString().trim();

                if (email.equals("")){
                    binding.edEmail.setError("Enter Name");
                }
                else if (password.equals("")){
                    binding.edPassword.setError("Enter Password");
                } else {

//                    DISABLING CLICK
//                    binding.edEmail.setEnabled(!isClickable);
//                    binding.edPassword.setEnabled(!isClickable);

//                    CREATING PROCESS DIALOG
                    progressDialog = new ProgressDialog(LoginActivity.this);
                    progressDialog.setTitle("Checking your credentials...");
                    progressDialog.show();


                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        if(binding.cbRemember.isChecked()){

//                                      STORING DATA IN SHARDED PREF.

                                            SharedPreferences sharedPreferences = getSharedPreferences("Leftovers", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedPreferences.edit();

                                            editor.putBoolean("isLogin", true);
                                            editor.putString("email", email);
                                            editor.apply();

                                            if (progressDialog.isShowing()) {
                                                progressDialog.dismiss();

                                                // If Sign in success, User will be navigated to home screen
                                                Toast.makeText(LoginActivity.this, "Logged In Successfully", Toast.LENGTH_SHORT).show();
                                                Intent homeScreen = new Intent(LoginActivity.this, NavDrawerLayout.class);
                                                startActivity(homeScreen);
                                                finish();
                                            }


                                        }

                                        else{
                                            if (progressDialog.isShowing()) {
                                                progressDialog.dismiss();

                                                // If Sign in success, User will be navigated to home screen
                                                Toast.makeText(LoginActivity.this, "Logged In Successfully", Toast.LENGTH_SHORT).show();
                                                Intent homeScreen = new Intent(LoginActivity.this, NavDrawerLayout.class);
                                                startActivity(homeScreen);
                                                finish();
                                            }
                                        }


                                    } else {
                                        if (progressDialog.isShowing()) {
                                            progressDialog.dismiss();

                                            Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                }
                            });
                }
                   
            }
        });


        binding.txtForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,ForgotPassword.class);
                startActivity(intent);
                   
            }
        });

        binding.txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegistrationLayout.class);
                startActivity(intent);
                finish();
                   
            }
        });




    }

}