package com.maity.maityspositiveliving.screen.UserSubCategoryActivity;

import android.Manifest;
import android.view.View;

import com.maity.maityspositiveliving.R;
import com.maity.maityspositiveliving.utils.MyToast;
import com.maity.maityspositiveliving.utils.SessionManager;

public class UserSubCategoryOnClick implements View.OnClickListener{
    UserSubCategoryActivity userCleaningActivity;
    UserSubCategoryViewBind userCleaningViewBind;
    Boolean saveLogin,saveRegistration;
    public static final int STORAGE_PERMISSION_CODE = 101;
    public static final int CAMERA_PERMISSION_CODE = 100;

    public UserSubCategoryOnClick(UserSubCategoryActivity userCleaningActivity, UserSubCategoryViewBind userCleaningViewBind) {
        this.userCleaningActivity=userCleaningActivity;
        this.userCleaningViewBind=userCleaningViewBind;
        setonclicklistner();
    }
    // set click listner.
    private void setonclicklistner() {

        userCleaningViewBind.back_icon.setOnClickListener(this);
        userCleaningViewBind.submit_btnid.setOnClickListener(this);
        userCleaningViewBind.tv_upload.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_icon:{
                // Toast.makeText(mainActivity,"hello",Toast.LENGTH_LONG).show();
                userCleaningActivity.onBackPressed();

            }
            break;

            case R.id.submit_btnid:{

                saveLogin= SessionManager.getAfterLogin();
                saveRegistration= SessionManager.getLogged();

                if (!userCleaningViewBind.etn_note.getText().toString().isEmpty()){
                    if (userCleaningActivity.fileArray.size()>0){
                        if (saveLogin) {
                            // orderapi implement after login
                            userCleaningActivity.callAfterLoginOrderApi();

                        }else if (saveRegistration) {
                            // orderapi implement after reg
                            userCleaningActivity.callAfterregOrderApi();
                        }

                    }else if (userCleaningActivity.fileArray.size()==0) {
                        MyToast.show(userCleaningActivity,"Please uplaod your order file",true);
                    }

                }else {
                    MyToast.show(userCleaningActivity,"Please enter your order description",true);
                }
            }
            break;


            case R.id.tv_upload:{
                // take permission external storage
                userCleaningActivity. checkPermission(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        STORAGE_PERMISSION_CODE);
                // Function to check and request permission.
                userCleaningActivity.  checkPermission(Manifest.permission.CAMERA,
                        CAMERA_PERMISSION_CODE);

                //show popup for upload image and pdf
                userCleaningActivity. ChooseImageDialog();
            }
            break;

        }
    }
}
