package com.example.maityspositiveliving.screen.UserStepOneRegistrationActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;


import com.example.maityspositiveliving.R;
import com.rts.commonutils_2_0.deviceinfo.DeviceResolution;

public class UserStepOneRegistrationViewBind extends DeviceResolution {
    UserStepOneRegistrationActivity userStepOneRegistrationActivity;
    View view;
    RelativeLayout nextid;
    EditText etn_emailid,etn_nameid;

    public UserStepOneRegistrationViewBind(UserStepOneRegistrationActivity userStepOneRegistrationActivity, View view) {
        super(userStepOneRegistrationActivity);
        this.userStepOneRegistrationActivity=userStepOneRegistrationActivity;
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
        etn_nameid=view.findViewById(R.id.etn_nameid);
        etn_emailid=view.findViewById(R.id.etn_emailid);

    }
}
