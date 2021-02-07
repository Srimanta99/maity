package com.example.maityspositiveliving.screen.UserHouseHoldActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.maityspositiveliving.R;
import com.rts.commonutils_2_0.deviceinfo.DeviceResolution;

public class UserHouseHoldViewBind extends DeviceResolution {
    UserHouseHoldActivity userHouseHoldActivity;
    View view;
    RelativeLayout back_icon;
    TextView toolname_id;
    LinearLayout cleaning_lvid;


    public UserHouseHoldViewBind(UserHouseHoldActivity userHouseHoldActivity, View view) {
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
        cleaning_lvid=view.findViewById(R.id.cleaning_lvid);
      //  toolname_id=view.findViewById(R.id.toolname_id);

    }
}

