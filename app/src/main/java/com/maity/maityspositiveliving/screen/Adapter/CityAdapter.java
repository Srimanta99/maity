package com.maity.maityspositiveliving.screen.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maity.maityspositiveliving.Interface.StateInterface;
import com.maity.maityspositiveliving.POJO.CityList;
import com.maity.maityspositiveliving.R;

import java.util.ArrayList;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {

    Context context;
    ArrayList<CityList> extraList;
    LayoutInflater layoutInflater;
    StateInterface stateInterface;

    public CityAdapter(Context context, ArrayList<CityList> extraList, StateInterface stateInterface) {
        this.context = context;
        this.extraList = extraList;
        this.stateInterface = stateInterface;
    }

    public void filterList(ArrayList<CityList> filteredList) {
        extraList = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.from(context).inflate(R.layout.lyt_spinner_item,parent,false);
        CityViewHolder stateViewHolder=new  CityViewHolder(view);
        return stateViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        CityList stateListpos=extraList.get(position);
        holder. txt_State_item.setText(stateListpos.getCity_name());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stateInterface.statename(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return extraList.size();    }

    public class CityViewHolder extends RecyclerView.ViewHolder{
        TextView txt_State_item;
        View view1;
        public CityViewHolder(@NonNull View itemView) {
            super(itemView);
            view1 = itemView.findViewById(R.id.view);
            txt_State_item = itemView.findViewById(R.id.txt_item);
        }
    }
}
