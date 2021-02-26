package com.example.maityspositiveliving.screen.UserDashboardActivity;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.maityspositiveliving.R;
import com.example.maityspositiveliving.screen.Fragment.UserDeshBoardFragment.UserDeshBoardFragment;
import com.example.maityspositiveliving.screen.Fragment.UserMyOrderFragment.UserMyOrderFragment;
import com.example.maityspositiveliving.screen.Fragment.UserPaymentFragment.UserPaymentFragment;
import com.example.maityspositiveliving.screen.Fragment.UserUpdateProfileFragment.UserUpdateProfileFragment;
import com.example.maityspositiveliving.screen.UserStepOneRegistrationActivity.UserStepOneRegistrationActivity;
import com.example.maityspositiveliving.utils.SessionManager;

public class UserDashboardOnClick  implements View.OnClickListener{
    UserDashboardActivity userDashboardActivity;
    UserDashboardViewBind userDashboardViewBind;

    public UserDashboardOnClick(UserDashboardActivity userDashboardActivity, UserDashboardViewBind userDashboardViewBind) {
        this.userDashboardActivity=userDashboardActivity;
        this.userDashboardViewBind=userDashboardViewBind;


        setonclicklistner();



    }
    // set click listner.
    public void setonclicklistner() {
        userDashboardViewBind.dashboardid.setOnClickListener(this);
        userDashboardViewBind.paymentid.setOnClickListener(this);
        userDashboardViewBind.settingsid.setOnClickListener(this);
        userDashboardViewBind.myorderid.setOnClickListener(this);
        userDashboardViewBind.editid.setOnClickListener(this);
        userDashboardViewBind.menu_icon.setOnClickListener(this);
        userDashboardViewBind.tv_logout.setOnClickListener(this);

    }



    public void onBackPressed(){

        if (userDashboardViewBind.drawer.isDrawerOpen(GravityCompat.START)) {
            userDashboardViewBind.drawer.closeDrawer(GravityCompat.START);
        } else {
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.dashboardid:{
                loadFragment(new UserDeshBoardFragment());
                userDashboardViewBind.dashboard_tvid.setText("DASHBOARD");

                closeDrawer();
            }
            break;
            case R.id.menu_icon:
                userDashboardViewBind.drawer.openDrawer(Gravity.LEFT);


                break;

            case R.id.editid:{

                loadFragment(new UserUpdateProfileFragment());
                userDashboardViewBind.dashboard_tvid.setText("UPDATE PROFILE");
                closeDrawer();

            }
            break;

            case R.id.myorderid:{

                loadFragment(new UserMyOrderFragment());
                userDashboardViewBind.dashboard_tvid.setText("MY ORDER");
                closeDrawer();

            }
            break;

            case R.id.paymentid:{

                loadFragment(new UserPaymentFragment());
                userDashboardViewBind.dashboard_tvid.setText("PAYMENT");
                closeDrawer();

            }
            break;

            case R.id.tv_logout:{
                SessionManager.setLogged(false);
                Intent intent=new Intent(userDashboardActivity, UserStepOneRegistrationActivity.class);
                userDashboardActivity.startActivity(intent);
                userDashboardActivity.finish();
            }


        }
    }

    public void closeDrawer() {
        if (userDashboardViewBind.drawer.isDrawerOpen(GravityCompat.START)) {
            userDashboardViewBind.drawer.closeDrawer(GravityCompat.START);
        }
    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction =userDashboardActivity.getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragmentid, fragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();

    }



}
