package com.example.fwd;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;

public class CheckInternet {

    private static final int CHECK_INTERVAL = 3000; // 3 seconds
    private Context context;
    private Handler handler;
    private Runnable runnable;

    public void InternetConnectivityChecker(Context context) {
        this.context = context;
        this.handler = new Handler();
        this.runnable = new Runnable() {
            @Override
            public void run() {
                checkInternetConnectivity();
                handler.postDelayed(this, CHECK_INTERVAL);
            }
        };
    }

    public void start() {
        handler.postDelayed(runnable, CHECK_INTERVAL);
    }

    public void stop() {
        handler.removeCallbacks(runnable);
    }

    private void checkInternetConnectivity() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        AlertDialog builder = new AlertDialog.Builder(context).create();
        builder.setCancelable(false);
        builder.setButton(DialogInterface.BUTTON_POSITIVE,"Try Again",  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                builder.dismiss();
            }
        });
        if (isConnected) {
            builder.dismiss();
        } else {
            builder.setTitle("No Internet Connection");
            builder.setMessage("Please Check your internet connectivity and try again");
            builder.show();
        }
    }

}
