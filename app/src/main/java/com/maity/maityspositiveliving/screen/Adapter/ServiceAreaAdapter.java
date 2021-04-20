package com.maity.maityspositiveliving.screen.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maity.maityspositiveliving.Interface.StateInterface;
import com.maity.maityspositiveliving.POJO.ParentcategoryList;
import com.maity.maityspositiveliving.R;

import java.util.List;

public class ServiceAreaAdapter extends RecyclerView.Adapter<ServiceAreaAdapter.ServiceAreaViewHolder> {
    Context context;
    List<ParentcategoryList> parentcategoryLists;
    StateInterface stateInterface;

    LayoutInflater layoutInflater;


    public ServiceAreaAdapter(Context context, List<ParentcategoryList> parentcategoryLists, StateInterface stateInterface) {
        this.context = context;
        this.parentcategoryLists = parentcategoryLists;
        this.stateInterface = stateInterface;
    }
    public void filterList(List<ParentcategoryList> filteredList) {
        parentcategoryLists = filteredList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ServiceAreaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.from(context).inflate(R.layout.lyt_spinner_item,parent,false);
        ServiceAreaViewHolder parentcategoryViewHolder=new ServiceAreaViewHolder(view);

        return parentcategoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceAreaViewHolder holder, int position) {
        ParentcategoryList parentcategoryListpostion=parentcategoryLists.get(position);

        holder.txt_item.setText(parentcategoryListpostion.getCategory_name());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stateInterface.statename(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return parentcategoryLists.size();
    }

    public class ServiceAreaViewHolder extends RecyclerView.ViewHolder{
        TextView txt_item;


        View view1;
        public ServiceAreaViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_item=itemView.findViewById(R.id.txt_item);
            view1=itemView.findViewById(R.id.view);
        }
    }
}


