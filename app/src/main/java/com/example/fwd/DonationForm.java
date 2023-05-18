package com.example.fwd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.fwd.databinding.ActivityDonationFormBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class DonationForm extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Donator");
    private ActivityDonationFormBinding binding;

//  TO GET CURRENT DATE AND TIME
    final Calendar c = Calendar.getInstance();

    //  INITIALIZING VARIABLES
    int year = c.get(Calendar.YEAR);
    int month = c.get(Calendar.MONTH);
    int day = c.get(Calendar.DAY_OF_MONTH);
    int hour = c.get(Calendar.HOUR_OF_DAY);
    int minute = c.get(Calendar.MINUTE);
    ProgressDialog progressDialog;
    CheckInternet internet = new CheckInternet();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDonationFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        internet.InternetConnectivityChecker(this);
        internet.start();

//        DATE AND TIME PICKER IN FOOD AVAILABLE FROM
        binding.edAvailableFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        DonationForm.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                // on below line we are setting date to our edit text.
//                                binding.edAvailableFrom.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                                TimePickerDialog timePickerDialog = new TimePickerDialog(DonationForm.this, new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minuteOfDay) {
                                        binding.edAvailableFrom.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year + "/" + hourOfDay + ":" + minuteOfDay);
                                    }
                                },hour,minute,true);
                                timePickerDialog.show();
                            }
                        },

                        // month and day for selected date in date picker.
                        year, month, day);

                // display date picker dialog.
                datePickerDialog.show();

            }
        });
//        FINISH DATE AND TIME PICKER IN FOOD AVAILABLE FROM

//        DATE AND TIME PICKER IN FOOD AVAILABLE TO

        binding.edAvailableTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        DonationForm.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                TimePickerDialog timePickerDialog = new TimePickerDialog(DonationForm.this, new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minuteOfDay) {

                                        // on below line we are setting date to our edit text.
                                        binding.edAvailableTo.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year + "/" + hourOfDay + ":" + minuteOfDay);
                                    }
                                },hour,minute,true);
                                timePickerDialog.show();
                            }
                        },

                        // month and day for selected date in date picker.
                        year, month, day);

                // display date picker dialog.
                datePickerDialog.show();

            }
        });



        binding.btnDonationSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //validation start
                String name = binding.edDonorName.getText().toString().trim();
                String phoneNumber = binding.edDonorPhone.getText().toString().trim();
                String foodType = binding.edFoodType.getText().toString().trim();
                String foodExpriy = binding.edExpiry.getText().toString();
                String foodCount = binding.edFoodCount.getText().toString().trim();
                String availableFrom =binding.edAvailableFrom.getText().toString();
                String availableTo= binding.edAvailableTo.getText().toString();
                String address = binding.edAddress.getText().toString().trim();
                String status = binding.txtStatus.getText().toString();


                if (name.equals("")) {
                    binding.edDonorName.setError("Enter name");
                } else if (phoneNumber.equals("")) {
                    binding.edDonorPhone.setError("Enter Phone Number");
                } else if (foodType.equals("")) {
                    binding.edFoodType.setError("Enter Food type");
                } else if (foodExpriy.equals("")) {
                    binding.edExpiry.setError("Enter Food Type");
                } else if (foodCount.equals("")) {
                    binding.edFoodCount.setError("Enter Food Count");
                }
                else if (availableFrom.equals("")) {
                    binding.edAvailableFrom.setError("Enter Available from time");
                }
                else if (availableTo.equals("")) {
                    binding.edAvailableTo.setError("Enter Available to time");
                }
                else if (address.equals("")) {
                    binding.edAddress.setError("Enter Address");
                }
                else if (phoneNumber.length()<10){
                    Toast.makeText(DonationForm.this, "Enter valid phone number", Toast.LENGTH_SHORT).show();
                }
                //validation completed
                else{

                    progressDialog = new ProgressDialog(DonationForm.this);
                    progressDialog.setTitle("Storing and displaying your Donation Request ...");
                    progressDialog.show();

//                    STORING DATA IN DATABASE
                    String key = myRef.push().getKey();
                    Donator donator = new Donator(name,phoneNumber,foodType,foodExpriy,foodCount,availableFrom,availableTo,address,status,key);
                    myRef.child(key).setValue(donator);

                    if (progressDialog.isShowing()){
                        progressDialog.dismiss();

//                    NAVIGATION TO VIEW REQUEST ACTIVITY
                        Intent intent = new Intent(DonationForm.this, viewRequests.class);
                        startActivity(intent);
                        Toast.makeText(DonationForm.this, "Your Request is stored successfully....", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }
            }
        });
    }
}