package com.maity.maityspositiveliving.screen.Fragment.UserMyOrderFragment;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maity.maityspositiveliving.Interface.OrderDeleteInterface;
import com.maity.maityspositiveliving.POJO.OrderList;
import com.maity.maityspositiveliving.R;
import com.maity.maityspositiveliving.Retrofit.interfaces.OnCallBackListner;
import com.maity.maityspositiveliving.Retrofit.models.ApiRequest;
import com.maity.maityspositiveliving.screen.Adapter.OrderListAdapter;
import com.maity.maityspositiveliving.utils.ApplicationConstant;
import com.maity.maityspositiveliving.utils.MyToast;
import com.maity.maityspositiveliving.utils.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class UserMyOrderFragment extends Fragment implements OnCallBackListner {
    UserMyOrderViewBind userMyOrderViewBind;
    UserMyOrderOnClick userMyOrderOnClick;
    Boolean saveLogin,saveRegistration;
    AlertDialog dialog;
    List<OrderList> orderLists;
    ApiRequest apiRequest;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_my_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        userMyOrderViewBind= new UserMyOrderViewBind(this,view);
        userMyOrderOnClick=new UserMyOrderOnClick(this,userMyOrderViewBind);

        saveLogin= SessionManager.getAfterLogin();
        saveRegistration= SessionManager.getLogged();
        apiRequest=new ApiRequest(getContext(),this);
        orderLists=new ArrayList<>();

        if (saveLogin) {
           AfterLoginapifororderlist(); // api implement for orderlist After login


        }else if (saveRegistration) {

            AfterRegapifororderlist();// api implement for orderlist After registration
        }
        super.onViewCreated(view, savedInstanceState);
    }
    // api implement for orderlist After login
    public void AfterLoginapifororderlist(){
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("customer_id", SessionManager.getLoginidvalue());
     apiRequest.callPOSTJSON_OBJECT(ApplicationConstant.OrderApilist_url,hashMap,"OrderApilist");
    }
    // api implement for orderlist After registration
    public void AfterRegapifororderlist(){
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("customer_id", SessionManager.getregistrationidvalue());
        apiRequest.callPOSTJSON_OBJECT(ApplicationConstant.OrderApilist_url,hashMap,"OrderApilist");
    }
    // api implement for orderdelete
    public void apifororderDelete(String order_id){
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("order_id", order_id);
        apiRequest.callPOSTJSON_OBJECT(ApplicationConstant.orderDelete_url,hashMap,"orderDelete");
        dialog.dismiss();

    }

    @Override
    public void OnCallBackSuccess(String tag, String body) {

    }

    @Override
    public void OnCallBackSuccess(String tag, JSONObject jsonObject) {
        if (tag.equalsIgnoreCase("OrderApilist")) {

            try {
                JSONArray jsonArray=jsonObject.getJSONArray("_orders");
                for (int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                    OrderList orderList=new OrderList(
                            jsonObject1.getString("order_id"),
                            jsonObject1.getString("order_note"),
                            jsonObject1.getString("paid"),
                            jsonObject1.getString("amount"),
                            jsonObject1.getString("order_date"),
                            jsonObject1.getString("order_head_title"),
                            jsonObject1.getString("order_status")
                    );

                    orderLists.add(orderList);
                    OrderListAdapter orderListAdapter = new OrderListAdapter(getActivity(), orderLists, new OrderDeleteInterface() {
                        @Override
                        public void orderDelete(int position) {
                            AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                            View view1= LayoutInflater.from(getActivity()).inflate(R.layout.alert_layout_yesno,null);

                            builder.setView(view1);
                             dialog=builder.create();

                            dialog.setCancelable(true);
                            dialog.show();
                            TextView tv_cencel,tv_confirm;
                            tv_cencel =view1.findViewById(R.id.tv_cencel);
                            tv_confirm =view1.findViewById(R.id.tv_confirm);
                            tv_confirm.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    String  order_id=  orderLists.get(position).getOrder_id();
                                    // api implement for orderdelete
                                    apifororderDelete(order_id);

                                }
                            });
                            tv_cencel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                   dialog.dismiss();
                                }
                            });

                        /*  String  order_id=  orderLists.get(position).getOrder_id();
                         apifororderDelete(order_id);*/
                        }
                    });
                    userMyOrderViewBind. rv_orderlist.setAdapter(orderListAdapter);

                    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
                    userMyOrderViewBind. rv_orderlist.setLayoutManager(linearLayoutManager);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else  if (tag.equalsIgnoreCase("orderDelete")) {
            try {

               // AfterLoginapifororderlist();
                if (saveLogin) {
                    MyToast.show(getContext(),""+jsonObject.getString("_message"),true);
                    orderLists.clear();
                    AfterLoginapifororderlist(); // api implement for orderlist


                }else if (saveRegistration) {
                    MyToast.show(getContext(),""+jsonObject.getString("_message"),true);
                    orderLists.clear();
                    AfterRegapifororderlist();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void OnCallBackError(String tag, String error, int i) {

    }
}