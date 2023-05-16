package com.example.fwd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
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
import com.google.firebase.storage.OnProgressListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ImageUpload extends AppCompatActivity {

    //  VARIABLES
    StorageReference storageReference;
    private ActivityImageUploadBinding binding;
    Uri imageUri;
    ProgressDialog progressDialog;
    private final int Gallery_Req_Code = 1000;
    private DatabaseReference imagedb = FirebaseDatabase.getInstance().getReference("Community");
    String description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityImageUploadBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


//        SETTING IMAGE NAME
        storageReference = FirebaseStorage.getInstance().getReference();
//        IMAGE NAME FINISHED



        binding.imgUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });


        binding.btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //validation start
                if (imageUri == null) {
                    Toast.makeText(ImageUpload.this, "Please upload Image", Toast.LENGTH_SHORT).show();
                } else {
                    uploadImage(imageUri);
                }
            }
        });
    }

    //  SELECT IMAGE METHOD
    private void selectImage() {

        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setData((MediaStore.Images.Media.EXTERNAL_CONTENT_URI));
        startActivityForResult(galleryIntent, Gallery_Req_Code);
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
    //  SELECT IMAGE METHOD COMPLETE


    //    UPLOADING IMAGE
    public void uploadImage(Uri uri) {

//        CREATING PROGRESSBAR
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Your image is being Uploaded...");
        progressDialog.show();

        description = binding.edDescription.getText().toString().trim();
        StorageReference reference = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        ImageUploadModel imageUploadModel = new ImageUploadModel(uri.toString(),description);
                        String modelId = imagedb.push().getKey();
                        imagedb.child(modelId).setValue(imageUploadModel,description);
                        Toast.makeText(ImageUpload.this, "Image Uploaded successfully...", Toast.LENGTH_SHORT).show();

                        if (progressDialog.isShowing()){
                            progressDialog.dismiss();

                            Intent intent = new Intent(ImageUpload.this, Community.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                if (!progressDialog.isShowing()){
                    progressDialog.isShowing();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                if (progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
                binding.imgUpload.setImageResource(R.mipmap.upload);
                Toast.makeText(ImageUpload.this, "Image upload failed !!! ", Toast.LENGTH_SHORT).show();
            }
        });

    }

//    WILL RETURN THE SELECTED IMAGE EXTENSION
    public String getFileExtension(Uri muri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(muri));
    }
    //    UPLOADING IMAGE FINISHED

}