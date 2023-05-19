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
import com.google.firebase.database.core.utilities.Validation;

import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;


public class RegistrationLayout extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference regData = database.getReference("Registration");

    private ActivityRegistrationLayoutBinding binding;
    private FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    String  email,password;
    CheckInternet internet = new CheckInternet();




//    public boolean isValidMobile(String phone) {
//        int number;
//        number = return android.util.Patterns.PHONE.matcher(phone).matches();
//    }

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

        internet.InternetConnectivityChecker(this);
        internet.start();

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {
                email = binding.edEmail.getText().toString().trim();
                password =binding.edPassword.getText().toString().trim();

                String name = binding.edName.getText().toString().trim();
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
                        binding.edPassword.setError("Enter Valid Password");
                    } else if (cPassword.equals("")) {
                        binding.edConfirmPassword.setError("Reenter password is empty");
                    }else if(!password.equals(cPassword)){
                        Toast.makeText(RegistrationLayout.this, "Both passwords are not same ", Toast.LENGTH_SHORT).show();
                    }
                    else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                        Toast.makeText(RegistrationLayout.this, "Please Enter a valid email", Toast.LENGTH_SHORT).show();
                    } else if (phoneNumber.length()<10){
                        Toast.makeText(RegistrationLayout.this, "Please Enter a valid phone number", Toast.LENGTH_SHORT).show();
                    }
                    else if (!binding.cbTerms.isChecked()){
                        Toast.makeText(RegistrationLayout.this, "Please agree our terms to continue", Toast.LENGTH_SHORT).show();
                    }

                    // validation completed

                    else {

//                  CREATING PROGRESSBAR
                        progressDialog = new ProgressDialog(RegistrationLayout.this);
                        progressDialog.setTitle("You are being registered ...");
                        progressDialog.show();

                        mAuth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(RegistrationLayout.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {

                                            SharedPreferences splash = getSharedPreferences("splash", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor splasheditor = splash.edit();

                                            splasheditor.putBoolean("isLogin", true);
                                            splasheditor.putString("email", email);
                                            splasheditor.apply();


                                            SharedPreferences sharedPreferences = getSharedPreferences("Leftovers", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedPreferences.edit();

                                            editor.putBoolean("isLogin", true);
                                            editor.putString("email", email);
                                            editor.putString("name",name);
                                            editor.putString("phone",phoneNumber);
                                            editor.apply();

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
                                                                    Registration registration = new Registration(name,phoneNumber,email);
                                                                    regData.child(key).setValue(registration);

                                                                    if (progressDialog.isShowing()){
                                                                        progressDialog.dismiss();

                                                                        Intent homeScreen = new Intent(RegistrationLayout.this,LoginActivity.class);
                                                                        startActivity(homeScreen);
                                                                        finish();
                                                                    }

                                                                }else{
                                                                    Log.i("Email Verify","False");
                                                                }
                                                            }
                                                        },0,800);

                                                    }else{
                                                        if (progressDialog.isShowing()){
                                                            progressDialog.dismiss();
                                                            Toast.makeText(RegistrationLayout.this,"Email verification failed",Toast.LENGTH_LONG).show();
                                                        }
                                                    }
                                                }
                                            });

//                                  Will print Failure until the link is clicked
                                        } else {
                                            // If sign in fails, a message will be displayed to the user.
                                            Log.w(TAG, "createUserWithEmail:failure", task.getException());

                                            if (progressDialog.isShowing()){
                                                progressDialog.dismiss();
                                                Toast.makeText(RegistrationLayout.this, "Email verification timing exceeded", Toast.LENGTH_SHORT).show();
                                            }
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

        binding.txtTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationLayout.this, Terms.class);
                startActivity(intent);
                    
            }
        });
    }
}