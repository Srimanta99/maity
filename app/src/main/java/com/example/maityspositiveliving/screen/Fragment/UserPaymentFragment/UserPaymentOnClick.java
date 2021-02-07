package com.example.maityspositiveliving.screen.Fragment.UserPaymentFragment;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maityspositiveliving.R;
import com.example.maityspositiveliving.screen.UserCleaningActivity.UserCleaningActivity;
import com.example.maityspositiveliving.screen.UserCleaningActivity.UserCleaningViewBind;
import com.example.maityspositiveliving.screen.UserCleaningPlaceOrderActivity.UserCleaningPlaceOrderActivity;
import com.example.maityspositiveliving.screen.UserCongratulationAcitivity.UserCongratulationActivity;
import com.rts.commonutils_2_0.deviceinfo.DeviceResolution;

public class UserPaymentOnClick implements View.OnClickListener{
    UserPaymentFragment userPaymentFragment;
    UserPaymentViewBind userPaymentViewBind;

    public UserPaymentOnClick( UserPaymentFragment userPaymentFragment,  UserPaymentViewBind userPaymentViewBind) {
        this.userPaymentFragment=userPaymentFragment;
        this.userPaymentViewBind=userPaymentViewBind;
        setonclicklistner();
    }
    // set click listner.
    private void setonclicklistner() {
        userPaymentViewBind.payment_btnid.setOnClickListener(this);
        userPaymentViewBind.bhim_upi_btnid.setOnClickListener(this);
        userPaymentViewBind.debit_card_btnid.setOnClickListener(this);
        userPaymentViewBind.credit_card_btnid.setOnClickListener(this);
        userPaymentViewBind.statebank_ivid.setOnClickListener(this);
        userPaymentViewBind.axisbank_ivid.setOnClickListener(this);
        userPaymentViewBind.upiid_ivid.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.payment_btnid:{
                // Toast.makeText(mainActivity,"hello",Toast.LENGTH_LONG).show();

                Intent intent=new Intent(userPaymentFragment.getActivity(), UserCongratulationActivity.class);
                userPaymentFragment. getActivity().startActivity(intent);

            }
            break;

            case R.id.bhim_upi_btnid:{
                // Toast.makeText(mainActivity,"hello",Toast.LENGTH_LONG).show();
              userPaymentViewBind.  bhim_upi_btnid.setBackgroundResource(R.drawable.blue_tab_bg);
                userPaymentViewBind.    credit_card_btnid.setBackgroundResource(R.drawable.bluedrawableright_tab);
                userPaymentViewBind.    debit_card_btnid.setBackgroundColor(Color.parseColor("#0F297E"));

                userPaymentViewBind.   bhim_upi_layout.setVisibility(View.VISIBLE);
                userPaymentViewBind.   credit_card_layout.setVisibility(View.GONE);
                userPaymentViewBind.   debit_card_layout.setVisibility(View.GONE);

            }
            break;
            case R.id.debit_card_btnid:{
                // Toast.makeText(mainActivity,"hello",Toast.LENGTH_LONG).show();
                userPaymentViewBind.    debit_card_btnid.setBackgroundColor(Color.parseColor("#0333ad"));
                userPaymentViewBind.   bhim_upi_btnid.setBackgroundResource(R.drawable.bhim_upi_tab_dark_blue);
                userPaymentViewBind.   credit_card_btnid.setBackgroundResource(R.drawable.bluedrawableright_tab);

                userPaymentViewBind.   debit_card_layout.setVisibility(View.VISIBLE);
                userPaymentViewBind. credit_card_layout.setVisibility(View.GONE);
                userPaymentViewBind.  bhim_upi_layout.setVisibility(View.GONE);

            }
            break;
            case R.id.credit_card_btnid:{
                // Toast.makeText(mainActivity,"hello",Toast.LENGTH_LONG).show();
                userPaymentViewBind.   credit_card_btnid.setBackgroundResource(R.drawable.credit_card_tab_light_blue);
                userPaymentViewBind.   debit_card_btnid.setBackgroundColor(Color.parseColor("#0F297E"));
                userPaymentViewBind.   bhim_upi_btnid.setBackgroundResource(R.drawable.bhim_upi_tab_dark_blue);
                userPaymentViewBind.   credit_card_layout.setVisibility(View.VISIBLE);
                userPaymentViewBind.   bhim_upi_layout.setVisibility(View.GONE);
                userPaymentViewBind.    debit_card_layout.setVisibility(View.GONE);

            }
            break;
            case R.id.statebank_ivid:{
                userPaymentViewBind.statebank_ivid.setImageResource(R.drawable.tick);
                userPaymentViewBind.axisbank_ivid.setImageResource(R.drawable.rediobtn);
                userPaymentViewBind.upiid_ivid.setImageResource(R.drawable.rediobtn);

                //   SessionManager.setGendervalue(Gender);

            }
            break;
            case R.id.axisbank_ivid:{
                userPaymentViewBind.axisbank_ivid.setImageResource(R.drawable.tick);
                userPaymentViewBind.statebank_ivid.setImageResource(R.drawable.rediobtn);
                userPaymentViewBind.upiid_ivid.setImageResource(R.drawable.rediobtn);
                //   SessionManager.setGendervalue(Gender);

            }
            break;
            case R.id.upiid_ivid:{

                userPaymentViewBind.upiid_ivid.setImageResource(R.drawable.tick);
                userPaymentViewBind.axisbank_ivid.setImageResource(R.drawable.rediobtn);
                userPaymentViewBind.statebank_ivid.setImageResource(R.drawable.rediobtn);
                //   SessionManager.setGendervalue(Gender);

            }
            break;


        }
    }
}

