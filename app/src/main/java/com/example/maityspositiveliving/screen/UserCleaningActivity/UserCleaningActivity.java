package com.example.maityspositiveliving.screen.UserCleaningActivity;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.maityspositiveliving.R;
import com.example.maityspositiveliving.Retrofit.interfaces.OnCallBackListner;
import com.example.maityspositiveliving.Retrofit.models.ApiRequest;
import com.example.maityspositiveliving.Retrofit.models.PART;
import com.example.maityspositiveliving.utils.ApplicationConstant;
import com.example.maityspositiveliving.utils.FileUtils;
import com.example.maityspositiveliving.utils.MyToast;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import static com.example.maityspositiveliving.screen.UserCleaningActivity.UserCleaningOnClick.CAMERA_PERMISSION_CODE;

public class UserCleaningActivity extends AppCompatActivity implements OnCallBackListner {

    UserCleaningViewBind userCleaningViewBind;
    UserCleaningOnClick userCleaningOnClick;
    String description,amount,categories_id;
    ApiRequest apiRequest;
    public static final String IMAGE_DIRECTORY = "/demonuts_upload_gallery";
    public static final int BUFFER_SIZE = 1024 * 2;
    public final int GALLERY = 1, PDF = 2;


    File file;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_user_cleaning);


        View view= LayoutInflater.from(this).inflate(R.layout.activity_user_cleaning,null);
        setContentView(view);
        userCleaningViewBind= new UserCleaningViewBind(this,view);
        userCleaningOnClick=new UserCleaningOnClick(this,userCleaningViewBind);

        description=   getIntent().getStringExtra("description");
        categories_id=   getIntent().getStringExtra("categories_id");
        amount=   getIntent().getStringExtra("amount");


        apiRequest=new ApiRequest(UserCleaningActivity.this,this);
       userCleaningViewBind. headername_tvid.setText("CLEANING");
       userCleaningViewBind.tv_amount.setText("\u20B9"+  amount);


    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }


   /* public void showFileChooser(int code) {

        Intent intent = Utility.getCustomFileChooserIntent(IMAGE, PDF);


        try {
            startActivityForResult(Intent.createChooser(intent, "Select Your .pdf File"), code);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "Please Install a File Manager", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (resultCode == RESULT_OK && data != null) {

            file = null;

            try {
                String fullPath = FileUtils.getPath(this, data.getData());
                file = new File(fullPath);

                userCleaningViewBind.lv_attachmentone.setVisibility(View.VISIBLE);
                Toast.makeText(this, ""+file, Toast.LENGTH_SHORT).show();

            } catch (Exception exception) {
                file = null;
                ToastUtils.showLong(this,"This file is not supported because it's a temp file " +
                        "please choose from " +
                        "different folder",true);


                return;
            }


            if (requestCode == CODE_IMAGE_PDF) {



                // if (file != null) callApiforUploadDocument(file, "Matric");


            }


        }

        super.onActivityResult(requestCode, resultCode, data);  // You MUST have this line to be here
        // so ImagePicker can work with fragment mode

    }
*/


    public void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Select PDF " };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                choosePDF();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }


    public void choosePDF() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        startActivityForResult(intent,PDF);

    }


    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (resultCode==this.RESULT_OK && data!=null){

            // uniform resorce identifire

            Uri dataa = data.getData();

            file = null;

          /*  try {
                String fullPath = FileUtils.getPath(this, data.getData());
                file = new File(fullPath);
                Toast.makeText(this, ""+file, Toast.LENGTH_SHORT).show();
                userCleaningViewBind.lv_attachmentone.setVisibility(View.VISIBLE);
            } catch (Exception exception) {
                file = null;
                Toast.makeText(UserCleaningActivity.this,"This file is not supported because it's a temp file " +
                        "please choose from " +
                        "different folder",Toast.LENGTH_LONG);


                return;
            }
*/

            switch (requestCode){

                case GALLERY: {
                    Log.d("path", dataa.toString());
                    try {
                        String fullPath = FileUtils.getPath(this, data.getData());
                        file = new File(fullPath);
                        Toast.makeText(this, ""+file, Toast.LENGTH_SHORT).show();
                        userCleaningViewBind.lv_attachmentone.setVisibility(View.VISIBLE);
                    } catch (Exception exception) {
                        file = null;
                        Toast.makeText(UserCleaningActivity.this,"This file is not supported because it's a temp file " +
                                "please choose from " +
                                "different folder",Toast.LENGTH_LONG);


                        return;
                    }

                //    imageiconid.setImageURI(dataa);

                }
                break;

                case PDF:
                    Uri uri = data.getData();

                    String fullPath = getFilePathFromURI(UserCleaningActivity.this,uri);
                    file = new File(fullPath);
                    Log.d("ioooo",""+file);




                    String[] parts = fullPath.split(":");
                    String part1 = parts[0];
                    //  String part3 = parts[2];

                    if (part1.equalsIgnoreCase("/storage/emulated/0/demonuts_upload_gallery/msf")){
                        Toast.makeText(this, "This file is not supported because it's a temp file " +
                                "please choose from " +
                                "different folder", Toast.LENGTH_SHORT).show();

                    }else if (!part1.equalsIgnoreCase("/storage/emulated/0/demonuts_upload_gallery/msf")) {
                        userCleaningViewBind.lv_attachmentone.setVisibility(View.VISIBLE);

                        Toast.makeText(this, ""+file, Toast.LENGTH_SHORT).show();

                    }
                   // Toast.makeText(this, ""+dataa.toString(), Toast.LENGTH_SHORT).show();


                    //   imageiconid.setImageURI(dataa);
                    break;


                default:
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    // Function to check and request permission.
    public void checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(UserCleaningActivity.this, permission)
                == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(UserCleaningActivity.this,
                    new String[] { permission },
                    requestCode);
        }
        else {
            Toast.makeText(UserCleaningActivity.this,
                    "Permission already granted",
                    Toast.LENGTH_SHORT)
                    .show();
        }
    }


    // This function is called when the user accepts or decline the permission.
    // Request Code is used to check which permission called this function.
    // This request code is provided when the user is prompt for permission.

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        super
                .onRequestPermissionsResult(requestCode,
                        permissions,
                        grantResults);

        if (requestCode == userCleaningOnClick.CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(UserCleaningActivity.this,
                        "Camera Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
            }
            else {
                Toast.makeText(UserCleaningActivity.this,
                        "Camera Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }
        else if (requestCode ==userCleaningOnClick.STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(UserCleaningActivity.this,
                        "Storage Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
            }
            else {
                Toast.makeText(UserCleaningActivity.this,
                        "Storage Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }


    public static String getFilePathFromURI(Context context, Uri contentUri) {
        //copy file and send new file path
        String fileName = getFileName(contentUri);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }
        if (!TextUtils.isEmpty(fileName)) {
            File copyFile = new File(wallpaperDirectory + File.separator + fileName);
            // create folder if not exists

            copy(context, contentUri, copyFile);
            return copyFile.getAbsolutePath();
        }
        return null;
    }

    public static String getFileName(Uri uri) {
        if (uri == null) return null;
        String fileName = null;
        String path = uri.getPath();
        int cut = path.lastIndexOf('/');
        if (cut != -1) {
            fileName = path.substring(cut + 1);
        }
        return fileName;
    }

    public static void copy(Context context, Uri srcUri, File dstFile) {
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(srcUri);
            if (inputStream == null) return;
            OutputStream outputStream = new FileOutputStream(dstFile);
            copystream(inputStream, outputStream);
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static int copystream(InputStream input, OutputStream output) throws Exception, IOException {
        byte[] buffer = new byte[BUFFER_SIZE];

        BufferedInputStream in = new BufferedInputStream(input, BUFFER_SIZE);
        BufferedOutputStream out = new BufferedOutputStream(output, BUFFER_SIZE);
        int count = 0, n = 0;
        try {
            while ((n = in.read(buffer, 0, BUFFER_SIZE)) != -1) {
                out.write(buffer, 0, n);
                count += n;
            }
            out.flush();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                Log.e(e.getMessage(), String.valueOf(e));
            }
            try {
                in.close();
            } catch (IOException e) {
                Log.e(e.getMessage(), String.valueOf(e));
            }
        }
        return count;
    }

    private void  requestMultiplePermissions(){
        Dexter.withActivity(this)
                .withPermissions(

                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            Toast.makeText(getApplicationContext(), "All permissions are granted by user!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings

                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

                    }


                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }





    public void apiForOrderApi(){

       HashMap<String,String> hashMap=new HashMap<>();
       hashMap.put("categories_id", categories_id);
       hashMap.put("customar_id","10");
       hashMap.put("description",description);
     //  hashMap.put("pdf_file", String.valueOf(file));

       hashMap.put("amount",amount);

     //  Log.d("sunta", "apiForOrderApi: "+hashMap);

      // apiRequest.callPOST(ApplicationConstant.OrderApi_url,hashMap,"OrderApi");
  if (file==null){
      MyToast.show(UserCleaningActivity.this,"please  Select image or PDF",true);

  }else {

      apiRequest.callFileUpload(ApplicationConstant.OrderApi_url, hashMap, new PART("pdf_file", file), "OrderApi");
  }

   }

    @Override
    public void OnCallBackSuccess(String tag, String body) {
        if (tag.equalsIgnoreCase("OrderApi")) {
            try {
                JSONObject jsonObject = new JSONObject(body);
                JSONObject jsonObject1 = jsonObject.getJSONObject("messages");
                jsonObject1.getString("success");

                MyToast.show(UserCleaningActivity.this, "" + jsonObject1.getString("success"), true);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void OnCallBackError(String tag, String error, int i) {

    }
}