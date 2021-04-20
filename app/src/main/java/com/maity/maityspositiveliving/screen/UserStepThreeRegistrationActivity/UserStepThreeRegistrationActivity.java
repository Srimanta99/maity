package com.maity.maityspositiveliving.screen.UserStepThreeRegistrationActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.maity.maityspositiveliving.Interface.StateInterface;
import com.maity.maityspositiveliving.POJO.CityList;
import com.maity.maityspositiveliving.POJO.StateList;
import com.maity.maityspositiveliving.R;

import com.maity.maityspositiveliving.Retrofit.interfaces.OnCallBackListner;
import com.maity.maityspositiveliving.Retrofit.models.ApiRequest;
import com.maity.maityspositiveliving.screen.Adapter.CityAdapter;
import com.maity.maityspositiveliving.screen.Adapter.StateAdapter;
import com.maity.maityspositiveliving.screen.UserThankYouActivity.UserThankYouActivity;
import com.maity.maityspositiveliving.utils.ApplicationConstant;
import com.maity.maityspositiveliving.utils.MyToast;
import com.maity.maityspositiveliving.utils.RegistrationConstant;
import com.maity.maityspositiveliving.utils.SessionManager;
import com.maity.maityspositiveliving.utils.base64;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class UserStepThreeRegistrationActivity extends AppCompatActivity implements OnCallBackListner {
    UserStepThreeRegistrationViewBind userStepThreeRegistrationViewBind;
    UserStepThreeRegistrationOnClick userStepThreeRegistrationOnClick;
    String password,passworddecrypt;
    byte[] base64Byte=null;

    StateAdapter stateAdapter;
    Dialog dialog;
    CityAdapter cityAdapter;

    ArrayList<StateList> stateLists;
    ArrayList<CityList> cityLists;

    ApiRequest request;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view= LayoutInflater.from(this).inflate(R.layout.activity_user_step_three_registration,null);
        setContentView(view);
        userStepThreeRegistrationViewBind= new UserStepThreeRegistrationViewBind(this,view);
        userStepThreeRegistrationOnClick=new UserStepThreeRegistrationOnClick(this,userStepThreeRegistrationViewBind);

        userStepThreeRegistrationViewBind.tv_state.setText(RegistrationConstant.STATE);//if go to another page then back this page then set STATE
        userStepThreeRegistrationViewBind.tv_city.setText(RegistrationConstant.CITY);//if go to another page then back this page then set CITY
        userStepThreeRegistrationViewBind.address_etnid.setText(RegistrationConstant.ADDRESS);//if go to another page then back this page then set ADDRESS
        userStepThreeRegistrationViewBind.pin_etnid.setText(RegistrationConstant.PIN);//if go to another page then back this page then set PIN

        request=new ApiRequest(this,this);

        stateLists=new ArrayList<>();
        cityLists=new ArrayList<>();

    }
    //method  call for show state
    public void opendialogForState(){
        dialog=new Dialog(UserStepThreeRegistrationActivity.this);
        dialog.setContentView(R.layout.dialog_searchable_spinner);


        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics ();
        display.getMetrics(outMetrics);
        dialog.getWindow().setLayout((int)(405 * outMetrics.density), (int)(536 * outMetrics.density));
        dialog.show();
        // api call for show state
        apiForState();

       userStepThreeRegistrationViewBind. etn_search=dialog.findViewById(R.id.etn_search);
        userStepThreeRegistrationViewBind. rv_state =dialog.findViewById(R.id.rv_state);

        userStepThreeRegistrationViewBind.  etn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userStepThreeRegistrationViewBind. etn_search.getText().toString().length() > 0) {
                 // api call for show state
                    apiForState();

                }
            }
        });


    }
    //method call for show city
    public void opendialogForCity(){
        dialog=new Dialog(UserStepThreeRegistrationActivity.this);
        dialog.setContentView(R.layout.dialog_spinner_for_city);
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics ();
        display.getMetrics(outMetrics);
        dialog.getWindow().setLayout((int)(405 * outMetrics.density), (int)(536 * outMetrics.density));        //  dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        // api call for show city
        apiForCity();



        userStepThreeRegistrationViewBind. etn_search=dialog.findViewById(R.id.etn_search);
        userStepThreeRegistrationViewBind. rv_city =dialog.findViewById(R.id.rv_city);

        userStepThreeRegistrationViewBind.  etn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userStepThreeRegistrationViewBind. etn_search.getText().toString().length() > 0) {
                    // api call for show city
                    apiForCity();


                }
            }
        });


    }
    // api call for show state
    private void apiForState() {

        request.callGET(ApplicationConstant.state_url,"state");
    }
    // api call for show city

    private void apiForCity() {
        request.callGET(ApplicationConstant.CityApi_url+RegistrationConstant.STATEID,"city");
    }
    // api call for Register
    public void apiForRegister(){
        RegistrationConstant.PIN=   userStepThreeRegistrationViewBind.pin_etnid.getText().toString();
        RegistrationConstant.ADDRESS= userStepThreeRegistrationViewBind.address_etnid.getText().toString();

        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("customar_name",RegistrationConstant.NAME);
        hashMap.put("customar_email",RegistrationConstant.EMAIL_ID);
        hashMap.put("customar_phone",RegistrationConstant.MOBILE_NUMBER);
        hashMap.put("customar_dob",RegistrationConstant.DOB);

        hashMap.put("customar_gender",RegistrationConstant.GENDER);

        hashMap.put("customar_country","1");

        hashMap.put("customar_state", String.valueOf(RegistrationConstant.STATEID));

        hashMap.put("customar_city",RegistrationConstant.CITYID);

        hashMap.put("customar_address",RegistrationConstant.ADDRESS);

        hashMap.put("customar_pincode",RegistrationConstant.PIN);
/*
        hashMap.put("password","123456");

        hashMap.put("cpassword","123456");*/
        Log.d("sunita", "apiForRegister: "+hashMap);

        request.callPOST(ApplicationConstant.register_url,hashMap,"Register");


    }

    @Override
    public void OnCallBackSuccess(String tag, String body) {
        stateLists.clear();

        if (tag.equalsIgnoreCase("state")){

            try {
                JSONArray jsonArray = new JSONArray(body);
                for (int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    StateList stateList=new StateList(
                            jsonObject.getString("id"),
                       jsonObject.getString("state_name"),


                            jsonObject.getString("country_id")
                    );

                    stateLists.add(stateList);

                    stateAdapter = new StateAdapter(this, stateLists, new StateInterface() {
                        @Override
                        public void statename(int pos) {
                            userStepThreeRegistrationViewBind.  etn_search.setText(stateLists.get(pos).getState_name());
                            dialog.dismiss();
                          userStepThreeRegistrationViewBind.  tv_state.setText(stateLists.get(pos).getState_name());

                            RegistrationConstant.STATE=stateLists.get(pos).getState_name();
                            RegistrationConstant.STATEID=stateLists.get(pos).getId();

                        }
                    });
                    userStepThreeRegistrationViewBind. rv_state.setAdapter(stateAdapter);

                    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
                    userStepThreeRegistrationViewBind.  rv_state.setLayoutManager(linearLayoutManager);

                    userStepThreeRegistrationViewBind.  etn_search.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void afterTextChanged(Editable editable) {

                            filter(editable.toString());

                        }
                    });

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else   if (tag.equalsIgnoreCase("city")){
            cityLists.clear();

            try {
                JSONArray jsonArray = new JSONArray(body);
                for (int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    CityList cityList=new CityList(
                            jsonObject.getString("city_id"),
                            jsonObject.getString("city_name"),
                            jsonObject.getString("country_id")
                    );

                    cityLists.add(cityList);

                    cityAdapter = new CityAdapter(this, cityLists, new StateInterface() {
                        @Override
                        public void statename(int pos) {
                            userStepThreeRegistrationViewBind.  etn_search.setText(cityLists.get(pos).getCity_name());
                            dialog.dismiss();
                             userStepThreeRegistrationViewBind.  tv_city.setText(cityLists.get(pos).getCity_name());

                             RegistrationConstant.CITY=cityLists.get(pos).getCity_name();
                            RegistrationConstant.CITYID=cityLists.get(pos).getCity_id();

                        }
                    });
                    userStepThreeRegistrationViewBind. rv_city.setAdapter(cityAdapter);

                    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
                    userStepThreeRegistrationViewBind.  rv_city.setLayoutManager(linearLayoutManager);



                    userStepThreeRegistrationViewBind.  etn_search.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void afterTextChanged(Editable editable) {

                            filterCity(editable.toString());

                        }
                    });

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


      else   if (tag.equalsIgnoreCase("Register")){
            try {
                JSONObject jsonObject=new JSONObject(body);
              String status=  jsonObject.getString("status");
                Log.d("status", "OnCallBackSuccess: "+status);


                JSONObject jsonObject1=jsonObject.getJSONObject("messages");

                if (status.equalsIgnoreCase("1")){
                    JSONObject jsonObject2=jsonObject.getJSONObject("_user");
                    String id=  jsonObject2.getString("id");
                    String name=  jsonObject2.getString("customar_name");
                    String email= jsonObject2.getString("customar_email");
                    String phno=    jsonObject2.getString("customar_phone");
                    jsonObject2.getString("customar_dob");
                    jsonObject2.getString("customar_gender");
                    jsonObject2.getString("customar_country");
                    jsonObject2.getString("customar_state");
                    jsonObject2.getString("customar_city");
                    String address= jsonObject2.getString("customar_address");
                    jsonObject2.getString("customar_pincode");
                    jsonObject2.getString("status");
                    String customar_dob=  jsonObject2.getString("customar_dob");
                    String customar_gender=   jsonObject2.getString("customar_gender");
                    String customar_country=   jsonObject2.getString("customar_country");
                    String customar_state=   jsonObject2.getString("customar_state");
                    String customar_city=   jsonObject2.getString("customar_city");
                    String customar_pincode=   jsonObject2.getString("customar_pincode");


                    String  customer_wallet_balance= jsonObject2.getString("customer_wallet_balance");
                    password=  jsonObject2.getString("password");
                    decrypt();  // method call for password decrypt

                    SessionManager.setregistrationidvalue(id);
                    SessionManager.setnamevalue(name);
                    SessionManager.setemailidvalue(email);
                    SessionManager.setphnovalue(phno);
                    SessionManager.setDOBvalue(customar_dob);
                    SessionManager.setGendervalue(customar_gender);
                    SessionManager.setcustomar_countryvalue(customar_country);
                    SessionManager.setSelectStatevalue(customar_state);
                    SessionManager.setSelectCityvalue(customar_city);
                    SessionManager.setAddressvalue(address);
                    SessionManager.setPinNovalue(customar_pincode);
                    SessionManager.setregcustomer_wallet_balancevalue(customer_wallet_balance);
                    SessionManager.setreg_passwordvalue(passworddecrypt);

                    SessionManager.setLogged(true);
                    Intent mainIntent = new Intent(UserStepThreeRegistrationActivity.this, UserThankYouActivity.class).addFlags(
                            Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                    Intent.FLAG_ACTIVITY_CLEAR_TOP
                    );
                    startActivity(mainIntent);
                }else {
                    MyToast.show(UserStepThreeRegistrationActivity.this,"User already exists,please use a different email address",true);

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


    }

    @Override
    public void OnCallBackSuccess(String tag, JSONObject jsonObject) {

    }

    @Override
    public void OnCallBackError(String tag, String error, int i) {


    }

    @Override
    public void onBackPressed() {
        userStepThreeRegistrationOnClick.saveWithFinish();
    }


    private void filterCity(String text) {
        ArrayList<CityList> filteredList = new ArrayList<>();

        for (CityList item : cityLists) {
            if (item.getCity_name().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        if (cityAdapter!=null){
            cityAdapter.filterList(filteredList);


            cityAdapter = new CityAdapter( UserStepThreeRegistrationActivity.this, filteredList, new StateInterface() {
                @Override
                public void statename(int pos) {
                    userStepThreeRegistrationViewBind. etn_search.setText(filteredList.get(pos).getCity_name());
                    dialog.dismiss();
                    userStepThreeRegistrationViewBind. tv_city.setText(filteredList.get(pos).getCity_name());
                    RegistrationConstant.CITY=cityLists.get(pos).getCity_name();
                    RegistrationConstant.CITYID=cityLists.get(pos).getCity_id();

                }
            });
            userStepThreeRegistrationViewBind.rv_city.setAdapter(cityAdapter);

        }


    }


    private void filter(String text) {
        ArrayList<StateList> filteredList = new ArrayList<>();

        for (StateList item : stateLists) {
            if (item.getState_name().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        if (stateAdapter!=null){
            stateAdapter.filterList(filteredList);


            stateAdapter = new StateAdapter( UserStepThreeRegistrationActivity.this, filteredList, new StateInterface() {
                @Override
                public void statename(int pos) {
                    userStepThreeRegistrationViewBind. etn_search.setText(filteredList.get(pos).getState_name());
                    dialog.dismiss();
                    userStepThreeRegistrationViewBind.  tv_state.setText(filteredList.get(pos).getState_name());
                    RegistrationConstant.STATE=filteredList.get(pos).getState_name();
                    RegistrationConstant.STATEID=filteredList.get(pos).getId();


                }
            });
            userStepThreeRegistrationViewBind.rv_state.setAdapter(stateAdapter);

        }


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
        passworddecrypt=output;
    }

}