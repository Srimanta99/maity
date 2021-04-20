package com.maity.maityspositiveliving.screen.UserLoginActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.maity.maityspositiveliving.R;
import com.rts.commonutils_2_0.deviceinfo.DeviceResolution;

public class UserLoginViewBind extends DeviceResolution {
    UserLoginActivity userLoginActivity;
    View view;
    EditText etn_password,etn_mobno;
    RelativeLayout loginid;
    ImageView show_pass_id;
    public UserLoginViewBind(  UserLoginActivity userLoginActivity, View view) {
        super(userLoginActivity);
        this.userLoginActivity=userLoginActivity;
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
        loginid=view. findViewById(R.id.loginid);
        etn_password=view.findViewById(R.id.etn_password);
        etn_mobno=view.findViewById(R.id.etn_mobno);
        show_pass_id=view.findViewById(R.id.show_pass_id);

    }
}
