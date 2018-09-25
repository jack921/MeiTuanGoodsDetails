package com.jack.meituangoodsdetails.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class TickerScrollView extends HorizontalScrollView {
    private LinearLayout linearLayout;
    private int mTicketViewHeight;
    private int animatorHeight;
    private Context context;
    private boolean first=true;

    public TickerScrollView(Context context) {
        this(context,null);
    }

    public TickerScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TickerScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        linearLayout=new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(first){
                    mTicketViewHeight=getHeight();
                    hideView();
                    first=false;
                }
            }
        });
        addView(linearLayout);
    }

    public void hideView(){
        LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams) getLayoutParams();
        layoutParams.height=0;
        setLayoutParams(layoutParams);
    }

    public void initView(int number){
        for(int i=0;i<number;i++){
            TicketView ticketView=new TicketView(context);
            linearLayout.addView(ticketView);
        }
    }

    public void animatorView(int dy){
        // dy<0向下 展示,dy>0向上 隐藏
        animatorHeight-=dy;
        if(animatorHeight<=0){
            animatorHeight=0;
        }else if(animatorHeight>=mTicketViewHeight){
            animatorHeight=mTicketViewHeight;
        }

        Log.e("animatorView",dy+":"+animatorHeight);

        LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams) getLayoutParams();
        layoutParams.height=animatorHeight;
        setLayoutParams(layoutParams);

        for(int i=0;i<linearLayout.getChildCount();i++){
            ((TicketView)linearLayout.getChildAt(i)).animatorView(dy);
        }
    }

}
