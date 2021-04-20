package com.maity.maityspositiveliving.screen.UserVerifyMobileNumberActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.maity.maityspositiveliving.R;
import com.rts.commonutils_2_0.deviceinfo.DeviceResolution;

public class UserVerifyMobileNumberViewBind extends DeviceResolution {
    UserVerifyMobileNumberActivity userVerifyMobileNumberActivity;
    View view;
    LinearLayout verifylvid,resendlvid;
    RelativeLayout back_icon;
    EditText etotpno1id,etotpno2id,etotpno3id,etotpno4id;

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
        resendlvid=view.findViewById(R.id.resendlvid);
        back_icon=view.findViewById(R.id.back_icon);
        etotpno1id=view.findViewById(R.id.etotpno1id);
        etotpno2id=view.findViewById(R.id.etotpno2id);
        etotpno3id=view.findViewById(R.id.etotpno3id);
        etotpno4id=view.findViewById(R.id.etotpno4id);

    }
}
