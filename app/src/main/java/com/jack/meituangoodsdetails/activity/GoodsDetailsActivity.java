package com.jack.meituangoodsdetails.activity;

import android.os.Bundle;

import com.jack.meituangoodsdetails.R;
import com.jack.meituangoodsdetails.view.GoodsListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GoodsDetailsActivity extends AppCompatActivity {
    public GoodsListView goodsListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        goodsListView=findViewById(R.id.goods_list_view);
        goodsListView.initView(getSupportFragmentManager());

    }
}
