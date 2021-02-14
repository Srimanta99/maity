package com.example.maityspositiveliving.screen.Fragment.UserDeshBoardFragment;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maityspositiveliving.POJO.ParentcategoryList;
import com.example.maityspositiveliving.R;

import com.example.maityspositiveliving.Retrofit.interfaces.OnCallBackListner;
import com.example.maityspositiveliving.Retrofit.models.ApiRequest;
import com.example.maityspositiveliving.screen.Adapter.ParentcategoryAdapter;
import com.example.maityspositiveliving.utils.ApplicationConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class UserDeshBoardFragment extends Fragment implements OnCallBackListner {
    UserDeshBoardViewBind userDeshBoardViewBind;
    UserDeshBoardOnCilck userDeshBoardOnCilck;

    ApiRequest apiRequest;
    List<ParentcategoryList> parentcategoryLists;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_desh_board, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        userDeshBoardViewBind= new UserDeshBoardViewBind(this,view);
        userDeshBoardOnCilck=new UserDeshBoardOnCilck(this,userDeshBoardViewBind);
        apiRequest=new ApiRequest(getContext(),this);
        parentcategoryLists=new ArrayList<>();
        apiForparentcategory();


        super.onViewCreated(view, savedInstanceState);
    }
    public void apiForparentcategory(){
        apiRequest.callGET(ApplicationConstant.parentcategory_url,"parentcategory");
    }


    @Override
    public void OnCallBackSuccess(String tag, String body) {
        if (tag.equalsIgnoreCase("parentcategory")){
            try {

                JSONObject jsonObject=new JSONObject(body);
                JSONArray jsonArray = jsonObject.getJSONArray("table");
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                    ParentcategoryList parentcategoryList = new ParentcategoryList(
                            jsonObject1.getString("id"),
                            jsonObject1.getString("category_name"),
                            jsonObject1.getString("category_icon"),
                            jsonObject1.getString("parent_category"),
                            jsonObject1.getString("status")

                    );
                    parentcategoryLists.add(parentcategoryList);

                }

                ParentcategoryAdapter parentcategoryAdapter = new ParentcategoryAdapter(getActivity(), parentcategoryLists);
               userDeshBoardViewBind. rv_parentcategory.setAdapter(parentcategoryAdapter);

                GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getActivity(), 2);
                userDeshBoardViewBind. rv_parentcategory.setLayoutManager(gridLayoutManager1);
                userDeshBoardViewBind. rv_parentcategory.setItemAnimator(new DefaultItemAnimator());
                userDeshBoardViewBind. rv_parentcategory.setNestedScrollingEnabled(false);
               userDeshBoardViewBind. rv_parentcategory.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(1), true));

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


       /* else if (tag.equalsIgnoreCase("subcategory")){
            try {

                JSONObject jsonObject=new JSONObject(body);
                JSONArray jsonArray = jsonObject.getJSONArray("table");
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                    ParentcategoryList parentcategoryList = new ParentcategoryList(
                            jsonObject1.getString("id"),
                            jsonObject1.getString("category_name"),
                            jsonObject1.getString("category_icon"),
                            jsonObject1.getString("parent_category"),
                            jsonObject1.getString("status")

                    );
                    parentcategoryLists.add(parentcategoryList);

                }

                ParentcategoryAdapter parentcategoryAdapter = new ParentcategoryAdapter(getActivity(), parentcategoryLists, new ParentcategoryInterface() {
                    @Override
                    public void pass_subcategory_url(String subcategory_url_withid) {
                        apiForsubcategory_url(subcategory_url_withid);
                    }
                });
                userDeshBoardViewBind. rv_parentcategory.setAdapter(parentcategoryAdapter);

                GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getActivity(), 2);
                userDeshBoardViewBind. rv_parentcategory.setLayoutManager(gridLayoutManager1);
                userDeshBoardViewBind. rv_parentcategory.setItemAnimator(new DefaultItemAnimator());
                userDeshBoardViewBind. rv_parentcategory.setNestedScrollingEnabled(false);
                userDeshBoardViewBind. rv_parentcategory.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(1), true));

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }*/
    }

    @Override
    public void OnCallBackError(String tag, String error, int i) {

    }


    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        //Defining retrofit api service

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }




}