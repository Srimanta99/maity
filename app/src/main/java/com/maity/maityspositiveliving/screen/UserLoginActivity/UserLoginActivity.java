package com.maity.maityspositiveliving.screen.UserLoginActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.maity.maityspositiveliving.R;
import com.maity.maityspositiveliving.Retrofit.interfaces.OnCallBackListner;
import com.maity.maityspositiveliving.Retrofit.models.ApiRequest;
import com.maity.maityspositiveliving.screen.UserDeshboardActivity.UserDeshboardActivity;
import com.maity.maityspositiveliving.screen.main.MainActivity;
import com.maity.maityspositiveliving.utils.AESCrypt;
import com.maity.maityspositiveliving.utils.ApplicationConstant;
import com.maity.maityspositiveliving.utils.MyToast;
import com.maity.maityspositiveliving.utils.SessionManager;
import com.maity.maityspositiveliving.utils.base64;


import org.json.JSONException;
import org.json.JSONObject;

import java.security.GeneralSecurityException;
import java.util.HashMap;

public class UserLoginActivity extends AppCompatActivity implements OnCallBackListner {
    UserLoginViewBind userLoginViewBind;
    UserLoginOnClick userLoginOnClick;
    ApiRequest apiRequest;
    String password,passworddecrypt;
    byte[] base64Byte=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view= LayoutInflater.from(this).inflate(R.layout.activity_user_login,null);
        setContentView(view);
        userLoginViewBind= new UserLoginViewBind(this,view);
        userLoginOnClick=new UserLoginOnClick(this,userLoginViewBind);


        apiRequest=new ApiRequest(this,this);


    }
    // api call for user login
    public void apicallForCustomarApilogin(){
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("customer_phone",userLoginViewBind.etn_mobno.getText().toString());
        hashMap.put("customer_password",userLoginViewBind.etn_password.getText().toString());
        Log.d("sunita", "apicallForCustomarApilogin: "+hashMap);

        apiRequest.callPOSTJSON_OBJECT(ApplicationConstant.CustomarApilogin_url,hashMap, "CustomarApilogin");
    }

    @Override
    public void OnCallBackSuccess(String tag, String body) {

    }

    @Override
    public void OnCallBackSuccess(String tag, JSONObject jsonObject) {
        try {
            if (jsonObject.getString("_success").equalsIgnoreCase("1")){
                JSONObject jsonObject1=jsonObject.getJSONObject("_data");
                String id=  jsonObject1.getString("id");
                String name=  jsonObject1.getString("customar_name");
                String email= jsonObject1.getString("customar_email");
                String phno=    jsonObject1.getString("customar_phone");
                jsonObject1.getString("customar_dob");
                jsonObject1.getString("customar_gender");
                jsonObject1.getString("customar_country");
                jsonObject1.getString("customar_state");
                jsonObject1.getString("customar_city");
                String address= jsonObject1.getString("customar_address");
                jsonObject1.getString("customar_pincode");
                jsonObject1.getString("status");
              String customar_dob=  jsonObject1.getString("customar_dob");
                String customar_gender=   jsonObject1.getString("customar_gender");
                String customar_country=   jsonObject1.getString("customar_country");
                String customar_state=   jsonObject1.getString("customar_state");
                String customar_city=   jsonObject1.getString("customar_city");
                String customar_pincode=   jsonObject1.getString("customar_pincode");


                String  customer_wallet_balance= jsonObject1.getString("customer_wallet_balance");
                password=  jsonObject1.getString("password");



                decrypt();  // method call for password decrypt

                SessionManager.setAfterLogin(true);
                SessionManager.setLoginidvalue(id); // set customer_id
                SessionManager.setLoginnamevalue(name);// set customer_name
                SessionManager.setLoginphnovalue(phno);// set customer_phno
                SessionManager.setLoginemailidvalue(email);// set customer_email
                SessionManager.setLoginaddressvalue(address);// set customer_address
                SessionManager.setLogincustomer_wallet_balance(customer_wallet_balance);// set customer_wallet_balance
                SessionManager.setLogincustomar_pincode(customar_pincode);
                SessionManager.setLogincustomar_city(customar_city);

                SessionManager.setLogincustomar_state(customar_state);

                SessionManager.setLogincustomar_country(customar_country);

                SessionManager.setLogincustomar_gender(customar_gender);
                SessionManager.setLogincustomar_dob(customar_dob);


                SessionManager.setLoginPassword(passworddecrypt);



                Intent intent=new Intent(UserLoginActivity.this, UserDeshboardActivity.class);
                //all clear activity
              //  intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finishAffinity();


            }else {
                MyToast.show(this,jsonObject.getString("_message"),true);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void OnCallBackError(String tag, String error, int i) {

    }

    // method call for password decrypt
    public void decrypt(){
        String base64Str=password;

        try {
            base64Byte= base64.decryptBASE64(base64Str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String output=new String(base64Byte);

        Log.d("sunita", "decrypt: "+output);
        passworddecrypt=output;
    }



    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

}