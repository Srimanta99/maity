package com.maity.maityspositiveliving.screen.UserSubCategoryActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.maity.maityspositiveliving.R;
import com.maity.maityspositiveliving.Retrofit.interfaces.OnCallBackListner;
import com.maity.maityspositiveliving.Retrofit.models.ApiRequest;
import com.maity.maityspositiveliving.screen.UserSubCategoryPlaceOrderActivity.UserSubCategoryPlaceOrderActivity;
import com.maity.maityspositiveliving.utils.ApplicationConstant;
import com.maity.maityspositiveliving.utils.FileUtils;
import com.maity.maityspositiveliving.utils.SessionManager;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class UserSubCategoryActivity extends AppCompatActivity implements OnCallBackListner {

    UserSubCategoryViewBind userCleaningViewBind;
    UserSubCategoryOnClick userCleaningOnClick;
    String description,amount,categories_id;
    ApiRequest apiRequest;
    public static final String IMAGE_DIRECTORY = "/demonuts_upload_gallery";
    public static final int BUFFER_SIZE = 1024 * 2;
    public final int GALLERY = 1, PDF = 2,CAMERA=3;
    Boolean saveLogin,saveRegistration;
    int attachCount=0;
    File file;
    Dialog dialog;
    public String SelectedFilepath ="";
    Bitmap    selectedImage;
    ArrayList fileArray=new  ArrayList<File>();
    ArrayList  filetype=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view= LayoutInflater.from(this).inflate(R.layout.activity_user_sub_category,null);
        setContentView(view);
        userCleaningViewBind= new UserSubCategoryViewBind(this,view);
        userCleaningOnClick=new UserSubCategoryOnClick(this,userCleaningViewBind);

        userCleaningViewBind.headername_tvid.setText(SessionManager.getsub_Categoryname());


        saveLogin= SessionManager.getAfterLogin();
        saveRegistration= SessionManager.getLogged();

        description=   getIntent().getStringExtra("description");
        categories_id=   getIntent().getStringExtra("categories_id");

        apiRequest=new ApiRequest(UserSubCategoryActivity.this,this);



        if (saveLogin) {
            userCleaningViewBind.tv_name.setText(SessionManager.getLoginnamevalue());
            userCleaningViewBind.tv_phno.setText(SessionManager.getLoginphnovalue());
            userCleaningViewBind.tv_address.setText(SessionManager.getLoginaddressvalue());
            userCleaningViewBind.tv_email.setText(SessionManager.getLoginemailidvalue());
            userCleaningViewBind.tv_amount.setText("\u20B9"+  SessionManager.getLogincustomer_wallet_balance());


        }else if (saveRegistration) {
            userCleaningViewBind.tv_address.setText(SessionManager.getAddressvalue());
            userCleaningViewBind.tv_email.setText(SessionManager.getemailidvalue());
            userCleaningViewBind.tv_name.setText(SessionManager.getnamevalue());
            userCleaningViewBind.tv_phno.setText(SessionManager.getphnovalue());
            userCleaningViewBind.tv_amount.setText("\u20B9"+  SessionManager.getregcustomer_wallet_balancevalue());

        }



    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    //show popup for upload image and pdf
public  void ChooseImageDialog(){
    android.app.AlertDialog.Builder builder=new android.app.AlertDialog.Builder(this,R.style.CustomAlertDialogforchooseimage);
    View view1= LayoutInflater.from(getApplicationContext()).inflate(R.layout.choose_image_layout,null);

    builder.setView(view1);
    dialog=builder.create();

    dialog.setCancelable(true);
    dialog.show();

    LinearLayout lv_gallery,lv_camera,lv_pdf;
    TextView tv_cencel;

    lv_gallery=view1. findViewById(R.id.lv_gallery);
    lv_camera=view1.findViewById(R.id.lv_camera);
    lv_pdf=view1.findViewById(R.id.lv_pdf);
    tv_cencel=view1.findViewById(R.id.tv_cencel);

    lv_gallery.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            choosePhotoFromGallary();
        }
    });

    lv_camera.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // take picture from camera
            Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(takePicture, CAMERA);
        }

    });
    lv_pdf.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            choosePDF();
        }
    });
    tv_cencel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dialog.dismiss();
        }
    });
}
    public void choosePDF() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");

        startActivityForResult(Intent.createChooser(intent, "Select File"), PDF);

    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        //  startActivityForResult(galleryIntent, GALLERY);
        Intent intent =new  Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select File"), GALLERY);
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (resultCode==this.RESULT_OK && data!=null){
            dialog.dismiss();

            // uniform resorce identifire

            Uri dataa = data.getData();

            file = null;


            switch (requestCode){

                case GALLERY: {
                    Log.d("path", dataa.toString());
                    Bitmap bm = null;
                    try {
                        bm = MediaStore.Images.Media.getBitmap(this.getContentResolver(), dataa);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    attachCount++;
                    try {
                        String fullPath = FileUtils.getPath(this, data.getData());
                        SelectedFilepath =fullPath;

                        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/maity";
                        File dir = new File(path);
                        if (!dir.exists())
                            dir.mkdirs();
                        File file1 = new File(dir, "myimage"+String.valueOf(attachCount)+".jpg");
                        file1.createNewFile();
                        if (file1.exists())
                            file1.delete();
                        try {

                            FileOutputStream out =new  FileOutputStream(file1);
                            bm.compress(Bitmap.CompressFormat.JPEG, 100, out);
                            out.flush();
                            out.close();
                            file = file1;
                            filetype.add("image/jpg");
                            fileArray.add(file);
                        } catch ( FileNotFoundException e) {
                            e.printStackTrace();
                        } catch ( IOException e) {
                            e.printStackTrace();
                        }
                       addAttachmentView();

                    } catch (Exception exception) {
                        file = null;
                        Toast.makeText(UserSubCategoryActivity.this,"This file is not supported because it's a temp file " +
                                "please choose from " +
                                "different folder",Toast.LENGTH_LONG);


                        return;
                    }


                }
                break;

                case PDF:
                    Uri uri = data.getData();
                    //get File Path From URI
                    String fullPath = getFilePathFromURI(UserSubCategoryActivity.this,uri);
                    file = new File(fullPath);

                    String[] parts = fullPath.split(":");
                    String part1 = parts[0];

                    if (part1.equalsIgnoreCase("/storage/emulated/0/demonuts_upload_gallery/msf")){
                        Toast.makeText(this, "This file is not supported because it's a temp file " + "please choose from " + "different folder", Toast.LENGTH_SHORT).show();

                    }else if (!part1.equalsIgnoreCase("/storage/emulated/0/demonuts_upload_gallery/msf")) {
                        attachCount++;
                       addAttachmentView();
                        fileArray.add(file);
                        filetype.add("application/pdf");


                    }

                    break;


                case CAMERA: {

                   // Bitmap selectedImage = (Bitmap) data.getExtras().get("data");

                    if(data.getData() == null) {
                            selectedImage =  (Bitmap) data.getExtras().get("data");
                    } else {
                        try {
                            selectedImage = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }


                    file= bitmapToFile(this, selectedImage, "sunita.jpg");
                    attachCount++;
                    addAttachmentView();
                    fileArray.add(file);
                    filetype.add("image/jpg");

                }
                    break;


                default:
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void addAttachmentView() {
        LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = vi.inflate(R.layout.item_add_attachement, null);
        TextView tv_attachment=v.findViewById(R.id.tv_attachment);
        tv_attachment.setText("Attachment "+attachCount);
        userCleaningViewBind.lv_attachmentone.addView(v);
    }

    // Function to check and request permission.
    public void checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(UserSubCategoryActivity.this, permission)
                == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(UserSubCategoryActivity.this,
                    new String[] { permission },
                    requestCode);
        }
        else {

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
             /*   Toast.makeText(UserSubCategoryActivity.this,
                        "Camera Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();*/
            }
            else {

            }
        }
        else if (requestCode ==userCleaningOnClick.STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                /*Toast.makeText(UserSubCategoryActivity.this,
                        "Storage Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();*/
            }
            else {
               /* Toast.makeText(UserSubCategoryActivity.this,
                        "Storage Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();*/
            }
        }
    }

    //get File Path From URI
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

    // get file name
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



    public static File bitmapToFile(Context context, Bitmap bitmap, String fileNameToSave) { // File name like "image.png"
        //create a file to write bitmap data
        File file = null;
        try {
            file = new File(Environment.getExternalStorageDirectory() + File.separator + fileNameToSave);
            file.createNewFile();

//Convert bitmap to byte array
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 0 , bos); // YOU can also save it in JPEG
            byte[] bitmapdata = bos.toByteArray();

//write the bytes in file
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
            Log.d("sunitafile", "bitmapToFile: "+file);

            return file;
        }catch (Exception e){
            e.printStackTrace();
            return file; // it will return null
        }
    }


    // orderapi implement after login
    public void callAfterLoginOrderApi(){
        amount=   getIntent().getStringExtra("amount");
     ProgressDialog progressDialog = new ProgressDialog(this);
    progressDialog.setMessage("Please Wait...");
    progressDialog.setCancelable(false);
    progressDialog.show();
    int GST= Integer.valueOf(amount) * 18/100;
    int  tAmount=Integer.valueOf(amount)+GST;
    MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
    builder.addFormDataPart("category_id",categories_id);
    builder.addFormDataPart("customer_id",SessionManager.getLoginidvalue());
   // builder.addFormDataPart("customer_note",description);
        builder.addFormDataPart("customer_note",userCleaningViewBind.etn_note.getText().toString());

        builder.addFormDataPart("amount",amount);
        builder.addFormDataPart("amount_total", String.valueOf(tAmount));
      //  builder.addFormDataPart("amount_total", String.valueOf(tAmount));

    if (fileArray.size()>0) {

       for (int i=0;i<fileArray.size();i++) {
           builder.addFormDataPart("customer_file[]",((File) fileArray.get(i)).getName(), RequestBody.create(
                   MediaType.parse(String.valueOf(filetype.get(i))), (File) fileArray.get(i)));
           Log.d("fileArray", "callAfterLoginOrderApi: "+fileArray.get(i));
          //builder.addFormDataPart("customer_file[]", fileArray.get(i).toString(), RequestBody.create(MediaType.parse("image/jpg"), fileArray.get(i)));
       }
    }
    RequestBody requestBody = builder.build();
    Request request = null;

    request = new Request.Builder()
            .addHeader("Content-Type","application/json")
            .addHeader("Content-Type", "multipart/form-data")
            .url(ApplicationConstant.OrderApi_url)
            .post(requestBody)
            .build();

    OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .build();
    Call call = client.newCall(request);
    call.enqueue(new Callback() {
        @Override
        public void onFailure(@NotNull Call call, @NotNull IOException e) {
            if(progressDialog.isShowing())
                progressDialog.dismiss();
        }

        @Override
        public void onResponse(@NotNull Call call,  Response response) throws IOException {
            if(progressDialog.isShowing())
                progressDialog.dismiss();
            try {
                JSONObject jsonObject = new JSONObject(response.body().string());
                Log.d("sunitt", "OnCallBackSuccess: "+jsonObject);
                int _success=jsonObject.optInt("_success");
                if (_success==1) {
                    JSONObject jsonObject1 = jsonObject.getJSONObject("_data");
                    String razorpay_order_id = jsonObject1.getString("razorpay_order_id");
                    Log.d("sunita", "OnCallBackSuccess: " + razorpay_order_id);
                    UserSubCategoryActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                           // MyToast.show(UserCleaningActivity.this, "" + jsonObject.optString("_message"), true);
                            Intent intent = new Intent(UserSubCategoryActivity.this, UserSubCategoryPlaceOrderActivity.class);
                            intent.putExtra("razorpay_order_id", razorpay_order_id);
                            intent.putExtra("note", userCleaningViewBind.etn_note.getText().toString());
                            intent.putExtra("amount", amount);
                            intent.putExtra("fileArray", fileArray);
                            intent.putExtra("filetype", filetype);
                            intent.putExtra("categories_id", categories_id);
                          //  intent.putExtra("GST", GST);

                            Log.d("tAmount", "run: "+userCleaningViewBind.etn_note.getText().toString());
                            Log.d("tAmount", "run: "+amount);
                            Log.d("tAmount", "run: "+fileArray);
                            Log.d("tAmount", "run: "+tAmount);
                            Log.d("tAmount", "run: "+categories_id);
                            Log.d("tAmount", "run: "+GST);
                            startActivity(intent);                        }
                    });


                }else{
                    UserSubCategoryActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                           // MyToast.show(UserSubCategoryActivity.this, "" + jsonObject.optString("_message"), true);
                        }
                    });

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    });



}

    // orderapi implement after reg
    public void callAfterregOrderApi(){
        amount=   getIntent().getStringExtra("amount");
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        int GST= Integer.valueOf(amount) * 18/100;
        int  tAmount=Integer.valueOf(amount)+GST;
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("category_id",categories_id);
        builder.addFormDataPart("customer_id",SessionManager.getregistrationidvalue());
        // builder.addFormDataPart("customer_note",description);
        builder.addFormDataPart("customer_note",userCleaningViewBind.etn_note.getText().toString());

        builder.addFormDataPart("amount",amount);
        builder.addFormDataPart("amount_total", String.valueOf(tAmount));
        //  builder.addFormDataPart("amount_total", String.valueOf(tAmount));

        if (fileArray.size()>0) {

            for (int i=0;i<fileArray.size();i++) {
                builder.addFormDataPart("customer_file[]",((File) fileArray.get(i)).getName(), RequestBody.create(
                        MediaType.parse(String.valueOf(filetype.get(i))), (File) fileArray.get(i)));
                Log.d("fileArray", "callAfterLoginOrderApi: "+fileArray.get(i));

                //builder.addFormDataPart("customer_file[]", fileArray.get(i).toString(), RequestBody.create(MediaType.parse("image/jpg"), fileArray.get(i)));
            }
        }
        RequestBody requestBody = builder.build();
        Request request = null;

        request = new Request.Builder()
                .addHeader("Content-Type","application/json")
                .addHeader("Content-Type", "multipart/form-data")
                .url(ApplicationConstant.OrderApi_url)
                .post(requestBody)
                .build();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                if(progressDialog.isShowing())
                    progressDialog.dismiss();
            }

            @Override
            public void onResponse(@NotNull Call call,  Response response) throws IOException {
                if(progressDialog.isShowing())
                    progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    Log.d("sunitt", "OnCallBackSuccess: "+jsonObject);
                    int _success=jsonObject.optInt("_success");
                    if (_success==1) {
                        JSONObject jsonObject1 = jsonObject.getJSONObject("_data");
                        String razorpay_order_id = jsonObject1.getString("razorpay_order_id");
                        Log.d("sunita", "OnCallBackSuccess: " + razorpay_order_id);
                        UserSubCategoryActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                // MyToast.show(UserCleaningActivity.this, "" + jsonObject.optString("_message"), true);
                                Intent intent = new Intent(UserSubCategoryActivity.this, UserSubCategoryPlaceOrderActivity.class);
                                intent.putExtra("razorpay_order_id", razorpay_order_id);
                                intent.putExtra("note", userCleaningViewBind.etn_note.getText().toString());
                                intent.putExtra("amount", amount);
                                intent.putExtra("fileArray", fileArray);
                                intent.putExtra("filetype", filetype);
                                intent.putExtra("categories_id", categories_id);
                                //  intent.putExtra("GST", GST);

                                Log.d("tAmount", "run: "+userCleaningViewBind.etn_note.getText().toString());
                                Log.d("tAmount", "run: "+amount);
                                Log.d("tAmount", "run: "+fileArray);
                                Log.d("tAmount", "run: "+tAmount);
                                Log.d("tAmount", "run: "+categories_id);
                                Log.d("tAmount", "run: "+GST);
                                startActivity(intent);                        }
                        });


                    }else{
                        UserSubCategoryActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                              //  MyToast.show(UserSubCategoryActivity.this, "" + jsonObject.optString("_message"), true);
                            }
                        });

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });



    }



    @Override
    public void OnCallBackSuccess(String tag, String body) {

    }

    @Override
    public void OnCallBackSuccess(String tag, JSONObject jsonObject) {

    }

    @Override
    public void OnCallBackError(String tag, String error, int i) {

    }
}