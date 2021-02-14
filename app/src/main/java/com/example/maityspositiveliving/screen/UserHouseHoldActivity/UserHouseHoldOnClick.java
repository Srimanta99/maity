package com.example.maityspositiveliving.screen.UserHouseHoldActivity;

import android.content.Intent;
import android.view.View;

import com.example.maityspositiveliving.R;
import com.example.maityspositiveliving.screen.UserCleaningActivity.UserCleaningActivity;


public class UserHouseHoldOnClick implements View.OnClickListener{
    UserHouseHoldActivity userHouseHoldActivity;
    UserHouseHoldViewBind userHouseHoldViewBind;

    public UserHouseHoldOnClick(UserHouseHoldActivity userHouseHoldActivity, UserHouseHoldViewBind userHouseHoldViewBind) {
        this.userHouseHoldActivity=userHouseHoldActivity;
        this.userHouseHoldViewBind=userHouseHoldViewBind;
        setonclicklistner();
    }
    // set click listner.
    private void setonclicklistner() {

        userHouseHoldViewBind.back_icon.setOnClickListener(this);
      //  userHouseHoldViewBind.cleaning_lvid.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_icon:{
                // Toast.makeText(mainActivity,"hello",Toast.LENGTH_LONG).show();
                userHouseHoldActivity.onBackPressed();

            }
            break;

           /* case R.id.cleaning_lvid:{
                Intent intent=new Intent(userHouseHoldActivity, UserCleaningActivity.class);
                userHouseHoldActivity. startActivity(intent);
              //  userHouseHoldActivity.finish();
            }
            break;*/
        }
    }
}
