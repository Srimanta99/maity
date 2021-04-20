package com.maity.maityspositiveliving.screen.UserStepOneRegistrationActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.maity.maityspositiveliving.R;
import com.maity.maityspositiveliving.Retrofit.interfaces.OnCallBackListner;
import com.maity.maityspositiveliving.Retrofit.models.ApiRequest;
import com.maity.maityspositiveliving.screen.UserStepTwoRegistrationActivity.UserStepTwoRegistrationActivity;
import com.maity.maityspositiveliving.utils.ApplicationConstant;
import com.maity.maityspositiveliving.utils.MyToast;
import com.maity.maityspositiveliving.utils.RegistrationConstant;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.regex.Pattern;


public class UserStepOneRegistrationActivity extends AppCompatActivity implements OnCallBackListner {
    UserStepOneRegistrationViewBind userStepOneRegistrationViewBind;
    UserStepOneRegistrationOnClick userStepOneRegistrationOnClick;

    ApiRequest request;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view= LayoutInflater.from(this).inflate(R.layout.activity_user_step_one_registration,null);
        setContentView(view);
        userStepOneRegistrationViewBind= new UserStepOneRegistrationViewBind(this,view);
        userStepOneRegistrationOnClick=new UserStepOneRegistrationOnClick(this,userStepOneRegistrationViewBind);

        request=new ApiRequest(this,this);


        userStepOneRegistrationViewBind.etn_nameid.setText(RegistrationConstant.NAME); //if go to another page then back this page then set name
        userStepOneRegistrationViewBind.etn_emailid.setText(RegistrationConstant.EMAIL_ID);//if go to another page then back this page then set email



    }

    // method call for isValidEmailformet
    public boolean isValidEmailId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }
    // api call for unique Email
    public void apicallForsEmailUnique(){
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("customar_email",RegistrationConstant.EMAIL_ID);

        request.callPOSTJSON_OBJECT(ApplicationConstant.isEmailUnique_url,hashMap,"isEmailUnique");
    }

    @Override
    public void OnCallBackSuccess(String tag, String body) {

    }

    @Override
    public void OnCallBackSuccess(String tag, JSONObject jsonObject) {
        if (tag.equalsIgnoreCase("isEmailUnique")){

            try {
            String success=jsonObject.getString("_success");

                if (success.equalsIgnoreCase("1")){
                        Intent mainIntent = new Intent(UserStepOneRegistrationActivity.this, UserStepTwoRegistrationActivity.class);
                    startActivity(mainIntent);
                }else {
                    MyToast.show(UserStepOneRegistrationActivity.this,jsonObject.getString("_message"),true);

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