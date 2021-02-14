package com.example.maityspositiveliving.screen.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maityspositiveliving.POJO.SubcategoryList;
import com.example.maityspositiveliving.R;

import java.util.List;

public class SubcategoryAdapter extends RecyclerView.Adapter<SubcategoryAdapter.SubcategoryViewHolder> {
    Context context;
    List<SubcategoryList> subcategoryLists;
    LayoutInflater layoutInflater;

    public SubcategoryAdapter(Context context, List<SubcategoryList> subcategoryLists) {
        this.context = context;
        this.subcategoryLists = subcategoryLists;
    }

    @NonNull
    @Override
    public SubcategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.from(context).inflate(R.layout.samplesubcategory,parent,false);
        SubcategoryAdapter.SubcategoryViewHolder  subcategoryViewHolder=new SubcategoryAdapter.SubcategoryViewHolder(view);

        return subcategoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubcategoryViewHolder holder, int position) {
        SubcategoryList subcategoryListposition=subcategoryLists.get(position);
        holder.tv_category_name.setText(subcategoryListposition.getCategory_name());
        if (position%2==0) {

        }else if (position%2==1){
            holder.lv_subcatagory.setBackgroundResource(R.color.sky_bg);

        }

    }

    @Override
    public int getItemCount() {
        return subcategoryLists.size();
    }

    public class SubcategoryViewHolder extends RecyclerView.ViewHolder{
    TextView tv_category_name;
    LinearLayout lv_subcatagory;
        public SubcategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_category_name=itemView.findViewById(R.id.tv_category_name);
            lv_subcatagory=itemView.findViewById(R.id.lv_subcatagory);
        }
    }
}
