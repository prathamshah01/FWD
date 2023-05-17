package com.example.fwd;

import static com.example.fwd.R.id.navHistory;
import static com.example.fwd.R.id.navHome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class NavDrawerLayout extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    boolean doubleBackToExitPressedOnce;
    CheckInternet internet = new CheckInternet();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer_layout);

        internet.InternetConnectivityChecker(this);
        internet.start();


//        ID
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolBar);

//        TOOLBAR
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(NavDrawerLayout.this,drawerLayout,toolbar, R.string.OpenDrawer, R.string.CloseDrawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        loadFragment(new HomeFragment());

//        NAVIGATION DRAWER AND EXCHANGING FRAGMENTS
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                if (id == R.id.navHome){
                    loadFragment(new HomeFragment());

                } else if (id == R.id.navHistory) {
                    loadFragment( new HistoryFragment());

                } else if (id == R.id.navFeedback) {
                    loadFragment(new FeedbackFragment());

                } else if (id == R.id.navRecommend) {

                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.share);

                    File file = new File(getExternalCacheDir(), "share.jpg");
                    try {
                        FileOutputStream fos = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                        fos.flush();
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

//                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
//                    shareIntent.setType("image/jpeg");
//                    Uri imageUri = FileProvider.getUriForFile(NavDrawerLayout.this, "com.example.app.file-provider", file);
//                    shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
//                    shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                    startActivity(Intent.createChooser(shareIntent, "Share Image"));




                    Intent sendIntent = new Intent();

//                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
//                    shareIntent.setType("image/*");
//                    Uri imageUri = Uri.fromFile(R.mipmap.share); // Replace imageFile with the actual File object representing the image
//                    shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
//                    String text = "Hello, Food should not be wasted";
//                    shareIntent.putExtra(Intent.EXTRA_TEXT, text);
//                    startActivity(Intent.createChooser(shareIntent, "Share Image and Text"));

                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "Food Shouldn't  be wasted");
                    sendIntent.setType("text/*");
                    startActivity(sendIntent);

                } else if (id == R.id.navSettings) {
                    loadFragment(new SettingsFragment());

                } else if (id == R.id.navLogout){
                    SharedPreferences myPrefs = getSharedPreferences("Leftovers",
                            MODE_PRIVATE);
                    SharedPreferences.Editor editor = myPrefs.edit();
                    editor.clear();
                    editor.commit();
                    Toast.makeText(NavDrawerLayout.this, "You are loged out", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(NavDrawerLayout.this,Home.class);
                    startActivity(intent);

                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;

            }
        });
    }

//    HANDELING BACKPRESS
    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
                drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
                if (doubleBackToExitPressedOnce) {
                    super.onBackPressed();
                    return;
                }

                this.doubleBackToExitPressedOnce = true;
                Toast.makeText(this, "Please click back again to exit", Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce = false;
                    }
                }, 2000);
            }

        }


//    REPLACE FRAGMENT METHOD
    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.container,fragment);
        ft.commit();
    }



}