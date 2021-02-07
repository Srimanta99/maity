package com.example.maityspositiveliving.screen.Fragment.UserMyOrderFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maityspositiveliving.R;

import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class UserMyOrderFragment extends Fragment {

TextView date_tvid;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_my_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        date_tvid=view.findViewById(R.id.date_tvid);

        String s= "15 JAN 2021";

       /* String[] parts = s. split(" JAN 2021");
        String part1 = parts[0];

        int textSize2 = getResources().getDimensionPixelSize(R.dimen.text_size_2);

        SpannableString ss1=  new SpannableString(part1);
        ss1.setSpan(new AbsoluteSizeSpan(textSize2), 0, part1.length(), SPAN_INCLUSIVE_INCLUSIVE);*/
     //   Spannable span = new SpannableString(s);
     //   span.setSpan(new RelativeSizeSpan(0.8f), 0, 0, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

//        String  part2 = parts[1];
       // String part3 = parts[2];
       // ss1.setSpan(new ForegroundColorSpan(Color.RED), 0, 5, 0);// set color

     //   date_tvid.setText(Html.fromHtml("<html><body><H1> 15 </H1></body><html>"));
       // date_tvid.setText(""+ss1);
        super.onViewCreated(view, savedInstanceState);
    }
}