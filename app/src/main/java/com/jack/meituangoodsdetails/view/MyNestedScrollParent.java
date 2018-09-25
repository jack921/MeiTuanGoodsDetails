package com.jack.meituangoodsdetails.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jack.meituangoodsdetails.R;

import androidx.annotation.NonNull;
import androidx.core.view.NestedScrollingParent2;
import androidx.core.view.NestedScrollingParentHelper;

public class MyNestedScrollParent extends LinearLayout implements NestedScrollingParent2 {
    private NestedScrollingParentHelper mNestedScrollingParentHelper;
    private MyNestedScrollChild scrollChildView;
    private ImageView foodIV;
    private TextView titleTV;
    private int imageHeight;
    private int titleHeight;
    private int imageMargin;
    private int scrollY;

    public MyNestedScrollParent(Context context) {
        this(context,null);
    }

    public MyNestedScrollParent(Context context, AttributeSet attrs) {
        super(context, attrs);
        mNestedScrollingParentHelper=new NestedScrollingParentHelper(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        FrameLayout frameLayout=(FrameLayout) getChildAt(0);
        scrollChildView=(MyNestedScrollChild) getChildAt(1);
        foodIV=frameLayout.findViewById(R.id.foodIV);
        titleTV=frameLayout.findViewById(R.id.titleTV);
        foodIV.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                imageHeight=foodIV.getHeight();
            }
        });
        titleTV.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                titleHeight=titleTV.getHeight();
            }
        });

    }

    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes, int type) {
        if(target instanceof MyNestedScrollChild) {
            return true;
        }
        return false;
    }

    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes, int type) {
        mNestedScrollingParentHelper.onNestedScrollAccepted(child,target,axes,type);
    }

    @Override
    public void onStopNestedScroll(@NonNull View target, int type) {
        mNestedScrollingParentHelper.onStopNestedScroll(target,type);
    }

    @Override
    public int getNestedScrollAxes() {
        return mNestedScrollingParentHelper.getNestedScrollAxes();
    }

    @Override
    public void onStopNestedScroll(@NonNull View target) {
        mNestedScrollingParentHelper.onStopNestedScroll(target);
    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed,
                               int dxUnconsumed, int dyUnconsumed, int type) {

    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        imageMargin=titleHeight-imageHeight;
        scrollY+=dy;
        if(scrollY<=imageMargin){
            scrollY=imageMargin;
            scrollChildView.setTranslationY(scrollY);
        }else{
            if(dy<0){
                //上滑
                consumed[1]=dy;
                scrollChildView.setTranslationY(scrollY);
            }else{
                //下滑
                if(!scrollChildView.canChildScrollUp()){
                    scrollY-=dy;
                }
                if(scrollY>=0){
                    scrollY=0;
                }
                scrollChildView.setTranslationY(scrollY);
            }
        }

//        Log.e("scrollChildView",scrollY+":"+imageMargin+":"+titleHeight+":"+imageHeight+":"+dy);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes) {
        return false;
    }

}
