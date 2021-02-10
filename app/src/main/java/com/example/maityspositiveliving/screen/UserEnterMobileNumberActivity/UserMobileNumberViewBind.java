package com.example.maityspositiveliving.screen.UserEnterMobileNumberActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.maityspositiveliving.R;
import com.rts.commonutils_2_0.deviceinfo.DeviceResolution;

public class UserMobileNumberViewBind extends DeviceResolution {
    UserMobileNumberActivity userMobileNumberActivity;
    View view;
    RelativeLayout nextid;
    Spinner spinner_country_code;
    EditText etn_mobno;

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
        spinner_country_code=view.findViewById(R.id.spinner_country_code);
        etn_mobno=view.findViewById(R.id.etn_mobno);

    }
}
