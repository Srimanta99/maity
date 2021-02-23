package com.example.maityspositiveliving;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.maityspositiveliving.Retrofit.models.ToastUtils;
import com.example.maityspositiveliving.utils.FileUtils;
import com.example.maityspositiveliving.utils.Utility;

import java.io.File;

import static com.example.maityspositiveliving.utils.Utility.IMAGE;
import static com.example.maityspositiveliving.utils.Utility.PDF;

public class UploadImageAndPDfActivity extends AppCompatActivity {

    private static final int CODE_IMAGE_PDF =10 ;
    File file ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image_and_p_df);
    }

    private void showFileChooser(int code) {

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

}