package com.maity.maityspositiveliving.screen.Fragment.UserCategoryListFragment;

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

import com.maity.maityspositiveliving.POJO.ParentcategoryList;
import com.maity.maityspositiveliving.R;

import com.maity.maityspositiveliving.Retrofit.interfaces.OnCallBackListner;
import com.maity.maityspositiveliving.Retrofit.models.ApiRequest;
import com.maity.maityspositiveliving.screen.Adapter.ParentcategoryAdapter;
import com.maity.maityspositiveliving.utils.ApplicationConstant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class UserCategoryListFragment extends Fragment implements OnCallBackListner {
    UserCategoryListViewBind userCategoryListViewBind;
    UserCategoryListOnCilck userCategoryListOnCilck;

     ApiRequest apiRequest;
    List<ParentcategoryList> parentcategoryLists;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_categorylist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        userCategoryListViewBind= new UserCategoryListViewBind(this,view);
        userCategoryListOnCilck=new UserCategoryListOnCilck(this,userCategoryListViewBind);
        apiRequest=new ApiRequest(getContext(),this);

        parentcategoryLists=new ArrayList<>(); // categorylist

        apiForparentcategory(); // api implement for categorylist


        super.onViewCreated(view, savedInstanceState);
    }

    public void apiForparentcategory(){
        apiRequest.callGET(ApplicationConstant.parentcategory_url,"parentcategory");
    }


    @Override
    public void OnCallBackSuccess(String tag, String body) {
        if (tag.equalsIgnoreCase("parentcategory")){
            try {

                JSONArray jsonArray=new JSONArray(body);
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                    ParentcategoryList parentcategoryList = new ParentcategoryList(
                            jsonObject1.getString("id"),
                             jsonObject1.getString("category_name"),
                            jsonObject1.getString("category_desc"),
                            jsonObject1.getString("status")

                    );
                    parentcategoryLists.add(parentcategoryList);

                }

                ParentcategoryAdapter parentcategoryAdapter = new ParentcategoryAdapter(getActivity(), parentcategoryLists);
                userCategoryListViewBind. rv_parentcategory.setAdapter(parentcategoryAdapter);

                GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getActivity(), 2);
                userCategoryListViewBind. rv_parentcategory.setLayoutManager(gridLayoutManager1);
                userCategoryListViewBind. rv_parentcategory.setItemAnimator(new DefaultItemAnimator());
                userCategoryListViewBind. rv_parentcategory.setNestedScrollingEnabled(false);
                userCategoryListViewBind. rv_parentcategory.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(1), true));

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void OnCallBackSuccess(String tag, JSONObject jsonObject) {

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