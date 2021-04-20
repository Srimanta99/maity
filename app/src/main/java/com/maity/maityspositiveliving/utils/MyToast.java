package com.maity.maityspositiveliving.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.maity.maityspositiveliving.R;

public class MyToast {


    public static void show(Context context, String text, boolean isShort) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.toast_layout, null);

        ImageView image = (ImageView) layout.findViewById(R.id.toast_image);
        image.setImageResource(R.drawable.error_icon);

        TextView textV = (TextView) layout.findViewById(R.id.toast_text);
        textV.setText(text);

        Toast toast = new Toast(context);
     //   toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.setDuration((isShort) ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
