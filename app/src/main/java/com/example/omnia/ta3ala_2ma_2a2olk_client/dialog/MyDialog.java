package com.example.omnia.ta3ala_2ma_2a2olk_client.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.CountDownTimer;
import android.widget.Toast;

/**
 * Created by HeshamMuhammed on 6/18/2018.
 */

public class MyDialog {

    ProgressDialog progressDialog;
    boolean state = false;

    public void show(final Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Loading");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading Data From Server Please Wait");
        progressDialog.show();
        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                progressDialog.dismiss();
                if (state == false){
                    Toast.makeText(context, "Error While Loading Data Please Try Again Later", Toast.LENGTH_LONG).show();
                }
            }
        }.start();
    }

    public void dismiss() {
        state = true;
        progressDialog.dismiss();
    }
}