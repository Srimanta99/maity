package com.example.maityspositiveliving.screen.UserCleaningActivity;

import android.content.Intent;
import android.view.View;

import com.example.maityspositiveliving.R;
import com.example.maityspositiveliving.screen.UserCleaningPlaceOrderActivity.UserCleaningPlaceOrderActivity;
import com.example.maityspositiveliving.screen.UserHouseHoldActivity.UserHouseHoldActivity;
import com.example.maityspositiveliving.screen.UserHouseHoldActivity.UserHouseHoldViewBind;

public class UserCleaningOnClick implements View.OnClickListener{
    UserCleaningActivity userCleaningActivity;
    UserCleaningViewBind userCleaningViewBind;

    public UserCleaningOnClick( UserCleaningActivity userCleaningActivity,UserCleaningViewBind userCleaningViewBind) {
        this.userCleaningActivity=userCleaningActivity;
        this.userCleaningViewBind=userCleaningViewBind;
        setonclicklistner();
    }
    // set click listner.
    private void setonclicklistner() {

        userCleaningViewBind.back_icon.setOnClickListener(this);
        userCleaningViewBind.submit_btnid.setOnClickListener(this);

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
                Intent intent=new Intent(userCleaningActivity, UserCleaningPlaceOrderActivity.class);
                userCleaningActivity.startActivity(intent);
            }
            break;


        }
    }
}
