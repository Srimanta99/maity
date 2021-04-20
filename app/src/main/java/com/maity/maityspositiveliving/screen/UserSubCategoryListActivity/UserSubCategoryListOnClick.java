package com.maity.maityspositiveliving.screen.UserSubCategoryListActivity;

import android.view.View;

import com.maity.maityspositiveliving.R;


public class UserSubCategoryListOnClick implements View.OnClickListener{
    UserSubCategoryListActivity userHouseHoldActivity;
    UserSubCategoryListViewBind userHouseHoldViewBind;

    public UserSubCategoryListOnClick(UserSubCategoryListActivity userHouseHoldActivity, UserSubCategoryListViewBind userHouseHoldViewBind) {
        this.userHouseHoldActivity=userHouseHoldActivity;
        this.userHouseHoldViewBind=userHouseHoldViewBind;
        setonclicklistner();
    }
    // set click listner.
    private void setonclicklistner() {

        userHouseHoldViewBind.back_icon.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_icon:{
                userHouseHoldActivity.onBackPressed();

            }
            break;

        }
    }
}
