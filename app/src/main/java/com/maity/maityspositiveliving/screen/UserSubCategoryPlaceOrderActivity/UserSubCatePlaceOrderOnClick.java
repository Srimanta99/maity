package com.maity.maityspositiveliving.screen.UserSubCategoryPlaceOrderActivity;

import android.view.View;

import com.maity.maityspositiveliving.R;


public class UserSubCatePlaceOrderOnClick implements View.OnClickListener{
    UserSubCategoryPlaceOrderActivity userSubCategoryPlaceOrderActivity;
    UserSubCatePlaceOrderViewBind userSubCatePlaceOrderViewBind;

    public UserSubCatePlaceOrderOnClick(UserSubCategoryPlaceOrderActivity userSubCategoryPlaceOrderActivity,UserSubCatePlaceOrderViewBind userSubCatePlaceOrderViewBind) {
        this.userSubCategoryPlaceOrderActivity=userSubCategoryPlaceOrderActivity;
        this.userSubCatePlaceOrderViewBind=userSubCatePlaceOrderViewBind;
        setonclicklistner();
    }
    // set click listner.
    private void setonclicklistner() {
       userSubCatePlaceOrderViewBind.back_icon.setOnClickListener(this);
        userSubCatePlaceOrderViewBind.tv_place_order.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_icon:{
               userSubCategoryPlaceOrderActivity. onBackPressed();
            }
            break;

            case R.id.tv_place_order:{
               // open dialog for online pay or wallet
                userSubCategoryPlaceOrderActivity. opendialog_Online_OR_Wallet();
            }
            break;


        }
    }
}