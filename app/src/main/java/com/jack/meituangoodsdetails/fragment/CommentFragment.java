package com.jack.meituangoodsdetails.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jack.meituangoodsdetails.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class CommentFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_comment,container,false);

        return view;
    }


}
