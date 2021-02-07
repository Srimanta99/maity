package com.example.maityspositiveliving.screen.Fragment.UserPaymentFragment;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.maityspositiveliving.R;
import com.example.maityspositiveliving.screen.UserCleaningActivity.UserCleaningActivity;
import com.rts.commonutils_2_0.deviceinfo.DeviceResolution;

public class UserPaymentViewBind extends DeviceResolution {
    UserPaymentFragment userPaymentFragment;
    View view;

    RelativeLayout payment_btnid,addmoney_btnid;
    LinearLayout bhim_upi_btnid,debit_card_btnid,credit_card_btnid,bhim_upi_layout,debit_card_layout,credit_card_layout;
ImageView upiid_ivid,axisbank_ivid,statebank_ivid;

    public UserPaymentViewBind(UserPaymentFragment userPaymentFragment, View view) {
        super(userPaymentFragment.getActivity());
        this.userPaymentFragment=userPaymentFragment;
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
     /*   etnameid=view.findViewById(R.id.etnameid);
        et_email_id=view.findViewById(R.id.et_email_id);
        et_ph_no_id=view.findViewById(R.id.et_ph_no_id);
        tvsubmmitid=view.findViewById(R.id.tvsubmmitid);
        imageiconid=view.findViewById(R.id.imageiconid);*/


        payment_btnid=view.findViewById(R.id.payment_btnid);
        //addmoney_btnid=view.findViewById(R.id.addmoney_btnid);

        bhim_upi_btnid=view.findViewById(R.id.bhim_upi_btnid);
        debit_card_btnid=view.findViewById(R.id.debit_card_btnid);
        credit_card_btnid=view.findViewById(R.id.credit_card_btnid);
        credit_card_layout=view.findViewById(R.id.credit_card_layout);
        bhim_upi_layout=view.findViewById(R.id.bhim_upi_layout);
        debit_card_layout=view.findViewById(R.id.debit_card_layout);
        statebank_ivid=view.findViewById(R.id.statebank_ivid);
        axisbank_ivid=view.findViewById(R.id.axisbank_ivid);
        upiid_ivid=view.findViewById(R.id.upiid_ivid);


    }
}
