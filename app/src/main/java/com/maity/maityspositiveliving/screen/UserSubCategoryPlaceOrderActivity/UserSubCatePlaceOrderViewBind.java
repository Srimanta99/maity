package com.maity.maityspositiveliving.screen.UserSubCategoryPlaceOrderActivity;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maity.maityspositiveliving.R;
import com.rts.commonutils_2_0.deviceinfo.DeviceResolution;

public class UserSubCatePlaceOrderViewBind extends DeviceResolution {
    UserSubCategoryPlaceOrderActivity userSubCategoryPlaceOrderActivity;
    View view;
    TextView headername_tvid,tv_service_charge,tv_GST,tv_tamount,tv_place_order,tv_note,tv_category_name,tv_sub_category;
    RelativeLayout back_icon;
    public UserSubCatePlaceOrderViewBind( UserSubCategoryPlaceOrderActivity userSubCategoryPlaceOrderActivity, View view) {
        super(userSubCategoryPlaceOrderActivity);
        this.userSubCategoryPlaceOrderActivity=userSubCategoryPlaceOrderActivity;
        this.view=view;
        initviewBind();
        setCustomTypeface();
    }

    // for adding custom typeface
    private void setCustomTypeface() {

    }

    // for findview
    private void initviewBind() {
        tv_category_name=view.findViewById(R.id.tv_category_name);
        tv_sub_category=view.findViewById(R.id.tv_sub_category);
        headername_tvid=view.findViewById(R.id.headername_tvid);
        back_icon=view.findViewById(R.id.back_icon);
        tv_service_charge=view.findViewById(R.id.tv_service_charge);
        tv_GST=view.findViewById(R.id.tv_GST);
        tv_tamount=view.findViewById(R.id.tv_tamount);
        tv_place_order=view.findViewById(R.id.tv_place_order);
        tv_note=view.findViewById(R.id.tv_note);

    }
}
