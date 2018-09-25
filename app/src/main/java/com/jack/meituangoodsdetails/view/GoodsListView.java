package com.jack.meituangoodsdetails.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.material.tabs.TabLayout;
import com.jack.meituangoodsdetails.R;
import com.jack.meituangoodsdetails.adapter.FragmentAdapter;
import com.jack.meituangoodsdetails.fragment.CommentFragment;
import com.jack.meituangoodsdetails.fragment.GoodsListFragment;
import com.jack.meituangoodsdetails.fragment.MerchantFragment;
import java.util.ArrayList;
import java.util.List;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * @author Jack
 */
public class GoodsListView extends FrameLayout {
    public List<Fragment> listFragment=new ArrayList<>();
    private FragmentManager fragmentManager;
    public MyViewPager viewPager;
    private TabLayout tabLayout;
    private Context context;

    public GoodsListView(Context context) {
        this(context,null);
    }

    public GoodsListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public GoodsListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundColor(context.getColor(R.color.colorffffff));
        this.context=context;
    }

    public void initView(FragmentManager fragmentManager){
        this.fragmentManager=fragmentManager;
        initData(context);
    }

    public List<Fragment> getFragment(){
        return this.listFragment;
    }

    public void initData(Context context){
        View view= LayoutInflater.from(context).inflate(R.layout.layout_goods_list,null);
        viewPager=view.findViewById(R.id.viewPager);
        tabLayout=view.findViewById(R.id.tabLayout);
        listFragment.add(new GoodsListFragment());
        listFragment.add(new CommentFragment());
        listFragment.add(new MerchantFragment());
        FragmentAdapter fragmentAdapter= new FragmentAdapter(fragmentManager,listFragment);
        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
        addView(view);
    }

}
