package com.jack.meituangoodsdetails.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jack.meituangoodsdetails.R;
import com.jack.meituangoodsdetails.util.DensityUtils;

public class GoodsTitleView extends FrameLayout {
    private RelativeLayout titleView;
    private LinearLayout searchBroad;
    private TextView searchTip;

    private int mMaxWidth;
    private int mMinWidth;
    private int mWidth;
    private float mAlpha;

    public GoodsTitleView(Context context) {
        this(context,null);
    }

    public GoodsTitleView(Context context, AttributeSet attrs) {
        this(context,null,0);
    }

    public GoodsTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void initView(Context context){
        titleView= (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.layout_goods_title,null);
        searchBroad=titleView.findViewById(R.id.search_broad);
        searchTip=titleView.findViewById(R.id.search_tip);

        titleView.getBackground().setAlpha(0);
        mMinWidth= DensityUtils.dp2px(context,40);
        mAlpha=0;

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mMaxWidth=searchBroad.getRight();
                if(mWidth==0){
                    mWidth=mMinWidth;
                }
            }
        });

        addView(titleView);
    }

    public void updateView(int dy){
        mWidth+=dy;
        mAlpha+=dy;
        if(mWidth>=mMaxWidth){
            mWidth=mMaxWidth;
        }else if(mWidth<=mMinWidth){
            mWidth=mMinWidth;
        }
        if(mAlpha<=0){
            mAlpha=0;
        }else if(mAlpha>=255){
            mAlpha=255;
        }
        RelativeLayout.LayoutParams layoutParams= (RelativeLayout.LayoutParams)searchBroad.getLayoutParams();
        layoutParams.width=mWidth;
        searchBroad.setLayoutParams(layoutParams);
        searchTip.setAlpha(mAlpha);
        titleView.getBackground().setAlpha((int)mAlpha);
    }

    public void checkView(){
        if(mWidth>mMinWidth){
            RelativeLayout.LayoutParams layoutParams= (RelativeLayout.LayoutParams)searchBroad.getLayoutParams();
            layoutParams.width=mMinWidth;
            searchBroad.setLayoutParams(layoutParams);
            this.mAlpha=0;
            searchTip.setAlpha(this.mAlpha);
            titleView.getBackground().setAlpha((int)mAlpha);
        }
    }


}
