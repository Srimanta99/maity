package com.maity.maityspositiveliving.screen.UserEnterMobileNumberActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.maity.maityspositiveliving.R;

import com.maity.maityspositiveliving.Retrofit.interfaces.OnCallBackListner;
import com.maity.maityspositiveliving.Retrofit.models.ApiRequest;
import com.maity.maityspositiveliving.screen.UserVerifyMobileNumberActivity.UserVerifyMobileNumberActivity;
import com.maity.maityspositiveliving.screen.main.MainActivity;
import com.maity.maityspositiveliving.utils.ApplicationConstant;
import com.maity.maityspositiveliving.utils.MyToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class UserMobileNumberActivity extends AppCompatActivity implements OnCallBackListner {
    UserMobileNumberViewBind userMobileNumberViewBind;
    UserMobileNumberOnClick userMobileNumberOnClick;

    ApiRequest apiRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view= LayoutInflater.from(this).inflate(R.layout.activity_user_mobile_number,null);
        setContentView(view);
        userMobileNumberViewBind= new UserMobileNumberViewBind(this,view);
        userMobileNumberOnClick=new UserMobileNumberOnClick(this,userMobileNumberViewBind);

        apiRequest=new ApiRequest(this,this);

    }

    // api call for unique contact
    public void apicallForisContactNUmberUnique() {
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("customer_phone",userMobileNumberViewBind.etn_mobno.getText().toString());

        apiRequest.callPOSTJSON_OBJECT(ApplicationConstant.isContactNUmberUnique_url,hashMap, "isContactNUmberUnique");
    }


    @Override
    public void OnCallBackSuccess(String tag, String body) {

    }

    @Override
    public void OnCallBackSuccess(String tag, JSONObject jsonObject) {
        if (tag.equalsIgnoreCase("isContactNUmberUnique")){

            try {
                String success=jsonObject.getString("_success");

                if (success.equalsIgnoreCase("1")){
                    Intent mainIntent = new Intent(UserMobileNumberActivity.this, UserVerifyMobileNumberActivity.class);
                    mainIntent.putExtra("customer_phone",userMobileNumberViewBind.etn_mobno.getText().toString());
                    startActivity(mainIntent);
                }else {
                    MyToast.show(UserMobileNumberActivity.this,jsonObject.getString("_message"),true);

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