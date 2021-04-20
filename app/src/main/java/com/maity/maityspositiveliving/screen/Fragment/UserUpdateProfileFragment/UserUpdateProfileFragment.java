package com.maity.maityspositiveliving.screen.Fragment.UserUpdateProfileFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.maity.maityspositiveliving.R;

import com.maity.maityspositiveliving.Retrofit.interfaces.OnCallBackListner;
import com.maity.maityspositiveliving.Retrofit.models.ApiRequest;
import com.maity.maityspositiveliving.utils.ApplicationConstant;
import com.maity.maityspositiveliving.utils.MyToast;
import com.maity.maityspositiveliving.utils.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class UserUpdateProfileFragment extends Fragment implements OnCallBackListner {

    // TODO: Rename parameter arguments, choose names that match
    UserUpdateProfileViewBInd userUpdateProfileViewBInd;
    UserUpdateProfileOnClick userUpdateProfileOnClick;
    ApiRequest apiRequest;

   public static Boolean saveLogin,saveRegistration;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_update_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        userUpdateProfileViewBInd= new UserUpdateProfileViewBInd(this,view);
        userUpdateProfileOnClick=new UserUpdateProfileOnClick(this,userUpdateProfileViewBInd);

        apiRequest=new ApiRequest(getContext(),this);

        saveLogin= SessionManager.getAfterLogin(); //get boolean value after login
        saveRegistration= SessionManager.getLogged(); //get boolean value after registration


        if (saveLogin) {
           // apiForupdateAferlogin();

            userUpdateProfileViewBInd.etn_nameid.setText(SessionManager.getLoginnamevalue());// after login get name then settext in all edittext
            userUpdateProfileViewBInd.etn_mobnoid.setText(SessionManager.getLoginphnovalue());// after login get phno then settext in all edittext
            userUpdateProfileViewBInd.etn_emailid.setText(SessionManager.getLoginemailidvalue());// after login get email then settext in all edittext
            userUpdateProfileViewBInd.etn_currentpasswordid.setText(SessionManager.getLoginPassword());// after login get password then settext in all edittext

        }else if (saveRegistration) {
           // apiForupdateAferregistration();
            userUpdateProfileViewBInd.etn_nameid.setText(SessionManager.getnamevalue());// after registration get name then settext in all edittext
            userUpdateProfileViewBInd.etn_emailid.setText(SessionManager.getemailidvalue());// after registration get email then settext in all edittext
            userUpdateProfileViewBInd.etn_mobnoid.setText(SessionManager.getphnovalue());// after registration get phno then settext in all edittext
            userUpdateProfileViewBInd.etn_currentpasswordid.setText(SessionManager.getreg_passwordvalue());// after registration get passsword then settext in all edittext
        }


        super.onViewCreated(view, savedInstanceState);
    }
    // implement api for update profile
    public  void apiForupdateAferregistration(){

        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("customar_name", SessionManager.getnamevalue());
        hashMap.put("customar_email",SessionManager.getemailidvalue());
        hashMap.put("customar_phone",SessionManager.getphnovalue());
        hashMap.put("customar_dob",SessionManager.getDOBvalue());

        hashMap.put("customar_gender",SessionManager.getGendervalue());

        hashMap.put("customar_country",SessionManager.getcustomar_countryvalue());

        hashMap.put("customar_state", String.valueOf(SessionManager.getSelectStatevalue()));

        hashMap.put("customar_city",SessionManager.getSelectCityvalue());

        hashMap.put("customar_address",SessionManager.getAddressvalue());

        hashMap.put("customar_pincode",SessionManager.getPinNovalue());

      /*  hashMap.put("password",SessionManager.getreg_passwordvalue());

        hashMap.put("cpassword",SessionManager.getreg_passwordvalue());
        Log.d("sunita", "apiForRegister: "+hashMap);*/
        String newpassword= userUpdateProfileViewBInd.etn_newpasswordid.getText().toString();
        String confirmpassword= userUpdateProfileViewBInd.etn_confirmpasswordid.getText().toString();
        hashMap.put("password",newpassword);

        hashMap.put("cpassword",confirmpassword);
        Log.d("sunita", "apiForlogin: "+hashMap);


        if (newpassword.equalsIgnoreCase(""+confirmpassword)){
            apiRequest.callPOST(ApplicationConstant.update_url,hashMap,"Update");
         //   SessionManager.setreg_passwordvalue(confirmpassword);
        }else {
            MyToast.show(getContext(),"Confirm password doesn't match",true);
        }


    }
    public  void apiForupdateAferlogin(){

        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("customar_name", SessionManager.getLoginnamevalue());
        hashMap.put("customar_email",SessionManager.getLoginemailidvalue());
        hashMap.put("customar_phone",SessionManager.getLoginphnovalue());
        hashMap.put("customar_dob",SessionManager.getLogincustomar_dob());
        hashMap.put("customar_gender",SessionManager.getLogincustomar_gender());
        hashMap.put("customar_country",SessionManager.getLogincustomar_country());
        hashMap.put("customar_state", String.valueOf(SessionManager.getLogincustomar_state()));

        hashMap.put("customar_city",SessionManager.getLogincustomar_city());

        hashMap.put("customar_address",SessionManager.getLoginaddressvalue());

        hashMap.put("customar_pincode",SessionManager.getLogincustomar_pincode());

      /*  hashMap.put("password",SessionManager.getLoginPassword());

        hashMap.put("cpassword",SessionManager.getLoginPassword());*/
       String newpassword= userUpdateProfileViewBInd.etn_newpasswordid.getText().toString();
        String confirmpassword= userUpdateProfileViewBInd.etn_confirmpasswordid.getText().toString();

        hashMap.put("password",newpassword);

        hashMap.put("cpassword",confirmpassword);
        Log.d("sunita", "apiForlogin: "+hashMap);


        if (newpassword.equalsIgnoreCase(""+confirmpassword)){
            apiRequest.callPOST(ApplicationConstant.update_url,hashMap,"Update");
          //  SessionManager.setLoginPassword(confirmpassword);

        }else {
            MyToast.show(getContext(),"Confirm password doesn't match",true);
        }


    }

    @Override
    public void OnCallBackSuccess(String tag, String body) {
        if (tag.equalsIgnoreCase("Update")){
            try {
                JSONObject jsonObject=new JSONObject(body);
                JSONObject jsonObject1=jsonObject.getJSONObject("messages");


                MyToast.show(getContext(),""+jsonObject1.getString("success"),true);


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
}