package com.example.maityspositiveliving.screen.Fragment.UserUpdateProfileFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.maityspositiveliving.R;

import com.example.maityspositiveliving.Retrofit.interfaces.OnCallBackListner;
import com.example.maityspositiveliving.Retrofit.models.ApiRequest;
import com.example.maityspositiveliving.screen.Fragment.UserPaymentFragment.UserPaymentOnClick;
import com.example.maityspositiveliving.screen.Fragment.UserPaymentFragment.UserPaymentViewBind;
import com.example.maityspositiveliving.screen.UserStepThreeRegistrationActivity.UserStepThreeRegistrationActivity;
import com.example.maityspositiveliving.screen.UserThankYouActivity.UserThankYouActivity;
import com.example.maityspositiveliving.utils.ApplicationConstant;
import com.example.maityspositiveliving.utils.MyToast;
import com.example.maityspositiveliving.utils.RegistrationConstant;
import com.example.maityspositiveliving.utils.SessionManager;

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


        userUpdateProfileViewBInd.etn_nameid.setText(SessionManager.getnamevalue());
        userUpdateProfileViewBInd.etn_emailid.setText(SessionManager.getemailidvalue());
        userUpdateProfileViewBInd.etn_mobnoid.setText(SessionManager.getphnovalue());
        userUpdateProfileViewBInd.etn_currentpasswordid.setText(SessionManager.getcPassword());

        super.onViewCreated(view, savedInstanceState);
    }

    public  void apiForupdate(){

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

        hashMap.put("password",SessionManager.getPassword());

        hashMap.put("cpassword",SessionManager.getcPassword());
        Log.d("sunita", "apiForRegister: "+hashMap);

        apiRequest.callPOST(ApplicationConstant.update_url,hashMap,"Update");
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
    public void OnCallBackError(String tag, String error, int i) {

    }
}