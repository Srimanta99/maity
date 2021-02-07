package com.example.maityspositiveliving.screen.UserVerifyMobileNumberActivity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.maityspositiveliving.R;
import com.example.maityspositiveliving.screen.UserEnterMobileNumberActivity.UserMobileNumberActivity;
import com.rts.commonutils_2_0.deviceinfo.DeviceResolution;

public class UserVerifyMobileNumberViewBind extends DeviceResolution {
    UserVerifyMobileNumberActivity userVerifyMobileNumberActivity;
    View view;
    LinearLayout verifylvid;
    RelativeLayout back_icon;

    public UserVerifyMobileNumberViewBind( UserVerifyMobileNumberActivity userVerifyMobileNumberActivity, View view) {
        super(userVerifyMobileNumberActivity);
        this.userVerifyMobileNumberActivity=userVerifyMobileNumberActivity;
        this.view=view;
        initviewBind();
        setCustomTypeface();
    }

    // for adding custom typeface
    private void setCustomTypeface() {
        // studentlvid.setTypeface(getbebas(mainActivity));
//        btn_hello_wrold.setTypeface(getbebas(mainActivity));
    }

    // for findview
    private void initviewBind() {
        verifylvid=view.findViewById(R.id.verifylvid);
        back_icon=view.findViewById(R.id.back_icon);

    }
}
