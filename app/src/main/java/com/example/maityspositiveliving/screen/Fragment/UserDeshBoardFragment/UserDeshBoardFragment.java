package com.example.maityspositiveliving.screen.Fragment.UserDeshBoardFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.maityspositiveliving.R;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class UserDeshBoardFragment extends Fragment {
    UserDeshBoardViewBind userDeshBoardViewBind;
    UserDeshBoardOnCilck userDeshBoardOnCilck;

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


        super.onViewCreated(view, savedInstanceState);
    }
}