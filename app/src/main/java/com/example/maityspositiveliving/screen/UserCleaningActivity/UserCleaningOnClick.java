package com.example.maityspositiveliving.screen.UserCleaningActivity;

import android.Manifest;
import android.content.Intent;
import android.view.View;

import com.example.maityspositiveliving.R;
import com.example.maityspositiveliving.screen.UserCleaningPlaceOrderActivity.UserCleaningPlaceOrderActivity;
import com.example.maityspositiveliving.screen.UserHouseHoldActivity.UserHouseHoldActivity;
import com.example.maityspositiveliving.screen.UserHouseHoldActivity.UserHouseHoldViewBind;

public class UserCleaningOnClick implements View.OnClickListener{
    UserCleaningActivity userCleaningActivity;
    UserCleaningViewBind userCleaningViewBind;
    public static final int STORAGE_PERMISSION_CODE = 101;
    public static final int CAMERA_PERMISSION_CODE = 100;

    public UserCleaningOnClick( UserCleaningActivity userCleaningActivity,UserCleaningViewBind userCleaningViewBind) {
        this.userCleaningActivity=userCleaningActivity;
        this.userCleaningViewBind=userCleaningViewBind;
        setonclicklistner();
    }
    // set click listner.
    private void setonclicklistner() {

        userCleaningViewBind.back_icon.setOnClickListener(this);
        userCleaningViewBind.submit_btnid.setOnClickListener(this);
        userCleaningViewBind.tv_upload.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_icon:{
                // Toast.makeText(mainActivity,"hello",Toast.LENGTH_LONG).show();
                userCleaningActivity.onBackPressed();

            }
            break;

            case R.id.submit_btnid:{

                userCleaningActivity.apiForOrderApi();

               /* Intent intent=new Intent(userCleaningActivity, UserCleaningPlaceOrderActivity.class);
                userCleaningActivity.startActivity(intent);*/
            }
            break;


            case R.id.tv_upload:{
                userCleaningActivity. checkPermission(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        STORAGE_PERMISSION_CODE);

                userCleaningActivity.  checkPermission(Manifest.permission.CAMERA,
                        CAMERA_PERMISSION_CODE);
                userCleaningActivity.showPictureDialog();
            }
            break;

        }
    }
}
