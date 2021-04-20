package com.maity.maityspositiveliving.screen.UserStepTwoRegistrationActivity;

import android.content.Intent;
import android.view.View;

import com.maity.maityspositiveliving.R;
import com.maity.maityspositiveliving.screen.UserStepThreeRegistrationActivity.UserStepThreeRegistrationActivity;
import com.maity.maityspositiveliving.utils.MyToast;
import com.maity.maityspositiveliving.utils.RegistrationConstant;


public class UserStepTwoRegistrationOnClick implements View.OnClickListener {
    UserStepTwoRegistrationActivity userStepTwoRegistrationActivity;
    UserStepTwoRegistrationViewBind userStepTwoRegistrationViewBind;


  //  String Gender="";

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
        userStepTwoRegistrationViewBind.dob_tvid.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nextid: {

                RegistrationConstant.DOB=userStepTwoRegistrationActivity.DOB;
                RegistrationConstant.GENDER=userStepTwoRegistrationActivity.Gender;

                if (userStepTwoRegistrationViewBind.dob_tvid.getText().toString().isEmpty()){
                    MyToast.show(userStepTwoRegistrationActivity,"please Enter Your DOB",true);

                }else if (RegistrationConstant.GENDER.isEmpty()){
                    MyToast.show(userStepTwoRegistrationActivity,"please Enter Your Gender",true);
                }
                else {
                    Intent mainIntent = new Intent(userStepTwoRegistrationActivity, UserStepThreeRegistrationActivity.class);
                    userStepTwoRegistrationActivity.startActivity(mainIntent);
                }
            }
            break;
            case R.id.back_icon: {
                RegistrationConstant.DOB=userStepTwoRegistrationActivity.DOB; // enter DOB in edittext ,this DOB is catch statically RegistrationConstant class
                RegistrationConstant.GENDER=userStepTwoRegistrationActivity.Gender;// enter Gender in edittext ,this Gender is catch statically RegistrationConstant class
                userStepTwoRegistrationActivity.finish();
            }
            break;

            case R.id.select_calender:{

                userStepTwoRegistrationActivity.calender();

            }
            break;
            case R.id.dob_tvid:{
                userStepTwoRegistrationActivity.calender();

            }
            break;
            case R.id.male_ivid:{
                userStepTwoRegistrationViewBind.male_ivid.setImageResource(R.drawable.red_circle);
                userStepTwoRegistrationViewBind.female_ivid.setImageResource(R.drawable.circle);
                userStepTwoRegistrationViewBind.other_ivid.setImageResource(R.drawable.circle);

                userStepTwoRegistrationActivity.Gender="M";


            }
            break;
            case R.id.female_ivid:{
                userStepTwoRegistrationViewBind.female_ivid.setImageResource(R.drawable.red_circle);
                userStepTwoRegistrationViewBind.male_ivid.setImageResource(R.drawable.circle);
                userStepTwoRegistrationViewBind.other_ivid.setImageResource(R.drawable.circle);
                userStepTwoRegistrationActivity.Gender="F";
                //SessionManager.setGendervalue(Gender);

            }
            break;
            case R.id.other_ivid:{

                userStepTwoRegistrationViewBind.other_ivid.setImageResource(R.drawable.red_circle);
                userStepTwoRegistrationViewBind.female_ivid.setImageResource(R.drawable.circle);
                userStepTwoRegistrationViewBind.male_ivid.setImageResource(R.drawable.circle);
                userStepTwoRegistrationActivity.Gender="O";
                //   SessionManager.setGendervalue(Gender);

            }
            break;
        }


    }

    void finis(){

        RegistrationConstant.DOB=userStepTwoRegistrationActivity.DOB;
        RegistrationConstant.GENDER=userStepTwoRegistrationActivity.Gender;
        userStepTwoRegistrationActivity.finish();
    }
}