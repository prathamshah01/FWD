package com.example.fwd;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.fwd.databinding.ActivityLoginBinding;
import com.example.fwd.databinding.ActivityRegistrationLayoutBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Timer;
import java.util.TimerTask;


public class RegistrationLayout extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference regData = database.getReference("Registration");

    private ActivityRegistrationLayoutBinding binding;
    private FirebaseAuth mAuth;
    String  email,password;



    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }

    private void reload() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {
                email = binding.edEmail.getText().toString().trim();
                password =binding.edPassword.getText().toString().trim();

                String name = binding.edName.getText().toString();
                String phoneNumber = binding.edPhone.getText().toString();
                String cPassword = binding.edConfirmPassword.getText().toString();

                    //specific colum validation
                    if (name.equals("")) {
                        binding.edName.setError("Enter Name");
                    } else if (phoneNumber.equals("")) {
                        binding.edPhone.setError("Enter Phone Number");
                    } else if (email.equals("")) {
                        binding.edEmail.setError("Enter Email");
                    } else if (password.equals("")) {
                        binding.edPassword.setError("Enter Password");
                    } else if (cPassword.equals("")) {
                        binding.edConfirmPassword.setError("Enter Repassword");
                    }else if(!password.equals(cPassword)){
                        Toast.makeText(RegistrationLayout.this, "Both password are not same ", Toast.LENGTH_SHORT).show();
                    }
                    // validation completed

                    else {
                        mAuth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(RegistrationLayout.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(RegistrationLayout.this,"Click the link sent to your email for registration",Toast.LENGTH_LONG).show();

                                            // If Registration success, The user will be redirected to home screen and data will be stored in Firebase
                                            FirebaseUser user = mAuth.getCurrentUser();
                                            mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if(task.isSuccessful()){
                                                        Timer timer = new Timer();
                                                        timer.schedule(new TimerTask() {

                                                            @Override
                                                            public void run() {
                                                                mAuth.getCurrentUser().reload();
                                                                if(mAuth.getCurrentUser().isEmailVerified()){
                                                                    timer.cancel();

                                                                    String key = regData.push().getKey();
                                                                    Registration registration = new Registration(name,phoneNumber,email,password,cPassword);
                                                                    regData.child(key).setValue(registration);

                                                                    Intent homeScreen = new Intent(RegistrationLayout.this,NavDrawerLayout.class);
                                                                    startActivity(homeScreen);
                                                                    finish();
                                                                }else{
                                                                    Log.i("Email Verify","False");
                                                                }
                                                            }
                                                        },0,800);

                                                    }else{
                                                        Toast.makeText(RegistrationLayout.this,"Email is Not verified",Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                            });

//                                  Will print Failure until the link is clicked
                                        } else {
                                            // If sign in fails, a message will be displayed to the user.
                                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                            Toast.makeText(RegistrationLayout.this, "Email already exists",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                    }
            }
        });

        binding.txtRegLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(RegistrationLayout.this,LoginActivity.class);
                startActivity(login);
                finish();
            }
        });
    }
}