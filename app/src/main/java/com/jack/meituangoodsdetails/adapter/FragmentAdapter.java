package com.jack.meituangoodsdetails.adapter;


import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter {

    String[] title=new String[]{"点菜","评价","商家"};
    List<Fragment> listFragment;

    public FragmentAdapter(FragmentManager fm, List<Fragment> listFragment) {
        super(fm);
        this.listFragment=listFragment;
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return this.listFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }


}
