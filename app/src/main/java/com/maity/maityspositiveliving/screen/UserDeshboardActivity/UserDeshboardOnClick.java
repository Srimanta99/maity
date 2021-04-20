package com.maity.maityspositiveliving.screen.UserDeshboardActivity;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.maity.maityspositiveliving.R;
import com.maity.maityspositiveliving.screen.Fragment.UserCategoryListFragment.UserCategoryListFragment;
import com.maity.maityspositiveliving.screen.Fragment.UserMyOrderFragment.UserMyOrderFragment;
import com.maity.maityspositiveliving.screen.Fragment.UserMyWalletFragment.UserMyWalletFragment;
import com.maity.maityspositiveliving.screen.Fragment.UserUpdateProfileFragment.UserUpdateProfileFragment;
import com.maity.maityspositiveliving.screen.UserLoginActivity.UserLoginActivity;
import com.maity.maityspositiveliving.utils.SessionManager;

public class UserDeshboardOnClick implements View.OnClickListener{
    UserDeshboardActivity userDashboardActivity;
    UserDeshboardViewBind userDashboardViewBind;

    public UserDeshboardOnClick(UserDeshboardActivity userDashboardActivity, UserDeshboardViewBind userDashboardViewBind) {
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
userDashboardViewBind.img_close.setOnClickListener(this);
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
                //open CategoryListFragment
                loadFragment(new UserCategoryListFragment());
                userDashboardViewBind.dashboard_tvid.setText("DASHBOARD");

                closeDrawer();
            }
            break;
            case R.id.menu_icon:
                userDashboardViewBind.drawer.openDrawer(Gravity.LEFT);


                break;

            case R.id.editid:{
                //open UpdateProfileFragment
                loadFragment(new UserUpdateProfileFragment());
                userDashboardViewBind.dashboard_tvid.setText("UPDATE PROFILE");
                closeDrawer();

            }
            break;

            case R.id.myorderid:{
                //open MyOrderFragment
                loadFragment(new UserMyOrderFragment());
                userDashboardViewBind.dashboard_tvid.setText("MY ORDERS");
                closeDrawer();

            }
            break;

            case R.id.paymentid:{
                 //open MyWalletFragment
                loadFragment(new UserMyWalletFragment());
                userDashboardViewBind.dashboard_tvid.setText("My Wallet");
                closeDrawer();

            }
            break;

            case R.id.tv_logout:{
                SessionManager.setLogged(false);
                SessionManager.setAfterLogin(false);
                Intent intent=new Intent(userDashboardActivity, UserLoginActivity.class);
                userDashboardActivity.startActivity(intent);
                userDashboardActivity.finish();
            }
           break;
            case R.id.img_close:{
                userDashboardViewBind.drawer.closeDrawer(GravityCompat.START);
            }
            break;

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
