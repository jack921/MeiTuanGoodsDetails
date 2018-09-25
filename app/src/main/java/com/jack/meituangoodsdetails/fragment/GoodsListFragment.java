package com.jack.meituangoodsdetails.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jack.meituangoodsdetails.R;
import com.jack.meituangoodsdetails.adapter.MainAdapter;
import com.jack.meituangoodsdetails.interfaces.ScrollableViewStatus;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class GoodsListFragment extends Fragment implements ScrollableViewStatus {
    public  RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_goods_list,container,false);
        recyclerView=view.findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLinearLayoutManager);
        recyclerView.setAdapter(new MainAdapter(getActivity()));

        return view;
    }


    @Override
    public boolean isScrollAble() {
        return !recyclerView.canScrollVertically(-1);
    }

}
