package com.maity.maityspositiveliving.screen.UserVerifyMobileNumberActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.maity.maityspositiveliving.R;
import com.maity.maityspositiveliving.Retrofit.interfaces.OnCallBackListner;
import com.maity.maityspositiveliving.Retrofit.models.ApiRequest;
import com.maity.maityspositiveliving.screen.UserEnterMobileNumberActivity.UserMobileNumberActivity;
import com.maity.maityspositiveliving.screen.UserStepOneRegistrationActivity.UserStepOneRegistrationActivity;
import com.maity.maityspositiveliving.utils.ApplicationConstant;
import com.maity.maityspositiveliving.utils.MyToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class UserVerifyMobileNumberActivity extends AppCompatActivity implements OnCallBackListner {
    UserVerifyMobileNumberViewBind userVerifyMobileNumberViewBind;
    UserVerifyMobileNumberOnClick userVerifyMobileNumberOnClick;
    ApiRequest apiRequest;
    String customer_phone;
    String otp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view= LayoutInflater.from(this).inflate(R.layout.activity_user_verify_mobile_number,null);
        setContentView(view);
        userVerifyMobileNumberViewBind= new UserVerifyMobileNumberViewBind(this,view);
        userVerifyMobileNumberOnClick=new UserVerifyMobileNumberOnClick(this,userVerifyMobileNumberViewBind);

        customer_phone=getIntent().getStringExtra("customer_phone");
        apiRequest=new ApiRequest(this,this);
        setupOtpLayout();

        apicallForgetOTP(customer_phone);

    }

    private void setupOtpLayout() {

        StringBuilder sb=new StringBuilder();

        userVerifyMobileNumberViewBind.etotpno1id.requestFocus();
        userVerifyMobileNumberViewBind. etotpno1id.setCursorVisible(true);

        userVerifyMobileNumberViewBind. etotpno1id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(  userVerifyMobileNumberViewBind. etotpno1id.length()==1)
                { sb.append(charSequence);
                    userVerifyMobileNumberViewBind.  etotpno2id.clearFocus();
                    userVerifyMobileNumberViewBind.  etotpno2id.requestFocus();
                    userVerifyMobileNumberViewBind.etotpno2id.setCursorVisible(true);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        userVerifyMobileNumberViewBind.etotpno2id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(  userVerifyMobileNumberViewBind.etotpno2id.length()==1)
                { sb.append(charSequence);
                    userVerifyMobileNumberViewBind. etotpno3id.clearFocus();
                    userVerifyMobileNumberViewBind. etotpno3id.requestFocus();
                    userVerifyMobileNumberViewBind. etotpno3id.setCursorVisible(true);
                }
                else if (  userVerifyMobileNumberViewBind.etotpno2id.length()==0){
                    userVerifyMobileNumberViewBind. etotpno2id.clearFocus();
                    userVerifyMobileNumberViewBind. etotpno1id.requestFocus();
                    userVerifyMobileNumberViewBind. etotpno1id.setCursorVisible(true);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        userVerifyMobileNumberViewBind. etotpno3id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(  userVerifyMobileNumberViewBind.etotpno3id.length()==1)
                { sb.append(charSequence);
                    userVerifyMobileNumberViewBind. etotpno4id.clearFocus();
                    userVerifyMobileNumberViewBind.  etotpno4id.requestFocus();
                    userVerifyMobileNumberViewBind. etotpno4id.setCursorVisible(true);
                }
                else if (  userVerifyMobileNumberViewBind.etotpno3id.length()==0){
                    userVerifyMobileNumberViewBind.etotpno3id.clearFocus();
                    userVerifyMobileNumberViewBind. etotpno2id.requestFocus();
                    userVerifyMobileNumberViewBind. etotpno2id.setCursorVisible(true);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        userVerifyMobileNumberViewBind.etotpno4id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(  userVerifyMobileNumberViewBind.etotpno4id.length()==1)
                {hideKeyboard(UserVerifyMobileNumberActivity.this);
                }
                else if (  userVerifyMobileNumberViewBind.etotpno4id.length()==0){
                    userVerifyMobileNumberViewBind.etotpno4id.clearFocus();
                    userVerifyMobileNumberViewBind. etotpno3id.requestFocus();
                    userVerifyMobileNumberViewBind.etotpno3id.setCursorVisible(true);
                }

            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });




    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void apicallForverifyOTP(String otp){
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("customer_phone",customer_phone);
        hashMap.put("otp",otp);

        Log.d("hash", "apicallForgetOTP: "+hashMap);
        apiRequest.callPOSTJSON_OBJECT(ApplicationConstant.verifyOTP_url,hashMap,"verifyOTP");
    }

    public void apicallForgetOTP(String customer_phone){
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("customer_phone",customer_phone);
        Log.d("hash", "apicallForgetOTP: "+hashMap);
        apiRequest.callPOSTJSON_OBJECT(ApplicationConstant.getOTP_url,hashMap,"getOTP");
    }

    @Override
    public void OnCallBackSuccess(String tag, String body) {

    }

    @Override
    public void OnCallBackSuccess(String tag, JSONObject jsonObject) {
        if (tag.equalsIgnoreCase("getOTP")) {
            try {

                MyToast.show(UserVerifyMobileNumberActivity.this, jsonObject.getString("messages"), true);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else  if (tag.equalsIgnoreCase("verifyOTP")) {
            try {

                if (jsonObject.getString("status").equalsIgnoreCase("0")){
                    MyToast.show(UserVerifyMobileNumberActivity.this, jsonObject.getString("messages"), true);

                }else {
                    MyToast.show(UserVerifyMobileNumberActivity.this, jsonObject.getString("messages"), true);
                    Intent mainIntent = new Intent(UserVerifyMobileNumberActivity.this, UserStepOneRegistrationActivity.class);
                    startActivity(mainIntent);
                    finish();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void OnCallBackError(String tag, String error, int i) {

    }
}