package com.maity.maityspositiveliving.screen.UserEnterMobileNumberActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maity.maityspositiveliving.R;
import com.rts.commonutils_2_0.deviceinfo.DeviceResolution;

public class UserMobileNumberViewBind extends DeviceResolution {
    UserMobileNumberActivity userMobileNumberActivity;
    View view;
    RelativeLayout nextid;
    EditText etn_mobno;
    TextView login_underline;
    LinearLayout lv_login;

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
        etn_mobno=view.findViewById(R.id.etn_mobno);
        lv_login=view.findViewById(R.id.lv_login);
        login_underline=view.findViewById(R.id.login_underline);

    }
}
