package com.maity.maityspositiveliving.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.maity.maityspositiveliving.R;


public class CustomAlertDialog {

    public static void NoInternetAlert(Activity activity){
        AlertDialog.Builder builder=new AlertDialog.Builder(activity,R.style.CustomAlertDialog);
        View view= LayoutInflater.from(activity).inflate(R.layout.no_internet_alert_sample_view,null);

        builder.setView(view);
        AlertDialog dialog=builder.create();

        dialog.setCancelable(false);
        dialog.show();


        view.findViewById(R.id.okid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();


            }
        });
    }
    public static void PayAlert(Activity activity){
       /* AlertDialog.Builder builder=new AlertDialog.Builder(activity,R.style.CustomAlertDialog);
        View view= LayoutInflater.from(activity).inflate(R.layout.payalert__layout,null);

        builder.setView(view);
        AlertDialog dialog=builder.create();

        dialog.setCancelable(false);
        dialog.show();


        view.findViewById(R.id.lv_payonline).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // startPayment();

               // dialog.dismiss();
                UserCleaningPlaceOrderActivity userCleaningPlaceOrderActivity=new UserCleaningPlaceOrderActivity();
                userCleaningPlaceOrderActivity.startPayment();

            }
        });*/
    }


}