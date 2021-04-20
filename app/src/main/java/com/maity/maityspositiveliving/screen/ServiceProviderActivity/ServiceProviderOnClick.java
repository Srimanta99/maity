package com.maity.maityspositiveliving.screen.ServiceProviderActivity;

import android.view.View;

import com.maity.maityspositiveliving.R;
import com.maity.maityspositiveliving.utils.MyToast;

public class ServiceProviderOnClick implements View.OnClickListener{
    ServiceProviderActivity serviceProviderActivity;
    ServiceProviderViewBind serviceProviderViewBind;
   public String provider_name,provider_email,provider_phone,provider_address,provider_pincode;

    public ServiceProviderOnClick(ServiceProviderActivity serviceProviderActivity, ServiceProviderViewBind serviceProviderViewBind) {
        this.serviceProviderActivity=serviceProviderActivity;
        this.serviceProviderViewBind=serviceProviderViewBind;
        setonclicklistner();
    }
    // set click listner.
    private void setonclicklistner() {
        serviceProviderViewBind. male_ivid.setOnClickListener(this);
        serviceProviderViewBind. female_ivid.setOnClickListener(this);
        serviceProviderViewBind.  other_ivid.setOnClickListener(this);
        serviceProviderViewBind.  back_icon.setOnClickListener(this);
        serviceProviderViewBind. tv_state.setOnClickListener(this);
        serviceProviderViewBind. lv_submit.setOnClickListener(this);
        serviceProviderViewBind.  img_state.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_icon: {

              serviceProviderActivity.finish();
            }
            break;

            case R.id.male_ivid:{
                serviceProviderViewBind.   male_ivid.setImageResource(R.drawable.fill);
                serviceProviderViewBind.    female_ivid.setImageResource(R.drawable.outline);
                serviceProviderViewBind.   other_ivid.setImageResource(R.drawable.outline);

                serviceProviderActivity.Gender="Male";


            }
            break;
            case R.id.female_ivid:{
                serviceProviderViewBind.  female_ivid.setImageResource(R.drawable.fill);
                serviceProviderViewBind.  male_ivid.setImageResource(R.drawable.outline);
                serviceProviderViewBind.   other_ivid.setImageResource(R.drawable.outline);
                serviceProviderActivity.Gender="Female";


            }
            break;
            case R.id.other_ivid:{

                serviceProviderViewBind.  other_ivid.setImageResource(R.drawable.fill);
                serviceProviderViewBind.  female_ivid.setImageResource(R.drawable.outline);
                serviceProviderViewBind.  male_ivid.setImageResource(R.drawable.outline);
                serviceProviderActivity.Gender="Other";


            }
            break;

            case R.id.tv_state:{
              serviceProviderActivity.  opendialogForState(); //method  call for show state
            }
            break;

            case R.id.img_state:{
                serviceProviderActivity. opendialogForState();
            }
            break;
            case R.id.lv_submit: {
                provider_name= serviceProviderViewBind. etn_nameid.getText().toString();
                provider_email=  serviceProviderViewBind. etn_emailid.getText().toString();
                provider_phone= serviceProviderViewBind. etn_mobno.getText().toString();
                provider_address= serviceProviderViewBind. etn_address.getText().toString();
                provider_pincode=serviceProviderViewBind. pin_etnid.getText().toString();
                if ( provider_name.isEmpty()){
                    MyToast.show( serviceProviderActivity,"Please enter your name",true);

                }else if (provider_email .isEmpty()){  //if etn_email is empty
                    MyToast.show(serviceProviderActivity,"Please enter your email id",true);

                }  // method call for isValidEmailformet
                else if (!  serviceProviderActivity.isValidEmailId( serviceProviderViewBind. etn_emailid.getText().toString().trim())){
                    MyToast.show(serviceProviderActivity,"InValid email address.",true);

                }
                else if (serviceProviderActivity.provider_category.equalsIgnoreCase("null")){
                    MyToast.show(serviceProviderActivity,"Please select your service area",true);

                }else if (provider_phone.isEmpty()){
                    MyToast.show(serviceProviderActivity,"Please enter your contact number",true);

                } else if ( provider_address.isEmpty()){
                    MyToast.show(serviceProviderActivity,"Please enter your address",true);

                }else if (provider_pincode .isEmpty()){
                    MyToast.show(serviceProviderActivity,"Please enter your pincode",true);

                }else {
                   serviceProviderActivity.apiforProviderApi_add();
                }
            }
        }
    }
}