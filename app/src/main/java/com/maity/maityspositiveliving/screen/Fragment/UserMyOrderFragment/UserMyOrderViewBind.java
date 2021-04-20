package com.maity.maityspositiveliving.screen.Fragment.UserMyOrderFragment;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.maity.maityspositiveliving.R;
import com.rts.commonutils_2_0.deviceinfo.DeviceResolution;

public class UserMyOrderViewBind extends DeviceResolution {
    UserMyOrderFragment userMyOrderFragment;
    View view;

   RecyclerView rv_orderlist;

    public static TextView tv_customer_wallet_balance;

    public UserMyOrderViewBind(UserMyOrderFragment userMyOrderFragment, View view) {
        super(userMyOrderFragment.getActivity());
        this.userMyOrderFragment=userMyOrderFragment;
        this.view=view;
        initviewBind();
        setCustomTypeface();
    }

    // for adding custom typeface
    private void setCustomTypeface() {

    }

    // for findview
    private void initviewBind() {

        rv_orderlist=view.findViewById(R.id.rv_orderlist);

    }
    }