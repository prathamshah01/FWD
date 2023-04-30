package com.example.fwd;

import static com.example.fwd.R.id.navHistory;
import static com.example.fwd.R.id.navHome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;


public class NavDrawerLayout extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    boolean doubleBackToExitPressedOnce;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer_layout);

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

                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "Food Shouldn't  be wasted");
                    sendIntent.setType("text/plain");
                    startActivity(sendIntent);

                } else if (id == R.id.navSettings) {

                    loadFragment(new SettingsFragment());

                } else if (id == R.id.navLogout){

                    SharedPreferences myPrefs = getSharedPreferences("MY",
                            MODE_PRIVATE);
                    SharedPreferences.Editor editor = myPrefs.edit();
                    editor.clear();
                    editor.commit();
                    Intent intent = new Intent(NavDrawerLayout.this,Home.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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