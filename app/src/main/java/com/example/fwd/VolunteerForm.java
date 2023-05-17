package com.example.fwd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.fwd.databinding.ActivityVolunteerFormBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class VolunteerForm extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Volunteer");

    private ActivityVolunteerFormBinding binding;
    ProgressDialog progressDialog;

    //  TO GET CURRENT DATE AND TIME
    final Calendar c = Calendar.getInstance();

    //  INITIALIZING VARIABLES
    int hour = c.get(Calendar.HOUR_OF_DAY);
    int minute = c.get(Calendar.MINUTE);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =   ActivityVolunteerFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.edPickupTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(VolunteerForm.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minuteOfDay) {
                        binding.edPickupTime.setText( hourOfDay + ":" + minuteOfDay);
                    }
                },hour,minute,true);
                timePickerDialog.show();
            }
        });


        binding.btnVolunteerSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //validation start
                String vName = binding.edVolunteerName.getText().toString().trim();
                String vPhone = binding.edVolunteerPhone.getText().toString().trim();
                String vAddress = binding.edVolunteerAddress.getText().toString().trim();
                String vPickupTime = binding.edPickupTime.getText().toString().trim();

                if (vName.equals("")){
                    binding.edVolunteerName.setError("Enter Name first");
                } else if (vPhone.equals("")) {
                    binding.edVolunteerPhone.setError("Enter Phone number first");
                }
                else if (vAddress.equals("")) {
                    binding.edVolunteerAddress.setError("Enter Address first");
                } else if (vPickupTime.equals("")) {
                    binding.edPickupTime.setError("Enter Pickup Time First");
                }
                else if (vPhone.length()<10){
                    Toast.makeText(VolunteerForm.this, "Enter valid phone number", Toast.LENGTH_SHORT).show();
                }

                else{

                    progressDialog = new ProgressDialog(VolunteerForm.this);
                    progressDialog.setTitle("Volunteer is being registered and navigated to donation Request ...");
                    progressDialog.show();

//                  STORING DATA IN DATABASE

                    String key = myRef.push().getKey();
                    Volunteer volunteer = new Volunteer(vName, vPhone, vAddress, vPickupTime);
                    myRef.child(key).setValue(volunteer);

                    if (progressDialog.isShowing()){
                        progressDialog.dismiss();

                        Toast.makeText(VolunteerForm.this, "Volunteer is registered", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(VolunteerForm.this, viewRequests.class);
                        startActivity(intent);
                        finish();

                    }

                    }

            }
        });

    }
}