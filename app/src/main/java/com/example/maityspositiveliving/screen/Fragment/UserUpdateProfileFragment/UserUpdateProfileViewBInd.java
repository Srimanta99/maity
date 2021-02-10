package com.example.maityspositiveliving.screen.Fragment.UserUpdateProfileFragment;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.maityspositiveliving.R;
import com.example.maityspositiveliving.screen.Fragment.UserPaymentFragment.UserPaymentFragment;
import com.rts.commonutils_2_0.deviceinfo.DeviceResolution;

public class UserUpdateProfileViewBInd extends DeviceResolution {
    UserUpdateProfileFragment userUpdateProfileFragment;
    View view;

    RelativeLayout btn_submit;
  EditText etn_nameid,etn_emailid,etn_mobnoid,etn_currentpasswordid,etn_newpasswordid,etn_confirmpasswordid;

    public UserUpdateProfileViewBInd( UserUpdateProfileFragment userUpdateProfileFragment, View view) {
        super(userUpdateProfileFragment.getActivity());
        this.userUpdateProfileFragment=userUpdateProfileFragment;
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

        etn_nameid=view.findViewById(R.id.etn_nameid);
        etn_emailid=view.findViewById(R.id.etn_emailid);
        etn_mobnoid=view.findViewById(R.id.etn_mobnoid);
        etn_currentpasswordid=view.findViewById(R.id.etn_currentpasswordid);
        etn_newpasswordid=view.findViewById(R.id.etn_newpasswordid);
        etn_confirmpasswordid=view.findViewById(R.id.etn_confirmpasswordid);
        btn_submit=view.findViewById(R.id.btn_submit);


    }
}