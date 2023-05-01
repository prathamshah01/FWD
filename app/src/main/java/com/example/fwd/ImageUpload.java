package com.example.fwd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fwd.databinding.ActivityCommunityBinding;
import com.example.fwd.databinding.ActivityImageUploadBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ImageUpload extends AppCompatActivity {


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Image");
    private ActivityImageUploadBinding binding;
    StorageReference storageReference;
    Uri imageUri;
    ProgressDialog progressDialog;
    private final int Gallery_Req_Code = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityImageUploadBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imgUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectImage();
            }
        });

        binding.btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String description = binding.edDescription.getText().toString().trim();

                //validation start
                if (description.equals("")){
                    binding.edDescription.setError("Please enter your experience");

                }
                else if (imageUri == null){
                    Toast.makeText(ImageUpload.this, "Please upload Image", Toast.LENGTH_SHORT).show();
                }

                else {
                    uploadImage();




                }

            }
        });

    }

    private void uploadData() {


    }

    //  SELECT IMAGE METHOD
    private void selectImage() {

        Intent iGalery = new Intent(Intent.ACTION_PICK);
        iGalery.setData((MediaStore.Images.Media.EXTERNAL_CONTENT_URI));
        startActivityForResult(iGalery, Gallery_Req_Code);
    }

//    UPLODING IMAGE
    private void uploadImage() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Your image is being Uploaded...");
        progressDialog.show();

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-hh:mm", Locale.CANADA);
        Date now = new Date();
        String filename = formatter.format(now);

        storageReference = FirebaseStorage.getInstance().getReference("images/"+filename);

        storageReference.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        binding.imgUpload.setImageURI(null);
                        Toast.makeText(ImageUpload.this, "Successfully Uploaded", Toast.LENGTH_SHORT).show();

                        if (progressDialog.isShowing()){
                            progressDialog.dismiss();
                        }
                        binding.imgUpload.setImageResource(R.mipmap.upload);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if(progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

                        Toast.makeText(ImageUpload.this, "Failed to Upload", Toast.LENGTH_SHORT).show();

                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null && data.getData() != null){

            imageUri = data.getData();
            if (requestCode == Gallery_Req_Code){
                // FOR SETTING IMAGE
                binding.imgUpload.setImageURI(data.getData());
            }
        }
    }


}