package com.jack.meituangoodsdetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jack.meituangoodsdetails.activity.GoodsDetailsActivity;
import com.jack.meituangoodsdetails.activity.NestedScrollActivity;
import com.jack.meituangoodsdetails.view.GoodsListView;

public class MainActivity extends AppCompatActivity {

    Button mGoodsDetails;
    Button mNestedScrolling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGoodsDetails=(Button)findViewById(R.id.goods_details);
        mNestedScrolling=(Button)findViewById(R.id.nested_scrolling);

        mGoodsDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GoodsDetailsActivity.class));
            }
        });

        mNestedScrolling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NestedScrollActivity.class));
            }
        });

    }

}
