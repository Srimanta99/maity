package com.maity.maityspositiveliving.screen.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maity.maityspositiveliving.Interface.OrderDeleteInterface;
import com.maity.maityspositiveliving.POJO.OrderList;
import com.maity.maityspositiveliving.R;

import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.OrderListViewHolder> {
    LayoutInflater layoutInflater;
Context context;
List<OrderList> orderLists;
    OrderDeleteInterface orderDeleteInterface;

    public OrderListAdapter(Context context, List<OrderList> orderLists, OrderDeleteInterface orderDeleteInterface) {
        this.context = context;
        this.orderLists = orderLists;
        this.orderDeleteInterface = orderDeleteInterface;
    }

    @NonNull
    @Override
    public OrderListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.from(context).inflate(R.layout.sample_orderlist_layout,parent,false);
        OrderListViewHolder orderListViewHolder=new OrderListViewHolder(view);
        return orderListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderListViewHolder holder, int position) {
    OrderList orderListpos=orderLists.get(position);
    holder.tv_order_head_title.setText(orderListpos.getOrder_head_title());
        holder.tv_order_note.setText(orderListpos.getOrder_note());

        holder.tv_paid.setText(orderListpos.getPaid());

        holder.tv_order_status.setText(orderListpos.getOrder_status());

        holder.tv_tamount.setText("Total Amount : Rs. "+orderListpos.getAmount());

        String[] parts = orderListpos.getOrder_date(). split(" ");
        String part1 = parts[0];
        String part2 = parts[1];
        String part3 = parts[2];

        Log.d("part1", "onBindViewHolder: "+part1);
        Log.d("part1", "onBindViewHolder: "+part2);
        Log.d("part1", "onBindViewHolder: "+part3);

        holder.tv_day.setText(part1);
       String part=  part2.substring(0,3);
        holder.tv_month.setText(part);

        holder.tv_year.setText(part3);


        holder.img_order_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                orderDeleteInterface.orderDelete(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return orderLists.size();
    }

    public class OrderListViewHolder extends RecyclerView.ViewHolder{
    TextView tv_order_note,tv_paid,tv_tamount,tv_order_head_title,tv_order_status,tv_year,tv_month,tv_day;
    ImageView img_order_delete;
        public OrderListViewHolder(@NonNull View itemView) {
            super(itemView);
            img_order_delete=itemView.findViewById(R.id.img_order_delete);
            tv_order_note=itemView.findViewById(R.id.tv_order_note);
            tv_order_head_title=itemView.findViewById(R.id.tv_order_head_title);
            tv_paid=itemView.findViewById(R.id.tv_paid);
            tv_order_status=itemView.findViewById(R.id.tv_order_status);
            tv_year=itemView.findViewById(R.id.tv_year);
            tv_month=itemView.findViewById(R.id.tv_month);
            tv_day=itemView.findViewById(R.id.tv_day);

            tv_tamount=itemView.findViewById(R.id.tv_tamount);




        }
    }
}
