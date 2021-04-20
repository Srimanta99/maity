package com.maity.maityspositiveliving.screen.main;

import android.content.Intent;
import android.view.View;

import com.maity.maityspositiveliving.R;
import com.maity.maityspositiveliving.screen.ServiceProviderActivity.ServiceProviderActivity;
import com.maity.maityspositiveliving.screen.UserEnterMobileNumberActivity.UserMobileNumberActivity;


public class MainActivityOnClick implements View.OnClickListener{
    MainActivity mainActivity;
    MainActivityViewBind mainActivityViewBind;

    public MainActivityOnClick(MainActivity mainActivity, MainActivityViewBind mainActivityViewBind) {
        this.mainActivity=mainActivity;
        this.mainActivityViewBind=mainActivityViewBind;
        setonclicklistner();
    }
    // set click listner.
    private void setonclicklistner() {
        mainActivityViewBind.serviceuserid.setOnClickListener(this);
        mainActivityViewBind.serviceproviderid.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.serviceuserid:{
                Intent mainIntent = new Intent(mainActivity, UserMobileNumberActivity.class);
                mainActivity.startActivity(mainIntent);
               // mainActivity.finish();
            }
            break;


            case R.id.serviceproviderid:{
                Intent mainIntent = new Intent(mainActivity, ServiceProviderActivity.class);
                mainActivity.startActivity(mainIntent);
                //  mainActivity.finish();
            }
            break;


        }
    }
}
