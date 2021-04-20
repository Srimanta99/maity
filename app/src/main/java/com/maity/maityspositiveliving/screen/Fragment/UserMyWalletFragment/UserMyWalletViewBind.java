package com.maity.maityspositiveliving.screen.Fragment.UserMyWalletFragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maity.maityspositiveliving.R;
import com.rts.commonutils_2_0.deviceinfo.DeviceResolution;

public class UserMyWalletViewBind extends DeviceResolution {
    UserMyWalletFragment userPaymentFragment;
    View view;

    RelativeLayout payment_btnid,addmoney_btnid;
    LinearLayout bhim_upi_btnid,debit_card_btnid,credit_card_btnid,bhim_upi_layout,debit_card_layout,credit_card_layout;
ImageView upiid_ivid,axisbank_ivid,statebank_ivid;
   public static  TextView tv_customer_wallet_balance;

    public UserMyWalletViewBind(UserMyWalletFragment userPaymentFragment, View view) {
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

        addmoney_btnid=view.findViewById(R.id.addmoney_btnid);
        tv_customer_wallet_balance=view.findViewById(R.id.tv_customer_wallet_balance);


    }
}
