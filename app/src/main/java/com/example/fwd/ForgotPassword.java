package com.example.fwd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.fwd.databinding.ActivityForgotPasswordBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private FirebaseAuth mAuth;
    CheckInternet internet = new CheckInternet();


    private ActivityForgotPasswordBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        internet.InternetConnectivityChecker(this);
        internet.start();

        mAuth = FirebaseAuth.getInstance();
        boolean isClickable = true;

        binding.btnForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = binding.edForgotEmail.getText().toString().trim();

                if (email.equals("")){
                    binding.edForgotEmail.setError("Enter email first");
                }

                else {
                    mAuth.sendPasswordResetEmail(binding.edForgotEmail.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {


                            if (task.isSuccessful()) {
                                Toast.makeText(ForgotPassword.this, "Password reset link is sent to your mail", Toast.LENGTH_SHORT).show();
                                binding.edForgotEmail.setEnabled(!isClickable);


                                Intent intent = new Intent(ForgotPassword.this, LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(ForgotPassword.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }

                    });
                }
            }
        });

    }
}