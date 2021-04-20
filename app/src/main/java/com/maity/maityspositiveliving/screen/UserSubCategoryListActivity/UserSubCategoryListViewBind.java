package com.maity.maityspositiveliving.screen.UserSubCategoryListActivity;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.maity.maityspositiveliving.R;
import com.rts.commonutils_2_0.deviceinfo.DeviceResolution;

public class UserSubCategoryListViewBind extends DeviceResolution {
    UserSubCategoryListActivity userHouseHoldActivity;
    View view;
    RelativeLayout back_icon;
    TextView toolname_id,headername_tvid;

    RecyclerView rv_subcategory;


    public UserSubCategoryListViewBind(UserSubCategoryListActivity userHouseHoldActivity, View view) {
        super(userHouseHoldActivity);
        this.userHouseHoldActivity=userHouseHoldActivity;
        this.view=view;
        initviewBind();
        setCustomTypeface();
    }

    // for adding custom typeface
    private void setCustomTypeface() {

    }

    // for findview
    private void initviewBind() {
        back_icon =view. findViewById(R.id.back_icon);
        rv_subcategory=view.findViewById(R.id.rv_subcategory);
        headername_tvid=view.findViewById(R.id.headername_tvid);
    }
}

