package com.example.maityspositiveliving.screen.UserEnterMobileNumberActivity;

import android.view.View;
import android.widget.RelativeLayout;

import com.example.maityspositiveliving.R;
import com.rts.commonutils_2_0.deviceinfo.DeviceResolution;

public class UserMobileNumberViewBind extends DeviceResolution {
    UserMobileNumberActivity userMobileNumberActivity;
    View view;
    RelativeLayout nextid;

    public UserMobileNumberViewBind( UserMobileNumberActivity userMobileNumberActivity, View view) {
        super(userMobileNumberActivity);
        this.userMobileNumberActivity=userMobileNumberActivity;
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
        nextid=view.findViewById(R.id.nextid);

    }
}
