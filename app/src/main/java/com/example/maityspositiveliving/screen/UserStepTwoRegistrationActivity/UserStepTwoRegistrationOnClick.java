package com.example.maityspositiveliving.screen.UserStepTwoRegistrationActivity;

import android.content.Intent;
import android.view.View;

import com.example.maityspositiveliving.R;
import com.example.maityspositiveliving.screen.UserStepThreeRegistrationActivity.UserStepThreeRegistrationActivity;
import com.example.maityspositiveliving.utils.RegistrationConstant;


public class UserStepTwoRegistrationOnClick implements View.OnClickListener {
    UserStepTwoRegistrationActivity userStepTwoRegistrationActivity;
    UserStepTwoRegistrationViewBind userStepTwoRegistrationViewBind;


    String Gender="";

    public UserStepTwoRegistrationOnClick(UserStepTwoRegistrationActivity userStepTwoRegistrationActivity, UserStepTwoRegistrationViewBind userStepTwoRegistrationViewBind) {
        this.userStepTwoRegistrationActivity = userStepTwoRegistrationActivity;
        this.userStepTwoRegistrationViewBind = userStepTwoRegistrationViewBind;
        setonclicklistner();
    }

    // set click listner.
    private void setonclicklistner() {
        userStepTwoRegistrationViewBind.nextid.setOnClickListener(this);
        userStepTwoRegistrationViewBind.back_icon.setOnClickListener(this);
        userStepTwoRegistrationViewBind.select_calender.setOnClickListener(this);
        userStepTwoRegistrationViewBind.male_ivid.setOnClickListener(this);
        userStepTwoRegistrationViewBind.female_ivid.setOnClickListener(this);
        userStepTwoRegistrationViewBind.other_ivid.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nextid: {


                //  SessionManager.getGendervalue();

                RegistrationConstant.DOB=userStepTwoRegistrationActivity.DOB;
                RegistrationConstant.GENDER=userStepTwoRegistrationActivity.Gender;

                Intent mainIntent = new Intent(userStepTwoRegistrationActivity, UserStepThreeRegistrationActivity.class);
                userStepTwoRegistrationActivity.startActivity(mainIntent);

            }
            break;
            case R.id.back_icon: {
                RegistrationConstant.DOB=userStepTwoRegistrationActivity.DOB;
                RegistrationConstant.GENDER=Gender;
                userStepTwoRegistrationActivity.finish();
            }
            break;

            case R.id.select_calender:{

                userStepTwoRegistrationActivity.calender();

            }
            break;
            case R.id.male_ivid:{
                userStepTwoRegistrationViewBind.male_ivid.setImageResource(R.drawable.red_circle);
                userStepTwoRegistrationViewBind.female_ivid.setImageResource(R.drawable.circle);
                userStepTwoRegistrationViewBind.other_ivid.setImageResource(R.drawable.circle);

                Gender="Male";

                //   SessionManager.setGendervalue(Gender);

            }
            break;
            case R.id.female_ivid:{
                userStepTwoRegistrationViewBind.female_ivid.setImageResource(R.drawable.red_circle);
                userStepTwoRegistrationViewBind.male_ivid.setImageResource(R.drawable.circle);
                userStepTwoRegistrationViewBind.other_ivid.setImageResource(R.drawable.circle);
                Gender="Female";
                //   SessionManager.setGendervalue(Gender);

            }
            break;
            case R.id.other_ivid:{

                userStepTwoRegistrationViewBind.other_ivid.setImageResource(R.drawable.red_circle);
                userStepTwoRegistrationViewBind.female_ivid.setImageResource(R.drawable.circle);
                userStepTwoRegistrationViewBind.male_ivid.setImageResource(R.drawable.circle);
                Gender="Other";
                //   SessionManager.setGendervalue(Gender);

            }
            break;



        }


    }

    void finis(){

        RegistrationConstant.DOB=userStepTwoRegistrationActivity.DOB;
        RegistrationConstant.GENDER=Gender;
        userStepTwoRegistrationActivity.finish();
    }
}