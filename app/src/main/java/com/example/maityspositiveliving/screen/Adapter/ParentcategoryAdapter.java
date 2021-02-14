package com.example.maityspositiveliving.screen.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maityspositiveliving.POJO.ParentcategoryList;
import com.example.maityspositiveliving.R;

import com.example.maityspositiveliving.screen.UserHouseHoldActivity.UserHouseHoldActivity;
import com.example.maityspositiveliving.utils.ApplicationConstant;

import java.util.List;

public class ParentcategoryAdapter extends RecyclerView.Adapter<ParentcategoryAdapter.ParentcategoryViewHolder> {
    Context context;
    List<ParentcategoryList> parentcategoryLists;
    LayoutInflater layoutInflater;
    String id,subcategory_url_withid;

    public ParentcategoryAdapter(Context context, List<ParentcategoryList> parentcategoryLists) {
        this.context = context;
        this.parentcategoryLists = parentcategoryLists;

    }

    @NonNull
    @Override
    public ParentcategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.from(context).inflate(R.layout.sampleparentcategory,parent,false);
        ParentcategoryViewHolder parentcategoryViewHolder=new ParentcategoryViewHolder(view);

        return parentcategoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ParentcategoryViewHolder holder, int position) {
        ParentcategoryList parentcategoryListpostion=parentcategoryLists.get(position);

        holder.tv_category_name.setText(parentcategoryListpostion.getCategory_name());
        holder.lv_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 id=   parentcategoryListpostion.getId();
               subcategory_url_withid=  ApplicationConstant.subcategory_url.concat(id);


                Intent intent=new Intent(context, UserHouseHoldActivity.class);
                intent.putExtra("subcategory_url",subcategory_url_withid);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return parentcategoryLists.size();
    }

    public class ParentcategoryViewHolder extends RecyclerView.ViewHolder{
TextView tv_category_name;
LinearLayout lv_id;
        public ParentcategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_category_name=itemView.findViewById(R.id.tv_category_name);
            lv_id=itemView.findViewById(R.id.lv_id);
        }
    }
}


