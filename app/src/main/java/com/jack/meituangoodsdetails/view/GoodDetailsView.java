package com.jack.meituangoodsdetails.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.jack.meituangoodsdetails.R;
import com.jack.meituangoodsdetails.util.DensityUtils;

import android.view.View;
import android.widget.TextView;

public class GoodDetailsView extends FrameLayout {
    public RelativeLayout expandBtn;
    public TextView storeCenterName;
    public TickerScrollView ticketScroll;
    public ImageView avatar;
    public TextView storeName;
    public TextView storeTip;
    public Context context;
    public int translationX;
    public int mCenterAlpha;
    public int mAlpha=0;
    public int textSize=0;

    public int minScrollWidth;
    public int maxScrollWidth;


    public GoodDetailsView(Context context) {
        this(context,null);
    }

    public GoodDetailsView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public GoodDetailsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        initView(context);
    }

    public void initView(final Context context){
        View view= LayoutInflater.from(context).inflate(R.layout.layout_goods_details,null);
        storeCenterName=view.findViewById(R.id.store_center_name);
        ticketScroll=view.findViewById(R.id.ticket_scroll);
        expandBtn=view.findViewById(R.id.expand_ease);
        storeName=view.findViewById(R.id.store_name);
        storeTip=view.findViewById(R.id.store_tip);
        avatar=view.findViewById(R.id.avatar);
        storeCenterName.setTextColor(Color.argb(0,0,0,0));
        ticketScroll.initView(4);
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                minScrollWidth=((LinearLayout.LayoutParams) avatar.getLayoutParams()).leftMargin;
                maxScrollWidth=(DensityUtils.getScreenWidth(context)-avatar.getWidth())/2-minScrollWidth;
            }
        });
        addView(view);
    }


    public void updateView(int dy){
        mAlpha+=dy;
        mCenterAlpha-=dy;
        translationX-=dy;
        textSize+=dy;
        if(mAlpha<=0){
            mAlpha=0;
        }else if(mAlpha>=255){
            mAlpha=255;
        }

        if(mCenterAlpha<=0){
            mCenterAlpha=0;
        }else if(mCenterAlpha>=255){
            mCenterAlpha=255;
        }

        if(translationX<=0){
            translationX=0;
            storeCenterName.setVisibility(View.GONE);
        }else if(translationX>=maxScrollWidth){
            translationX=maxScrollWidth;
        }else{
            if(storeCenterName.getVisibility()==View.GONE){
                storeCenterName.setVisibility(View.VISIBLE);
            }
        }

        ticketScroll.animatorView(dy/2);
        avatar.setTranslationX(translationX);
        storeTip.setTextColor(Color.argb(mAlpha,255,255,255));
        storeName.setTextColor(Color.argb(mAlpha,255,255,255));
        storeCenterName.setTextColor(Color.argb(mCenterAlpha,0,0,0));

    }

}
